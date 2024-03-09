package kobako.backend.global.prompt.toneAndManner.headCopy;

import kobako.backend.advertisement.dto.request.GenerateAdvertisementCopyRequest;
import kobako.backend.global.enums.TargetAge;
import kobako.backend.global.enums.TargetGender;
import kobako.backend.global.prompt.option.AgeSetter;
import kobako.backend.global.prompt.option.GenderSetter;
import kobako.backend.global.prompt.option.KeywordSetter;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.List;

//행동촉구형
public class H_question {
    public static JSONObject buildCopy(
            GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest
    ) {

        String product = generateAdvertisementCopyRequest.getProductName();
        List<String> keyword = generateAdvertisementCopyRequest.getKeyword();
        TargetGender targetGender = generateAdvertisementCopyRequest.getTargetGender();
        TargetAge targetAge = generateAdvertisementCopyRequest.getTargetAge();

        JSONObject request_data = new JSONObject();

        request_data.put("text", """
               질문하는 형태의 광고카피를 만들어줘.
               문장에 무조건 의문문이 있어야해.
               
               ##
               상품:현대자동차
               키워드:안전,가족
               타겟:40대 남성
               메시지:도로 위 다양한 위험. 가족을 지키는 첫 걸음, 현대자동차와 함께하는건 어떨까요?
               ##
               상품:해동 필라테스
               키워드:건강
               타겟:전체
               메세지:점점 늘어나는 체중, 나빠지는 건강지수. 해동 필라테스와 함께 새 삶을 시작하는 것은 어떠세요?
               ##
               상품:대한항공
               키워드:편안함, 높은 만족도
               타겟:50대 전체
               메시지:여행 중 편안함, 높은 만족도가 가장 중요하다면, 대한항공이 최선의 선택이 아닐까요?
               ##
               상품:갤럭시 폰
               키워드:무료 체험
               메시지:무료 갤럭시 체험으로 갤럭시의 매력을 직접 확인해 보실래요?
               ##
               상품:""" + product + "\n" + """
               키워드:""" + KeywordSetter.settingKeyword(keyword) + "\n" + """
               타겟:""" + AgeSetter.settingAge(targetAge) + " " + GenderSetter.settingGender(targetGender) + "\n" +"""
               메시지:""");

        request_data.put("start", "");
        request_data.put("restart", "");
        request_data.put("includeTokens", true);
        request_data.put("topP", 0.8);
        request_data.put("topK", 0);
        request_data.put("maxTokens", 100);
        request_data.put("temperature", 0.3);
        request_data.put("repeatPenalty", 5.0);
        request_data.put("stopBefore", Arrays.asList("상품:", "##"));
        request_data.put("includeAiFilters", true);

        return request_data;
    }
}
