package yahav.openweathermap;

import io.reactivex.rxjava3.core.Single;
import javafx.scene.control.RadioButton;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class OpenWeatherMapControllerTest {
    RadioButton celsius, fahrenheit;
    OpenWeatherMapServiceFactory factory = new OpenWeatherMapServiceFactory();

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(() -> {
        });
    }
    private void givenOpenWeatherMapController(){

    }
    @Test
    public void initialize() {
        // given
        OpenWeatherMapService service = factory.newInstance();
        OpenWeatherMapController controller = new OpenWeatherMapController();
        celsius = mock(RadioButton.class);
        controller.celsius =  celsius;
        fahrenheit = mock(RadioButton.class);
        controller.fahrenheit = fahrenheit;

        // when
        controller.initialize();

        // then
        verify(celsius).setToggleGroup(any());
        verify(fahrenheit).setToggleGroup(any());
        verify(fahrenheit).setSelected(true);
    }
    @Test
    public void onSubmit(){
        //given
       //OpenWeatherMapService service = mock(OpenWeatherMapService.class);
    }
    @Test
    public void onOpenWeatherMapFeed(){

    }
    @Test
    public void onOpenWeatherMapForecast(){

    }
}
