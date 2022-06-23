package com.careerdevs.weatherapi.controllers;

import com.careerdevs.weatherapi.models.CurrentWeather;
import com.careerdevs.weatherapi.models.CurrentWeatherReport;
import com.careerdevs.weatherapi.validation.WeatherValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("/api/current")
public class CurrentWeatherController {

    @Autowired
    private Environment env;

    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    @GetMapping("/city/{cityName}")
    public ResponseEntity<?> getCurrentWeatherByCityPV (RestTemplate restTemplate,
                                                        @PathVariable String cityName){

        try {
            String units ="imperial";

            HashMap<String ,String> validationErrors = WeatherValidation.validateQuery(cityName,units);

            // if validation fails return error  messages
            if (validationErrors.size()!=0) {
                return  ResponseEntity.badRequest().body(validationErrors);
            }
            String apikey = env.getProperty("OW_API_KEY");
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

            return ResponseEntity.ok(owRes.createReport(units));

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


    //city providence

//    @GetMapping("/")
//    public ResponseEntity<?>  testingRequestParamRQ(
//
//        RestTemplate restTemplate,
//        @RequestParam String city,
//        @RequestParam String units
//        ) {
//        try {
//
//            String apikey = env.getProperty("OW_API_KEY");
//            String queryString = "?q=" + city + "&appid="+ apikey + "&units="+ units;
//            String openWeatherURL = BASE_URL + queryString;
//
//            CurrentWeather owRes = restTemplate.getForObject(openWeatherURL,CurrentWeather.class);
//
//            return ResponseEntity.ok(owRes);
//
//        } catch(Exception e) {
//            return ResponseEntity.internalServerError().body(e.getMessage());
//        }
//    }
//  http:localhost:8080/api/current/city?name=providence&units


    @GetMapping("/city")
    public ResponseEntity<?>  getCurrentWeatherByCityRequestParams(

            RestTemplate restTemplate,
            @RequestParam (value ="name")String cityName,
            @RequestParam (defaultValue = "imperial" ) String units

    ) {
        try {
            HashMap<String ,String> validationErrors = WeatherValidation.validateQuery(cityName,units);

            // if validation fails return error  messages
            if (validationErrors.size()!=0) {
                return  ResponseEntity.badRequest().body(validationErrors);
            }

            String apikey = env.getProperty("OW_API_KEY");
            String queryString = "?q=" + cityName + "&appid="+ apikey + "&units="+ units;
            String openWeatherURL = BASE_URL + queryString;

            CurrentWeather owRes = restTemplate.getForObject(openWeatherURL,CurrentWeather.class);
            assert owRes != null;
            return ResponseEntity.ok(owRes.createReport(units));



        } catch (HttpClientErrorException.NotFound e) {
           return ResponseEntity.status(404).body("City Not Found "+ cityName);

        }catch(Exception e) {

            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
