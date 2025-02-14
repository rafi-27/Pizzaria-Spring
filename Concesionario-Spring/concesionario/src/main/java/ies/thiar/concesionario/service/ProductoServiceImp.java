package ies.thiar.concesionario.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ies.thiar.concesionario.model.Ingrediente;
import ies.thiar.concesionario.model.Pasta;
import ies.thiar.concesionario.model.Pizza;
import ies.thiar.concesionario.model.Producto;
import ies.thiar.concesionario.repository.IngredienteRepository;
import ies.thiar.concesionario.repository.ProductoRepository;

@Service
public class ProductoServiceImp implements ProductoService{
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Override
    public Producto saveProduct(Producto producto) {
        if (productoRepository.existsByNombre(producto.getNombre())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe un producto con el nombre: " + producto.getNombre());
        }

        if (producto instanceof Pizza pizza) {
            List<Ingrediente> ingredientesUnicos = pizza.getListaIngredientesPizza().stream()
                    .map(ing -> ingredienteRepository.findByNombre(ing.getNombre()).orElse(ing))
                    .distinct()
                    .collect(Collectors.toList());
            pizza.setListaIngredientesPizza(ingredientesUnicos);
            return productoRepository.save(pizza);
        } else if (producto instanceof Pasta pasta) {
            List<Ingrediente> ingredientesUnicos = pasta.getListaIngredientePasta().stream()
                    .map(ing -> ingredienteRepository.findByNombre(ing.getNombre()).orElse(ing))
                    .distinct()
                    .collect(Collectors.toList());
            pasta.setListaIngredientePasta(ingredientesUnicos);
            return productoRepository.save(pasta);
        }

        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> getAllProducts() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findProductById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findProductById'");
    }

    @Override
    public Producto updateProduct(Producto producto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public void deleteProduct(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
    }
    
}
