package ma.xproce.video_service.repositories;

import ma.xproce.video_service.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatorRepository extends JpaRepository<Creator, Long> {
}