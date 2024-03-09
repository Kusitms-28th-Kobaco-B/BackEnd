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
public class B_review {
    public static JSONObject buildCopy(
            GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest
    ) {

        String product = generateAdvertisementCopyRequest.getProductName();
        List<String> keyword = generateAdvertisementCopyRequest.getKeyword();
        TargetGender targetGender = generateAdvertisementCopyRequest.getTargetGender();
        TargetAge targetAge = generateAdvertisementCopyRequest.getTargetAge();

        JSONObject request_data = new JSONObject();

        request_data.put("text", """
                유튜버가 광고하는 것처럼 광고카피를 만들어줘
                                     
                ##
                상품:톤업 선크림
                키워드:촉촉
                타겟:20대 여성
                메시지:여성 여러분, 피부관리에 어려움을 느끼시나요? 피부에 촉촉함을 선사하면서, 또한 톤업 효과까지 원하시나요? 그렇다면 제가 사용해본 선크림 중에서 이 제품을 강력히 추천합니다. 하루 종일 피부를 촉촉하게 유지하면서 톤업 효과까지 제공해주니, 선크림을 찾고 계신다면 이 제품이 답입니다! 피부에 부담이 없어, 매일 사용하기에도 안성맞춤입니다. 여성의 피부를 위한 최고의 선택, 이 선크림을 추천합니다!
                ##
                상품:멀티비타민
                키워드:에너지, 가성비
                타겟:60대 전체
                메시지:어르신 여러분, 영양제를 여러 개 복용하는 것은 부담스럽지 않으신가요? 저는 이 멀티비타민 하나만 먹습니다. 멀티비타민이라는 이름 답게 다양한 비타민을 함유하고 있어서, 여러 개의 영양제를 따로 복용할 필요가 없습니다. 또한, 가격 대비 효과가 우수해서 가성비 면에서도 탁월합니다. 어르신들의 에너지와 건강을 위해, 이 멀티비타민을 강력히 추천합니다!
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
