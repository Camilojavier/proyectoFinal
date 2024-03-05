package com.camilo.arce.proyecto.domain.entities;

import com.camilo.arce.proyecto.domain.annotations.OpenIDUsersAnnotations;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = OpenIDUsersAnnotations.OPENID_USERS_TABLE_NAME)
public class OpenIDUsers implements OpenIDUsersAnnotations{

    @Id
    @SequenceGenerator(name = OPENID_USERS_SEQUENCE, allocationSize = ALLOCATION_SIZE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = OPENID_USERS_SEQUENCE)
    @Column(name = OPENID_USERS_ID)
    private Long openIdUsersId;

    @Column(name = SUBJECT_ID)
    private String subjectId;

    private String mail;

    private String issuer;

    @Column(name = OPENID_DN)
    private String openIdDN;

    @ManyToOne
    @JoinColumn(name = USER_ID)
    private Users users;

    @ManyToOne
    @JoinColumn(name = PROVIDER_ID)
    private Providers providers;

}

