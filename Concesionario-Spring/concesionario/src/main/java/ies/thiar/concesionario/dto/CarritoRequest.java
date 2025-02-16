package ies.thiar.concesionario.dto;

import lombok.Data;

@Data
public class CarritoRequest {
    private long idCliente;
    private long idProducto;
    private int cantidad;
}
