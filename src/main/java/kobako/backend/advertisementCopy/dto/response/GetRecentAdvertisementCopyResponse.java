package kobako.backend.advertisementCopy.dto.response;

import kobako.backend.advertisementCopy.domain.AdvertisementCopy;
import kobako.backend.global.ENUM.Service;
import kobako.backend.global.ENUM.Tone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetRecentAdvertisementCopyResponse {

    private List<AdvertisementCopyResponse> advertisementCopies;


    public static GetRecentAdvertisementCopyResponse of (List<AdvertisementCopyResponse> advertisementCopies){
        return GetRecentAdvertisementCopyResponse.builder()
                .advertisementCopies(advertisementCopies)
                .build();
    }
}
