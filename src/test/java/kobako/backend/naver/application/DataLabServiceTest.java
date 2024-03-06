package kobako.backend.naver.application;

import static org.junit.jupiter.api.Assertions.*;

import kobako.backend.naver.presentation.dto.DatalabSearchRequest;
import kobako.backend.naver.presentation.dto.DatalabSearchResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataLabServiceTest {

    @Autowired
    private DataLabService dataLabService;

    @Test
    void 데이터랩을_통해_트렌드_검색을_진행한다() {
        // given
        DatalabSearchRequest request = new DatalabSearchRequest("2021-01-01", "2021-01-31", "코로나");

        // when
        DatalabSearchResponse response = dataLabService.search(request);

        // then
        Assertions.assertThat(response).isNotNull();

    }
}