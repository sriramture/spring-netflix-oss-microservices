package com.springnetflixossmicroservices.cardstatementcomposite.service;

import com.springnetflixossmicroservices.cardstatementcomposite.model.CardVO;
import com.springnetflixossmicroservices.cardstatementcomposite.model.StatementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class CardStatementCompositeController {
    @Autowired
    private CardClient cardClient;

    @Autowired
    private StatementClient statementClient;

    @GetMapping(value = "statement-by-card")
    public ResponseEntity<Map<CardVO, List<StatementVO>>> getStatementByCard(@RequestParam Long cardId) {
        Map<CardVO, List<StatementVO>> response = new HashMap<>();
        response.put(cardClient.getCard(cardId), statementClient.getStatements(cardId));

        return new ResponseEntity<Map<CardVO, List<StatementVO>>>(response, HttpStatus.OK);
    }
}
