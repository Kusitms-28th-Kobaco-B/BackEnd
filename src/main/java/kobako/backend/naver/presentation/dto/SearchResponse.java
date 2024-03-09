package kobako.backend.naver.presentation.dto;

import java.util.List;
import kobako.backend.naver.domain.ProductItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResponse {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<ProductItem> items;
}
