package activitytest.example.com.coolweather.model;

import java.util.List;

/**
 * Created by lyx on 2016/4/14.
 */
public class Results {


    private String currentCity;
    private String pm25;
    private List<Index> index;
    private List<Weather> weather_data;

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }
    public String getPm25(){return pm25;}

    public String getCurrentCity()
    {
        return currentCity;
    }
    public void setCurrentCity(String currentCity)
    {
        this.currentCity = currentCity;
    }
    public List<Weather> getWeather_data()
    {
        return weather_data;
    }
    public void setWeather_data(List<Weather> weather_data)
    {
        this.weather_data = weather_data;
    }
    public List<Index> getIndex(){return index;}

    public void setIndex(List<Index> index){this.index=index;}
    @Override
    public String toString()
    {
        return "Results [currentCity=" + currentCity + ", weather_data="
                + weather_data + "]";

    }}

