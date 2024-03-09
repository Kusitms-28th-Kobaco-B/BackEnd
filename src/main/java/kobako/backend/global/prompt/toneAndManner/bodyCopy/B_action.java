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
public class B_action {
    public static JSONObject buildCopy(
            GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest
    ) {

        String product = generateAdvertisementCopyRequest.getProductName();
        List<String> keyword = generateAdvertisementCopyRequest.getKeyword();
        TargetGender targetGender = generateAdvertisementCopyRequest.getTargetGender();
        TargetAge targetAge = generateAdvertisementCopyRequest.getTargetAge();

        JSONObject request_data = new JSONObject();

        request_data.put("text", """
                상대방에게 구매를 강요하는 헤드카피를 만들어줘.
                무조건 끝문장은 행동을 촉구하는 문장이여야해.
                                             
                ##
                상품:멀티비타민
                키워드:에너지
                타겟:50대 전체
                메시지:어르신 여러분, 하루하루 건강 문제에 대한 걱정은 이제 그만! 당장 해결할 수 있는 절호의 기회가 여기 있습니다. 그것은 바로 멀티비타민입니다. 이 멀티비타민은 하루 필요한 모든 영양소를 쉽타민과 미네랄이 풍부하게 들어있는 이 멀티비타민은 여러분의 건강을 지키는 데 필요한 에너지를 제공합니다. 이 기회를 놓치지 마세요, 건강한 하루를 위해 지금 바로 구매하세요!
                ##
                상품명:해동 필라테스
                키워드:무료 레슨 체험, 건강
                타겟층:전체
                메시지:올해도 다짐만 하실건가요? 날씬한 몸매를 원하신다면, 이번 기회를 놓치지 마세요. 지금 바로, 해동 필라테스에서 무료 1:1 레슨 체험을 예약하세요! 필라테스는 몸의 균형을 맞추고, 근육을 강화하는 데 도움이 됩니다. 무료 레슨 체험을 통해 필라테스가 어떻게 여러분의 건강에 도움이 되는지 직접 체험해보세요. 지금 당장 예약하세요!
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
