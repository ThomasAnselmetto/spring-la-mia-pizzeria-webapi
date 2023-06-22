package org.lessons.pizzeria.repository;

import org.lessons.pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

//passo il model e la primary key al repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
}
