package com.springnetflixossmicroservices.cardservice.api;

import com.springnetflixossmicroservices.cardservice.model.Card;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class CardServiceController {

    private List<Card> fakeRepo;

    @PostConstruct
    public void init() {
        fakeRepo = new ArrayList<>();
        fakeRepo.add(new Card(1L, "David Warner", "123", "12/31/2021"));
        fakeRepo.add(new Card(2L, "Mike Hussey", "123", "10/07/2021"));
        fakeRepo.add(new Card(3L, "Stuart Broad", "123", "2/2/2021"));
        fakeRepo.add(new Card(4L, "David Cooper", "123", "8/1/2021"));
    }

    @GetMapping(value = "/cards")
    public List<Card> getCards() {
        return fakeRepo;
    }

    @GetMapping(value = "/card/{cardId}")
    public Card getCard(@PathVariable Long cardId) {
        return Optional.ofNullable(fakeRepo.stream().filter(card -> card.getId().equals(cardId)).reduce(null, (u, v) ->{
            if (u != null && v != null)
                throw  new IllegalStateException("More than one cardId found");
            else return u == null ? v : u;
        })).get();
    }

    @PostMapping(value = "/new-card", headers = "Accept=application/json")
    public void createCard(@RequestBody Card card) {
        if (card.getId() == null) {
            fakeRepo.add(card);
        }
        System.out.println("New Card : " + card);
    }
}
