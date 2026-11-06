package pe.com.megacentimetros.ventas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.megacentimetros.ventas.entity.DetallePedidoEntity;

public interface DetallePedidoRepository extends JpaRepository<DetallePedidoEntity, Long> {

	@Query("select d from DetallePedidoEntity d where d.estado = true")
	List<DetallePedidoEntity> findAllCustom();
}
