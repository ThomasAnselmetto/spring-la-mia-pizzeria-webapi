package org.lessons.pizzeria.service;

import org.lessons.pizzeria.dto.PizzaDto;
import org.lessons.pizzeria.model.Pizza;
import org.lessons.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    // metodo che restituisce la lista di tutti i libri filtrata o no a seconda del parametro
    public List<Pizza> getAll(Optional<String> keywordOpt) {
        if (keywordOpt.isEmpty()) {
            return pizzaRepository.findAll();
        } else {
            return pizzaRepository.findByNome(keywordOpt.get());
        }
    }

    // metodo che restituisce il libro preso per id o un'eccezione se non lo trova
    public Pizza getById(Integer id) throws RuntimeException {
        Optional<Pizza> pizzaOpt = pizzaRepository.findById(id);
        if (pizzaOpt.isPresent()) {
            return pizzaOpt.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // metodo che salva un nuovo libro a partire da quello passato come parametro
    public Pizza create(Pizza pizza) throws RuntimeException {
        // valido l'Nome del pizza
//        if (!isUniqueNome(pizza)) {
//            throw new RuntimeException(pizza.getNome());
//        }
        // creo il pizza da salvare
        Pizza pizzaToPersist = new Pizza();
        // genero il timestamp di createdAt
        pizzaToPersist.setCreatedAt(LocalDateTime.now());
        // copio tutti i campi di pizza che mi interessano
        pizzaToPersist.setNome(pizza.getNome());
        pizzaToPersist.setDescrizione(pizza.getDescrizione());
        pizzaToPersist.setIngredienti(pizza.getIngredienti());
        pizzaToPersist.setPrezzo(pizza.getPrezzo());
        pizzaToPersist.setCover(pizza.getCover());
        // persisto il pizza
        return pizzaRepository.save(pizzaToPersist);
    }

    // metodo che crea un nuovo Pizza a partire da un PizzaDto
    public Pizza create(PizzaDto pizzaDto) throws RuntimeException {
        // converto il PizzaDto in un Pizza
        Pizza pizza = mapPizzaDtoToPizza(pizzaDto);
        // salvo il Pizza to database
        return create(pizza);
    }

    // metodo per creare un PizzaDto a partire dall'id di un Pizza salvato su db
    public PizzaDto getPizzaDtoById(Integer id) throws RuntimeException {
        Pizza pizza = getById(id);
        return mapPizzaToPizzaDto(pizza);
    }

    public Pizza update(PizzaDto pizzaDto) throws RuntimeException {
        // converto il PizzaDto in pizza
        Pizza pizza = mapPizzaDtoToPizza(pizzaDto);
        // cerco il pizza su database
        Pizza pizzaDb = getById(pizza.getId());
        pizzaDb.setNome(pizza.getNome());
        pizzaDb.setDescrizione(pizza.getDescrizione());
        pizzaDb.setIngredienti(pizza.getIngredienti());
        pizzaDb.setPrezzo(pizza.getPrezzo());
        pizzaDb.setCover(pizza.getCover());
        // salvo il pizza in update
        return pizzaRepository.save(pizzaDb);
    }

    // metodo per verificare se su database c'è già un pizza con lo stesso Nome del pizza passato come parametro lo lascio commentato perche' nome non e' unique
//    private boolean isUniqueNome(Pizza formPizza) {
//        List<Pizza> result = pizzaRepository.findByNome(formPizza.getNome());
//        return result.isEmpty();
//    }

    // metodo per convertire un PizzaDto in un Pizza
    private Pizza mapPizzaDtoToPizza(PizzaDto pizzaDto) {
        // creo un nuovo Pizza vuoto
        Pizza pizzaConvertita = new Pizza();
        // copio i campi con corrispondenza esatta
        pizzaConvertita.setId(pizzaDto.getId());
        pizzaConvertita.setNome(pizzaDto.getNome());
        pizzaConvertita.setDescrizione(pizzaDto.getDescrizione());
        pizzaConvertita.setIngredienti(pizzaDto.getIngredienti());
        pizzaConvertita.setPrezzo(pizzaDto.getPrezzo());
        // converto il campo cover,converto con il metodo prendo il cover preso dal form e lo trasformo in un array di bytes
        byte[] coverBytes = multipartFileToByteArray(pizzaDto.getCover());
        pizzaConvertita.setCover(coverBytes);

        return pizzaConvertita;
    }

    private PizzaDto mapPizzaToPizzaDto(Pizza pizza) {
        // creo un nuovo PizzaDto vuoto
        PizzaDto pizzaDto = new PizzaDto();
        // copio i campi con corrispondenza esatta
        pizzaDto.setId(pizza.getId());
        pizzaDto.setNome(pizza.getNome());
        pizzaDto.setDescrizione(pizza.getDescrizione());
        pizzaDto.setIngredienti(pizza.getIngredienti());
        pizzaDto.setPrezzo(pizza.getPrezzo());
        return pizzaDto;
    }

    //creo il metodo che prende in ingresso il parametro mpf di tipo MultipartFile e lo converte in un array di byte se tutto cva a buon fine e non ci sono null o empty o exception si assegna alla variabile bytes l'array'
    private byte[] multipartFileToByteArray(MultipartFile mpf) {
        byte[] bytes = null;
        if (mpf != null && !mpf.isEmpty()) {
            try {
                bytes = mpf.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }//IOExceptio(input output exception)
        return bytes;
    }
}