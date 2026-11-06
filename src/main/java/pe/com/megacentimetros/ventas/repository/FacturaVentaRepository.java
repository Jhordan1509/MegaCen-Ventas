package pe.com.megacentimetros.ventas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.megacentimetros.ventas.entity.FacturaVentaEntity;

public interface FacturaVentaRepository extends JpaRepository<FacturaVentaEntity, Long> {

	@Query("select d from FacturaVentaEntity d where d.estado = true")
	List<FacturaVentaEntity> findAllCustom();
}
