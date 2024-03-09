package kobako.backend.global.prompt.toneAndManner.bodyCopy;

import kobako.backend.advertisementCopy.dto.request.GenerateAdvertisementCopyRequest;
import kobako.backend.global.ENUM.TargetAge;
import kobako.backend.global.ENUM.TargetGender;
import kobako.backend.global.prompt.option.AgeSetter;
import kobako.backend.global.prompt.option.GenderSetter;
import kobako.backend.global.prompt.option.KeywordSetter;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.List;

//행동촉구형
public class B_warning {
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
                메시지:교통사고는 예고없이 찾아옵니다. 순식간에 모든 것을 앗아갈 수 있습니다. 당신의 가족, 그 소중한 존재를 지키고 싶지 않으신가요? 안전은 우리의 최우선 과제입니다. 현대자동차는 당신의 가족을 위한 다양한 안전장치가 있습니다. 가족을 위한 약속, 현대자동차가 지켜드리겠습니다. 안전을 위한 선택, 그것이 바로 현대자동차 입니다.
                ##
                상품:해동 피트니스
                키워드:건강, 비만
                타겟:전체
                메시지:당신의 건강이 위험에 처해 있습니다. 비만은 당신의 건강을 위협하고, 그 결과로 다양한 합병증을 초래할 수 있습니다. 합병증의 원인 1위 비만, 아직도 방치하실 겁니까? 지금 바로 행동에 옮기세요. 해동 피트니스와 함께라면 당신의 건강을 되찾을 수 있습니다. 전문적인 트레이너와 맞춤형 운동 프로그램으로 당신의 건강을 지켜드립니다. 지금 당장 해동 피트니스와 함께 건강을 되찾으세요!
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
