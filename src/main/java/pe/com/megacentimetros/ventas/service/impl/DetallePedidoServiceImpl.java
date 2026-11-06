package pe.com.megacentimetros.ventas.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import pe.com.megacentimetros.ventas.client.InventarioFeignClient;
import pe.com.megacentimetros.ventas.exception.ResourceNotFoundException;
import pe.com.megacentimetros.ventas.entity.DetallePedidoEntity;
import pe.com.megacentimetros.ventas.repository.DetallePedidoRepository;
import pe.com.megacentimetros.ventas.service.DetallePedidoService;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

	private DetallePedidoRepository repositorio;
	private InventarioFeignClient inventarioFeignClient;

	public DetallePedidoServiceImpl(DetallePedidoRepository repositorio,
			InventarioFeignClient inventarioFeignClient) {
		this.repositorio = repositorio;
		this.inventarioFeignClient = inventarioFeignClient;
	}

	@Override
	public List<DetallePedidoEntity> findAll() {
		List<DetallePedidoEntity> lista = repositorio.findAll();
		for (DetallePedidoEntity detallePedido : lista) {
			detallePedido.setProducto(inventarioFeignClient.getProducto(detallePedido.getCodprod()));
		}
		return lista;
	}
	@Override
	public List<DetallePedidoEntity> findAllCustom() {
		List<DetallePedidoEntity> lista = repositorio.findAllCustom();
		for (DetallePedidoEntity detallePedido : lista) {
			detallePedido.setProducto(inventarioFeignClient.getProducto(detallePedido.getCodprod()));
		}
		return lista;
	}
	@Override
	public DetallePedidoEntity findById(Long id) {
		DetallePedidoEntity detallePedido = repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("DetallePedido con id " + id + " no existe"));
		detallePedido.setProducto(inventarioFeignClient.getProducto(detallePedido.getCodprod()));
		return detallePedido;
	}
	@Override
	public DetallePedidoEntity add(DetallePedidoEntity obj) {
		obj.setEstado(true);
		if (obj.getDescuento() == null) {
			obj.setDescuento(java.math.BigDecimal.ZERO);
		}
		return repositorio.save(obj);
	}
	@Override
	public DetallePedidoEntity update(DetallePedidoEntity obj, Long id) {
		DetallePedidoEntity objdet = repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("DetallePedido con id " + id + " no existe"));
		BeanUtils.copyProperties(obj, objdet, "codigo");
		return repositorio.save(objdet);
	}
	@Override
	public DetallePedidoEntity delete(Long id) {
		DetallePedidoEntity objdet = repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("DetallePedido con id " + id + " no existe"));
		objdet.setEstado(false);
		return repositorio.save(objdet);
	}
	@Override
	public DetallePedidoEntity enable(Long id) {
		DetallePedidoEntity objdet = repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("DetallePedido con id " + id + " no existe"));
		objdet.setEstado(true);
		return repositorio.save(objdet);
	}
}
