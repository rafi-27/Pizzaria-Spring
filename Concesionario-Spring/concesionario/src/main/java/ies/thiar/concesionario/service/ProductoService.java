package ies.thiar.concesionario.service;

import java.sql.SQLException;
import java.util.List;

import ies.thiar.concesionario.model.Producto;

public interface ProductoService {
    Producto saveProduct(Producto producto) throws SQLException;;
    List<Producto> getAllProducts() throws SQLException;;
    Producto findProductById(long id) throws SQLException;;
    Producto updateProduct(Producto producto) throws SQLException;;
    void deleteProduct(long id) throws SQLException;;
}
