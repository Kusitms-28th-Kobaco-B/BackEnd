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
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
        List<AdvertisementCopy> advertisementCopies = advertisementCopyRepository.findByMember_MemberIdOrderByCreatedDateDesc(memberId);

        // List 반환
        return AdvertisementCopyResponse.ofAdvertisementCopiesList(advertisementCopies);
    }

    public void generateAdvertisementCopy(
            GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest
    ) {
        JSONObject request_data = new JSONObject();

        request_data.put("text", """
            광고 헤드카피를 만들어줘
            
            ##
            상품: 현대자동차
            키워드: 안전, 가족
            타겟: 아버지
            메시지: 당신 곁엔 언제나 든든한 현대자동차! 사랑하는 가족들을 매일매일 지켜줄 수 있습니다.
            
            ##
            상품: 멀티비타민
            키워드: 에너지, 건강
            타겟: 학생
            메시지: 멀티비타민으로 피로를 날려버리고 에너지 충전! 멀티비타민은 건강한 수험생활을 위한 필수조건!
            
            ##
            상품: 허쉬 초콜릿
            키워드: 달콤함, 스트레스 해소, 수능
            타겟: 고등학생
            메시지:
            """);

        request_data.put("start", "");
        request_data.put("restart", "");
        request_data.put("includeTokens", true);
        request_data.put("topP", 0.8);
        request_data.put("topK", 0);
        request_data.put("maxTokens", 100);
        request_data.put("temperature", 0.5);
        request_data.put("repeatPenalty", 5.0);
        request_data.put("stopBefore", new ArrayList<>());
        request_data.put("includeAiFilters", true);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-NCP-CLOVASTUDIO-API-KEY", clovaApiKey);
        headers.set("X-NCP-APIGW-API-KEY", apiGwKey);
        headers.set("X-NCP-CLOVASTUDIO-REQUEST-ID", requestId);

        HttpEntity<String> entity = new HttpEntity<>(request_data.toString(), headers);

        ResponseEntity<String> response = restTemplate.postForEntity("https://clovastudio.apigw.ntruss.com/testapp/v1/completions/LK-D2", entity, String.class);

        System.out.println(response);

        // 맨 마지막 상품의 '메시지:' 이후의 텍스트 추출
        /*String[] productSplit = response_text.split("##");
        String lastProductInfo = productSplit[productSplit.length - 1].trim();
        String message = lastProductInfo.substring(lastProductInfo.indexOf("메시지:") + 5).trim();
        System.out.println(message);*/
    }


    public AdvertisementCopyResponse loadAdvertisementCopy(Long memberId, Long advertisementCopyId){

        // 광고카피 탐색
        AdvertisementCopy advertisementCopy = advertisementCopyRepository.findByMember_MemberIdAndAdvertisementCopyId(memberId, advertisementCopyId)
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
