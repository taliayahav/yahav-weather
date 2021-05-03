package yahav.openweathermap;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapService {

    @GET("/data/2.5/weather?appid=c65f21012c4876d2dc360667ec9a4a1b")
    Single<OpenWeatherMapFeed> getCurrentWeather(
            @Query("q") String location,
            @Query("units") String units
    );

    @GET("/data/2.5/forecast?appid=c65f21012c4876d2dc360667ec9a4a1b")
    Single<OpenWeatherMapForecast> getWeatherForecast(
            @Query("q") String location,
            @Query("units") String units
    );


}