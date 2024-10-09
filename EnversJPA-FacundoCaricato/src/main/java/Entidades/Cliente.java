package Entidades;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "Cliente")
@Audited
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Builder.Default
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_domicilio")
    private Domicilio domicilio;

    //@Builder.Default
    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas = new ArrayList<Factura>();

    @Column(name = "nombre") //Asigna el nombre de la columna
    private String nombre;

    //Si no agrego @Column, entonces toma el nombre del atributo por defecto
    private String apellido;

    @Column(name = "dni", unique = true)
    private int dni;
}
