package kobako.backend.advertisementCopy.application;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kobako.backend.copyGallery.domain.CopyGallery;
import kobako.backend.copyGallery.infra.presistence.CopyGalleryRepository;
import kobako.backend.member.infra.presistence.MemberRepository;
import kobako.backend.advertisementCopy.domain.AdvertisementCopy;
import kobako.backend.advertisementCopy.dto.request.GenerateAdvertisementCopyRequest;
import kobako.backend.advertisementCopy.dto.request.UpdateAdvertisementCopyRequest;
import kobako.backend.advertisementCopy.dto.response.AdvertisementCopyResponse;
import kobako.backend.advertisementCopy.infra.presistence.AdvertisementCopyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.NoSuchElementException;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AdvertisementCopyService {

    @Value("${naver.clova.clovaApiKey}")
    private String clovaApiKey;

    @Value("${naver.clova.ApiGwKey}")
    private String apiGwKey;

    @Value("${naver.clova.RequestId}")
    private String requestId;


    private final MemberRepository memberRepository;
    private final AdvertisementCopyRepository advertisementCopyRepository;
    private final CopyGalleryRepository copyGalleryRepository;



    public List<AdvertisementCopyResponse> getRecentAdvertisementCopy (
            Long memberId
    ) {
        // 사용자가 생성한 광고카피를 최신순으로 탐색
        List<AdvertisementCopy> advertisementCopies = advertisementCopyRepository.findByMemberIdOrderByCreatedDateDesc(memberId);

        // List 반환
        return AdvertisementCopyResponse.ofAdvertisementCopiesList(advertisementCopies);
    }

    public AdvertisementCopyResponse generateAdvertisementCopy(
            GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest
    ) {
        String message = "";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("https://clovastudio.apigw.ntruss.com/testapp/v1/completions/LK-D2");

            // Headers 설정
            httpPost.addHeader("X-NCP-CLOVASTUDIO-API-KEY", clovaApiKey);  // CLOVA STUDIO API 키 설정
            httpPost.addHeader("X-NCP-APIGW-API-KEY", apiGwKey);  // API Gateway API 키 설정
            httpPost.addHeader("X-NCP-CLOVASTUDIO-REQUEST-ID", requestId);  // CLOVA STUDIO 요청 ID 설정
            httpPost.addHeader("Content-Type", "application/json");  // 요청의 Content-Type 설정

            // Request body 설정
            String requestBody = "{\"text\":\"##\\n상품:현대자동차\\n키워드:안전,가족\\n메시지:현대자동차의 안전한 운전 환경과 가족 중심의 디자인은 정말 감명 깊어요. 가족 모두가 함께하는 여정을 안심하며 즐길 수 있어서 좋아요.\\n##\\n상품:멀티비타민\\n키워드:에너지\\n메시지:원래 건강기능식품 같은 거 잘 안 챙겨 먹는데 이건 꼭 먹어요. 야근과 회식 잦은 직장인들에게 필수템! 확실히 체력적으로 힘든게 덜 하네요.\\n##\\n상품:다짐 필라테스\\n키워드:휴식\\n메시지:무료 체험을 통해 다짐 필라테스의 매력을 느낄 수 있어요. 전문 강사와 함께 몸과 마음의 균형을 찾아낼 수 있어요.\\n##\\n상품:인터파크 동남아투어 패키지\\n키워드:특별 혜택 3종 세트\\n메시지:특별 혜택 3종 세트로 더욱 특별한 인터파크 동남아투어 패키지! 여행을 더욱 풍성하게 만들어준 최고의 선택이에요.\\n##\\n상품:초콜릿\\n키워드:달콤함,수능\\n메시지:\",\"start\":\"\",\"restart\":\"\",\"includeTokens\":true,\"topP\":0.8,\"topK\":0,\"maxTokens\":100,\"temperature\":0.6,\"repeatPenalty\":5.0,\"stopBefore\":[\"상품:\",\"##\"],\"includeAiFilters\":true}";
            httpPost.setEntity(new StringEntity(requestBody));  // 요청 바디 설정

            // 요청 보내고 응답 받기
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                Header contentTypeHeader = response.getFirstHeader("Content-Type");
                if (contentTypeHeader != null) {
                    String contentType = contentTypeHeader.getValue();
                    System.out.println("Content-Type: " + contentType);
                }

                // 응답 바디를 문자열로 변환
                InputStream inputStream = response.getEntity().getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                StringBuilder responseBody = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);
                }

                // JSON 파싱
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(responseBody.toString());

                // "result" 객체 안의 "text" 값을 가져오기
                message = jsonNode.get("result").get("text").asText();
                log.info(message);  // 로그로 메시지 출력
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());  // 에러 발생 시 메시지 출력
        }

        AdvertisementCopy advertisementCopy = advertisementCopyRepository.save(generateAdvertisementCopyRequest.toBodyCopy(message));  // 광고카피 저장
        return AdvertisementCopyResponse.of(advertisementCopy);  // 광고카피 응답 생성
    }


    public AdvertisementCopyResponse loadAdvertisementCopy(Long memberId, Long advertisementCopyId){

        // 광고카피 탐색
        AdvertisementCopy advertisementCopy = advertisementCopyRepository.findByMemberIdAndAdvertisementCopyId(memberId, advertisementCopyId)
                .orElseThrow(() -> new NoSuchElementException("AdvertisementCopy not found with id: " + advertisementCopyId));

        // 카피갤러리 생성
        CopyGallery copyGallery = CopyGallery.builder()
                .advertisementCopy(advertisementCopy)
                .member(advertisementCopy.getMember())
                .service(advertisementCopy.getService())
                .productName(advertisementCopy.getProductName())
                .tone(advertisementCopy.getTone())
                .views(0L)
                .message(advertisementCopy.getMessage())
                .build();
        copyGalleryRepository.save(copyGallery);

        return AdvertisementCopyResponse.of(advertisementCopy);
    }




    public AdvertisementCopyResponse updateAdvertisementCopy(
            Long advertisementCopyId,
            UpdateAdvertisementCopyRequest updateAdvertisementCopyRequest
    ) {

        AdvertisementCopy advertisementCopy = advertisementCopyRepository.findById(advertisementCopyId)
                .orElseThrow(() -> new NoSuchElementException("AdvertisementCopy not found with id: " + advertisementCopyId));

        advertisementCopy.setMessage(updateAdvertisementCopyRequest.getMessage());
        advertisementCopyRepository.save(advertisementCopy);

        return AdvertisementCopyResponse.of(advertisementCopy);
    }

    public void deleteAdvertisementCopy(
            Long advertisementCopyId
    ) {
        if (!advertisementCopyRepository.existsById(advertisementCopyId)) {
            //추후 오류 추가
            throw new NoSuchElementException("AdvertisementCopy not found with id: " + advertisementCopyId);
        }

        advertisementCopyRepository.deleteById(advertisementCopyId);
    }
}
