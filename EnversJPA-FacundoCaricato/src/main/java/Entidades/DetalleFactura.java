package Entidades;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@Table(name = "DetalleFactura")
@Audited
public class DetalleFactura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Builder.Default
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_detalleFactura")
    private Factura factura;

    //@Builder.Default
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_articulo")
    private Articulo articulo;

    private int cantidad;
    private int subtotal;
}
