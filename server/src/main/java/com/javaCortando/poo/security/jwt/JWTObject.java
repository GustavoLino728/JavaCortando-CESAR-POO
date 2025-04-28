package com.javaCortando.poo.security.jwt;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class JWTObject {
    private String subject; //nome de usuário
    private Date issueedAT; //Data de expedição
    private Date expiration; //Data de expiração
    private List<String> roles; //Perfis de acesso

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getIssueedAT() {
        return issueedAT;
    }

    public void setIssueedAT(Date issueedAT) {
        this.issueedAT = issueedAT;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setRoles(String... roles){
        this.roles = Arrays.asList(roles);
    }
}
