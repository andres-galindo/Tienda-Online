package com.tienda.controller;

import com.tienda.model.Categoria;
import com.tienda.repository.CategoriaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    private final CategoriaRepository repo;

    public CategoriaController(CategoriaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Categoria> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Categoria guardar(@RequestBody Categoria c) {
        return repo.save(c);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    public Categoria actualizar(@PathVariable Long id, @RequestBody Categoria categoriaActualizada) {
        return repo.findById(id).map(categoria -> {
            categoria.setNombre(categoriaActualizada.getNombre());
            // aquí puedes actualizar más campos si los tienes
            return repo.save(categoria);
        }).orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    }

}
