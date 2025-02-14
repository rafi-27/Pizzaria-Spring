package ies.thiar.concesionario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ies.thiar.concesionario.model.Producto;
import ies.thiar.concesionario.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    //http://localhost:8080/api/clientes
    @GetMapping
    public List<Producto> findAllProductos(){
        return productoService.getAllProducts();
    }








}