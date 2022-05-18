package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Pedido;

public interface PedidosDao extends JpaRepository<Pedido,Integer> { //Notacion importante! 
	
	
	/* Un recurso que dará de alta un nuevo pedido al recibir en una petición de tipo post , que incluirá en el cuerpo de la misma los 
	 * datos del pedido. Desde este servicio se realizarán las correspondientes llamadas a los recursos del servicio de 
	 * productos para actualizar el stock. 
	 * 
	 * Un recurso que al recibir una petición get devuelva todos los pedidos registrado */
}
