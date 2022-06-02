package com.careerdevs.weatherapi.controllers;


import com.careerdevs.weatherapi.models.Forecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/forecast")
public class ForecastController {

    @Autowired
    private Environment env;

    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";

    @GetMapping("/city/{city}")
    public ResponseEntity<?> getForecastByCity (RestTemplate restTemplate, @PathVariable String city){
        try{
          String units ="imperial";
          String apiKey = env.getProperty("OW_API_KEY");
          String queryString = "?q" + city + "&units=" + units + "&appid="+ apiKey;
          String url = BASE_URL + queryString ;

          Forecast owRes = restTemplate.getForObject(url,Forecast.class);

          // generate report

            return ResponseEntity.ok(owRes);


        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return  ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}
