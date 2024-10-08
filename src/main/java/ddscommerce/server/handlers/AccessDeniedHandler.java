package ddscommerce.server.handlers;

import ddscommerce.exceptions.AccessDeniedException;
import io.javalin.Javalin;

public class AccessDeniedHandler implements IHandler {

    @Override
    public void setHandle(Javalin app) {
        app.exception(AccessDeniedException.class, (e, context) -> {
            context.status(401);
            context.render("401.hbs");
        });
    }
}
