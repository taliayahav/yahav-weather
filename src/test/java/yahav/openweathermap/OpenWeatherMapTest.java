package yahav.openweathermap;

import io.reactivex.rxjava3.core.Single;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

public class OpenWeatherMapTest {
    @Test
    public void getCurrentWeather(){
        // given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class);

        // when
        Single<OpenWeatherMapFeed> single = service.getCurrentWeather("New York");
        // DO NOT USE BLOCKING GET!
        OpenWeatherMapFeed feed = single.blockingGet();

         //then
        assertNotNull(feed);
        assertNotNull(feed.main);
//        assertNotNull(feed.features.get(0).properties);
//        assertNotNull(feed.features.get(0).properties.place);
        assertTrue(feed.main.temp > 0);
//        assertTrue(feed.features.get(0).properties.time > 0);
//        assertNotNull(feed.features.get(0).geometry);
//        assertNotNull(feed.features.get(0).geometry.coordinates);
//        assertFalse(feed.features.get(0).geometry.coordinates.isEmpty());
    }
}
