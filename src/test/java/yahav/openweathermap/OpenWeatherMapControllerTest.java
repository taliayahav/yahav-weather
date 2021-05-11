package yahav.openweathermap;

import io.reactivex.rxjava3.core.Single;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class OpenWeatherMapControllerTest {
    RadioButton celsius, fahrenheit;
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
        //OpenWeatherMapService service = mock(OpenWeatherMapService.class);
        OpenWeatherMapController controller = new OpenWeatherMapController();
        OpenWeatherMapService service = factory.newInstance();
        celsius = mock(RadioButton.class);
        controller.celsius =  celsius;
        fahrenheit = mock(RadioButton.class);
        controller.fahrenheit = fahrenheit;
        doReturn(true).when(controller.celsius).isSelected();
        //doReturn(Single.never()).when(controller.service).getWeatherForecast("New York","imperial");

        //when
        controller.onSubmit(mock(MouseEvent.class));

        //then
        verify(controller.celsius).isSelected();
        Assert.assertEquals(controller.units,"imperial");
        verify(controller.locationAnswer).getText();
        // verify(controller.service).getWeatherForecast("New York", "imperial");
    }
    @Test
    public void onOpenWeatherMapFeed(){
        //given
        OpenWeatherMapService service = factory.newInstance();
        OpenWeatherMapController controller = new OpenWeatherMapController();
        OpenWeatherMapFeed openWeatherMapFeed = mock(OpenWeatherMapFeed.class);
        openWeatherMapFeed.main = mock(OpenWeatherMapFeed.Main.class);
        openWeatherMapFeed.main.temp = 70.00;
        doReturn("70.00").when(controller.currentWeather).getText();
        doReturn("New York").when(controller.locationAnswer).getText();

        //when
        controller.onOpenWeatherMapFeed(openWeatherMapFeed);

        //then

    }
    @Test
    public void onOpenWeatherMapForecast(){
        //given
        OpenWeatherMapService service = factory.newInstance();
        OpenWeatherMapController controller = new OpenWeatherMapController();
        OpenWeatherMapForecast openWeatherMapForecast = mock(OpenWeatherMapForecast.class);


        //when
        controller.onOpenWeatherMapForecast(openWeatherMapForecast);

        //then


    }
}
