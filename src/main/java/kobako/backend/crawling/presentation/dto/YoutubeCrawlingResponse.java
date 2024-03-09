package kobako.backend.crawling.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class YoutubeCrawlingResponse {

    private String imageUrl;
    private String href;
    private String title;

}
