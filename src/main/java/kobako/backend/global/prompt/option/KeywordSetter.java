package kobako.backend.global.prompt.option;

import java.util.List;

public class KeywordSetter {
    public static String settingKeyword(List<String> keyword){
        return String.join(", ", keyword);
        // 키워드들을 쉼표로 구분 후 나눔
    }
}
