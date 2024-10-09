package Entidades;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@Table(name = "Domicilio")
@Audited
public class Domicilio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@Builder.Default
    @OneToOne(mappedBy = "domicilio")
    private Cliente cliente;

    private String nombreCalle;
    private int numero;
}
