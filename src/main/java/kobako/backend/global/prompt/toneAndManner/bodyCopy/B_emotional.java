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
public class B_emotional {
    public static JSONObject buildCopy(
            GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest
    ) {

        String product = generateAdvertisementCopyRequest.getProductName();
        List<String> keyword = generateAdvertisementCopyRequest.getKeyword();
        TargetGender targetGender = generateAdvertisementCopyRequest.getTargetGender();
        TargetAge targetAge = generateAdvertisementCopyRequest.getTargetAge();

        JSONObject request_data = new JSONObject();

        request_data.put("text", """
                상대방에게 감정을 호소하는 광고카피를 만들어줘.
                                    
                ##
                상품:동물보호소
                키워드:사랑, 유기견
                타겟:전체
                메시지:유기견들이 여러분의 사랑을 갈망하고 있습니다. 그들은 불행한 환경에서 벗어나 따뜻한 보호를 필요로 합니다. 유기견에게도 사랑이 필요합니다. 당신의 작은 관심이 그들의 큰 행복을 만듭니다. 좌절과 상처를 겪은 그들에게 새로운 삶의 기회를 선물해주세요. 고요한 관심과 따뜻한 포옹, 그리고 사랑으로 그들의 일상을 변화시킬 수 있습니다.
                ##
                상품:유니셰프
                키워드:기부, 사랑
                타겟:전체
                메시지:당신의 사랑이 필요한 이들이 있습니다. 세상 어디에도 버림받은 아이들은 없어야 합니다. 당신의 작은 기부가 그들의 큰 희망이 될 수 있습니다. 기부를 통해 그들에게 사랑을 전해주세요. 나눔으로 시작하는 행복, 당신의 손길이 필요합니다. 당신의 사랑이 그들의 미래를 밝게 할 수 있습니다. 우리 모두가 한 손을 내미면, 그 손길은 세상을 바꿀 수 있습니다. 사랑과 기부로 시작하는 나날들, 우리와 함께해주세요.
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
