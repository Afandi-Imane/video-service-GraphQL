package ma.xproce.video_service.web;

import ma.xproce.video_service.dtos.CreatorRequest;
import ma.xproce.video_service.dtos.VideoRequest;
import ma.xproce.video_service.entities.Creator;
import ma.xproce.video_service.entities.Video;
import ma.xproce.video_service.mappers.VideoMapper;
import ma.xproce.video_service.repositories.CreatorRepository;
import ma.xproce.video_service.repositories.VideoRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.stream.Stream;

import java.util.List;

@Controller
public class VideoGraphQlController {

    private final CreatorRepository creatorRepository;
    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;

    VideoGraphQlController(CreatorRepository creatorRepository,
                           VideoRepository videoRepository,
                           VideoMapper videoMapper) {
        this.creatorRepository = creatorRepository;
        this.videoRepository = videoRepository;
        this.videoMapper = videoMapper;
    }

    @QueryMapping
    public List<Video> videoList() {
        return videoRepository.findAll();
    }

    @QueryMapping
    public Creator creatorById(@Argument Long id) {
        return creatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Creator %s not found", id)));
    }

    @QueryMapping
    public List<Creator> creatorList() {
        return creatorRepository.findAll();
    }

    @QueryMapping
    public Video videoById(@Argument Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Video %s not found", id)));
    }

    @MutationMapping
    public Creator saveCreator(@Argument CreatorRequest creator) {
        return creatorRepository.save(videoMapper.toCreator(creator));
    }

    @MutationMapping
    public Video saveVideo(@Argument VideoRequest video) {
        Video entity = videoMapper.toVideo(video);
        if (entity.getCreator() != null) {
            creatorRepository.save(entity.getCreator());
        }
        return videoRepository.save(entity);
    }
    @SubscriptionMapping
    public Flux<Video> notifyVideoChange() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(tick -> {
                    Creator creator = creatorRepository.save(
                            Creator.builder()
                                    .name("x" + new Random().nextInt())
                                    .email("x@gmail.com")
                                    .build());
                    Video video = videoRepository.findById(1L)
                            .orElseThrow(() -> new RuntimeException("Video 1 not found"));
                    video.setCreator(creator);
                    return videoRepository.save(video);
                });
    }
}