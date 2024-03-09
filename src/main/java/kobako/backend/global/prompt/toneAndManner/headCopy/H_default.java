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
public class H_default {
    public static JSONObject buildCopy(
            GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest
    ) {

        String product = generateAdvertisementCopyRequest.getProductName();
        List<String> keyword = generateAdvertisementCopyRequest.getKeyword();
        TargetGender targetGender = generateAdvertisementCopyRequest.getTargetGender();
        TargetAge targetAge = generateAdvertisementCopyRequest.getTargetAge();

        JSONObject request_data = new JSONObject();

        request_data.put("text", """
                상대방에게 행동을 촉구하는 헤드카피를 만들어줘.
                무조건 끝문장은 행동을 요구하는 문장이여야해.
                   
                ##
                상품:멀티비타민
                키워드:에너지
                타겟:50대 전체
                메시지:하루하루 걱정되는 건강문제, 당장 해결할 수 있는 절호의 기회! 지금 바로 멀티비타민을 구입하세요!
                ##
                상품:해동 필라테스
                키워드:무료 레슨 체험, 건강
                타겟:20대 여성
                메시지:올해도 다짐만 하실건가요? 날씬한 몸매를 원한다면 지금 바로, 해동동 필라테스에서 무료 1:1 레슨 체험을 예약하세요!
                ##
                상품:인터파크 동남아투어 패키지
                키워드:특별 혜택 3종 세트
                타겟:50대 전체
                메시지:내일까지 인터파크 동남아투어 패키지를 예약하는 분에 한해서 특별 혜택 3종 세트를 드립니다. 지금 바로 예약하세요!
                ##
                상품:현대자동차
                키워드:안전,가족
                타겟:40대 남성
                메시지:현대자동차로 안전한 카 라이프를 즐기며 가족과 함께하는 특별한 시간을 만들어보세요!
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
