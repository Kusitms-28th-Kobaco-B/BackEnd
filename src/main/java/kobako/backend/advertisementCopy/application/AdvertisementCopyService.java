package kobako.backend.advertisementCopy.application;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kobako.backend.copyGallery.domain.CopyGallery;
import kobako.backend.copyGallery.infra.presistence.CopyGalleryRepository;
import kobako.backend.global.prompt.PromptSetter;
import kobako.backend.member.domain.Member;
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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import java.util.Arrays;
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

    public AdvertisementCopyResponse generateAdvertisementCopy(
            Long memberId,
            GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest
    ) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));


        JSONObject request_data = PromptSetter.GeneratePrompt(generateAdvertisementCopyRequest);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-NCP-CLOVASTUDIO-API-KEY", clovaApiKey);
        headers.set("X-NCP-APIGW-API-KEY", apiGwKey);
        headers.set("X-NCP-CLOVASTUDIO-REQUEST-ID", requestId);

        HttpEntity<String> entity = new HttpEntity<>(request_data.toString(), headers);

        ResponseEntity<String> response = restTemplate.postForEntity("https://clovastudio.apigw.ntruss.com/testapp/v1/completions/LK-D2", entity, String.class);

        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(response.getBody());

            JSONObject result = (JSONObject) json.get("result");
            String outputText = ((String) result.get("outputText")).trim();

            log.info(outputText);

            AdvertisementCopy advertisementCopy
                            = advertisementCopyRepository.save(generateAdvertisementCopyRequest.toAdvertismentCopy(member,outputText));

            return AdvertisementCopyResponse.of(advertisementCopy);

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
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

    public AdvertisementCopyResponse loadAdvertisementCopy(Long memberId, Long advertisementCopyId){

        // 이미 저장된 광고 카피면 Exception 발생
        copyGalleryRepository.findByAdvertisementCopy_AdvertisementCopyId(advertisementCopyId)
                .ifPresent(existingCopyGallery -> {throw new IllegalArgumentException("AdvertisementCopy already exists in CopyGallery");});

        // 광고카피 탐색
        AdvertisementCopy advertisementCopy = advertisementCopyRepository.findByMember_MemberIdAndAdvertisementCopyId(memberId, advertisementCopyId)
                .orElseThrow(() -> new NoSuchElementException("AdvertisementCopy not found with id: " + advertisementCopyId));

        // List<String>은 따로 복사해서 다시 넣기.
        List<String> keywords = new ArrayList<>(advertisementCopy.getKeywords());

        // 카피갤러리 생성
        CopyGallery copyGallery = CopyGallery.builder()
                .advertisementCopy(advertisementCopy)
                .member(advertisementCopy.getMember())
                .service(advertisementCopy.getService())
                .productName(advertisementCopy.getProductName())
                .tone(advertisementCopy.getTone())
                .views(0L)
                .keywords(keywords)
                .message(advertisementCopy.getMessage())
                .build();
        copyGalleryRepository.save(copyGallery);

        return AdvertisementCopyResponse.of(advertisementCopy);
    }


}
