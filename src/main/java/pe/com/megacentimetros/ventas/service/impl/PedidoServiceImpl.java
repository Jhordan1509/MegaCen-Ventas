package pe.com.megacentimetros.ventas.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import pe.com.megacentimetros.ventas.client.PersonaFeignClient;
import pe.com.megacentimetros.ventas.exception.ResourceNotFoundException;
import pe.com.megacentimetros.ventas.entity.PedidoEntity;
import pe.com.megacentimetros.ventas.repository.PedidoRepository;
import pe.com.megacentimetros.ventas.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	private PedidoRepository repositorio;
	private PersonaFeignClient personaFeignClient;

	public PedidoServiceImpl(PedidoRepository repositorio,
			PersonaFeignClient personaFeignClient) {
		this.repositorio = repositorio;
		this.personaFeignClient = personaFeignClient;
	}

	@Override
	public List<PedidoEntity> findAll() {
		List<PedidoEntity> lista = repositorio.findAll();
		for (PedidoEntity pedido : lista) {
			pedido.setCliente(personaFeignClient.getCliente(pedido.getCodcli()));
		}
		return lista;
	}
	@Override
	public List<PedidoEntity> findAllCustom() {
		List<PedidoEntity> lista = repositorio.findAllCustom();
		for (PedidoEntity pedido : lista) {
			pedido.setCliente(personaFeignClient.getCliente(pedido.getCodcli()));
		}
		return lista;
	}
	@Override
	public PedidoEntity findById(Long id) {
		PedidoEntity pedido = repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido con id " + id + " no existe"));
		pedido.setCliente(personaFeignClient.getCliente(pedido.getCodcli()));
		return pedido;
	}
	@Override
	public PedidoEntity add(PedidoEntity obj) {
		obj.setEstado(true);
		if (obj.getFecha() == null) {
			obj.setFecha(LocalDateTime.now());
		}
		if (obj.getSituacion() == null) {
			obj.setSituacion("REGISTRADO");
		}
		if (obj.getSubtotal() == null) {
			obj.setSubtotal(java.math.BigDecimal.ZERO);
		}
		if (obj.getDescuento() == null) {
			obj.setDescuento(java.math.BigDecimal.ZERO);
		}
		if (obj.getIgv() == null) {
			obj.setIgv(java.math.BigDecimal.ZERO);
		}
		if (obj.getTotal() == null) {
			obj.setTotal(java.math.BigDecimal.ZERO);
		}
		return repositorio.save(obj);
	}
	@Override
	public PedidoEntity update(PedidoEntity obj, Long id) {
		PedidoEntity objped = repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido con id " + id + " no existe"));
		BeanUtils.copyProperties(obj, objped, "codigo");
		return repositorio.save(objped);
	}
	@Override
	public PedidoEntity delete(Long id) {
		PedidoEntity objped = repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido con id " + id + " no existe"));
		objped.setEstado(false);
		return repositorio.save(objped);
	}
	@Override
	public PedidoEntity enable(Long id) {
		PedidoEntity objped = repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido con id " + id + " no existe"));
		objped.setEstado(true);
		return repositorio.save(objped);
	}
}
