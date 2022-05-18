package init;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*auth
        .inMemoryAuthentication() //usuarios en memoria
        .withUser("admin")
          .password("{noop}admin") //lo de {noop} se pone para no obligar a usar mecanismo de encriptación
          .roles("ADMIN");*/
		
		auth.jdbcAuthentication()
    	.passwordEncoder(new BCryptPasswordEncoder()) //contraseñas codificas en bcrypt
    	.dataSource(dataSource())
    	.usersByUsernameQuery("select user, pwd, enabled"
        	+ " from users where user=?")
    	.authoritiesByUsernameQuery("select user, rol "
        	+ "from roles where user=?");
		
		
		

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		//solo los miembros del rol admin podrán realizar altas
		//y para acceder la lista de cursos, tendrán que estar autenticados
		.antMatchers(HttpMethod.GET,"/Reserva").hasRole("ADMIN")
		.and() 
		.httpBasic(); //manera en la que se solicita los credenciales. 
		
		//ACCESO LIBRE. SIN USUARIO NI CONTRASEÑA.
		//Lo que NO este ahí arriba se puede acceder libremente.
		
		
		
	}
	
	private DataSource dataSource() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/springsecurity?serverTimezone=UTC");
		ds.setUsername("root");
		ds.setPassword("");
		return ds;
	}

}

