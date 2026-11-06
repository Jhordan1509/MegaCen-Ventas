package pe.com.megacentimetros.ventas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.megacentimetros.ventas.entity.PedidoEntity;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

	@Query("select d from PedidoEntity d where d.estado = true")
	List<PedidoEntity> findAllCustom();
}
