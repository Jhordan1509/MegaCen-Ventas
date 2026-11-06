package pe.com.megacentimetros.ventas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MegacentimetrosMicroservicioVentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MegacentimetrosMicroservicioVentasApplication.class, args);
	}

}
