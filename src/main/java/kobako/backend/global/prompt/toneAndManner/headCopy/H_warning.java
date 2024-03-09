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
public class H_warning {
    public static JSONObject buildCopy(
            GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest
    ) {

        String product = generateAdvertisementCopyRequest.getProductName();
        List<String> keyword = generateAdvertisementCopyRequest.getKeyword();
        TargetGender targetGender = generateAdvertisementCopyRequest.getTargetGender();
        TargetAge targetAge = generateAdvertisementCopyRequest.getTargetAge();

        JSONObject request_data = new JSONObject();

        request_data.put("text", """
                상대방에게 경고를 한 후, 상품을 홍보하는 광고카피를 만들어줘.
                                  
                ##
                상품:현대자동차
                키워드:안전, 가족
                타겟:40대 남성
                메시지:예고 없이 찾아오는 교통사고, 순식간에 모든 것을 앗아갈 수 있습니다. 안전을 위한 약속, 현대자동차가 지켜드리겠습니다.
                ##
                상품:해동 피트니스
                키워드:건강, 비만
                타겟:전체
                메시지:합병증의 원인 1위 비만, 아직도 방치하실겁니까? 지금 당장 해동 피트니스와 함께 건강을 되찾으세요!
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
        request_data.put("temperature", 0.65);
        request_data.put("repeatPenalty", 5.0);
        request_data.put("stopBefore", Arrays.asList("상품:", "##"));
        request_data.put("includeAiFilters", true);

        return request_data;
    }
}
