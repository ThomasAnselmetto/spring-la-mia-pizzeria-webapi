package org.lessons.pizzeria.controller;

import jakarta.validation.Valid;
import org.lessons.pizzeria.model.Pizza;
import org.lessons.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "/pizzas/create"; //view di riferimento
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {
//        i dati della pizza sono dentro all'oggetto' formPizza
//        verifico se in validazione ci sono stati errrori
        if (bindingResult.hasErrors()) {
//            se ci sono errori
            return "/pizzas/create";
        } else {
            pizzaRepository.save(formPizza);
            return "redirect:/pizzas";
        }
    }

}




