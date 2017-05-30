package pl.edu.agh.toik.ec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import pl.edu.agh.toik.ec.starter.Starter;

@SpringBootApplication
public class MainApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext bootContext = SpringApplication.run(new Object[] {
                MainApp.class, "classpath:" + args[0]}, args);
		bootContext.getBean(Starter.class).run();
	}

}
