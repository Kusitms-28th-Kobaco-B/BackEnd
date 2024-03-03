package kobako.backend.naver.application;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kobako.backend.naver.presentation.dto.KeywordsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SearchAdService {

    @Value("${naver.searchad.client-id}")
    private String clientId;

    @Value("${naver.searchad.client-secret}")
    private String clientSecret;

    @Value("${naver.searchad.base-url}")
    private String baseUrl;

    @Value("${naver.searchad.customer-id}")
    private String customerId;

    private final String postfix = "/keywordstool";

    public String getRelatedKeywords(String keyword) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String method = "GET";
        String signature = generateSignature(timestamp, method, postfix);

        WebClient webClient = WebClient.create(baseUrl + postfix);

        System.out.print("timestamp: " + timestamp + "\n");
        System.out.print("signature: " + signature + "\n");
        System.out.print("clientId: " + clientId + "\n");
        System.out.print("customerId: " + customerId + "\n");

        KeywordsResponse response = webClient.get()
            .uri(uriBuilder -> uriBuilder
	.queryParam("hintKeywords", keyword)
	.queryParam("showDetail", 1)
	.build())
            .header("Content-Type", "application/json; charset=UTF-8")
            .header("X-Timestamp", timestamp)
            .header("X-API-KEY", clientId)
            .header("X-Customer", customerId)
            .header("X-Signature", signature)
            .retrieve()
            .bodyToMono(KeywordsResponse.class)
            .block();

        return response.getKeywordList().get(0).getRelKeyword();
    }

    private String generateSignature(String timestamp, String method, String uri) {
        try {
            String message = timestamp + "." + method + "." + uri;
            SecretKeySpec signingKey = new SecretKeySpec(clientSecret.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(message.getBytes());
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to calculate signature", e);
        }
    }
}

