package pe.edu.pe.grupo2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.pe.grupo2.entities.CentroReciclaje;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ICentroReciclajeRepository extends JpaRepository<CentroReciclaje, Integer> {
    @Query("SELECT acr FROM CentroReciclaje acr WHERE acr.direccion LIKE %:direccion%")
    public List<CentroReciclaje> findByDireccion(@Param("direccion") String direccion);


    @Query(value = "SELECT c.direccion, COUNT(*) AS cantidad_favoritos \n" +
            " FROM centro_reciclaje c \n" +
            " WHERE c.favoritos = TRUE \n" +
            " GROUP BY c.direccion \n" +
            " ORDER BY cantidad_favoritos DESC;", nativeQuery = true)
    public List<String[]> centroPopular();

    @Query(value ="SELECT c.direccion, COUNT(DISTINCT c.id_user) AS cantidadUsuarios " +
            " FROM centro_reciclaje c " +
            " INNER JOIN usuario u ON c.id_user = u.id_user " +
            " GROUP BY c.direccion", nativeQuery = true)
    public List<String[]> centroUsuarios();

}