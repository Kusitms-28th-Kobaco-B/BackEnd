package kobako.backend.gallery.dto.response;

import kobako.backend.gallery.domain.CopyGallery;
import kobako.backend.global.enums.Service;
import kobako.backend.global.enums.Tone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CopyGalleryResponse {

    private Long advertisementCopyId;
    private Service service;
    private Tone tone;
    private String message;
    private Long views;



    public static List<CopyGalleryResponse> ofCopyGalleriesList(List<CopyGallery> copyGalleriesList) {
        List<CopyGalleryResponse> copyGalleryResponses = copyGalleriesList.stream()
                .map(copyGallery -> CopyGalleryResponse.builder()
                        .advertisementCopyId(copyGallery.getAdvertisementCopy().getAdvertisementCopyId())
                        .service(copyGallery.getService())
                        .tone(copyGallery.getTone())
                        .message(copyGallery.getMessage())
                        .views(copyGallery.getViews())
                        .build())
                .collect(Collectors.toList());

        return copyGalleryResponses;
    }
}
