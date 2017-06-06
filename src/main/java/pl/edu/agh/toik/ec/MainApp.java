package pl.edu.agh.toik.ec;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import pl.edu.agh.toik.ec.starter.Starter;

@SpringBootApplication
public class MainApp {
	private static final Logger LOG = Logger.getLogger(MainApp.class);

	public static void main(String[] args) {
		if (args.length < 1) {
			LOG.error("No configuration supplied.\n"
					+ "Please run the program again with configuration path supplied.\n"
					+ "For example: gradlew bootrun -PappArgs=\"['pl/edu/agh/toik/ec/configuration/simple_configuration.xml']\"");
		} else {
			ConfigurableApplicationContext bootContext = SpringApplication
					.run(new Object[] { MainApp.class, "classpath:" + args[0] }, args);
			bootContext.getBean(Starter.class).run();
		}
	}

}
