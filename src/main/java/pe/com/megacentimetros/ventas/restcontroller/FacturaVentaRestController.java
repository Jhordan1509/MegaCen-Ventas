package pe.com.megacentimetros.ventas.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.megacentimetros.ventas.entity.FacturaVentaEntity;
import pe.com.megacentimetros.ventas.service.FacturaVentaService;

@RestController
@RequestMapping("/api/facturaventa")
public class FacturaVentaRestController {

	@Autowired
	private FacturaVentaService servicio;

	@GetMapping
	public List<FacturaVentaEntity> findAll() {
		return servicio.findAll();
	}

	@GetMapping("/custom")
	public List<FacturaVentaEntity> findAllCustom() {
		return servicio.findAllCustom();
	}

	@GetMapping("/{id}")
	public FacturaVentaEntity findById(@PathVariable Long id) {
		return servicio.findById(id);
	}

	@PostMapping
	public FacturaVentaEntity add(@RequestBody FacturaVentaEntity obj) {
		return servicio.add(obj);
	}

	@PutMapping("/{id}")
	public FacturaVentaEntity update(@RequestBody FacturaVentaEntity obj, @PathVariable Long id) {
		return servicio.update(obj, id);
	}

	@DeleteMapping("/{id}")
	public FacturaVentaEntity delete(@PathVariable Long id) {
		return servicio.delete(id);
	}

	@PatchMapping("/{id}")
	public FacturaVentaEntity enable(@PathVariable Long id) {
		return servicio.enable(id);
	}
}
