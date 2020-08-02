package app.service;

import app.entity.XStock;
import app.entity.YResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ApiService {

    @Value("${apiUrl}")
    private String apiUrl;
    @Value("${apiHost}")
    private String apiHost;
    @Value("${apiKey}")
    private String apiKey;

    private RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private HttpHeaders auth() {
        return new HttpHeaders() {{
            add("x-rapidapi-host", apiHost);
            add("x-rapidapi-key", apiKey);
        }};
    }

    private HttpEntity<Object> rqHttpEntity() {
        return new HttpEntity<>(auth());
    }



    public String getFromApi(){
        return restTemplate.exchange(apiUrl,
                HttpMethod.GET, rqHttpEntity(), String.class).getBody();
    }

    public List<XStock> getFromApiWithOwnEntities() {
        ResponseEntity<YResponse> responseEntity = restTemplate.exchange(apiUrl,
                HttpMethod.GET, rqHttpEntity(), YResponse.class);

        return Objects.requireNonNull(responseEntity
                .getBody())
                .getMarketSummaryResponse()
                .getResult()
                .stream()
                .map(el ->
                        new XStock(el.getExchangeTimezoneName(), el.getFullExchangeName(), el.getSymbol())
                )
                .collect(Collectors.toList());
    }
}
