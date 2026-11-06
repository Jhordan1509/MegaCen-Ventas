package pe.com.megacentimetros.ventas.service;

import java.util.List;
import pe.com.megacentimetros.ventas.entity.PedidoEntity;

public interface PedidoService {

	List<PedidoEntity> findAll();

	List<PedidoEntity> findAllCustom();

	PedidoEntity findById(Long id);

	PedidoEntity add(PedidoEntity obj);

	PedidoEntity update(PedidoEntity obj, Long id);

	PedidoEntity delete(Long id);

	PedidoEntity enable(Long id);
}
