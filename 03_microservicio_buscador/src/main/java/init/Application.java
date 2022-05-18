package init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"service" , "controller"})
//Añadimos los paquetes a escanear equivalantes a ServiceConfig y MvcConfig
public class Application {
//https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
