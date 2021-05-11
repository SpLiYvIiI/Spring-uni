package ge.tsu.spring2021.lecture6.weather;


import java.util.List;

public interface WeatherService {
    void add(WeatherView newWeather);
    void update(long id, WeatherView newWeather);
    List<WeatherView> getAll();
    void delete(long id);
    WeatherView getById(long id);
}
