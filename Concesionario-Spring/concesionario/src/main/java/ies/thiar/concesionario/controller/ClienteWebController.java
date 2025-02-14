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
    public String listarClientes(Model model) {
        //Para enviar datos desde el Controller a la vista se usa model que almacena pares clave-valor
        try {
            model.addAttribute("clientes", clienteService.getAllClientes());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Entra en clientes");
        return "clientes"; // El fichero clientes.html debe existir en resources/templates
    }

    @GetMapping("/{id}")
    public String verCliente(@PathVariable Long id, Model model) {
        Cliente cliente = null;
        try {
            cliente = clienteService.findClienteById(id);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute("cliente", cliente);
        return "cliente-detalle"; // El fichero cliente-detalle.html debe existir en resources/templates
    }
}
