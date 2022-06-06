package com.careerdevs.weatherapi.models;

import java.util.ArrayList;

public class ForecastReport {

    private final String cityName;
    private final String country;
    private final int population;
//    private final ForecastReportEntry[] reports;
    private final ArrayList<ForecastReportEntry> reports;
    private final  CurrentWeather.Coords coords;


public ForecastReport(Forecast forecast) {
        cityName = forecast.getCity().getName();
        country = forecast.getCity().getCountry();
        population = forecast.getCity().getPopulation();
        this.coords = forecast.getCity().getCoord();

//        reports = new ForecastReportEntry[forecast.getList().length];
        reports= new ArrayList<>();

        for (int i = 0; i < forecast.getList().length; i++) {
//            reports[i] = new ForecastReportEntry(forecast.getList()[i]);
            reports.add(new ForecastReportEntry (forecast.getList()[i]));

        }
    }


    public static class ForecastReportEntry {
        private final String description;
        private final String dateTime;
        private final String temp;
        private final String percentageOfPrecipitation;


        public ForecastReportEntry(Forecast.ForecastWeatherData wd) {
            description = wd.getWeather()[0].getMain() + " - " + wd.getWeather()[0].getDescription();
            dateTime = wd.getDateTime();
            temp = wd.getMain().getTemp() + "°F";
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
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }


    public int getPopulation() {
        return population;
    }

    public CurrentWeather.Coords getCoords() {
        return coords;
    }

    //    public ForecastReportEntry[] getReports() {
//        return reports;
//    }


    public ArrayList<ForecastReportEntry> getReports() {
        return reports;
    }
}



