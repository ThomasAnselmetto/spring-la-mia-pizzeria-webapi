package org.lessons.pizzeria.model;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
//@Table()
public class Pizza {
    private String nome;
    private String descrizione;
    private String fotoUrl;
    private BigDecimal prezzo;

    public Pizza(String nome, String descrizione, String fotoUrl, BigDecimal prezzo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.fotoUrl = fotoUrl;
        this.prezzo = prezzo;
    }

    //quando si lavora con i DB definire SEMPRE i getter e setter

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }
}

