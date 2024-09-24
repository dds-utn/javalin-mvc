package ddscommerce.controllers;

import ddscommerce.models.entities.Producto;
import ddscommerce.models.repositories.RepositorioDeProductos;
import ddscommerce.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductosController implements ICrudViewsHandler {
    private RepositorioDeProductos repositorioDeProductos;

    public ProductosController(RepositorioDeProductos repositorioDeProductos) {
        this.repositorioDeProductos = repositorioDeProductos;
    }

    @Override
    public void index(Context context) {
        //PRETENDE DEVOLVER UNA VISTA QUE CONTENGA A TODOS LOS PRODUCTOS ALMACENADOS EN MI SISTEMA
        List<Producto> productos = this.repositorioDeProductos.buscarTodos();

        Map<String, Object> model = new HashMap<>();
        model.put("productos", productos);
        model.put("titulo", "Listado de productos");

        context.render("productos/productos.hbs", model);
    }

    @Override
    public void show(Context context) {
        //RECIBE POR PATH PARAM EL ID DE UN PRODUCTO Y PRETENDE DEVOLVER UNA VISTA CON EL DETALLE DE ESE PRODUCTO
        Optional<Producto> posibleProductoBuscado = this.repositorioDeProductos.buscarPorId(Long.valueOf(context.pathParam("id")));

        //TODO
//        if(posibleProductoBuscado.isEmpty()) {
//            context.status(HttpStatus.NOT_FOUND);
//            return;
//        }

        Map<String, Object> model = new HashMap<>();
        model.put("producto", posibleProductoBuscado.get());

        context.render("productos/detalle_producto.hbs", model);
    }

    @Override
    public void create(Context context) {
        //PRETENDE DEVOLVER UNA VISTA CON UN FORMULARIO PARA DAR DE ALTA UN NUEVO PRODUCTO.
        context.render("productos/formulario_producto.hbs");
    }

    @Override
    public void save(Context context) {
        Producto nuevoProducto = new Producto();

        nuevoProducto.setNombre(context.formParam("nombre"));
        nuevoProducto.setPrecio(Float.valueOf(context.formParam("precio")));

        this.repositorioDeProductos.guardar(nuevoProducto);
        //O BIEN LANZO UNA PANTALLA DE EXITO
        //O BIEN REDIRECCIONO AL USER A LA PANTALLA DE LISTADO DE PRODUCTOS
        context.redirect("/productos");
    }

    @Override
    public void edit(Context context) {
        //PRETENDE DEVOLVER UNA VISTA CON UN FORMULARIO QUE PERMITA EDITAR AL RECURSO QUE LLEGA POR PATH PARAM
        Optional<Producto> posibleProductoBuscado = this.repositorioDeProductos.buscarPorId(Long.valueOf(context.pathParam("id")));

        //TODO
//        if(posibleProductoBuscado.isEmpty()) {
//            context.status(HttpStatus.NOT_FOUND);
//            return;
//        }

        Map<String, Object> model = new HashMap<>();
        model.put("producto", posibleProductoBuscado.get());
        model.put("edicion", true);

        context.render("productos/detalle_producto.hbs", model);
    }

    @Override
    public void update(Context context) {
        //TODO
    }

    @Override
    public void delete(Context context) {
        //TODO
    }
}
