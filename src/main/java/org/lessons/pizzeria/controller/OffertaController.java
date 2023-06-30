package org.lessons.pizzeria.controller;

import jakarta.validation.Valid;
import org.lessons.pizzeria.model.Offerta;
import org.lessons.pizzeria.model.Pizza;
import org.lessons.pizzeria.repository.OffertaRepository;
import org.lessons.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/offerte")
public class OffertaController {
    @Autowired
    private OffertaRepository offertaRepository;
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/create")
    public String create(@RequestParam("pizzaId") Integer pizzaId, Model model) {
//        creo una nuova offerta
        Offerta offerta = new Offerta();
//        precarico la data di offerta con la data odierna
        offerta.setDataInizioOfferta(LocalDate.now());
//        precarico la pizza pizzarepository dopo autowired mi passa il pizzaid come optional
        Optional<Pizza> pizza = pizzaRepository.findById(pizzaId);
        if (pizza.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza con id" + pizzaId + "non trovata");
        }
//        se trovo la pizza tra quelle che hanno offerte la setto tramite setPizza
        offerta.setPizza(pizza.get());
        model.addAttribute("offerta", offerta);//qui collega al modelattribute del post
        return "offerte/form";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("offerta") Offerta formOfferta,//in modelattribute dico che spring mantenga assegnato al model e che l'attributo si chiama offerta
                           BindingResult bindingResult) {
        // valido
        if (bindingResult.hasErrors()) {
            // se ci sono errori ricreo il template del form
            return "/offerte/form";
        }
        // se non ci sono errori salvo il borrowing
        offertaRepository.save(formOfferta);
        // faccio una redirect alla pagina di dettaglio del libro
        return "redirect:/pizzas/" + formOfferta.getPizza().getId();
    }
}
