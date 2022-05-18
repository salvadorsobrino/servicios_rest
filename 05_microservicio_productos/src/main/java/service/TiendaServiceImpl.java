package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.ProductosDao;
import model.Producto;

@Service
public class TiendaServiceImpl implements TiendaService {

	
	@Autowired
	ProductosDao productosDao;

	public TiendaServiceImpl(@Autowired ProductosDao productosDao) {
		super();
		this.productosDao = productosDao;
	}

	@Override
	public void alta(Producto p) {
		productosDao.save(p);
		
	}

	@Override
	public void eliminar(int idProducto) {
		Producto p = buscarProducto(idProducto);
		if(p!=null) {
			productosDao.deleteById(idProducto);
		}
	}


	@Override
	public List<Producto> buscar(String seccion) {
		return productosDao.findAll()
				.stream()
				.filter(p->p.getSeccion().equals(seccion))
				.collect(Collectors.toList());
	}

	@Override
	public Producto buscarProducto(int idProducto) {
		return productosDao.findById(idProducto).orElse(null);
	}


	@Override
	public List<Producto> todosProductos() {
		return productosDao.findAll();
	}
	
	
}
