package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Producto;
import service.ProductosService;


@CrossOrigin("*")  //Notacion importante!
@RestController
public class ProductosController {
	
	@Autowired 
	ProductosService service;
	
	/** Un recurso que ante una petición get
	 * @return la lista de productos existentes.
	 */
	
	@GetMapping(value="Productos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Producto>productos(){
		return service.listarProductos();
	}
	
	/** Un recurso que al recibir una petición put, actualiza el stock del producto indicado
	 * En la URL de la petición se reciben el código de producto y unidades compradas
	 * @param codigoProducto del producto
	 * @param unidadesCompradas del producto
	 * @return true si se ha actualizado el producto, false en caso contrario
	 */
	
	/*@PutMapping(value="Productos/{codigoProducto}/{unidadesCompradas}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String actualizar(@PathVariable("codigoProducto") int codigoProducto, 
			@PathVariable("unidadesCompradas") int unidadesCompradas) {
		return String.valueOf(service.actualizarStock(codigoProducto, unidadesCompradas));
	}*/
	
	@PutMapping(value="Productos/{codigoProducto}/{unidadesCompradas}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Void> actualizar(@PathVariable("codigoProducto") int codigoProducto, 
			@PathVariable("unidadesCompradas") int unidadesCompradas) {
		if (service.actualizarStock(codigoProducto, unidadesCompradas)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.PRECONDITION_FAILED);

	}
	
	/** Un recurso que al recibir una petición get con el código del producto devuelva el precio unitario del mismo
	 * @param codigoProducto del producto
	 * @return el precio unitario del producto 
	 */
	@GetMapping(value="Producto/{codigoProducto}", produces = MediaType.APPLICATION_JSON_VALUE)
	public double precioProducto(@PathVariable("codigoProducto") int codigoProducto){
		return service.precioUnitario(codigoProducto);
	}
}
