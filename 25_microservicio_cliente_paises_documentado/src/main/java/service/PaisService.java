package service;
import java.util.List;
import model.Pais;

public interface PaisService {
	//Lista de continentes
	List<String> continentes();
	//Paises por continente. Recibe una petición get con el nombre de un continente, y devuelve los paises del mismo
	List<Pais> paisesPorContinente(String continente);
	//Población continente. Recibe una peticion get con el nombre de un continente, y devuelve el total de habitantes de dicho continente
	Long poblacionContinente(String continente);

}
