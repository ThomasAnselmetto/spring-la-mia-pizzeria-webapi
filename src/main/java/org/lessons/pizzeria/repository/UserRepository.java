package org.lessons.pizzeria.repository;


import org.lessons.pizzeria.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
//metodo che recupera un user da email

    Optional<User> findByEmail(String mail);
    
}
