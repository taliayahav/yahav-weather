package yahav.openweathermap;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapService {
    @GET("/data/2.5/weather?APPID=1ec81aa1fd5f3eafc25f0856564b71b5")
    Single<OpenWeatherMapFeed> getCurrentWeather(@Query("q") String location);
}
