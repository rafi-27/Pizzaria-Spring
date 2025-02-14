package ies.thiar.concesionario.service;

import java.util.List;

import ies.thiar.concesionario.model.Producto;

public interface ProductoService {
    Producto saveProduct(Producto producto);
    List<Producto> getAllProducts();
    Producto findProductById(int id);
    Producto updateProduct(Producto producto);
    void deleteProduct(int id);
}
