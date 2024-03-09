package kobako.backend.advertisementCopy.dto.request;


import kobako.backend.advertisementCopy.domain.AdvertisementCopy;
import kobako.backend.global.ENUM.*;
import kobako.backend.member.domain.Member;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenerateAdvertisementCopyRequest {

    private Service service;
    private String projectName;
    private String productName;
    private TargetGender targetGender;
    private TargetAge targetAge;
    private List<String> keyword;
    private Tone tone;



    public AdvertisementCopy toAdvertismentCopy(Member member, String message){
        return AdvertisementCopy.builder()
                .service(this.service)
                .member(member)
                .projectName(this.projectName)
                .productName(this.productName)
                .targetGender(this.targetGender)
                .targetAge(this.targetAge)
                .tone(this.tone)
                .message(message)
                .build();
    }
}
