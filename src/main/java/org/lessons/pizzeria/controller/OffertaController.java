package org.lessons.pizzeria.controller;

import org.lessons.pizzeria.model.Offerta;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/offerte")
public class OffertaController {


    @GetMapping("/create")
    public String create(@RequestParam("pizzaId") Integer pizzaId, Model model) {
//        creo una nuova offerta
        Offerta offerta = new Offerta();
//        precarico la data di offerta con la data odierna
        offerta.setDataInizioOfferta(LocalDate.now());
//        precarico la pizza
        
        return "offerte/form";
    }

}
