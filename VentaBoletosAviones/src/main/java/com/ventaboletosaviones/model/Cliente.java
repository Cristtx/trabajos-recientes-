package com.ventaboletosaviones.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @Column(name = "dniruc", length = 12)
    private String dniruc;

    @Column(name = "nombres", length = 160, nullable = false)
    private String nombres;

    @Column(name = "rep_legal", length = 160)
    private String repLegal;

    @Column(name = "email", length = 120)
    private String email;

    public Cliente() {}

    public Cliente(String dniruc, String nombres, String repLegal, String email) {
        this.dniruc = dniruc;
        this.nombres = nombres;
        this.repLegal = repLegal;
        this.email = email;
    }

    public String getDniruc() { return dniruc; }
    public void setDniruc(String dniruc) { this.dniruc = dniruc; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getRepLegal() { return repLegal; }
    public void setRepLegal(String repLegal) { this.repLegal = repLegal; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
