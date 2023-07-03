package org.lessons.pizzeria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


//    dico che e' un entity e se non ho capito male che e' la principale nella relazione con le pizze.
@Entity
@Table(name = "offerte")
public class Offerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //    una data di inizio

    private LocalDate dataInizioOfferta;

    //    una data di fine
    private LocalDate dataFineOfferta;
    //    un titolo
    @NotNull
    private String nomeOfferta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Pizza pizza;
//e' buona norma che nelle one to many l'owner sia quella che "ne vede una"

    //Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataInizioOfferta() {
        return dataInizioOfferta;
    }

    public void setDataInizioOfferta(LocalDate dataInizioOfferta) {
        this.dataInizioOfferta = dataInizioOfferta;
    }

    public LocalDate getDataFineOfferta() {
        return dataFineOfferta;
    }

    public void setDataFineOfferta(LocalDate dataFineOfferta) {
        this.dataFineOfferta = dataFineOfferta;
    }

    public String getNomeOfferta() {
        return nomeOfferta;
    }

    public void setNomeOfferta(String nomeOfferta) {
        this.nomeOfferta = nomeOfferta;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}
