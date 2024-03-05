package kobako.backend.advertisementCopy.dto.response;

import kobako.backend.CopyGallery.domain.CopyGallery;
import kobako.backend.advertisementCopy.domain.AdvertisementCopy;
import kobako.backend.global.ENUM.Service;
import kobako.backend.global.ENUM.Tone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public static AdvertisementCopyResponse of (CopyGallery copyGallery){
        return AdvertisementCopyResponse.builder()
                .advertisementCopyId(copyGallery.getAdvertisementCopy().getAdvertisementCopyId())
                .service(copyGallery.getService())
                .tone(copyGallery.getTone())
                .message(copyGallery.getMessage())
                .build();
    }

}
