package ies.thiar.concesionario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Bebida extends Producto {
    @Enumerated(EnumType.STRING)
    private SIZE tamanyo;

}