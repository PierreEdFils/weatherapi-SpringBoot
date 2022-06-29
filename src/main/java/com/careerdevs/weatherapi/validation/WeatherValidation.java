package com.careerdevs.weatherapi.validation;

import java.util.HashMap;

public class WeatherValidation {

  public static HashMap<String,String> validateQuery(String city,String units) {
      HashMap<String ,String> validationErrors =new HashMap<>();

      //validation-name
      // name cant be blank
      if (city.trim().equals("")) {
          validationErrors.put("city","City name required");

      }else if (
              !city.replaceAll("[^a-zA-Z -]","").equals(city)
      ) {

          // name should not include special chars/numbers
          validationErrors.put("city","Invalid City Name");
      }

      //validation-units
      // is it metric or imperial
      if(!units.equals("metrics") && !units.equals("imperial") ){
          validationErrors.put("units","Units must be metrics OR imperial");
      }

      return validationErrors;
  }

    public static HashMap<String,String> validateQueryId (String id) {
        HashMap<String ,String> validationErrors =new HashMap<>();

        //validation-id(makes sure it is a long )
       try {
           Long.parseLong(id);

       }catch (NumberFormatException e){
           validationErrors.put("id","Id is invalid");
       }


        return validationErrors;
    }

}
