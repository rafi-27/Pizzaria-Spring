package ies.thiar.concesionario.service;

import java.util.List;

import ies.thiar.concesionario.dto.CarritoRequest;
import ies.thiar.concesionario.dto.FinalizarPedidoRequest;
import ies.thiar.concesionario.model.Pedido;

public interface PedidoService {
    Pedido addLineaPedido(CarritoRequest carritoRequest);
    List<Pedido> getAllPedidos();
    Pedido findPedidoById(long id);
    
    Pedido cancelarPedido(long id);
    Pedido entregarPedido(long id);
    Pedido finalizarPedido(FinalizarPedidoRequest finalizarPedidoRequest);
}
