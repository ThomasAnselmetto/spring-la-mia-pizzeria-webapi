package org.lessons.pizzeria.dto;

//PizzaDto diventa l'oggetto che ci permette di trasportare la Pizza compatibilmente al form HTML

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

//copio ed incollo tutti i campi di Pizza e li ripulisco dalle annotation che non ci servono(es quelle proprie dei DB)
public class PizzaDto {
    private Integer id;
    @Size(min = 4, max = 30, message = "Il numero di caratteri per il nome va da 4 a 30!")
    @NotBlank(message = "Il nome della pizza non puo' essere vuoto!")
    private String nome;

    private String descrizione;
    private String ingredienti;
    private String fotoUrl;
    @DecimalMin(value = "0", message = "Il prezzo deve essere superiore a 0.0")
    @NotNull(message = "Il prezzo della pizza non puo' essere vuoto")
    private BigDecimal prezzo;
    //    MultipartFile interfaccia di spring che serve per wrappare i file nel giusto formato(quello che ci invia il form)
    private MultipartFile cover;

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

    public MultipartFile getCover() {
        return cover;
    }

    public void setCover(MultipartFile cover) {
        this.cover = cover;
    }
}
