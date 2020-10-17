import config.BotConfig;
import exceptions.IncorrectCityNameException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.impl.WeatherServiceImpl;

public class BotMainApp {
    public static void main(String[] args) {
        var annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BotConfig.class);
        var weatherService = annotationConfigApplicationContext.getBean(WeatherServiceImpl.class);

        try {
            System.out.println(weatherService.getByCityName("Львів"));
        } catch (IncorrectCityNameException e) {
            System.out.println(e.getMessage());
        }
    }
}
