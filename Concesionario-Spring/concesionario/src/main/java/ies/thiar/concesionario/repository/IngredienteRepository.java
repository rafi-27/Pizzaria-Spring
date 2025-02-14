package ies.thiar.concesionario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ies.thiar.concesionario.model.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{
    Optional<Ingrediente> findByNombre(String nombre);
}
