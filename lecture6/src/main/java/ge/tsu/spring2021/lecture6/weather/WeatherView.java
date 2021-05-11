package ge.tsu.spring2021.lecture6.weather;

public class WeatherView {
    private long id;
    private String country;
    private String city;
    private int temperature;
    private boolean windy;

    public WeatherView(){

    }
    public WeatherView(String country, String city, int temperature, boolean windy) {
        this.country = country;
        this.city = city;
        this.temperature = temperature;
        this.windy = windy;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setWindy(boolean windy) {
        this.windy = windy;
    }

    public long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public int getTemperature() {
        return temperature;
    }

    public boolean isWindy() {
        return windy;
    }
}
