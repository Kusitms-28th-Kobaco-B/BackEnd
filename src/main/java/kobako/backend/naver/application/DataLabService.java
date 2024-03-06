package kobako.backend.naver.application;

import kobako.backend.naver.presentation.dto.DataLabRequest;
import kobako.backend.naver.presentation.dto.DatalabSearchRequest;
import kobako.backend.naver.presentation.dto.DatalabSearchResponse;
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

    public DatalabSearchResponse search(DatalabSearchRequest request) {
        WebClient webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("X-Naver-Client-Id", clientId)
            .defaultHeader("X-Naver-Client-Secret", clientSecret)
            .build();

        return webClient.post().uri("/datalab/search")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(DataLabRequest.of(request.startDate(), request.endDate(), request.keyword()))
            .retrieve()
            .bodyToMono(DatalabSearchResponse.class)
            .block();
    }
}
