package org.lessons.pizzeria.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pizzas")
public class Pizza {
    //    cosa da fare subito chiave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;
    private String descrizione;
    private String fotoUrl;
    @Column(nullable = false)
    private BigDecimal prezzo;

//    public Pizza(Integer id, String nome, String descrizione, String fotoUrl, BigDecimal prezzo) {
//        this.id = id;
//        this.nome = nome;
//        this.descrizione = descrizione;
//        this.fotoUrl = fotoUrl;
//        this.prezzo = prezzo;
//    }

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

