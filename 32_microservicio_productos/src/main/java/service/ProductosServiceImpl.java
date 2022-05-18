package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dao.ProductosDao;
import model.Producto;
@Service //Notacion importante ! 
public class ProductosServiceImpl implements ProductosService {
	
	ProductosDao productosDao;
	
	public ProductosServiceImpl(@Autowired ProductosDao productosDao) {
		super();
		this.productosDao = productosDao;
	}

	/**
	 * Método que devuelve la lista de productos existentes
	 */
	@Override
	public List<Producto> listarProductos() {
		//List<T> findAll(Sort sort)
		//Duda con existentes se refiere a stock > 0 o a listar todos los productos que existen aunque su stock sea 0
		return productosDao.findAll()
				.stream()
				.filter(p->p.getStock()>0)
				.collect(Collectors.toList());
	}

	/**
	 * Método que actualiza el stock del producto indicado
	 */
	@Override
	public boolean actualizarStock(int codigoProducto, int unidadesCompradas) {
		Producto p = productosDao.findById(codigoProducto).get(); 
		if (p!=null && p.getStock()>=unidadesCompradas) {
			p.setStock(p.getStock()-unidadesCompradas);
			productosDao.save(p);
			return true;
		}
		return false;

	}

	/**
	 * Método que devuelve el precio unitario
	 */
	@Override
	public double precioUnitario(int codigoProducto) {
		//Optional<T> findById(ID id)
		Producto p = productosDao.findById(codigoProducto).get();
		if(p!=null) {
			return p.getPrecioUnitario();
		}
		return 0;
	}

}
