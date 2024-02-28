package kobako.backend.naver.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DataLabService {

    @Value("${naver.datalab.client-id}")
    private String clientId;
}
