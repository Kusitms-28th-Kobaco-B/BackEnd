package kobako.backend.naver.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeywordData {
    private String relKeyword;
    private String monthlyPcQcCnt;
    private String monthlyMobileQcCnt;
    private String monthlyAvePcClkCnt;
    private String monthlyAveMobileClkCnt;
    private String monthlyAvePcCtr;
    private String monthlyAveMobileCtr;
    private String plAvgDepth;
    private String compIdx;
}
