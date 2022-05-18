package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"service"}) //configuracion de la logica de negocio
public class ServiceConfig {

}
