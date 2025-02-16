package ies.thiar.concesionario.dto;

import lombok.Data;

@Data
public class FinalizarPedidoRequest {
    private long idCliente;
    private String pagoMetod;
}
