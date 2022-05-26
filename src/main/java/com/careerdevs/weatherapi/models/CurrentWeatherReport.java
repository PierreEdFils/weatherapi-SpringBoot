package com.careerdevs.weatherapi.models;

public class CurrentWeatherReport {


     private String name ;
     private String main;
     private String description ;
     private String units;
     private float temp;
     private float feelsLike;
     private float tempMin;
     private float tempMax;
     private float pressure;
     private float humidity;
     private float lon;
     private float lat;


    public CurrentWeatherReport(
            String name,
            CurrentWeather.Coords coords ,
            CurrentWeather.Main main,
            CurrentWeather.Weather weather,
            String units
    ) {
        this.units = units;
        this.name = name;
        lon = coords.getLon();
        lat = coords.getLat();
        temp = main.getTemp();
        feelsLike = main.getFeels_like();
        tempMin = main.getTemp_min();
        tempMax = main.getTemp_max();
        pressure = main.getPressure();
        humidity = main.getHumidity();
        this.main = weather.getMain();
        description = weather.getDescription();
    }

    public String getName() {
        return name;
    }

    public float getLon() {
        return lon;
    }

    public float getLat() {
        return lat;
    }

    public float getTemp() {
        return temp;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public float getTempMin() {
        return tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public String getMain() {
        return main;
    }

    public String getUnits() {
        return units;
    }

    public String getDescription() {
        return description;


    }

//    @Override
//    public String toString() {
//        return "CurrentWeatherReport{" +
//                "units='" + units + '\'' +
//                ", name='" + name + '\'' +
//                ", lon=" + lon +
//                ", lat=" + lat +
//                ", temp=" + temp +
//                ", feelsLike=" + feelsLike +
//                ", tempMin=" + tempMin +
//                ", tempMax=" + tempMax +
//                ", pressure=" + pressure +
//                ", humidity=" + humidity +
//                ", main='" + main + '\'' +
//                ", description='" + description + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("\"name\":\"").append(name).append('"');
        sb.append(", \"main\":\"").append(main).append('"');
        sb.append(", \"description\":\"").append(description).append('"');
        sb.append(", \"units\":\"").append(units).append('"');
        sb.append(", \"temp\":\"").append(temp).append('°').append((units.equals("imperial") ? "F": "C")).append("\"");
        sb.append(", \"feelsLike\":").append(feelsLike).append('°').append((units.equals("imperial") ? "F": "C")).append("\"");
        sb.append(", \"tempMin\":\"").append(tempMin).append('°').append((units.equals("imperial") ? "F": "C")).append("\"");
        sb.append(", \"tempMax\":\"").append(tempMax).append('°').append((units.equals("imperial") ? "F": "C")).append("\"");
        sb.append(", \"pressure\":").append(pressure);
        sb.append(", \"humidity\":").append(humidity);
        sb.append(", \"lon\":").append(lon);
        sb.append(", \"lat\":").append(lat);
        sb.append('}');
        return sb.toString();
    }
}
