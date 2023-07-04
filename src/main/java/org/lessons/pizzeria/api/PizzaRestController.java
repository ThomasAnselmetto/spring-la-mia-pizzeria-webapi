package org.lessons.pizzeria.api;

import jakarta.validation.Valid;
import org.lessons.pizzeria.model.Pizza;
import org.lessons.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
//(convenzione)se faccio ucire una nuova versione posso cambiare il numero di versioone es v2
@RequestMapping("api/v1/pizzas")
public class PizzaRestController {
//    controller per la risorsa Pizza ovviamente richiamo il db con il nostro @Autowired delle pizze

    @Autowired
    private PizzaRepository pizzaRepository;

    //ottenimento lista delle pizze
    @GetMapping
    public List<Pizza> index() {
        return pizzaRepository.findAll();
    }

    //servizio per la singola pizza
    @GetMapping("/{id}")
//firma del metodo
    public Pizza get(@PathVariable Integer id) {
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if (pizza.isPresent()) {
            return pizza.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    //    servizio per creare una nuova pizza che arriva come JSON nel request body
//    solo la post,put e patch hanno il body
    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza pizza) {
//       opzione non sicura (oggetto incompleto,non coerente o non valido un id gia' esistente
        return pizzaRepository.save(pizza);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        pizzaRepository.deleteById(id);

    }

    //    la put lavora in sostituzione quindi si passa tutto l'oggetto al quale possono essere sostituiti uno o anche tutti i campi'

    //    cerco l'id e se esiste creo un oggetto con quell'id,se non esiste sarebbe meglio lanciare una 404
    @PutMapping("/{id}")
    public Pizza update(@PathVariable Integer id, @RequestBody Pizza pizza) {
//        setto l'id anche se nel json su postman non lo metto ma glielo passo nel path'
        pizza.setId(id);
        return pizzaRepository.save(pizza);
    }
}
