package kobako.backend.advertisementCopy.presentation;


import kobako.backend.advertisementCopy.application.AdvertisementCopyService;
import kobako.backend.advertisementCopy.dto.request.GenerateAdvertisementCopyRequest;
import kobako.backend.advertisementCopy.dto.request.UpdateAdvertisementCopyRequest;
import kobako.backend.advertisementCopy.dto.response.AdvertisementCopyResponse;
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




    // 최근 생성한 광고카피
    @GetMapping("/copies/recent/{memberId}")
    public ResponseEntity<List<AdvertisementCopyResponse>> getMyAdvertisementCopies(
            @PathVariable Long memberId
    ) {
        List<AdvertisementCopyResponse> advertisementCopyResponses
                = advertisementCopyService.getRecentAdvertisementCopy(memberId);

        return ResponseEntity.ok(advertisementCopyResponses);
    }


    //생성
    @PostMapping("/copies")
    public void generateAdvertisementCopy(
            @RequestBody GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest
    ) {
        //AdvertisementCopyResponse advertisementCopyResponse =
        advertisementCopyService.generateAdvertisementCopy(generateAdvertisementCopyRequest);
        //return ResponseEntity.ok(advertisementCopyResponse);
    }


    //저장
    @PostMapping("/copies/{memberId}/{advertisementCopyId}")
    public ResponseEntity<AdvertisementCopyResponse> LoadAdvertisementCopy(
            @PathVariable Long memberId,
            @PathVariable Long advertisementCopyId
    ) {
        AdvertisementCopyResponse advertisementCopyResponse
                = advertisementCopyService.loadAdvertisementCopy(memberId, advertisementCopyId);

        return ResponseEntity.ok(advertisementCopyResponse);
    }


    //수정
    @PatchMapping("/copies/{advertisementCopyId}")
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
