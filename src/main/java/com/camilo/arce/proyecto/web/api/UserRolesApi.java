package com.camilo.arce.proyecto.web.api;

public interface UserRolesApi extends Api{
    String USER_ROLES_ROUTE = API_VERSION + "/userRoles";
    String USER_ROLES_ID = "/{userRolesId}";
    String BY_ROLE_ID = "/role" + ROLE_ID;
}
