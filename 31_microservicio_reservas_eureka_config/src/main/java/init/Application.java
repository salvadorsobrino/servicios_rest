package init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"service", "controller", "init"}) //init debido a clase de configuracion 
@EnableJpaRepositories(basePackages = {"dao"})
@EntityScan(basePackages = {"model"})
//No hace falta crear DataSource, adaptador, factory, gestor de transacciones... (ServiceConfig) en el properties.

//spring.jpa.hibernate.naming.implicit-strategy=org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
//spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
//Soluciona los _ en los atributos cuando hay mayusculas.
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
