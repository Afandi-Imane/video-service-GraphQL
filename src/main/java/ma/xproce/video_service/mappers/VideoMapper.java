package ma.xproce.video_service.mappers;

import ma.xproce.video_service.dtos.CreatorRequest;
import ma.xproce.video_service.dtos.VideoRequest;
import ma.xproce.video_service.entities.Creator;
import ma.xproce.video_service.entities.Video;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class VideoMapper {

    private final ModelMapper modelMapper;

    public VideoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Creator toCreator(CreatorRequest request) {
        return modelMapper.map(request, Creator.class);
    }

    public Video toVideo(VideoRequest request) {
        Video video = new Video();
        video.setName(request.getName());
        video.setUrl(request.getUrl());
        video.setDescription(request.getDescription());
        video.setDatePublication(parseDate(request.getDatePublication()));
        if (request.getCreator() != null) {
            video.setCreator(toCreator(request.getCreator()));
        }
        return video;
    }

    private Date parseDate(String value) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(value);
        } catch (Exception e) {
            throw new RuntimeException("Format de date invalide (attendu : dd/MM/yyyy) : " + value);
        }
    }

}