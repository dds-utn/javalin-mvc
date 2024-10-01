package ddscommerce.models.entities;

import io.javalin.security.RouteRole;

public enum TipoRol implements RouteRole {
    ADMIN,
    CUSTOMER,
    SELLER,
}
