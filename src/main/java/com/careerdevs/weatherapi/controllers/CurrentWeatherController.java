package com.careerdevs.weatherapi.controllers;

import com.careerdevs.weatherapi.models.CurrentWeather;
import com.careerdevs.weatherapi.models.CurrentWeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/current")
public class CurrentWeatherController {

    @Autowired
    private Environment env;

    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    @GetMapping("/city/{cityName}")
    public ResponseEntity<?> getCurrentWeatherByCityPV (RestTemplate restTemplate, @PathVariable String cityName){

        try {
            String units ="imperial";
            String apikey = env.getProperty("OW_API_kEY");
            String queryString = "?q=" + cityName + "&appid="+ apikey + "&units="+ units;
            String openWeatherURL = BASE_URL + queryString;

            CurrentWeather owRes = restTemplate.getForObject(openWeatherURL,CurrentWeather.class);

//            System.out.println("°");

            assert owRes != null;
//            System.out.println("City: " + openWeatherResponse.getName());
//            System.out.println("Current Temp: " + openWeatherResponse.getMain().getTemp()+ " F");
//            System.out.println("Feels Like Temp: " + openWeatherResponse.getMain().getFeels_like()+ " F");
//            System.out.println("Max Temp: " + openWeatherResponse.getMain().getTemp_max());
//            System.out.println("Min Temp: " + openWeatherResponse.getMain().getTemp_min());
//            System.out.println("Coordinates ()lat : " + openWeatherResponse.getCoord().getLat());
//            System.out.println("Coordinates ()lon : " + openWeatherResponse.getCoord().getLon());
//            System.out.println("Weather Description: " + openWeatherResponse.getWeather()[0].getDescription());
            CurrentWeatherReport report = new CurrentWeatherReport(
                    owRes.getName(),
                    owRes.getCoord(),
                    owRes.getMain(),
                    owRes.getWeather()[0],
                    units
            );

            System.out.println(report);

            return ResponseEntity.ok(report);

        } catch (HttpClientErrorException.NotFound e){
            return ResponseEntity.status(404).body("City Not Found "+ cityName);

        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass());

            return ResponseEntity.internalServerError().body(e.getMessage());
        }


    }
//    @GetMapping("/city")
//    public ResponseEntity<?> getCurrentWeatherByCityRP (RestTemplate restTemplate, @RequestParam String cityName){
//
//        try {
//
//            String apikey = env.getProperty("OW_API_kEY");
//            String queryString = "?q=" + cityName + "&appid="+ apikey + "&units=imperial";
//            String openWeatherURL = BASE_URL + queryString;
//
//            CurrentWeather openWeatherResponse = restTemplate.getForObject(openWeatherURL,CurrentWeather.class);
//
//            return ResponseEntity.ok(openWeatherResponse);
//
//        } catch (HttpClientErrorException.NotFound e){
//            return ResponseEntity.status(404).body("City Not Found "+ cityName);
//
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//            System.out.println(e.getClass());
//
//            return ResponseEntity.internalServerError().body(e.getMessage());
//        }
//
//
//    }

//        @GetMapping("/city")
//    public ResponseEntity<?> getCurrentWeatherByCityRP (RestTemplate restTemplate, @RequestParam String cityName){
//
//        try {
//            String units ="imperial";
//            String apikey = env.getProperty("OW_API_kEY");
//            String queryString = "?q=" + cityName + "&appid="+ apikey + "&units=imperial";
//            String openWeatherURL = BASE_URL + queryString;
//
//            CurrentWeather owRes = restTemplate.getForObject(openWeatherURL,CurrentWeather.class);
//            CurrentWeatherReport report = new CurrentWeatherReport(
//                    owRes.getName(),
//                    owRes.getCoord(),
//                    owRes.getMain(),
//                    owRes.getWeather()[0],
//                    units
//            );
//
//            System.out.println(report);
//
//
//            return ResponseEntity.ok(report);
//
//        } catch (HttpClientErrorException.NotFound e){
//            return ResponseEntity.status(404).body("City Not Found "+ cityName);
//
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//            System.out.println(e.getClass());
//
//            return ResponseEntity.internalServerError().body(e.getMessage());
//        }
//
//
//    }

}
