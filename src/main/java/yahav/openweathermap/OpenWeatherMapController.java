package yahav.openweathermap;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class OpenWeatherMapController {
    @FXML
    TextField locationAnswer;
    @FXML
    RadioButton celsius, fahrenheit;
    @FXML
    Label weatherForecast, currentWeather;
    @FXML
    ImageView currentIconImage;
    @FXML
    ArrayList<Label> days;
    @FXML
    ArrayList<ImageView> icons;
    @FXML
    ArrayList<Label> temperature;
    String units;

    @FXML
    public void initialize(){
        ToggleGroup group = new ToggleGroup();
        fahrenheit.setToggleGroup(group);
        celsius.setToggleGroup(group);
        fahrenheit.setSelected(true);
    }

    public void onSubmit(MouseEvent mouseEvent) {
        if (celsius.isSelected()) {
            units = "metric";
        }
        else {
            units = "imperial";
        }
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

    public void onOpenWeatherMapFeed(OpenWeatherMapFeed openWeatherMapFeed) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                currentWeather.setText(String.valueOf(openWeatherMapFeed.main.temp));
                weatherForecast.setText("Current Weather");
                currentIconImage.setImage(new Image(openWeatherMapFeed.weather.get(0).getIconUrl()));
            }
        });
    }

    public void onOpenWeatherMapForecast(OpenWeatherMapForecast openWeatherMapForecast) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < days.size(); i++){
                    days.get(i).setText(openWeatherMapForecast.getForcastFor(i).getDate() + "");
                    temperature.get(i).setText(openWeatherMapForecast.getForcastFor(i).main.temp + "");
                    icons.get(i).setImage(new Image(openWeatherMapForecast.getForcastFor(i).weather.get(0).getIconUrl()));
                }
            }
        });
    }
        private void onError(Throwable throwable) {
            System.out.println("error in retrieving weather data");
    }
}