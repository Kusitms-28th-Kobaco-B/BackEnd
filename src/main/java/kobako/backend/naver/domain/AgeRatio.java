package kobako.backend.naver.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AgeRatio {

    private String ageGroup;
    private double ratio;

    public static AgeRatio of(String ageGroup, double ratio) {
        return new AgeRatio(ageGroup, ratio);
    }
}