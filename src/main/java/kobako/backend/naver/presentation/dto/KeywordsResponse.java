package kobako.backend.naver.presentation.dto;

import java.util.List;
import kobako.backend.naver.domain.KeywordData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeywordsResponse {

    private List<KeywordData> keywordList;
}
