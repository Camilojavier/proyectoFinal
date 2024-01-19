package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.OpenIDUsersMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class OpenIDUsersDto implements OpenIDUsersMessages {

    private Long openIdUsersId;

    @NotBlank(message = subjectIdNotBlankMessage)
    private String subjectId;

    @NotBlank(message = mailNotBlankMessage)
    private String mail;

    @NotBlank(message = issuerNotBlankMessage)
    private String issuer;

    @NotBlank(message = openIdDNNotBlankMessage)
    private String openIdDN;
    @NotNull(message = providersNotNullMessage)
    private ProvidersDto providers;

    @NotNull(message = userNotNullMessage)
    private UsersDto users;

    public Long getOpenIdUsersId() {
        return openIdUsersId;
    }

    public void setOpenIdUsersId(Long openIdUsersId) {
        this.openIdUsersId = openIdUsersId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getOpenIdDN() {
        return openIdDN;
    }

    public void setOpenIdDN(String openIdDN) {
        this.openIdDN = openIdDN;
    }

    public ProvidersDto getProviders() {
        return providers;
    }

    public void setProviders(ProvidersDto providers) {
        this.providers = providers;
    }

    public UsersDto getUsers() {
        return users;
    }

    public void setUsers(UsersDto users) {
        this.users = users;
    }
}
