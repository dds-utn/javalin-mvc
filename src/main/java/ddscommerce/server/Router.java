package ddscommerce.server;

import ddscommerce.config.ServiceLocator;
import ddscommerce.controllers.ProductosController;
import ddscommerce.models.entities.TipoRol;
import io.javalin.Javalin;

import java.util.Set;

public class Router {

    public static void init(Javalin app) {
        //EJEMPLOS
        app.get("/prueba", ctx -> ctx.result("Hola mundo!"));

        //Query Params
        app.get("/saludo", ctx -> {
            ctx.result("Hola " + ctx.queryParam("nombre") + " " + ctx.queryParam("apellido"));
        });

        //Route params | Path params
        app.get("/saludo-para/{nombre}", ctx -> ctx.result("Hola " + ctx.pathParam("nombre")));

        //Sesión
        app.get("/guardar-en-sesion", ctx -> {
            ctx.sessionAttribute("nombre", ctx.queryParam("nombre"));
            ctx.result("Variable de sesion guardada");
        });

        app.get("/saludo-sesionado", ctx -> ctx.result("Hola " + ctx.sessionAttribute("nombre")));

        app.get("/destruir-sesion", ctx -> ctx.req().getSession().invalidate());

        //PROYECTO
        app.get("/productos", ServiceLocator.instanceOf(ProductosController.class)::index);

        app.get("/productos/nuevo", ServiceLocator.instanceOf(ProductosController.class)::create, TipoRol.SELLER, TipoRol.ADMIN);

        app.get("/productos/{id}", ServiceLocator.instanceOf(ProductosController.class)::show);

        app.get("/productos/{id}/edicion", ServiceLocator.instanceOf(ProductosController.class)::edit);

        app.post("/productos/{id}/edicion", ServiceLocator.instanceOf(ProductosController.class)::update);

        app.post("/productos/{id}/eliminiacion", ServiceLocator.instanceOf(ProductosController.class)::delete);

        app.post("/productos", ServiceLocator.instanceOf(ProductosController.class)::save);
    }
}
