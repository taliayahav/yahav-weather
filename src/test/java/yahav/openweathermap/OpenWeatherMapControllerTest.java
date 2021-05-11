package yahav.openweathermap;

import io.reactivex.rxjava3.core.Single;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class OpenWeatherMapControllerTest {
    RadioButton celsius, fahrenheit;
    TextField locationAnswer;
    OpenWeatherMapServiceFactory factory = new OpenWeatherMapServiceFactory();
    OpenWeatherMapService service = factory.newInstance();

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(() -> {
        });
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
        OpenWeatherMapService service = mock(OpenWeatherMapService.class);
        OpenWeatherMapController controller = new OpenWeatherMapController();
        celsius = mock(RadioButton.class);
        controller.celsius =  celsius;
        fahrenheit = mock(RadioButton.class);
        controller.fahrenheit = fahrenheit;
        doReturn(true).when(controller.celsius).isSelected();
        locationAnswer = mock(TextField.class);
        controller.locationAnswer =  locationAnswer;
        //doReturn(Single.never()).when(controller.service).getWeatherForecast("New York","imperial");

        //when
        controller.onSubmit(mock(MouseEvent.class));

        //then
        verify(controller.celsius).isSelected();
        Assert.assertEquals(controller.units,"metric");
        // verify(controller.service).getWeatherForecast("New York", "imperial");
    }
    @Test
    public void onOpenWeatherMapFeed(){
    }
    @Test
    public void onOpenWeatherMapForecast(){
//


    }
}
