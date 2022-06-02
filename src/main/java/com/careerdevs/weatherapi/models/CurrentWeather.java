package com.careerdevs.weatherapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CurrentWeather  {



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
        private int sea_level;
        private int grnd_level;
        private float humidity;
        private float temp_kf;

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

        public int getSea_level() {
            return sea_level;
        }

        public int getGrnd_level() {
            return grnd_level;
        }

        public float getTemp_kf() {
            return temp_kf;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("{");
            sb.append("\"temp\":").append(temp);
            sb.append(", \"feels_like\":").append(feels_like);
            sb.append(", \"temp_min\":").append(temp_min);
            sb.append(", \"temp_max\":").append(temp_max);
            sb.append(", \"pressure\":").append(pressure);
            sb.append(", \"sea_level\":").append(sea_level);
            sb.append(", \"grnd_level\":").append(grnd_level);
            sb.append(", \"humidity\":").append(humidity);
            sb.append(", \"temp_kf\":").append(temp_kf);
            sb.append('}');
            return sb.toString();
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
}
