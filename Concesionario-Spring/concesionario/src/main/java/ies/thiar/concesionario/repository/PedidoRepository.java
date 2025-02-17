package ies.thiar.concesionario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ies.thiar.concesionario.model.EstadoPedido;
import ies.thiar.concesionario.model.Pedido;

public interface  PedidoRepository extends JpaRepository<Pedido, Long>{
    List<Pedido> findByClienteIdAndEstado(long clienteId, EstadoPedido estado);
}