package service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import dao.PedidosDao;
import model.Pedido;

@Service // Notacion importante !
public class PedidosServiceImpl implements PedidosService {

	PedidosDao pedidosDao;

	RestTemplate template; // para poder interactuar con el exterior
	// String urlBase = "http://servicio-productos/productos";
	String urlBase = "http://servicio-productos/"; // quitamos context-path en el config

	public PedidosServiceImpl(@Autowired PedidosDao pedidosDao, @Autowired RestTemplate template) {
		super();
		this.pedidosDao = pedidosDao;
		this.template = template;
	}

	/**
	 * Un recurso que dará de alta un nuevo pedido al recibir en una petición de
	 * tipo post , que incluirá en el cuerpo de la misma los datos del pedido. Desde
	 * este servicio se realizarán las correspondientes llamadas a los recursos del
	 * servicio de productos para actualizar el stock.
	 * 
	 */
	@Override
	public boolean altaPedido(Pedido pedido) {
		/*
		 * Exchange solución al void de template.put(). Permite realizar cualquier tipo
		 * de peticion Le das la url, metodo que vas a usar,cuerpo+cabecera,tipo de la
		 * respuesta, variables en url. el resultado es un response entity, tenemos
		 * cuerpo + cabecera
		 */

		// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html

		// https://developer.mozilla.org/es/docs/Web/HTTP/Status

		// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html
		// Programacion reactiva: https://javadoc.io/doc/io.projectreactor/reactor-core/latest/index.html

		try {
			ResponseEntity<Double> responseProductoPrecio = template.exchange(urlBase + "/Producto/{codigoProducto}",
					HttpMethod.GET, // tipo de peticion
					null, Double.class, pedido.getCodigoProducto());

			double cuerpoPrecioUnitario = responseProductoPrecio.getBody();

			ResponseEntity<String> response = template.exchange(
					urlBase + "/Productos/{codigoProducto}/{unidadesCompradas}", HttpMethod.PUT, // tipo de peticion
					null, // new HttpEntity(dato_cuerpo) nada en el cuerpo
					String.class, // lo que va a devolver
					pedido.getCodigoProducto(), pedido.getUnidades()); // valores de los parametros, evitar
																		// concatenaciones

			// no tiene por que llamarse igual que los placeholders del put {codigoProducto}

			// Solo guardamos el pedido si se ha actualizado el stock del producto
			// String cuerpo = response.getBody(); //Esto es chapucero mejor con try catch
			// if (cuerpo.equals("true")) {
			pedido.setTotal(cuerpoPrecioUnitario * pedido.getUnidades());
			pedido.setFechaPedido(new Date());
			pedidosDao.save(pedido);
			return true;

			// }
		} catch (HttpClientErrorException ex) {
			return false;
		}

	}

	/**
	 * Un recurso que al recibir una petición get devuelva todos los pedidos
	 * registrado
	 */
	@Override
	public List<Pedido> listarPedidos() {
		return pedidosDao.findAll();
	}

}
