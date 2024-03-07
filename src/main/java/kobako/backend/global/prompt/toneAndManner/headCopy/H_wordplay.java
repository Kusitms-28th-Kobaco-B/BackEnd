package kobako.backend.global.prompt.toneAndManner.headCopy;

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
public class H_wordplay {
    public static JSONObject buildCopy(
            GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest
    ) {

        String product = generateAdvertisementCopyRequest.getProductName();
        List<String> keyword = generateAdvertisementCopyRequest.getKeyword();
        TargetGender targetGender = generateAdvertisementCopyRequest.getTargetGender();
        TargetAge targetAge = generateAdvertisementCopyRequest.getTargetAge();

        JSONObject request_data = new JSONObject();

        request_data.put("text", """
                무조건 말장난을 활용한 헤드카피를 만들어줘.
                다양한 언어유희를 활용해서 만들어줘.
                                        
                ##
                상품:현대자동차
                키워드:안전,가족
                타겟:40대 남성
                메시지:아버지, 가족을 안전을 위한 ‘현’실적인 약속, 현대자동차가 지키겠습니다.
                ##
                상품:붉은대게버거
                키워드:대게
                타겟:전체
                메시지:게 있느냐! 게 아무도 없느냐? 게살의 진미를 맛보다. 붉은대게버거.
                ##
                상품:그릇
                키워드:삶
                타겟:전체
                메시지:그릇된 삶을 살 것인가? 큰 그릇이 되는 삶을 살 것인가?
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
        request_data.put("temperature", 0.9);
        request_data.put("repeatPenalty", 5.0);
        request_data.put("stopBefore", Arrays.asList("상품:", "##"));
        request_data.put("includeAiFilters", true);

        return request_data;
    }
}
