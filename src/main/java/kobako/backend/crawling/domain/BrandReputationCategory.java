package kobako.backend.crawling.domain;

import lombok.Getter;

@Getter
public class BrandReputationCategory {

    private String url;
    private String category;

    private BrandReputationCategory(String url, String category) {
        this.url = url;
        this.category = category;
    }

    public static BrandReputationCategory of(String url, String category) {
        return new BrandReputationCategory(url, category);
    }
}
