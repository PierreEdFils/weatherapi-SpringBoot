package com.careerdevs.weatherapi.controllers;


import com.careerdevs.weatherapi.models.Forecast;
import com.careerdevs.weatherapi.models.ForecastReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/forecast")
public class ForecastController {

    @Autowired
    private Environment env;

    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";

    @GetMapping("/city/{city}")
    public ResponseEntity<?> getForecastByCity(RestTemplate restTemplate, @PathVariable String city) {
        try {
            String units = "imperial";
            String apiKey = env.getProperty("OW_API_KEY");
            String queryString = "?q=" + city + "&units=" + units + "&appid=" + apiKey;
            String url = BASE_URL + queryString;
//            System.out.println(url);
            Forecast owRes = restTemplate.getForObject(url, Forecast.class);

            // generate report
//            System.out.println("City:  "+ owRes.getCity().getName() + ", " + owRes.getCity().getCountry() + " - Population: " + owRes.getCity().getPopulation());
//            System.out.println("Temp In 3 Hours : "+ owRes.getList()[0].getMain().getTemp());
            assert owRes != null;
            ForecastReport report = new ForecastReport(owRes);

            return ResponseEntity.ok(report);


        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(404).body("City Not Found " + city);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @GetMapping("/")
    public ResponseEntity<?> testingRequestParamForecastRQ(

            RestTemplate restTemplate,
            @RequestParam String city,
            @RequestParam String units
    ) {
        try {

            String apiKey = env.getProperty("OW_API_KEY");
            String queryString = "?q=" + city + "&units=" + units + "&appid=" + apiKey;
            String url = BASE_URL + queryString;

            Forecast owRes = restTemplate.getForObject(url, Forecast.class);

            return ResponseEntity.ok(owRes);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}
