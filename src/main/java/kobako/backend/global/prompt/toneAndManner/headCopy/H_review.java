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
public class H_review {
    public static JSONObject buildCopy(
            GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest
    ) {

        String product = generateAdvertisementCopyRequest.getProductName();
        List<String> keyword = generateAdvertisementCopyRequest.getKeyword();
        TargetGender targetGender = generateAdvertisementCopyRequest.getTargetGender();
        TargetAge targetAge = generateAdvertisementCopyRequest.getTargetAge();

        JSONObject request_data = new JSONObject();

        request_data.put("text", """
                유튜버가 광고하는것처럼 헤드카피를 만들어줘
                                
                ##
                상품:톤업 선크림
                키워드:촉촉
                타겟:20대 여성
                메시지:이 뜨거운 여름에 피부에 촉촉함을 선사하면서 톤업까지? 제가 지금까지 써본 선크림 중에선 최고에요! 선크림을 찾고 계신다면 이 제품이 답입니다!
                ##
                상품:멀티비타민
                키워드:에너지, 가성비
                타겟:50대 전체
                메시지:여러분 영양제 여러개 먹기 부담스럽지 않나요? 전 다른거 안먹어요~ 이것만 먹습니다! 저 믿고 이거 한번 드셔보세요!
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
        request_data.put("temperature", 0.8);
        request_data.put("repeatPenalty", 5.0);
        request_data.put("stopBefore", Arrays.asList("상품:", "##"));
        request_data.put("includeAiFilters", true);

        return request_data;
    }
}
