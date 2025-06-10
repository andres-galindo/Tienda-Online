package com.tienda.controller;

import com.tienda.model.Producto;
import com.tienda.repository.ProductoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoRepository repo;

    public ProductoController(ProductoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Producto> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Producto guardar(@RequestBody Producto p) {
        return repo.save(p);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id,
            @RequestBody Producto productoActualizado) {
        return repo.findById(id)
                .map(producto -> {
                    producto.setNombre(productoActualizado.getNombre());
                    producto.setPrecio(productoActualizado.getPrecio());
                    producto.setCategoria(productoActualizado.getCategoria());
                    // agrega los setters que necesites
                    return ResponseEntity.ok(repo.save(producto));
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
