package org.lessons.pizzeria.controller;

import jakarta.validation.Valid;
import org.lessons.pizzeria.model.Pizza;
import org.lessons.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

//    @GetMapping()
//    public String index(Model model) {
////    creo la lista la inserisco in una variabile che poi passo alla view
//        List<Pizza> pizzas = pizzaRepository.findAll();
//        model.addAttribute("pizzas", pizzas);
//        return "/pizzas/index";
//    }

    //    metodo che puo' ricevere opzionalmente un parametro da query string.
//            se quel parametro c'e dobbiamo filtrare per quel parametro se quel parametro non c'e' dobbiamo restituire tutte le pizze'
//    "keyword e' la parola che ritroviamo nella barra di ricerca"

    //INDEX
    @GetMapping()
    public String index(@RequestParam(name = "keyword", required = false) String searchString, Model model) {
        List<Pizza> pizzas;
        if (searchString == null || searchString.isBlank()) {
            pizzas = pizzaRepository.findAll();
        } else {
//            pizzas = pizzaRepository.findByNome(searchString);
            pizzas = pizzaRepository.findByNomeContainingIgnoreCase(searchString);
        }
        model.addAttribute("pizzas", pizzas);
        return "/pizzas/index";

    }

    //    SHOW
    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable Integer id) {
        Optional<Pizza> pizzaId = pizzaRepository.findById(id);
        if (pizzaId.isPresent()) {
            Pizza pizzaShow = pizzaId.get();
            model.addAttribute("pizzaDetail", pizzaShow);
            return "/pizzas/show";
        } else {
            return "redirect:/pizzas";
        }
    }

    //CREATE get del browser per mostrare il form
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "/pizzas/create"; //view di riferimento
    }

    //post del browser con all'interno gli elementi scritti nel form'
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {
//        i dati della pizza sono dentro all'oggetto' formPizza

//        verifico se in validazione ci sono stati errori
        if (bindingResult.hasErrors()) {
//            se ci sono errori

            return "/pizzas/create";
        } else {
            formPizza.setCreatedAt(LocalDateTime.now());

//            save vuole un entita' come parametro il metodo fa un create sql se non trova la PK altrimenti fa update
            pizzaRepository.save(formPizza);
            return "redirect:/pizzas";
        }
    }

    //UPDATE
    @GetMapping("edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
//    ver pizza con quell'id' uso findById di Repository per creare un Optional
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isEmpty()) {
//    se non esiste lancio eccezione
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la pizza con id" + id + "non e' stata trovata"); //eccezione che deve ridare una risposta http quindi uno status
//    aggiungo la pizza al model(get restituisce la Pizza che in Optional(che e' un contenitore di oggetti Pizza))

        }
        model.addAttribute("pizza", result.get());
        return "pizzas/edit";
    }
}


//DELETE
//    @PostMapping("/delete/{id}")
//    public String delete(@PathVariable Integer id){}
////verifichiamo che esista la pizza con quell'id
//    Pizza pizzaDaCancellare = get
//    //redirect alla lista
//}








