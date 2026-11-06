package pe.com.megacentimetros.ventas.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import pe.com.megacentimetros.ventas.exception.ResourceNotFoundException;
import pe.com.megacentimetros.ventas.entity.FacturaVentaEntity;
import pe.com.megacentimetros.ventas.repository.FacturaVentaRepository;
import pe.com.megacentimetros.ventas.service.FacturaVentaService;

@Service
public class FacturaVentaServiceImpl implements FacturaVentaService {

	private FacturaVentaRepository repositorio;

	public FacturaVentaServiceImpl(FacturaVentaRepository repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public List<FacturaVentaEntity> findAll() {
		return repositorio.findAll();
	}
	@Override
	public List<FacturaVentaEntity> findAllCustom() {
		return repositorio.findAllCustom();
	}
	@Override
	public FacturaVentaEntity findById(Long id) {
		FacturaVentaEntity facturaVenta = repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("FacturaVenta con id " + id + " no existe"));
		return facturaVenta;
	}
	@Override
	public FacturaVentaEntity add(FacturaVentaEntity obj) {
		obj.setEstado(true);
		if (obj.getFechaEmision() == null) {
			obj.setFechaEmision(LocalDateTime.now());
		}
		if (obj.getDescuento() == null) {
			obj.setDescuento(java.math.BigDecimal.ZERO);
		}
		if (obj.getIgv() == null) {
			obj.setIgv(java.math.BigDecimal.ZERO);
		}
		if (obj.getSituacion() == null) {
			obj.setSituacion("EMITIDA");
		}
		return repositorio.save(obj);
	}
	@Override
	public FacturaVentaEntity update(FacturaVentaEntity obj, Long id) {
		FacturaVentaEntity objfac = repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("FacturaVenta con id " + id + " no existe"));
		BeanUtils.copyProperties(obj, objfac, "codigo");
		return repositorio.save(objfac);
	}
	@Override
	public FacturaVentaEntity delete(Long id) {
		FacturaVentaEntity objfac = repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("FacturaVenta con id " + id + " no existe"));
		objfac.setEstado(false);
		return repositorio.save(objfac);
	}
	@Override
	public FacturaVentaEntity enable(Long id) {
		FacturaVentaEntity objfac = repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("FacturaVenta con id " + id + " no existe"));
		objfac.setEstado(true);
		return repositorio.save(objfac);
	}
}
