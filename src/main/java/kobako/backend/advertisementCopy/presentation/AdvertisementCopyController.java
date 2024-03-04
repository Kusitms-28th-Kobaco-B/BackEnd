package kobako.backend.advertisementCopy.presentation;


import kobako.backend.advertisementCopy.application.AdvertisementCopyService;
import kobako.backend.advertisementCopy.dto.request.GenerateAdvertisementCopyRequest;
import kobako.backend.advertisementCopy.dto.request.UpdateAdvertisementCopyRequest;
import kobako.backend.advertisementCopy.dto.response.AdvertisementCopyResponse;
import kobako.backend.advertisementCopy.dto.response.GetRecentAdvertisementCopyResponse;
import kobako.backend.global.domain.RequestUri;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = RequestUri.advertisement)
@RequiredArgsConstructor
public class AdvertisementCopyController {

    private final AdvertisementCopyService advertisementCopyService;


    // 30일간의 광고카피 정보
    /*@GetMapping("/copies/{memberId}")
    public ResponseEntity<List> GetMyAdvertismentCopies(
            @PathVariable Long memberId
    ) {
        advertisementCopyService.
    }*/



    // 모든 광고카피 정보
    @GetMapping("/copies/mypage/{memberId}")
    public ResponseEntity<Slice<GetRecentAdvertisementCopyResponse>> getMyAdvertismentCopies(
            @PathVariable Long memberId
    ) {
        Slice<GetRecentAdvertisementCopyResponse> getRecentAdvertisementCopyResponses
                = advertisementCopyService.getRecentAdvertisementCopy(memberId);

        return ResponseEntity.ok(getRecentAdvertisementCopyResponses);
    }

    /*@GetMapping("/copies")
    public ResponseEntity<List> GetAllAdvertismentCopies(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) Service service,
            @RequestParam(required = false) Tone tone
            ) {

        advertisementCopyService.
    }*/

    //생성
    @PostMapping("/copy")
    public ResponseEntity<AdvertisementCopyResponse> generateAdvertisementCopy(
            @RequestBody GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest
    ) {

        AdvertisementCopyResponse advertisementCopyResponse =  advertisementCopyService.generateAdvertisementCopy(generateAdvertisementCopyRequest);
        return ResponseEntity.ok(advertisementCopyResponse);
    }

    //저장
    @PostMapping("/copy/{advertisementCopyId}")
    public ResponseEntity<AdvertisementCopyResponse> LoadAdvertisementCopy(
            @PathVariable Long memberId,
            @PathVariable Long advertisementCopyId
    ) {
        AdvertisementCopyResponse advertisementCopyResponse
                = advertisementCopyService.loadAdvertisementCopy(memberId, advertisementCopyId);

        return ResponseEntity.ok(advertisementCopyResponse);
    }

    //수정
    @PatchMapping("/copy/{advertisementCopyId}")
    public ResponseEntity<AdvertisementCopyResponse> updateAdvertisementCopy(
            @PathVariable Long advertisementCopyId,
            @RequestBody UpdateAdvertisementCopyRequest updateAdvertisementCopyRequest
    ) {

        AdvertisementCopyResponse advertisementCopyResponse = advertisementCopyService.updateAdvertisementCopy(advertisementCopyId, updateAdvertisementCopyRequest);
        return ResponseEntity.ok(advertisementCopyResponse);
    }

    //삭제
    @DeleteMapping("/copies/{advertisementCopyId}")
    public ResponseEntity deleteAdvertisementCopies(
            @PathVariable Long advertisementCopyId
    ) {
        advertisementCopyService.deleteAdvertisementCopy(advertisementCopyId);
        return ResponseEntity.ok().build();
    }



}
