package com.camilo.arce.proyecto.web.api;

public interface OpenIDUsersApi extends Api{
    String OPENID_USERS_ROUTE = API_VERSION + "/openidUsers";
    String OPENID_USER_ID = "/{openidUsersId}";
}
