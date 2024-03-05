package kobako.backend.advertisementCopy.dto.response;

import kobako.backend.CopyGallery.domain.CopyGallery;
import kobako.backend.advertisementCopy.domain.AdvertisementCopy;
import kobako.backend.global.ENUM.Service;
import kobako.backend.global.ENUM.Tone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvertisementCopyResponse {

    private Long advertisementCopyId;
    private Service service;
    private Tone tone;
    private String message;


    public static AdvertisementCopyResponse of (AdvertisementCopy advertisementCopy){
        return AdvertisementCopyResponse.builder()
                .advertisementCopyId(advertisementCopy.getAdvertisementCopyId())
                .service(advertisementCopy.getService())
                .tone(advertisementCopy.getTone())
                .message(advertisementCopy.getMessage())
                .build();
    }

    // AdvertisementCopy를 AdvertisementCopyResponse로 변환 (List)
    public static List<AdvertisementCopyResponse> ofAdvertisementCopiesList(List<AdvertisementCopy> advertisementCopies) {
        return advertisementCopies.stream()
                .map(advertisementCopy -> AdvertisementCopyResponse.builder()
                        .advertisementCopyId(advertisementCopy.getAdvertisementCopyId())
                        .service(advertisementCopy.getService())
                        .tone(advertisementCopy.getTone())
                        .message(advertisementCopy.getMessage())
                        .build())
                .collect(Collectors.toList());
    }

    // copyGallery를 AdvertisementCopyResponse로 변환 (List)
    public static List<AdvertisementCopyResponse> ofCopyGalleriesList(List<CopyGallery> copyGalleries) {
        return copyGalleries.stream()
                .map(copyGallery -> AdvertisementCopyResponse.builder()
                        .advertisementCopyId(copyGallery.getAdvertisementCopy().getAdvertisementCopyId())
                        .service(copyGallery.getService())
                        .tone(copyGallery.getTone())
                        .message(copyGallery.getMessage())
                        .build())
                .collect(Collectors.toList());
    }



}
