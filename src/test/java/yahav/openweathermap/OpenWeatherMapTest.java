package yahav.openweathermap;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

public class OpenWeatherMapTest {

    OpenWeatherMapServiceFactory factory = new OpenWeatherMapServiceFactory();

    @Test
    public void getCurrentWeather() {
        // given
        OpenWeatherMapService service = factory.newInstance();

        // when
        OpenWeatherMapFeed feed = service.getCurrentWeather("New York", "imperial")
                .blockingGet();

        // then
        assertNotNull(feed);
        assertNotNull(feed.main);
        assertTrue(feed.main.temp > 0);
        assertTrue(feed.main.temp < 150);
        assertTrue(feed.dt > 0);
        assertEquals("New York", feed.name);
    }

    @Test
    public void getWeatherForecast() {
        // given
        OpenWeatherMapService service = factory.newInstance();

        // when
        OpenWeatherMapForecast forecast = service.getWeatherForecast("New York", "imperial")
                .blockingGet();

        // then
        assertNotNull(forecast);
        assertNotNull(forecast.list);
        assertFalse(forecast.list.isEmpty());
        assertTrue(forecast.list.get(0).dt > 0);
        assertNotNull(forecast.list.get(0).weather);
    }

    @Test
    public void getWeatherForecast_getForecastFor() {
        // given
        OpenWeatherMapService service = factory.newInstance();
        OpenWeatherMapForecast forecast = service.getWeatherForecast("New York", "imperial")
                .blockingGet();

        // when
        OpenWeatherMapForecast.HourlyForecast hourlyForecast = forecast.getForcastFor(1);

        // then
        assertNotNull(hourlyForecast);
        assertEquals(13, hourlyForecast.getDate().getHours());
    }

}