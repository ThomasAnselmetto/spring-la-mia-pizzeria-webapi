package org.lessons.pizzeria.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "utenti")
public class User {
    //    si puo' chiamare come vogliamo ma user e' piu intuitivo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //    @Column(name = "nome_utente")
    private String firstName;
    //    @Column(name = "cognome_utente")
    private String lastName;
    //    stabilisco che non posso avere sul database utenti senza mail e psw e che la mail deve essere unique
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> ruoli;

    //    GETTERS E SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRuoli() {
        return ruoli;
    }

    public void setRuoli(List<Role> ruoli) {
        this.ruoli = ruoli;
    }
}
