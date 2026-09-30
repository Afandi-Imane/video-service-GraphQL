package ma.xproce.video_service;

import ma.xproce.video_service.entities.Creator;
import ma.xproce.video_service.entities.Video;
import ma.xproce.video_service.repositories.CreatorRepository;
import ma.xproce.video_service.repositories.VideoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class VideoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CreatorRepository creatorRepository,
							VideoRepository videoRepository) {
		return args -> {
			List<Creator> creators = List.of(
					Creator.builder().name("Ahmed").email("ahmed@gmail.com").build(),
					Creator.builder().name("Sara").email("sara@gmail.com").build()
			);
			creatorRepository.saveAll(creators);

			List<Video> videos = List.of(
					Video.builder().name("Spring Boot tuto").url("url1")
							.description("Intro à Spring Boot")
							.datePublication(new Date()).creator(creators.get(0)).build(),
					Video.builder().name("GraphQL tuto").url("url2")
							.description("Intro à GraphQL")
							.datePublication(new Date()).creator(creators.get(1)).build(),
					Video.builder().name("JPA tuto").url("url3")
							.description("Relations JPA")
							.datePublication(new Date()).creator(creators.get(0)).build()
			);
			videoRepository.saveAll(videos);
		};
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
