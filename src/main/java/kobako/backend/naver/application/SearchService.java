package kobako.backend.naver.application;

import kobako.backend.naver.presentation.dto.response.SearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SearchService {

    @Value("${naver.search.client-id}")
    private String clientId;

    @Value("${naver.search.client-secret}")
    private String clientSecret;

    @Value("${naver.search.base-url}")
    private String baseUrl;
    private final String shoppingSearchUrl = "/shop.json";

    public SearchResponse searchProducts(String query) {
        WebClient webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("X-Naver-Client-Id", clientId)
            .defaultHeader("X-Naver-Client-Secret", clientSecret)
            .build();

        final int displayValue = 5;
        final int startValue = 1;
        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path(shoppingSearchUrl)
	.queryParam("query", query)
	.queryParam("display", displayValue)
	.queryParam("start", startValue)
	.build())
            .retrieve()
            .bodyToMono(SearchResponse.class)
            .block();
    }
}
