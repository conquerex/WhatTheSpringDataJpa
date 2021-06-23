package what.the.springdatajpa.resttemplate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import what.the.springdatajpa.resttemplate.model.Person;
import what.the.springdatajpa.resttemplate.model.PersonResponse;

@Service
@AllArgsConstructor
@Slf4j
public class RestTemplateTestService {

    private final ApiService<PersonResponse> apiService;

    public PersonResponse callPostExternalServer() {
        Person person = Person.builder()
                .age(22)
                .name("John")
                .build();

        HttpHeaders headers = new HttpHeaders();
        PersonResponse response = apiService
                .post("mockserver_url/testapi/first", headers, person, PersonResponse.class)
                .getBody();
        System.out.println(">>>> response = " + response);
        return response;
    }
}