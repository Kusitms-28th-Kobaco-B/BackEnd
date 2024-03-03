package kobako.backend.crawling.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record KakaoDataTrendCrawlingResponse(String male, String female, double teenager, double twenties,
		             double thirties, double forties, double fifties, double sixties) {

}
