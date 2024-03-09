package kobako.backend.naver.application;

import kobako.backend.naver.presentation.dto.request.CharacterAnalysisRequest;
import kobako.backend.naver.presentation.dto.response.DataLabRequest;
import kobako.backend.naver.presentation.dto.response.DatalabSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DataLabService {

    @Value("${naver.datalab.client-id}")
    private String clientId;

    @Value("${naver.datalab.client-secret}")
    private String clientSecret;

    @Value("${naver.datalab.base-url}")
    private String baseUrl;
    private final String trendBaseUrl = "/datalab/search";
    private final String shoppingBaseUrl = "/datalab/shopping/categories";

    public DatalabSearchResponse search(CharacterAnalysisRequest request) {
        WebClient webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("X-Naver-Client-Id", clientId)
            .defaultHeader("X-Naver-Client-Secret", clientSecret)
            .build();

        return webClient.post().uri(trendBaseUrl)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(DataLabRequest.of(convertToFormattedDate(request.startDate()), convertToFormattedDate(request.endDate()), request.keyword()))
            .retrieve()
            .bodyToMono(DatalabSearchResponse.class)
            .block();
    }

    public DatalabSearchResponse shoppingInsight(CharacterAnalysisRequest request) {
        WebClient webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("X-Naver-Client-Id", clientId)
            .defaultHeader("X-Naver-Client-Secret", clientSecret)
            .build();

        return webClient.post().uri(shoppingBaseUrl)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(DataLabRequest.of(request.startDate(), request.endDate(), request.keyword()))
            .retrieve()
            .bodyToMono(DatalabSearchResponse.class)
            .block();
    }

    private String convertToFormattedDate(String date) {
        return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
    }
}
