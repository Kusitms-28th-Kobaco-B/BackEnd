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
public class H_problem {
    public static JSONObject buildCopy(
            GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest
    ) {

        String product = generateAdvertisementCopyRequest.getProductName();
        List<String> keyword = generateAdvertisementCopyRequest.getKeyword();
        TargetGender targetGender = generateAdvertisementCopyRequest.getTargetGender();
        TargetAge targetAge = generateAdvertisementCopyRequest.getTargetAge();

        JSONObject request_data = new JSONObject();

        request_data.put("text", """
                상대방에게 문제를 제시하고, 해결책을 주는 광고카피를 만들어줘.
                                           
                ##
                상품:아이스크림
                키워드:저당, 동물성 크림
                타겟:20대 여성
                메시지:당신의 다이어트를 방해하는 아이스크림, 이제는 걱정하지 마세요. 아이스크림에 당이 많아 다이어트가 걱정인가요? 저희의 저당 아이스크림은 당신의 건강을 위해 만들어졌습니다. 동물성 크림 대신 식물성 크림을 사용하여, 더욱 건강하고 맛있는 아이스크림을 즐길 수 있습니다. 당신의 건강을 위한 선택, 저당 아이스크림으로 안심하세요!
                ##
                상품:염색약
                키워드:천연 염색 제품
                타겟:전체
                메시지:당신의 두피와 모발 건강을 위한 선택, 천연 염색 제품입니다. 염색 화학성분 때문에 두피 상태가 걱정인 사람들, 이제는 걱정하지 마세요. 천연 염색 제품은 화학성분을 최소화하여 두피와 모발을 보호합니다. 당신의 건강을 위한 선택, 천연 염색 제품으로 건강한 두피와 모발을 유지하세요!
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
        request_data.put("temperature", 0.6);
        request_data.put("repeatPenalty", 5.0);
        request_data.put("stopBefore", Arrays.asList("상품:", "##"));
        request_data.put("includeAiFilters", true);

        return request_data;
    }
}
