package ies.thiar.concesionario.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ies.thiar.concesionario.model.Cliente;
import ies.thiar.concesionario.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    //http://localhost:8080/api/clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(@RequestParam(value = "nombre", required = false) String nombre) {
        try {
            List<Cliente> clientes;
            
            if (nombre != null && !nombre.isEmpty()) {
                clientes = clienteService.getAllClientes().stream()
                    .filter(cliente -> cliente.getNombre().equalsIgnoreCase(nombre))
                    .toList();
            } else {
                clientes = clienteService.getAllClientes();
            }
            return ResponseEntity.ok(clientes);
    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
    
    //http://localhost:8080/api/clientes/1
    @GetMapping("{id}")
    public ResponseEntity<Cliente> findClienteById(@PathVariable("id") long clienteId){
        try {
            return new ResponseEntity<>(clienteService.findClienteById(clienteId),HttpStatus.CONFLICT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<Cliente> findClienteByDni(@PathVariable("dni") String clienteDni){
        try {
            return new ResponseEntity<>(clienteService.findClienteByDni(clienteDni),HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<Cliente>saveCliente(@RequestBody Cliente cliente){
        try {
            return new ResponseEntity<>(clienteService.save(cliente),HttpStatus.CREATED);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping
    public ResponseEntity<Cliente>updateCliente(@RequestBody Cliente cliente){
        try {
            return new ResponseEntity<>(clienteService.updateCliente(cliente),HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable("id") long id) {
        try {
            clienteService.deleteCliente(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }

}
