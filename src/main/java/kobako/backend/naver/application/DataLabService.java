package kobako.backend.naver.application;

import org.springframework.beans.factory.annotation.Value;
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

}
