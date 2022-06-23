package com.careerdevs.weatherapi.models;

import java.util.ArrayList;

public class ForecastReport {

    private final String cityName;
    private final String country;
    private final int population;
    private final  CurrentWeather.Coords coords;
    private final int  reportsCount;
//    private final ForecastReportEntry[] reports;
    private final ArrayList<ForecastReportEntry> reports;



public ForecastReport(Forecast forecast,String units) {
        cityName = forecast.getCity().getName();
        country = forecast.getCity().getCountry();
        population = forecast.getCity().getPopulation();
        this.coords = forecast.getCity().getCoord();
        reportsCount = forecast.getList().length;

//        reports = new ForecastReportEntry[forecast.getList().length];
        reports= new ArrayList<>();

        for (int i = 0; i < forecast.getList().length; i++) {
//            reports[i] = new ForecastReportEntry(forecast.getList()[i]);
            reports.add(new ForecastReportEntry (forecast.getList()[i], units));

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



