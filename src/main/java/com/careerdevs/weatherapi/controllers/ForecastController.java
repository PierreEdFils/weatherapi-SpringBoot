package com.careerdevs.weatherapi.controllers;


import com.careerdevs.weatherapi.models.CurrentWeatherReport;
import com.careerdevs.weatherapi.models.Forecast;
import com.careerdevs.weatherapi.models.ForecastReport;
import com.careerdevs.weatherapi.repositories.ForecastReportRepository;
import com.careerdevs.weatherapi.validation.WeatherValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/forecast")
public class ForecastController {

    @Autowired
    private ForecastReportRepository forecastReportRepo ;

    @Autowired
    private Environment env;

    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";

    @GetMapping("/city/{city}")
    public ResponseEntity<?> getForecastByCityPathVariable(RestTemplate restTemplate, @PathVariable String city) {
        try {
            String units = "imperial";

            HashMap<String ,String> validationErrors = WeatherValidation.validateQuery(city,units);

            // if validation fails return error  messages
            if (validationErrors.size()!=0) {
                return  ResponseEntity.badRequest().body(validationErrors);
            }
            String apiKey = env.getProperty("OW_API_KEY");
            String queryString = "?q=" + city + "&units=" + units + "&appid=" + apiKey;
            String url = BASE_URL + queryString;
//            System.out.println(url);
            Forecast owRes = restTemplate.getForObject(url, Forecast.class);

            // generate report

            assert owRes != null;

            return ResponseEntity.ok(owRes.createReport(units));


        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(404).body("City Not Found " + city);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @GetMapping("/city")
    public ResponseEntity<?> getForecastByCityReqParams(
            RestTemplate restTemplate,
            @RequestParam (value ="name")String city,
            @RequestParam (defaultValue = "imperial" ) String units,
            @RequestParam(defaultValue = "40") String count
    ) {
        try {

            HashMap<String ,String> validationErrors = WeatherValidation.validateQuery(city,units);

            //validate count is a number
            if(!count.replaceAll("[^a-zA-Z -]","").equals(count )){
                validationErrors.put("count", "Count must be a number");
            } else if(Integer.parseInt(count)<1 || Integer.parseInt(count) > 40 ){
                validationErrors.put("count", "Count must be a between 1 -40");
            }

            // if validation fails return error  messages
            if (validationErrors.size()!=0) {
                return  ResponseEntity.badRequest().body(validationErrors);
            }

            String apiKey = env.getProperty("OW_API_KEY");
            String queryString = "?q=" + city + "&units=" + units + "&appid=" + apiKey + "&cnt=" + count ;
            String url = BASE_URL + queryString;

            Forecast owRes = restTemplate.getForObject(url, Forecast.class);

            // generate report
            assert owRes != null;
            return ResponseEntity.ok(owRes.createReport(units));


        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(404).body("City Not Found " + city);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }


    @PostMapping("/city")
    public ResponseEntity<?> uploadForecastByCityReqParams(
            RestTemplate restTemplate,
            @RequestParam (value ="name")String city,
            @RequestParam (defaultValue = "imperial" ) String units,
            @RequestParam(defaultValue = "40") String count
    ) {
        try {

            HashMap<String ,String> validationErrors = WeatherValidation.validateQuery(city,units);

            //validate count is a number
            if(!count.replaceAll("[^0-9]","").equals(count )){
                validationErrors.put("count", "Count must be a number");
            } else if(Integer.parseInt(count)<1 || Integer.parseInt(count) > 40 ){
                validationErrors.put("count", "Count must be a between 1 -40");
            }

            // if validation fails return error  messages
            if (validationErrors.size()!=0) {
                return  ResponseEntity.badRequest().body(validationErrors);
            }

            String apiKey = env.getProperty("OW_API_KEY");
            String queryString = "?q=" + city + "&units=" + units + "&appid=" + apiKey + "&cnt=" + count ;
            String url = BASE_URL + queryString;

            Forecast owRes = restTemplate.getForObject(url, Forecast.class);

            // generate report
            assert owRes != null;


            //upload to the database
           ForecastReport savedReport = forecastReportRepo.save(owRes.createReport(units));

            return ResponseEntity.ok(savedReport);



        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(404).body("City Not Found " + city);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

//    @GetMapping("/")
//    public ResponseEntity<?> testingRequestParamForecastRQ(
//
//            RestTemplate restTemplate,
//            @RequestParam String city,
//            @RequestParam String units
//    ) {
//        try {
//
//            String apiKey = env.getProperty("OW_API_KEY");
//            String queryString = "?q=" + city + "&units=" + units + "&appid=" + apiKey;
//            String url = BASE_URL + queryString;
//
//            Forecast owRes = restTemplate.getForObject(url, Forecast.class);
//
//            return ResponseEntity.ok(owRes);
//
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body(e.getMessage());
//        }
//
//    }

    @GetMapping ("/all")
    public ResponseEntity<?>  getAllForecastWeatherReports() {
        try {
            List<ForecastReport> allReports =forecastReportRepo.findAll();
            return ResponseEntity.ok(allReports);

        }catch(Exception e) {

            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping ("/id/{id}")
    public ResponseEntity<?>  deleteForecastWeatherReportById(@PathVariable String id) {
        try {

            HashMap<String ,String> validationErrors = WeatherValidation.validateQueryId(id);

            // if validation fails return error  messages
            if (validationErrors.size()!=0) {
                return  ResponseEntity.badRequest().body(validationErrors);
            }

            Optional<ForecastReport> currentReport  =forecastReportRepo.findById(Long.parseLong(id));

            if (currentReport.isEmpty()){
                return ResponseEntity.status(404).body("Forecast Report not Found with ID : " + id);
            }

            forecastReportRepo.deleteById(Long.parseLong(id));

            return ResponseEntity.ok(currentReport);

        }catch(Exception e) {

            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping ("/id/{id}")
    public ResponseEntity<?>  getForecastWeatherReportById(@PathVariable String id) {
        try {

            HashMap<String ,String> validationErrors = WeatherValidation.validateQueryId(id);

            // if validation fails return error  messages
            if (validationErrors.size()!=0) {
                return  ResponseEntity.badRequest().body(validationErrors);
            }

            Optional<ForecastReport> forecastReport  =forecastReportRepo.findById(Long.parseLong(id));

            if (forecastReport.isEmpty()){
                return ResponseEntity.status(404).body("Forecast Report not Found with Id : " + id);
            }
            return ResponseEntity.ok(forecastReport);

        }catch(Exception e) {

            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}
