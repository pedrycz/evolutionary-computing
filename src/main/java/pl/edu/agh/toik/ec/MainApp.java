package pl.edu.agh.toik.ec;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.edu.agh.toik.ec.starter.Starter;

public class MainApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(args[0]);
        applicationContext.getBean(Starter.class).run();
    }

}
