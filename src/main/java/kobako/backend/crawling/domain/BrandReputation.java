package kobako.backend.crawling.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandReputation {

    int rank;
    String name;
    String description;

    public static BrandReputation of(int rank, String name, String category) {
        return new BrandReputation(rank, name, category);
    }
}
