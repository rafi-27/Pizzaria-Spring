package ies.thiar.concesionario.controller;

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
    public List<Cliente> findAll(){
        return clienteService.getAllClientes();
    }

    //http://localhost:8080/api/clientes/1
    @GetMapping("{id}")
    public ResponseEntity<Cliente> findClienteById(@PathVariable("id") long clienteId){
        return new ResponseEntity<>(clienteService.findClienteById(clienteId),HttpStatus.CONFLICT);
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<Cliente> findClienteByDni(@PathVariable("dni") String clienteDni){
        return new ResponseEntity<>(clienteService.findClienteByDni(clienteDni),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente>saveCliente(@RequestBody Cliente cliente){
        return new ResponseEntity<>(clienteService.save(cliente),HttpStatus.CREATED);
    }

    // @GetMapping("/filtro")
    // public List<Cliente> getClientesByMarcaCoche(@RequestParam("marca") String marca){
    //     return clienteService.findByMarca(marca);
    // }

    @PutMapping
    public ResponseEntity<Cliente>updateCliente(@RequestBody Cliente cliente){
        return new ResponseEntity<>(clienteService.updateCliente(cliente),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable("id") long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}
