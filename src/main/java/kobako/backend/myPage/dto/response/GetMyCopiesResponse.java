package kobako.backend.myPage.dto.response;

import kobako.backend.gallery.domain.CopyGallery;
import kobako.backend.global.enums.Service;
import kobako.backend.global.enums.Tone;
import lombok.*;

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

