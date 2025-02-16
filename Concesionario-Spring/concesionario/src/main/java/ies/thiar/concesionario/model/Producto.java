package ies.thiar.concesionario.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tipoProducto")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Pizza.class, name = "PIZZA"),
    @JsonSubTypes.Type(value = Pasta.class, name = "PASTA"),
    @JsonSubTypes.Type(value = Bebida.class, name = "BEBIDA")
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public abstract class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String nombre;
    private double precio;
}