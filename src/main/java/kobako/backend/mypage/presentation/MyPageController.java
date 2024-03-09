package kobako.backend.mypage.presentation;


import kobako.backend.global.enums.Service;
import kobako.backend.global.enums.Tone;
import kobako.backend.global.domain.RequestUri;
import kobako.backend.mypage.application.MyPageService;
import kobako.backend.mypage.presentation.dto.request.GetMyCopiesRequest;
import kobako.backend.mypage.presentation.dto.response.GetMyCopiesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = RequestUri.myPage)
@RequiredArgsConstructor
public class MyPageController implements MyPageUi {

    private final MyPageService myPageService;

    @GetMapping("/myCopies")
    public ResponseEntity<List<GetMyCopiesResponse>> GetMyCopyGallery(
            @RequestParam(required = true) Long memberId,
            @RequestParam(required = false) Service service,
            @RequestParam(required = false) Tone tone
    ) {
        GetMyCopiesRequest getMyCopiesRequest = new GetMyCopiesRequest(memberId, service, tone);

        List<GetMyCopiesResponse> getMyCopiesResponses
                = myPageService.getMyCopies(getMyCopiesRequest);

        return ResponseEntity.ok(getMyCopiesResponses);
    }
}
