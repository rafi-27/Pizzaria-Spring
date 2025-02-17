package ies.thiar.concesionario.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ies.thiar.concesionario.dto.CarritoRequest;
import ies.thiar.concesionario.dto.FinalizarPedidoRequest;
import ies.thiar.concesionario.model.Cliente;
import ies.thiar.concesionario.model.EstadoPedido;
import ies.thiar.concesionario.model.LineaPedido;
import ies.thiar.concesionario.model.Pagable;
import ies.thiar.concesionario.model.PagarEfectivo;
import ies.thiar.concesionario.model.PagarTarjeta;
import ies.thiar.concesionario.model.Pedido;
import ies.thiar.concesionario.model.Producto;
import ies.thiar.concesionario.repository.ClienteRepository;
import ies.thiar.concesionario.repository.PedidoRepository;
import ies.thiar.concesionario.repository.ProductoRepository;

@Service
public class PedidoServiceImp implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Pedido addLineaPedido(CarritoRequest carritoRequest) {
        Cliente cliente = clienteRepository.findById(carritoRequest.getIdCliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado."));

        Producto producto = productoRepository.findById(carritoRequest.getIdProducto())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado."));

        Pedido pedido = pedidoRepository.findByClienteIdAndEstado(cliente.getId(), EstadoPedido.PENDIENTE)
                .stream()
                .findFirst()
                .orElseGet(() -> {
                    Pedido nuevoPedido = new Pedido();
                    nuevoPedido.setCliente(cliente);
                    nuevoPedido.setFecha(LocalDate.now());
                    nuevoPedido.setEstado(EstadoPedido.PENDIENTE);
                    nuevoPedido.setPrecioTotal(0.0);
                    nuevoPedido.setLineaPedido(new ArrayList<>());
                    return nuevoPedido;
                });

        if (pedido.getId() > 0
                && pedidoRepository.findByClienteIdAndEstado(cliente.getId(), EstadoPedido.PENDIENTE).size() > 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este cliente tiene más de un pedido pendiente");
        }

        LineaPedido linea = new LineaPedido();
        linea.setCantidad(carritoRequest.getCantidad());
        linea.setProduct(producto);
        double precioLinea = carritoRequest.getCantidad() * producto.getPrecio();
        linea.setPrecioLineaPedido(precioLinea);
        linea.setPedido(pedido);

        pedido.getLineaPedido().add(linea);
        pedido.setPrecioTotal(pedido.getPrecioTotal() + precioLinea);

        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido findPedidoById(long id) {
        return pedidoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado con id: " + id));
    }

    @Override
    public Pedido cancelarPedido(long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        Pedido pedido = pedidoRepository.findByClienteIdAndEstado(cliente.getId(), EstadoPedido.PENDIENTE)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No hay pedidos pendientes para el cliente"));

        if (pedidoRepository.findByClienteIdAndEstado(cliente.getId(), EstadoPedido.PENDIENTE).size() > 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este cliente tiene más de un pedido pendiente");
        }

        pedido.setEstado(EstadoPedido.CANCELADO);
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido entregarPedido(long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));
        pedido.setEstado(EstadoPedido.ENTREGADO);
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido finalizarPedido(FinalizarPedidoRequest finalizarPedidoRequest) {
        Cliente cliente = clienteRepository.findById(finalizarPedidoRequest.getIdCliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        Pedido pedido = pedidoRepository.findByClienteIdAndEstado(cliente.getId(), EstadoPedido.PENDIENTE)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No hay pedidos pendientes para el cliente"));

        if (pedidoRepository.findByClienteIdAndEstado(cliente.getId(), EstadoPedido.PENDIENTE).size() > 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Existen múltiples pedidos pendientes para el cliente");
        }

        String metodoPago = finalizarPedidoRequest.getPagoMetod().toUpperCase();
        Pagable pago;
        if (metodoPago.equals("EFECTIVO")) {
            pago = new PagarEfectivo();
        } else if (metodoPago.equals("TARJETA")) {
            pago = new PagarTarjeta();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Método de pago no soportado");
        }

        pedido.setPago(pago);
        pedido.setEstado(EstadoPedido.FINALIZADO);
        return pedidoRepository.save(pedido);
    }

}
