package ddscommerce.middlewares;

import ddscommerce.exceptions.AccessDeniedException;
import ddscommerce.models.entities.TipoRol;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class AuthMiddleware {

    public static void apply(Javalin app) {
        app.beforeMatched(ctx -> {
            var userRole = getUserRoleType(ctx);
            if (!ctx.routeRoles().isEmpty() && !ctx.routeRoles().contains(userRole)) {
                throw new AccessDeniedException();
            }
        });
    }

    private static TipoRol getUserRoleType(Context context) {
        return context.sessionAttribute("tipo_rol") != null?
                TipoRol.valueOf(context.sessionAttribute("tipo_rol")) : null;
    }
}
