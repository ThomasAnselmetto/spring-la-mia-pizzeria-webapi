package org.lessons.pizzeria.repository;

import org.lessons.pizzeria.model.Offerta;
import org.springframework.data.jpa.repository.JpaRepository;

//per fare operazioni di crud su DB abbiamo l'obbligo di fare'(Creo l'entita' -> Creo il repository)
public interface OffertaRepository extends JpaRepository<Offerta, Integer> {//gestisce entita' di tipo offerta che ha PK che e' un Integer

    
}
