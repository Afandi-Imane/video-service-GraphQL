package ma.xproce.video_service.dtos;


import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreatorRequest {
    private String name;
    private String email;
}
