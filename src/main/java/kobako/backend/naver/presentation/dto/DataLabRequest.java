package kobako.backend.naver.presentation.dto;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataLabRequest {

    private String startDate;
    private String endDate;
    private String timeUnit;
    private ArrayList<KeywordGroup> keywordGroups;

    @Getter
    @Setter
    static class KeywordGroup {

        private String groupName;
        private String[] keywords;

        public KeywordGroup(String groupName, String[] keywords) {
            this.groupName = groupName;
            this.keywords = keywords;
        }
    }

    private DataLabRequest(String startDate, String endDate, String keyword) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeUnit = "month";
        this.keywordGroups = new ArrayList<>();
        keywordGroups.add(new KeywordGroup(keyword, new String[]{keyword}));
    }

    public static DataLabRequest of(String startDate, String endDate, String keyword) {
        return new DataLabRequest(startDate, endDate, keyword);
    }

}
