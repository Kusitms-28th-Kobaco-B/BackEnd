package kobako.backend.crawling.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record KakaoDataTrendCrawlingRequest(String startDate, String endDate, String keyword) {

}
