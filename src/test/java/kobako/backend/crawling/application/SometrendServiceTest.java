package kobako.backend.crawling.application;

import java.util.ArrayList;
import kobako.backend.naver.presentation.dto.request.CharacterAnalysisRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SometrendServiceTest {

    @Autowired
    private SometrendService sometrendService;

    @Test
    void 연관_검색어를_추출한다() {
        // given
        CharacterAnalysisRequest request = new CharacterAnalysisRequest("20240302", "20240308", "이민정");

        // when
        ArrayList<String> response = sometrendService.crawlSometrend(request);

        // then
        System.out.println(response.size());
        for (String keyword : response) {
            System.out.println(keyword);
        }
    }
}