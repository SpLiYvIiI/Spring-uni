package ge.tsu.spring2021.lecture6.weather;

import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService{

    @Autowired
    private DSLContext dls;

    @Override
    public void add(WeatherView newWeather) {
        dls.insertInto(Tables.WEATHER,
                Tables.WEATHER.COUNTRY,
                Tables.WEATHER.CITY,
                Tables.WEATHER.TEMPERATURE,
                Tables.WEATHER.WINDY)
                .values(newWeather.getCountry(),
                        newWeather.getCity(),
                        newWeather.getTemperature(),
                        newWeather.isWindy())
                .execute();
    }

    @Override
    public void update(long id, WeatherView newWeather) {
        dls.update(Tables.WEATHER)
                .set(Tables.WEATHER.COUNTRY, newWeather.getCountry())
                .set(Tables.WEATHER.CITY, newWeather.getCity())
                .set(Tables.WEATHER.TEMPERATURE, newWeather.getTemperature())
                .set(Tables.WEATHER.WINDY, newWeather.isWindy())
                .where(Tables.WEATHER.ID.equal(id))
                .execute();
    }

    @Override
    public List<WeatherView> getAll() {
        return dls.selectFrom(Tables.WEATHER)
                .fetch()
                .into(WeatherView.class);
    }

    @Override
    public void delete(long id) {
        dls.delete(Tables.WEATHER).where(Tables.WEATHER.ID.equal(id)).execute();
    }

    @Override
    public WeatherView getById(long id) {
        return dls.selectFrom(Tables.WEATHER)
                .where(Tables.WEATHER.ID.equal(id))
                .fetchOne()
                .into(WeatherView.class);
    }
}
