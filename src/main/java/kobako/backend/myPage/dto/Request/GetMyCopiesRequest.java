package kobako.backend.myPage.dto.Request;

import kobako.backend.global.ENUM.Service;
import kobako.backend.global.ENUM.Tone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetMyCopiesRequest {
    private Long memberId;
    private Service service;
    private Tone tone;
}
