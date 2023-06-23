package org.lessons.pizzeria.repository;

import org.lessons.pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//passo il model e la primary key al repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    //    metodo per cercare le pizze con nome uguale a quello passato
    List<Pizza> findByNome(String nome);
}