package ies.thiar.concesionario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ies.thiar.concesionario.dto.CarritoRequest;
import ies.thiar.concesionario.dto.FinalizarPedidoRequest;
import ies.thiar.concesionario.model.Pedido;
import ies.thiar.concesionario.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> addLineaPedido(@RequestBody CarritoRequest carritoRequest) {
        try {
            Pedido pedido = pedidoService.addLineaPedido(carritoRequest);
            return new ResponseEntity<>(pedido, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/finalizar")
    public ResponseEntity<Pedido> finalizarPedido(@RequestBody FinalizarPedidoRequest finalizarPedidoRequest) {
        try {
            Pedido pedido = pedidoService.finalizarPedido(finalizarPedidoRequest);
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cancelar/{clienteId}")
    public ResponseEntity<Pedido> cancelarPedido(@PathVariable long clienteId) {
        try {
            Pedido pedido = pedidoService.cancelarPedido(clienteId);
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/entregar/{pedidoId}")
    public ResponseEntity<Pedido> entregarPedido(@PathVariable int pedidoId) {
        try {
            Pedido pedido = pedidoService.entregarPedido(pedidoId);
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //GET /api/pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        try {
            List<Pedido> pedidos = pedidoService.getAllPedidos();
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //GET /api/pedidos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable int id) {
        try {
            Pedido pedido = pedidoService.findPedidoById(id);
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}