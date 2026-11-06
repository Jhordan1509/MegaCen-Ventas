package pe.com.megacentimetros.ventas.exception;

import java.time.LocalDateTime;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import feign.FeignException;
import feign.RetryableException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private ResponseEntity<ErrorResponse> respuesta(HttpStatus estado, String mensaje, HttpServletRequest request) {
		ErrorResponse cuerpo = new ErrorResponse(LocalDateTime.now(), estado.value(), estado.getReasonPhrase(), mensaje,
				request.getRequestURI());
		return new ResponseEntity<>(cuerpo, estado);
	}

	// Recurso inexistente en este microservicio (findById, update, delete, enable)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> recursoNoEncontrado(ResourceNotFoundException ex, HttpServletRequest request) {
		return respuesta(HttpStatus.NOT_FOUND, ex.getMessage(), request);
	}

	// Recurso inexistente en otro microservicio (consulta por Feign)
	@ExceptionHandler(FeignException.NotFound.class)
	public ResponseEntity<ErrorResponse> feignNoEncontrado(FeignException.NotFound ex, HttpServletRequest request) {
		return respuesta(HttpStatus.NOT_FOUND, "Un registro relacionado no existe en otro microservicio", request);
	}

	// Otro microservicio apagado o inaccesible
	@ExceptionHandler(RetryableException.class)
	public ResponseEntity<ErrorResponse> servicioNoDisponible(RetryableException ex, HttpServletRequest request) {
		return respuesta(HttpStatus.SERVICE_UNAVAILABLE, "Un microservicio requerido no esta disponible", request);
	}

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ErrorResponse> errorFeign(FeignException ex, HttpServletRequest request) {
		return respuesta(HttpStatus.BAD_GATEWAY, "Error al consultar otro microservicio", request);
	}

	// Id con formato invalido, por ejemplo /api/distrito/abc
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> parametroInvalido(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
		return respuesta(HttpStatus.BAD_REQUEST, "El valor '" + ex.getValue() + "' no es valido para " + ex.getName(), request);
	}

	// Duplicados, llaves foraneas inexistentes, campos obligatorios vacios
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> integridadDatos(DataIntegrityViolationException ex, HttpServletRequest request) {
		return respuesta(HttpStatus.CONFLICT, "Los datos enviados violan una restriccion de la base de datos", request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> errorGeneral(Exception ex, HttpServletRequest request) {
		return respuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", request);
	}
}
