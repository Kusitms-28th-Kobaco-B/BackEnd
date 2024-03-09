package kobako.backend.myPage.dto.request;

import kobako.backend.global.enums.Service;
import kobako.backend.global.enums.Tone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetMyCopiesRequest {
    private Long memberId;
    private Service service;
    private Tone tone;
}

