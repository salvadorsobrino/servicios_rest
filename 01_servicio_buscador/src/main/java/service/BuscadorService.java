package service;
import model.Pagina;

import java.util.List;

public interface BuscadorService {
	List <Pagina> buscar(String tematica);
	void alta(Pagina p);
	List<Pagina>paginas();
	void eliminar(String tematica);
	Pagina actualizar(Pagina pagina);
	void actualizarTematica(String direccion, String nuevaTematica);
}
