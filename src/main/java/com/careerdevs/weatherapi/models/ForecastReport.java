package com.careerdevs.weatherapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class ForecastReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)

    private long id;

    private  String cityName;
    private  String country;
    private  int population;

    private float lon;
    private float lat;
    private  int  reportsCount;
    @ElementCollection
    @OrderColumn()

    private String[] reports;
//   private  ForecastReportEntry[] reports;


//    private  ArrayList<String> reports;

public ForecastReport(){

}

public ForecastReport(Forecast forecast,String units) {
        cityName = forecast.getCity().getName();
        country = forecast.getCity().getCountry();
        population = forecast.getCity().getPopulation();
        lon = forecast.getCity().getCoord().getLon();
        lat = forecast.getCity().getCoord().getLat();
        reportsCount = forecast.getList().length;

      reports = new String[forecast.getList().length];
//        reports= new ArrayList<>();

        for (int i = 0; i < forecast.getList().length; i++) {
            reports[i] = new ForecastReportEntry(forecast.getList()[i],units).toString();
//            reports.add(new ForecastReportEntry (forecast.getList()[i], units).toString());

        }
    }


    public static class ForecastReportEntry {
        private final String description;
        private final String dateTime;
        private final String temp;
        private final String percentageOfPrecipitation;


        public ForecastReportEntry(Forecast.ForecastWeatherData wd, String units) {

            description = wd.getWeather()[0].getMain() + " - " + wd.getWeather()[0].getDescription();
            dateTime = wd.getDateTime();
            temp = wd.getMain().getTemp() + "°" + (units.equals("imperial") ? "F": "C");
            percentageOfPrecipitation = wd.getPop() * 100 + "%";
        }

        public String getDescription() {
            return description;
        }

        public String getDateTime() {
            return dateTime;
        }

        public String getTemp() {
            return temp;
        }

        public String getPercentageOfPrecipitation() {
            return percentageOfPrecipitation;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("{");
            sb.append("\"description\":\"").append(description).append('"');
            sb.append(", \"dateTime\":\"").append(dateTime).append('"');
            sb.append(", \"temp\":\"").append(temp).append('"');
            sb.append(", \"percentageOfPrecipitation\":\"").append(percentageOfPrecipitation).append('"');
            sb.append('}');
            return sb.toString();
        }
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }

    public int getReportsCount(){ return reportsCount;}

    public int getPopulation() {
        return population;
    }


    public long getId() {
        return id;
    }

    public float getLon() {
        return lon;
    }

    public float getLat() {
        return lat;
    }

        public String[] getReports() {
        return reports;
    }


//    public ArrayList<String> getReports() {
//        return reports;
//    }


}



