package kobako.backend.global.prompt.toneAndManner.bodyCopy;

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
public class B_default {
    public static JSONObject buildCopy(
            GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest
    ) {

        String product = generateAdvertisementCopyRequest.getProductName();
        List<String> keyword = generateAdvertisementCopyRequest.getKeyword();
        TargetGender targetGender = generateAdvertisementCopyRequest.getTargetGender();
        TargetAge targetAge = generateAdvertisementCopyRequest.getTargetAge();

        JSONObject request_data = new JSONObject();

        request_data.put("text", """
                광고카피를 만들어줘
                                
                ##
                상품:현대자동차
                키워드:안전, 가족
                타겟:40대 남성
                메시지:가족의 안전은 언제나 우리의 최우선입니다. 그렇기에 현대자동차가 당신 곁에 든든한 파트너로서 언제나 함께합니다. 현대자동차의 첨단 안전 시스템은 사랑하는 가족들을 매일매일 지켜줄 수 있습니다. 차의 안전성만큼 중요한 것이 없으니까요.아버지의 선택이 가족의 안전을 결정합니다. 당신 곁엔 언제나 든든한 현대자동차가 있습니다.
                ##
                상품:멀티비타민
                키워드:에너지,건강
                타겟:10대 전체
                메시지:에너지부터 건강까지, 멀티비타민이 모두 챙겨줍니다! 멀티비타민은 비타민과 미네랄의 조합으로 하루의 에너지를 보충하고, 영양소 공급을 도와줄 뿐만 아니라, 여러분의 체력과 면역을 강화시켜 활기찬 일상을 만들어줍니다. 지친 일상에서 멀티비타민이 함께하면, 피로와 스트레스에 대항하는 더 건강한 나를 발견할 수 있습니다.
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
        request_data.put("maxTokens", 300);
        request_data.put("temperature", 0.6);
        request_data.put("repeatPenalty", 5.0);
        request_data.put("stopBefore", Arrays.asList("상품:", "##"));
        request_data.put("includeAiFilters", true);

        return request_data;
    }
}
