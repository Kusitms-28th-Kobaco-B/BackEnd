package kobako.backend.crawling.application;

import java.util.ArrayList;
import kobako.backend.crawling.presentation.dto.SometrendRequest;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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
        SometrendRequest request = new SometrendRequest("이민정", "20240302", "20240308");

        // when
        ArrayList<String> response = sometrendService.crawlSometrend(request);

        // then
        System.out.println(response.size());
        for (String keyword : response) {
            System.out.println(keyword);
        }
    }
}