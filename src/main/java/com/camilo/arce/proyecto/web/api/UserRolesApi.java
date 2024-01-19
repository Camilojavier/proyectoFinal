package com.camilo.arce.proyecto.web.api;

public interface UserRolesApi extends Api{
    String USER_ROLES_ROUTE = API_VERSION + "/userRoles";
    String USER_ROLES_ID = "/{userRolesId}";
    String USERS_BY_ROLE_ID = "/role" + ROLE_ID;
    String ROLES_BY_USER_ID = "/user" + USER_ID;
}
