package kobako.backend.crawling.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandReputation {

    int rank;
    String description;

    public BrandReputation(int rank, String description) {
        this.rank = rank;
        this.description = description;
    }
}
