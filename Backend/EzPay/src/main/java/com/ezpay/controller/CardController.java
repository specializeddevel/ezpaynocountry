package com.ezpay.controller;


import com.ezpay.model.entity.Card;
import com.ezpay.repository.CardRepository;
import com.ezpay.service.CardService;
import com.ezpay.utils.CardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/card")
public class
CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardRepository cardRepository;

    @PostMapping(value = "/creation")
    public ResponseEntity<Card> creationCard (@RequestBody CardDto card) {
        return ResponseEntity.ok(cardService.createCard(card));

    }

    @PostMapping("/baja/{id}")
    public String BajaCard (@PathVariable Long id){

        cardService.darBaja(id);

        return "La tarjeta se ha dado de baja";
    }

    @GetMapping()
    public ResponseEntity<List<Card>> listCard (){
        var response = cardRepository.findAll();

        return ResponseEntity.ok(response);
    }

//    @GetMapping(value = "allActive")
//    public ResponseEntity<List<Card>> allCards(){
//        var response = cardService.cardIsActive();
//        return ResponseEntity.ok(response);
//    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Card> card (@PathVariable Long id){
        return ResponseEntity.ok(cardService.findById(id));
    }
}
