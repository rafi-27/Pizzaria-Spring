package ies.thiar.concesionario.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LineaPedido {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long id;
    
    private int cantidad;

    @OneToOne
    private Producto product;
        
    @ManyToOne(cascade = CascadeType.ALL)
    private Pedido pedido;

    private double precioLineaPedido;
}