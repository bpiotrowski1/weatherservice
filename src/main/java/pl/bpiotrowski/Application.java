package pl.bpiotrowski;

import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import pl.bpiotrowski.ui.UserInterface;

@Configuration
@ComponentScan("pl.bpiotrowski")
@PropertySource("application.properties")
public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Gson.class, Application.class);
        context.getBean(UserInterface.class).start();
    }
}
