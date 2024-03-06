package kobako.backend.naver.presentation.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatalabSearchResponse {

    private String startDate;
    private String endDate;
    private String timeUnit;
    private List<Result> results;

    @Getter
    @Setter
    public static class Result {

        private String title;
        private List<String> keywords;
        private List<Data> data;
    }

    @Getter
    @Setter
    public static class Data {

        private String period;
        private double ratio;
    }
}
