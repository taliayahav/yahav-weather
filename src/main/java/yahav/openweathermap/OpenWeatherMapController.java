package yahav.openweathermap;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.util.ArrayList;

public class OpenWeatherMapController {
    @FXML
    GridPane gridPane;
    @FXML
    TextField locationAnswer;
    @FXML
    RadioButton Celsius, Fahrenheit;
    @FXML
    Label weatherForecast, currentWeather;
    @FXML
    ImageView currentIconImage;
    @FXML
    ArrayList<Label> Days;
    @FXML
    ArrayList<ImageView> Icons;
    @FXML
    ArrayList<Label> Temperature;

    @FXML
    public void initialize(){
        ToggleGroup group = new ToggleGroup();
        Fahrenheit.setToggleGroup(group);
        Celsius.setToggleGroup(group);
        Fahrenheit.setSelected(true);
    }

    public void onSubmit(MouseEvent mouseEvent) {
        String units = Celsius.isSelected() ? "metric" : "imperial";
        OpenWeatherMapServiceFactory factory = new OpenWeatherMapServiceFactory();
        OpenWeatherMapService service = factory.newInstance();

        Disposable disposable = service.getWeatherForecast(locationAnswer.getText(), units)
                // request the data in the background
                .subscribeOn(Schedulers.io())
                // work with the data in the foreground
                .observeOn(Schedulers.trampoline())
                // work with the feed whenever it gets downloaded
                .subscribe(this::onOpenWeatherMapForecast, this::onError);

        Disposable currentDisposable = service.getCurrentWeather(locationAnswer.getText(), units)
                // request the data in the background
                .subscribeOn(Schedulers.io())
                // work with the data in the foreground
                .observeOn(Schedulers.trampoline())
                // work with the feed whenever it gets downloaded
                .subscribe(this::onOpenWeatherMapFeed, this::onError);
    }

    private void onOpenWeatherMapFeed(OpenWeatherMapFeed openWeatherMapFeed) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                currentWeather.setText(String.valueOf(openWeatherMapFeed.main.temp));
                weatherForecast.setText("Current Weather");
                currentIconImage.setImage(new Image(openWeatherMapFeed.weather.get(0).getIconUrl()));
            }
        });
    }

    private void onOpenWeatherMapForecast(OpenWeatherMapForecast openWeatherMapForecast) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <6; i++){
                    Days.get(i).setText(openWeatherMapForecast.getForcastFor(i).getDate() + "");
                    Temperature.get(i).setText(openWeatherMapForecast.getForcastFor(i).main.temp + "");
                    Icons.get(i).setImage(new Image(openWeatherMapForecast.getForcastFor(i).weather.get(0).getIconUrl()));
                }
            }
        });
    }
        private void onError(Throwable throwable) {
            System.out.println("error in retrieving weather data");
    }
}