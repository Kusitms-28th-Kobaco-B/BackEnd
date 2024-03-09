package kobako.backend.crawling.presentation.dto.response;

public record KakaoDataTrendCrawlingResponse(String maleRatio, String femaleRatio, double teenager,
		             double twenties,
		             double thirties, double forties, double fifties,
		             double sixties) {

}
