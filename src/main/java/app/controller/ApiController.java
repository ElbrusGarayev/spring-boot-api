package app.controller;

import app.entity.XStock;
import app.service.ApiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class ApiController {

    private final ApiService apiService;

    @GetMapping("body")
    public String getBody() {
        return apiService.getFromApi();
    }

    @GetMapping
    public List<XStock> getEntity(){
        return apiService.getFromApiWithOwnEntities();
    }
}
