package ies.thiar.concesionario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ies.thiar.concesionario.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByDni(String dni);
    Optional<Cliente>findByDni(String dni);

    // @Query("SELECT c FROM Cliente c JOIN c.coches coche WHERE coche.marca = :marca")
    // List<Cliente>findClientesByCocheMarca(@Param("marca") String marca);
}