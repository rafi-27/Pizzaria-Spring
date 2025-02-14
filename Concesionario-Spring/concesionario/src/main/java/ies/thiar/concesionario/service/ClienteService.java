package ies.thiar.concesionario.service;

import java.sql.SQLException;
import java.util.List;

import ies.thiar.concesionario.model.Cliente;

public interface ClienteService {
    Cliente save(Cliente empleado) throws SQLException; 
    List<Cliente> getAllClientes() throws SQLException;; 
    Cliente findClienteById(long id) throws SQLException;; 
    Cliente findClienteByDni(String dni) throws SQLException;; 
    Cliente updateCliente(Cliente cliente) throws SQLException;; 
    void deleteCliente(long id) throws SQLException;; 
}
