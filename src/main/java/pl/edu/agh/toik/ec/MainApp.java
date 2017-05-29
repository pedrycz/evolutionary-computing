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

		// ApplicationContext applicationContext = new
		// ClassPathXmlApplicationContext(args[0]);
		// bootContext.setParent(applicationContext);
		bootContext.getBean(Starter.class).run();
		// applicationContext.getBean(Starter.class).run();

		/*
		 * ConfigurableApplicationContext ctx = new SpringApplicationBuilder()
		 * .parent(MainApp.class) //.child(new Object[]{args[0]})
		 * //.child(BootConfiguration.class) .application() .run(args);
		 */
		// ctx.getBean(Starter.class).run();
		// SpringApplication app = new SpringApplication();
		/*
		 * app.addInitializers(new ApplicationContextInitializer() {
		 * 
		 * @Override public void initialize(ConfigurableApplicationContext
		 * applicationContext) { //TODO
		 * 
		 * }});
		 */
	}

}
