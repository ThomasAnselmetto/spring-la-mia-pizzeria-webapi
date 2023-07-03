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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String doCreate(@Valid @ModelAttribute("offerta") Offerta formOfferta, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // valido
        if (bindingResult.hasErrors()) {
            // se ci sono errori ricreo il template del form
            return "/offerte/form";
        }
        // se non ci sono errori salvo il borrowing
        offertaRepository.save(formOfferta);
        // faccio una redirect alla pagina di dettaglio del libro
        redirectAttributes.addFlashAttribute("message", "Creata " + formOfferta.getNomeOfferta());
        return "redirect:/pizzas/" + formOfferta.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
//        cerco l'offerta sul db'
        Optional<Offerta> offerta = offertaRepository.findById(id);
        if (offerta.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("offerta", offerta.get());
        return "/offerte/form";
    }

    @PostMapping("/edit/{id}")
    public String toEdit(@PathVariable Integer id, @Valid @ModelAttribute("offerta") Offerta formOfferta, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Optional<Offerta> offertaToEdit = offertaRepository.findById(id);
        if (offertaToEdit.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
//            setto l'id al mio formOfferta'
        formOfferta.setId(id);
//            salvo l'offerta su DB update'
        offertaRepository.save(formOfferta);
//            faccio un redirect passando i dati del form al dettaglio tramite getpizza.getid
        redirectAttributes.addFlashAttribute("message", "Modificata " + formOfferta.getNomeOfferta());
        return "redirect:/pizzas/" + formOfferta.getPizza().getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Offerta> offertaToDelete = offertaRepository.findById(id);
        if (offertaToDelete.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        offertaRepository.delete(offertaToDelete.get());
        redirectAttributes.addFlashAttribute("message", "Cancellata " + offertaToDelete.get().getNomeOfferta());
        return "redirect:/pizzas/" + offertaToDelete.get().getPizza().getId();
    }
}

