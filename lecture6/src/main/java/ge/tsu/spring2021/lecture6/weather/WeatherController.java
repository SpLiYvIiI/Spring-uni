package ge.tsu.spring2021.lecture6.weather;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/api/weather")
    List<WeatherView> getAll(){
        return weatherService.getAll();
    }
    @GetMapping("/api/weather/{id}")
    WeatherView getById(@PathVariable long id){
        return weatherService.getById(id);
    }
    @PostMapping("/api/weather")
    void add(@RequestBody WeatherView newWeather){
        weatherService.add(newWeather);
    }
    @PutMapping("/api/weather/{id}")
    void update(@PathVariable long id,@RequestBody WeatherView newWeather){
        weatherService.update(id,newWeather);
    }
    @DeleteMapping("/api/weather/{id}")
    void delete(@PathVariable long id){
        weatherService.delete(id);
    }


}
