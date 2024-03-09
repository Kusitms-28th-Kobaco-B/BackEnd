package kobako.backend.myPage.dto.Response;

import kobako.backend.copyGallery.domain.CopyGallery;
import kobako.backend.copyGallery.dto.Response.CopyGalleryResponse;
import kobako.backend.global.ENUM.Service;
import kobako.backend.global.ENUM.Tone;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetMyCopiesResponse {
    private Long advertisementCopyId;
    private Service service;
    private Tone tone;
    private String message;

    public static List<GetMyCopiesResponse> ofMyCopiesList(List<CopyGallery> copyGalleriesList) {
        List<GetMyCopiesResponse> copyGalleryResponses = copyGalleriesList.stream()
                .map(copyGallery -> GetMyCopiesResponse.builder()
                        .advertisementCopyId(copyGallery.getAdvertisementCopy().getAdvertisementCopyId())
                        .service(copyGallery.getService())
                        .tone(copyGallery.getTone())
                        .message(copyGallery.getMessage())
                        .build())
                .collect(Collectors.toList());

        return copyGalleryResponses;
    }

}

