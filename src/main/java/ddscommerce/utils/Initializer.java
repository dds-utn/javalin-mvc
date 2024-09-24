package ddscommerce.utils;

import ddscommerce.config.ServiceLocator;
import ddscommerce.models.entities.Producto;
import ddscommerce.models.repositories.RepositorioDeProductos;

public class Initializer {

    public static void init() {
        Producto producto1 = Producto
                .builder()
                .nombre("Café")
                .precio(14000F)
                .build();

        Producto producto2 = Producto
                .builder()
                .nombre("Yerba")
                .precio(5500F)
                .build();

        Producto producto3 = Producto
                .builder()
                .nombre("Té de boldo")
                .precio(3300F)
                .build();

        RepositorioDeProductos repositorioDeProductos = ServiceLocator.instanceOf(RepositorioDeProductos.class);

        repositorioDeProductos.guardar(producto1);
        repositorioDeProductos.guardar(producto2);
        repositorioDeProductos.guardar(producto3);
    }
}
