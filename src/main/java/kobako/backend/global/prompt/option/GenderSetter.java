package kobako.backend.global.prompt.option;

import kobako.backend.global.ENUM.TargetGender;

public class GenderSetter {
    public static String settingGender(TargetGender targetGender){
        switch (targetGender) {
            case ALL:
                return "전체";
            case MALE:
                return "남성";
            case FEMALE:
                return "여성";
            default:
                //추후 수정
                throw new IllegalArgumentException("잘못된 성별 타겟입니다: " + targetGender);
        }
    }
}
