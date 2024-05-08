package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.OpenIDUsersMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OpenIDUsersDto implements OpenIDUsersMessages {

    private Long openIdUsersId;

    @NotBlank(message = subjectIdNotBlankMessage)
    private String subjectId;

    @NotBlank(message = mailNotBlankMessage)
    private String mail;

    @NotBlank(message = issuerNotBlankMessage)
    private String issuer;

    private String openIdDN;

    @NotNull(message = providersNotNullMessage)
    private ProvidersDto providers;

    @NotNull(message = userNotNullMessage)
    private UsersDto users;

}
