package pe.com.megacentimetros.ventas.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "PedidoEntity")
@Table(name = "pedido")
public class PedidoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "codped")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "numped", length = 40, nullable = false, unique = true)
    private String numero;

    @Column(name = "fecped", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "sitped", length = 30, nullable = false)
    private String situacion;

    @Column(name = "subtotal", nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "descue", nullable = false, precision = 12, scale = 2)
    private BigDecimal descuento;

    @Column(name = "igv", nullable = false, precision = 12, scale = 2)
    private BigDecimal igv;

    @Column(name = "total", nullable = false, precision = 12, scale = 2)
    private BigDecimal total;

    @Column(name = "obs", length = 300)
    private String observacion;

    @Column(name = "codcli", nullable = false)
    private Long codcli;

    @Column(name = "estped", nullable = false)
    private Boolean estado;

    @Transient
    private Object cliente;
}
