package ddscommerce.models.repositories;

import ddscommerce.models.entities.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioDeProductos {
    private List<Producto> productos = new ArrayList<>();

    public void guardar(Producto producto) {
        producto.setId(Long.valueOf(this.productos.size()));
        this.productos.add(producto);
    }

    public List<Producto> buscarTodos() {
        return this.productos;
    }

    public Optional<Producto> buscarPorId(Long id) {
        return this.productos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
