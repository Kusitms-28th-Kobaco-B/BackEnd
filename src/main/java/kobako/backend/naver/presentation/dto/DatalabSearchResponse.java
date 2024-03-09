package kobako.backend.naver.presentation.dto;

import java.util.List;
import kobako.backend.naver.domain.SearchVolume;
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
        private List<SearchVolume> data;
    }

    public List<SearchVolume> getSearchVolumes() {
        final int initialIndex = 0;
        return results.get(initialIndex).getData();
    }
}
