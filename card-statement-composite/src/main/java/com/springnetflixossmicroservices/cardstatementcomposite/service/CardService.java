package com.springnetflixossmicroservices.cardstatementcomposite.service;

import com.springnetflixossmicroservices.cardstatementcomposite.model.CardVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CardService {

    final static String PREFIX = "api/";

    @GetMapping(value = PREFIX + "cards")
    public List<CardVO> getCards();

    @GetMapping(value = PREFIX + "card/{cardId}")
    public CardVO getCard(@PathVariable Long cardId);

    @PostMapping(value = PREFIX + "new-card")
    public void createCard(@RequestBody CardVO card);
}
