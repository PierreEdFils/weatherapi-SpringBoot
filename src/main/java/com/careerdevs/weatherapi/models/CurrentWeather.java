package com.careerdevs.weatherapi.models;


public class CurrentWeather {



    private  String name;

    private int timezone;

    private int visibility;

    private Coords coord;

    private Main main;

    private Weather[] weather;

    public String getName() {
        return name;
    }

    public int getTimezone() {
        return timezone;
    }

    public int getVisibility() {
        return visibility;
    }

    public Coords getCoord() {
        return coord;
    }

    public Main getMain() {
        return main;
    }

    public Weather[] getWeather() {
        return weather;
    }

//        "weather": [
//        {
//        "id": 804,
//        "main": "Clouds",
//        "description": "overcast clouds",
//        "icon": "04n"
//        }

    public static class Coords {
        private float lon;
        private float lat;

        public float getLon() {
            return lon;
        }

        public float getLat() {
            return lat;
        }
    }

    public static  class Main {

        private float temp;
        private float feels_like;
        private float temp_min;
        private float temp_max;
        private int pressure;
        private float humidity;

        public float getTemp() {
            return temp;
        }

        public float getFeels_like() {
            return feels_like;
        }

        public float getTemp_min() {
            return temp_min;
        }

        public float getTemp_max() {
            return temp_max;
        }

        public int getPressure() {
            return pressure;
        }

        public float getHumidity() {
            return humidity;
        }

        @Override
        public String toString() {
            return "{" +
                    "temp=" + temp +
                    ", feels_like=" + feels_like +
                    ", temp_min=" + temp_min +
                    ", temp_max=" + temp_max +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    '}';
        }
    }

    public static  class Weather {
        //        {
//        "id": 804,
//        "main": "Clouds",
//        "description": "overcast clouds",
//        "icon": "04n"
//        }
        private int id;
        private String main;
        private String description;
        private String icon;

        public int getId() {
            return id;
        }

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }
    }

    public CurrentWeatherReport createReport (String units) {

        return    new CurrentWeatherReport(
                getName(),
                getCoord(),
                getMain(),
                getWeather()[0],
                units
        );

    }
}
