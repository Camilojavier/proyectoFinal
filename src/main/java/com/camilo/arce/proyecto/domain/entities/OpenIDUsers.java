package com.camilo.arce.proyecto.domain.entities;

import com.camilo.arce.proyecto.domain.annotations.OpenIDUsersAnnotations;
import jakarta.persistence.*;


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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Providers getProviders() {
        return providers;
    }

    public void setProviders(Providers providers) {
        this.providers = providers;
    }
}

