package service;

import java.util.List;

import model.Producto;

public interface ProductosService {
	/*
	 * Un recurso que ante una petición get, devuelve la lista de productos existentes. 
	 * 
	 * Un recurso que al recibir una petición put, actualiza el stock del producto indicado. 
	 * En la URL de la petición se reciben el código de producto y unidades compradas
	 * 
	 * Un recurso que al recibir una petición get con el código del producto devuelva el precio unitario del mismo
	 */
	
	List<Producto> listarProductos(); 
	boolean actualizarStock(int codigoProducto, int unidadesCompradas);
	double precioUnitario(int codigoProducto);
}

