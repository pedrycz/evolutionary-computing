package pl.edu.agh.toik.ec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import pl.edu.agh.toik.ec.starter.Starter;

// TODO make configuration pluggable again 
@SpringBootApplication
@ImportResource("classpath:pl/edu/agh/toik/ec/configuration/simple_configuration.xml")
public class MainApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext bootContext = SpringApplication.run(MainApp.class, args);
		bootContext.getBean(Starter.class).run();
	}

}
