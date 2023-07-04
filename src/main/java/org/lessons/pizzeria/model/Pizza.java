package org.lessons.pizzeria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pizzas")
public class Pizza {
    //    cosa da fare subito chiave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min = 4, max = 30, message = "Il numero di caratteri per il nome va da 4 a 30!")
    @NotBlank(message = "Il nome della pizza non puo' essere vuoto!")
    @Column(nullable = false)
    private String nome;

    private String descrizione;
    private String ingredienti;
    private String fotoUrl;
    @DecimalMin(value = "0", message = "Il prezzo deve essere superiore a 0.0")
    @NotNull(message = "Il prezzo della pizza non puo' essere vuoto")
    @Column(nullable = false)
    private BigDecimal prezzo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //Relazione con le offerte
    //hibernate in automatico crea una tabella ponte se non stabiliamo che la relazione e'gia' stata mappata sull'entita offerte al campo "pizza" @ManyToOne @JoinColumn(nullable = false) private Pizza pizza;
//    possiamo creare il processo di cascade per poter eliminare tutte le offerte associate se cancelliamo una pizza
    @JsonIgnore
    @OneToMany(mappedBy = "pizza", cascade = {CascadeType.REMOVE})
    private List<Offerta> offerte = new ArrayList<>();
    //costruttore con parametri


    public Pizza(Integer id, String nome, String descrizione, String ingredienti, String fotoUrl, @NotNull(message = "Il prezzo della pizza non puo' essere vuoto") BigDecimal prezzo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.ingredienti = ingredienti;
        this.fotoUrl = fotoUrl;
        this.prezzo = prezzo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Pizza() {//costruttore vuoto
    }

//quando si lavora con i DB definire SEMPRE i getter e setter


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Offerta> getOfferte() {
        return offerte;
    }

    public void setOfferte(List<Offerta> offerte) {
        this.offerte = offerte;
    }

    // getter custom per il timestamp formattato
    public String getFormattedDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMMM dd 'alle' HH:mm");
        return now.format(formatter);
    }
}

