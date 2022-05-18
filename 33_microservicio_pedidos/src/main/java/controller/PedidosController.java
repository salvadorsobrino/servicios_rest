package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Pedido;
import service.PedidosService;


@CrossOrigin("*")  //Notacion importante!
@RestController
public class PedidosController {
	
	@Autowired 
	PedidosService service;
	
	/* Un recurso que dará de alta un nuevo pedido al recibir en una petición de tipo post , que incluirá en el cuerpo de la misma los 
	 * datos del pedido. Desde este servicio se realizarán las correspondientes llamadas a los recursos del servicio de 
	 * productos para actualizar el stock. 
	 * 
	 * Un recurso que al recibir una petición get devuelva todos los pedidos registrado */
	
	@PostMapping(value="Pedido",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> nuevoPedido(@RequestBody Pedido pedido) {
		if(service.altaPedido(pedido)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
	
	/** Un recurso que al recibir una petición get devuelva todos los pedidos registrado 
	 * @return lista de Pedidos,
	 */
	@GetMapping(value="Pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pedido>> pedidos(){
		//ResponseEntity(T body, HttpStatus status)
		return new ResponseEntity<>(service.listarPedidos(),HttpStatus.OK);
	}

}
