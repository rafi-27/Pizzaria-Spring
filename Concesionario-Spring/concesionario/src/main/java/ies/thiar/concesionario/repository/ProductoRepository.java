package ies.thiar.concesionario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ies.thiar.concesionario.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByNombre(String nombre);
    Optional<Producto> findByNombre(String nombre);
}