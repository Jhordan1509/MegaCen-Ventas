package pe.com.megacentimetros.ventas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "megacentimetros-microservicio-inventario", url = "http://localhost:9093/pmega/api/")
public interface InventarioFeignClient {
	@GetMapping("/producto/{id}")
	Object getProducto(@PathVariable("id") Long id);
}
