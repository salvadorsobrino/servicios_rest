package service;

import java.util.List;

import model.Producto;

//CRUD de Producto
public interface TiendaService {
	void alta(Producto p);
	void eliminar(int idProducto);
	List <Producto> buscar(String seccion);
	Producto buscarProducto(int idProducto);
	
	List <Producto> todosProductos();
}
