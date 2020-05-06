package com.springnetflixossmicroservices.cardstatementcomposite.service;

import com.springnetflixossmicroservices.cardstatementcomposite.model.StatementVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface StatementService {
    final static String PREFIX = "api/";

    @GetMapping(value = PREFIX + "statements")
    public List<StatementVO> getStatements();

    @GetMapping(value = PREFIX + "statement/{id}")
    public StatementVO getStatement(@PathVariable Long id);

    @GetMapping(value = PREFIX + "statement")
    public List<StatementVO> getStatements(@RequestParam Long cardId);
}
