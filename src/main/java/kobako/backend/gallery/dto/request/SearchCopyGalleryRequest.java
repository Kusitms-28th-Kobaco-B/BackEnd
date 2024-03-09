package kobako.backend.gallery.dto.request;

import kobako.backend.global.enums.Service;
import kobako.backend.global.enums.Tone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCopyGalleryRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private Service service;
    private Tone tone;
    private List<String> keywords;
}

