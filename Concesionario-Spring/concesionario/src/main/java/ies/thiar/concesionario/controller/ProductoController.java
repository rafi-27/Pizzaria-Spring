package ies.thiar.concesionario.controller;

import java.sql.SQLException;
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

import ies.thiar.concesionario.model.Producto;
import ies.thiar.concesionario.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    //http://localhost:8080/api/productos
    @GetMapping
    public List<Producto> findAllProductos(){
        try {
            return productoService.getAllProducts();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    //http://localhost:8080/api/clientes/1
    @GetMapping("{id}")
    public ResponseEntity<Producto> findProductoById(@PathVariable("id") long producto_id){
        try {
            return new ResponseEntity<>(productoService.findProductById(producto_id),HttpStatus.CONFLICT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<Producto>saveProducto(@RequestBody Producto producto){
        try {
            return new ResponseEntity<>(productoService.saveProduct(producto),HttpStatus.CREATED);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping
    public ResponseEntity<Producto>updateProducto(@RequestBody Producto producto){
        try {
            return new ResponseEntity<>(productoService.updateProduct(producto),HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable("id") long id) {
        try {
            productoService.deleteProduct(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }
}