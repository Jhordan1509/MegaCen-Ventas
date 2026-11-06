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
import pe.com.megacentimetros.ventas.entity.DetallePedidoEntity;
import pe.com.megacentimetros.ventas.service.DetallePedidoService;

@RestController
@RequestMapping("/api/detallepedido")
public class DetallePedidoRestController {

	@Autowired
	private DetallePedidoService servicio;

	@GetMapping
	public List<DetallePedidoEntity> findAll() {
		return servicio.findAll();
	}

	@GetMapping("/custom")
	public List<DetallePedidoEntity> findAllCustom() {
		return servicio.findAllCustom();
	}

	@GetMapping("/{id}")
	public DetallePedidoEntity findById(@PathVariable Long id) {
		return servicio.findById(id);
	}

	@PostMapping
	public DetallePedidoEntity add(@RequestBody DetallePedidoEntity obj) {
		return servicio.add(obj);
	}

	@PutMapping("/{id}")
	public DetallePedidoEntity update(@RequestBody DetallePedidoEntity obj, @PathVariable Long id) {
		return servicio.update(obj, id);
	}

	@DeleteMapping("/{id}")
	public DetallePedidoEntity delete(@PathVariable Long id) {
		return servicio.delete(id);
	}

	@PatchMapping("/{id}")
	public DetallePedidoEntity enable(@PathVariable Long id) {
		return servicio.enable(id);
	}
}
