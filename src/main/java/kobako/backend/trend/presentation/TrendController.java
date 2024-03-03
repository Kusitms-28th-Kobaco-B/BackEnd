package kobako.backend.trend.presentation;

import kobako.backend.global.domain.RequestUri;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RequestUri.trend)
public class TrendController {

    @PostMapping("/character-analysis")
    public void characterAnalysis() {

    }
}
