package ies.thiar.concesionario.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ies.thiar.concesionario.model.Cliente;
import ies.thiar.concesionario.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteWebController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarClientes(Model model) throws SQLException {
        model.addAttribute("clientes", clienteService.getAllClientes());
        return "clientes";
    }

    @GetMapping("/{id}")
    public String verCliente(@PathVariable Long id, Model model) throws SQLException {
        Cliente cliente = clienteService.findClienteById(id);
        model.addAttribute("cliente", cliente);
        return "cliente-detalle";
    }
}
