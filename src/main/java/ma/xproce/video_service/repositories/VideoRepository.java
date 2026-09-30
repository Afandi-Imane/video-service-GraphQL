package ma.xproce.video_service.repositories;

import ma.xproce.video_service.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}