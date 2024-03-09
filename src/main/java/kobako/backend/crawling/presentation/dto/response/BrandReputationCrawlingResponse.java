package kobako.backend.crawling.presentation.dto.response;

import java.util.List;
import kobako.backend.crawling.domain.BrandReputation;

public record BrandReputationCrawlingResponse(String category, int rank, List<BrandReputation> highestThreePeople) {

}
