package kobako.backend.CopyGallery.dto.Response;

import kobako.backend.CopyGallery.domain.CopyGallery;
import kobako.backend.advertisementCopy.domain.AdvertisementCopy;
import kobako.backend.advertisementCopy.dto.response.AdvertisementCopyResponse;
import kobako.backend.global.ENUM.Service;
import kobako.backend.global.ENUM.Tone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

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



    public static Page<CopyGalleryResponse> ofCopyGalleriesPage (Page<CopyGallery> copyGalleriesPage) {
        List<CopyGalleryResponse> copyGalleryResponses = copyGalleriesPage.getContent().stream()
                .map(copyGallery -> CopyGalleryResponse.builder()
                        .advertisementCopyId(copyGallery.getAdvertisementCopy().getAdvertisementCopyId())
                        .service(copyGallery.getService())
                        .tone(copyGallery.getTone())
                        .message(copyGallery.getMessage())
                        .views(copyGallery.getViews())
                        .build())
                .collect(Collectors.toList());

        // 변환된 AdvertisementCopyResponse를 slice로 변환
        return new PageImpl<>(copyGalleryResponses, copyGalleriesPage.getPageable(), copyGalleriesPage.getTotalElements());
    }



}
