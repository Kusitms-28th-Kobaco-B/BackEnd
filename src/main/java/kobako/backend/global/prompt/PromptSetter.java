package kobako.backend.global.prompt;

import kobako.backend.advertisement.dto.request.GenerateAdvertisementCopyRequest;
import kobako.backend.global.enums.Service;
import kobako.backend.global.enums.Tone;
import kobako.backend.global.prompt.toneAndManner.bodyCopy.*;
import kobako.backend.global.prompt.toneAndManner.headCopy.*;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;

@Slf4j
public class PromptSetter {
    public static JSONObject GeneratePrompt(
            GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest
    ) {
        Service service = generateAdvertisementCopyRequest.getService();
        log.info("현재 시간: {}, Service: {}, ProjectName: {}, ProductName: {}, TargetGender: {}, TargetAge: {}, Keywords: {}, Tone: {}",
                LocalDateTime.now(),
                generateAdvertisementCopyRequest.getService(),
                generateAdvertisementCopyRequest.getProjectName(),
                generateAdvertisementCopyRequest.getProductName(),
                generateAdvertisementCopyRequest.getTargetGender(),
                generateAdvertisementCopyRequest.getTargetAge(),
                generateAdvertisementCopyRequest.getKeyword(),
                generateAdvertisementCopyRequest.getTone());

        Tone tone = generateAdvertisementCopyRequest.getTone();

        switch (service) {
            case HEAD:
                return generateHeadCopy(generateAdvertisementCopyRequest);
            case BODY:
                return generateBodyCopy(generateAdvertisementCopyRequest);
            default:
                throw new IllegalArgumentException("잘못된 서비스 타입입니다: " + service);
        }

    }

    // 헤드카피
    private static JSONObject generateHeadCopy(GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest) {
        Tone tone = generateAdvertisementCopyRequest.getTone();

        switch (tone) {
            case DEFAULT:
                return H_default.buildCopy(generateAdvertisementCopyRequest);
            case REVIEW:
                return H_review.buildCopy(generateAdvertisementCopyRequest);
            case ACTION:
                return H_action.buildCopy(generateAdvertisementCopyRequest);
            case WORDPLAY:
                return H_wordplay.buildCopy(generateAdvertisementCopyRequest);
            case QUESTION:
                return H_question.buildCopy(generateAdvertisementCopyRequest);
            case WARNING:
                return H_warning.buildCopy(generateAdvertisementCopyRequest);
            case EMOTIONAL:
                return H_emotional.buildCopy(generateAdvertisementCopyRequest);
            case PROBLEM:
                return H_problem.buildCopy(generateAdvertisementCopyRequest);
            default:
                throw new IllegalArgumentException("잘못된 톤앤매너 타입입니다: " + tone);
        }


    }

    // 바디카피
    private static JSONObject generateBodyCopy(GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest) {
        Tone tone = generateAdvertisementCopyRequest.getTone();

        switch (tone) {
            case DEFAULT:
                return B_default.buildCopy(generateAdvertisementCopyRequest);
            case REVIEW:
                return B_review.buildCopy(generateAdvertisementCopyRequest);
            case ACTION:
                return B_action.buildCopy(generateAdvertisementCopyRequest);
            case WARNING:
                return B_warning.buildCopy(generateAdvertisementCopyRequest);
            case EMOTIONAL:
                return B_emotional.buildCopy(generateAdvertisementCopyRequest);
            case PROBLEM:
                return B_problem.buildCopy(generateAdvertisementCopyRequest);
            default:
                throw new IllegalArgumentException("잘못된 톤앤매너 타입입니다: " + tone);
        }
    }
}
