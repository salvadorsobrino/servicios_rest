package init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"service", "controller"})
@EnableJpaRepositories(basePackages = {"dao"})
@EntityScan(basePackages = {"model"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@LoadBalanced //Descubrimiento de servicios
	//El método de creación RestTemplate anotado con @LoadBalanced para activar Ribbon
	//Acceso mediante el nombre de registro: String URL_SERVICE="http://servicio-ciudades";
	@Bean
	public RestTemplate template() { //crear el objeto que luego necesitaremos
		return new RestTemplate();
	}

}
