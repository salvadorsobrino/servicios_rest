package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Producto;

public interface ProductosDao extends JpaRepository<Producto,Integer> { //Notacion importante! 
	
	/*
	 * Un recurso que ante una petición get, devuelve la lista de productos existentes. 
	 * 
	 * Un recurso que al recibir una petición put, actualiza el stock del producto indicado. 
	 * En la URL de la petición se reciben el código de producto y unidades compradas
	 * 
	 * Un recurso que al recibir una petición get con el código del producto devuelva el precio unitario del mismo
	 */
	
	/*@Query("select p.precio from Producto p where p.codigo=?1")
	double precioProducto(int codigoProducto);
	
	@Query("select avg(p.precio) from Producto p")
	double precioMedio();
	
	@Query("select p.precio, p.stock from Producto p") No recomendable , tomas partes de la entidad.
	Object[] datos();
	
	@Query(value="select precio, stock from productos",nativeQuery=true) No recomendable, usa SQL puro
	Object[] info();*/

}
