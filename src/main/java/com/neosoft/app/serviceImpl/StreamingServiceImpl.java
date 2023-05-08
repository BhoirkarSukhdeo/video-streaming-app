package com.neosoft.app.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.neosoft.app.entity.SubscriptionCategory;
import com.neosoft.app.entity.Video;
import com.neosoft.app.exception.FileAlreadyExistException;
import com.neosoft.app.exception.SubscriptionCategoryNotFoundException;
import com.neosoft.app.exception.VideoNotFoundException;
import com.neosoft.app.repository.SubscriptionCategoryRepository;
import com.neosoft.app.repository.VideoRepository;
import com.neosoft.app.response.ResponseDTO;
import com.neosoft.app.service.StreamingService;

import reactor.core.publisher.Mono;

@Service("streamingservice")
public class StreamingServiceImpl implements StreamingService {

	private static final String FORMAT = "E:\\upload\\";

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private SubscriptionCategoryRepository subscriptionCategoryRepository;

	@Override
	public Mono<Resource> getVideo(String title, Long CategoryId) {
		Optional<Video> video = videoRepository.findByTitleAndSubscriptionCategoryId(title, CategoryId);
		if (!video.isPresent()) {
			throw new VideoNotFoundException("video Not Found with title " + title);
		}
		return Mono.fromSupplier(() -> this.resourceLoader.getResource("file:" + video.get().getUrl()));
	}

	@Override
	public ResponseDTO uploadVideo(MultipartFile file)
			throws IllegalStateException, IOException {
		if (file.isEmpty()) {
			throw new FileAlreadyExistException("Video alrady exist with the same name :" + file.getOriginalFilename());
		}

		Video save = videoRepository.save(Video.builder().subscriptionCategory(setdefaultProperties(1L))
				.type(file.getContentType()).uploadDate(LocalDate.now()).url(FORMAT + file.getOriginalFilename())
				.title(file.getOriginalFilename()).build());

		file.transferTo(new File(FORMAT + file.getOriginalFilename()));

		return new ResponseDTO(save.getId(), "Video/Image uploaded successfully..!");
	}

	private SubscriptionCategory setdefaultProperties(Long id) {
		return subscriptionCategoryRepository.findById(id).orElseThrow(
				() -> new SubscriptionCategoryNotFoundException("Subscription Category not found with id :: " + id));
	}

}
