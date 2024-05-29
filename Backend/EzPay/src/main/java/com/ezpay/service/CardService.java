package com.ezpay.service;


import com.ezpay.exceptions.CardNotFoundException;
import com.ezpay.model.entity.Card;
import com.ezpay.repository.CardRepository;
import com.ezpay.utils.CardDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Transactional
    public Card createCard(CardDto cardDto){

        Card  card= new Card();

        card.setFullName(cardDto.getFullName());
        card.setCvv(cardDto.getCvv());
        card.setType(cardDto.getType());
        card.setCreationDate(cardDto.getCreationDate());
        card.setExpirationDate(cardDto.getExpirationDate());
        card.setCardNumber(cardDto.getCardNumber());
        card.setActive(true);

        cardRepository.save(card);

        return card;
    }

    @Transactional
    public void darBaja(Long id){

        Card card = findById(id);
        card.setActive(false);

        cardRepository.save(card);

    }

    public Card findById(Long id){

        Card card = cardRepository.findById(id).orElseThrow();

        return card;
    }

//    public List<Card> cardIsActive(){
//
//        List<Card> cards = cardRepository.findActiveCards();
//
//        return  cards;
//    }

    @Transactional
    public void deleteCard(Long id){

        Optional <Card> card = cardRepository.findById(id);

        if (card.isPresent()){
            cardRepository.delete(card.get());
        }
    }

    public void Validation(CardDto dto){

        try {
            if(dto.getFullName().isEmpty()){
                new CardNotFoundException("Nombre vacio o invalido");
            } else {
                if (dto.getCardNumber() != 16L) { new CardNotFoundException("Longitud de la tarjeta invalida");{
                    if (dto.getCreationDate() == null || dto.getExpirationDate() == null){ new CardNotFoundException("Fecha de creacion o vencimiento invalida");}
                    if (dto.getCvv() != 3){ new CardNotFoundException("Longitud de cvv invalida " + dto.getCvv());}
                    if(dto.getType().name().isEmpty()){ new CardNotFoundException("Tipo de tarjeta invalido");}
                    if(dto.isActive()){ new CardNotFoundException("Tarjeta dada de baja");}
                }
                }
            }
        }catch (CardNotFoundException e){

        }


    }

}
