package kobako.backend.myPage.presentation;


import kobako.backend.advertisementCopy.application.AdvertisementCopyService;
import kobako.backend.copyGallery.application.CopyGalleryService;
import kobako.backend.copyGallery.domain.CopyGallery;
import kobako.backend.copyGallery.dto.Response.CopyGalleryResponse;
import kobako.backend.global.ENUM.Service;
import kobako.backend.global.ENUM.Tone;
import kobako.backend.global.domain.RequestUri;
import kobako.backend.myPage.application.MyPageService;
import kobako.backend.myPage.dto.Request.GetMyCopiesRequest;
import kobako.backend.myPage.dto.Response.GetMyCopiesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping(value = RequestUri.myPage)
@RequiredArgsConstructor
public class MyPageController {

    private static MyPageService myPageService;

    @GetMapping("/myCopies")
    public ResponseEntity<Page<GetMyCopiesResponse>> GetMyCopyGallery(
            @RequestParam(required = true) Long memberId,
            @RequestParam(required = false) Service service,
            @RequestParam(required = false) Tone tone
    ) {
        GetMyCopiesRequest getMyCopiesRequest = new GetMyCopiesRequest(memberId, service, tone);

        Page<GetMyCopiesResponse> getMyCopiesResponses
                = myPageService.getMyCopies(getMyCopiesRequest);

        return ResponseEntity.ok(getMyCopiesResponses);
    }
}
