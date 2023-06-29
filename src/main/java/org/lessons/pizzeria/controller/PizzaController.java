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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
//        Optional<Pizza> pizzaId = pizzaRepository.findById(id);
//        if (pizzaId.isPresent()) {
//            Pizza pizzaShow = pizzaId.get();
//            model.addAttribute("pizzaDetail", pizzaShow);
//            return "/pizzas/show";
//        } else {
//            return "redirect:/pizzas";
//        }
        Pizza pizzaById = getPizzaById(id);
        model.addAttribute("pizzaDetail", pizzaById);
        return "/pizzas/show";
    }

    //CREATE get del browser per mostrare il form
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
//        return "/pizzas/create"; //view di riferimento
        return "/pizzas/edit";
    }

    //post del browser con all'interno gli elementi scritti nel form'
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        i dati della pizza sono dentro all'oggetto' formPizza

//        verifico se in validazione ci sono stati errori
        if (bindingResult.hasErrors()) {
//            se ci sono errori

            return "/pizzas/edit";
        } else {
            formPizza.setCreatedAt(LocalDateTime.now());

//            save vuole un entita' come parametro il metodo fa un create sql se non trova la PK altrimenti fa update
            pizzaRepository.save(formPizza);
            redirectAttributes.addFlashAttribute("message", "Pizza " + formPizza.getNome() + " creata");
            return "redirect:/pizzas";
        }
    }

    //UPDATE
    @GetMapping("edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Pizza pizzaById = getPizzaById(id);
        model.addAttribute("pizza", pizzaById);
        return "/pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Pizza PizzatoEdit = getPizzaById(id); //fotografia della pizza pre-modifica
//        trasferisco su formPizza tutti i dati che non sono presenti nel form(altrimenti li perdo)
        formPizza.setId(PizzatoEdit.getId());
        formPizza.setCreatedAt(PizzatoEdit.getCreatedAt());
//        salvo i dati passando a pizzaRepo il formPizza completo
        pizzaRepository.save(formPizza);
        redirectAttributes.addFlashAttribute("message", "Pizza " + PizzatoEdit.getNome() + " modificata");
        return "redirect:/pizzas";

    }

    //DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        //verifichiamo che esista la pizza con quell'id
        Pizza pizzaToDelete = getPizzaById(id);
//    lo cancelliamo (ci siamo passati l'entity e dunque siamo certi che se arrivo a questo punto ho preso l'entity per id             diversamente il nostro metodo throws)
        pizzaRepository.delete(pizzaToDelete);
        //redirect alla lista
//        prima di fare la ridirect voglio passare una mappa di attributi ed usero redirectAttributes(interfaccia di specializzazione del model) (ha un nome e un valore)
        redirectAttributes.addFlashAttribute("message", "Pizza " + pizzaToDelete.getNome() + " eliminata dalla lista.");
//        Aggiungo un messaggio di successo come flashattribute

        return "redirect:/pizzas";

    }
//Metodo per selezionare pizza da DB per ID o lancio di eccezione

    private Pizza getPizzaById(Integer id) {
//    ver pizza con quell'id' uso findById di Repository per creare un Optional
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isEmpty()) {
//    se non esiste lancio eccezione
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la pizza con id" + id + "non e' stata trovata"); //eccezione che deve ridare una risposta http quindi uno status
//    aggiungo la pizza al model(get restituisce la Pizza che in Optional(che e' un contenitore di oggetti Pizza))

        }
        return result.get();
    }
}













