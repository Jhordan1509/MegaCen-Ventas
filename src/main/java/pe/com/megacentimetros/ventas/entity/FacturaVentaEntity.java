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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "FacturaVentaEntity")
@Table(name = "facturaventa")
public class FacturaVentaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "codfacven")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "numfac", length = 40, nullable = false, unique = true)
    private String numero;

    @Column(name = "fecemi", nullable = false)
    private LocalDateTime fechaEmision;

    @Column(name = "subtotal", nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "descue", nullable = false, precision = 12, scale = 2)
    private BigDecimal descuento;

    @Column(name = "igv", nullable = false, precision = 12, scale = 2)
    private BigDecimal igv;

    @Column(name = "total", nullable = false, precision = 12, scale = 2)
    private BigDecimal total;

    @Column(name = "sitfac", length = 30, nullable = false)
    private String situacion;

    @Column(name = "codped", nullable = false, unique = true)
    private Long codped;

    @Column(name = "estfacven", nullable = false)
    private Boolean estado;
}
