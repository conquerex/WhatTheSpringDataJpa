package what.the.springdatajpa.resttemplate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import what.the.springdatajpa.resttemplate.model.PersonResponse;

@RestController
@AllArgsConstructor
@Slf4j
public class RestTemplateTestController {

    private final RestTemplateTestService restTemplateTestService;

    @PostMapping
    public ResponseEntity<PersonResponse> restTemplateTest1() {
        return ResponseEntity.ok(restTemplateTestService.callPostExternalServer());
    }
}