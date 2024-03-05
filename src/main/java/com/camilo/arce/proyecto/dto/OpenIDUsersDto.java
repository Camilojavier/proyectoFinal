package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.OpenIDUsersMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
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

}
