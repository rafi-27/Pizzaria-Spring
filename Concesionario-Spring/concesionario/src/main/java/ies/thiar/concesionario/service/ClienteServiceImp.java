package ies.thiar.concesionario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ies.thiar.concesionario.model.Cliente;
import ies.thiar.concesionario.repository.ClienteRepository;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente save(Cliente empleado) {
        // clienteRepository.save(empleado);
        if (clienteRepository.existsByDni(empleado.getDni())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe el cliente con ese dni.");
        }
        return clienteRepository.save(empleado);
    }

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findClienteById(long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clietne no encontrado con id: " + id));
    }

    @Override
    public Cliente findClienteByDni(String dni) {
        return clienteRepository.findByDni(dni).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clietne no encontrado con dni: " + dni));
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getId())){
            return clienteRepository.save(cliente);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con Id: " + cliente.getId());
        }
    }

    @Override
    public void deleteCliente(long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con Id: " + id);
        }
    }
}
