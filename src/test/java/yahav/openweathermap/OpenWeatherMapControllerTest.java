package yahav.openweathermap;

import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import org.junit.Assert;
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
        //OpenWeatherMapService service = factory.newInstance();
        OpenWeatherMapService service = mock(OpenWeatherMapService.class);
        OpenWeatherMapController controller = new OpenWeatherMapController();

        //when
        controller.onSubmit(mock(MouseEvent.class));

        //then
        verify(controller.celsius).isSelected();
        Assert.assertEquals(controller.units,"imperial");
        // verify(controller.service).getWeatherForecast("New York", "imperial");
    }
    @Test
    public void onOpenWeatherMapFeed(){
        //given
        OpenWeatherMapController controller = new OpenWeatherMapController();
        OpenWeatherMapFeed openWeatherMapFeed = mock(OpenWeatherMapFeed.class);

        //when
        controller.onOpenWeatherMapFeed(openWeatherMapFeed);

        //then
    }
    @Test
    public void onOpenWeatherMapForecast(){

    }
}
