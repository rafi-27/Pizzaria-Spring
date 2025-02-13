package ies.thiar.concesionario.service;

import java.util.List;

import ies.thiar.concesionario.model.Cliente;

public interface ClienteService {
    Cliente save(Cliente empleado); 
    List<Cliente> getAllClientes(); 
    Cliente findClienteById(long id); 
    Cliente findClienteByDni(String dni); 
    Cliente updateCliente(Cliente cliente); 
    void deleteCliente(long id); 
    List<Cliente> findByMarca(String ford);
}
