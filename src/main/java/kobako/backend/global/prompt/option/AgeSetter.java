package kobako.backend.global.prompt.option;

import kobako.backend.global.ENUM.TargetAge;

public class AgeSetter {
    public static String settingAge(TargetAge targetAge){
        switch (targetAge) {
            case ZERO:
                return "10대 미만";
            case TEN:
                return "10대";
            case TWENTY:
                return "20대";
            case THIRTY:
                return "30대";
            case FORTY:
                return "40대";
            case FIFTY:
                return "50대";
            case SIXTY:
                return "60대 이상";
            default:
                //추후 수정
                throw new IllegalArgumentException("잘못된 연령 타겟입니다: " + targetAge);
        }
    }
}
