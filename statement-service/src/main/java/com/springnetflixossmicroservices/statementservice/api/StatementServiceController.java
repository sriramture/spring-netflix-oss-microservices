package com.springnetflixossmicroservices.statementservice.api;

import com.springnetflixossmicroservices.statementservice.model.Statement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StatementServiceController {
    private List<Statement> fakeRepo;

    @PostConstruct
    public void init() {
        fakeRepo = new ArrayList<>();
        fakeRepo.add(new Statement(1L, 1L, "2/12/2019", "100"));
        fakeRepo.add(new Statement(2L, 2L, "2/12/2019", "120"));
        fakeRepo.add(new Statement(3L, 3L, "2/12/2019", "10"));
        fakeRepo.add(new Statement(4L, 4L, "2/12/2019", "2900"));
        fakeRepo.add(new Statement(5L, 3L, "2/12/2019", "101"));
        fakeRepo.add(new Statement(6L, 1L, "2/12/2019", "1"));
    }

    @GetMapping(value = "/statements")
    public List<Statement> getStatements() {
        return fakeRepo;
    }

    @GetMapping(value = "/statement/{id}")
    public Statement getStatementById(@PathVariable Long id) {
        return Optional.ofNullable(fakeRepo.stream().filter(statement -> statement.getId().equals(id)).reduce(null, (u, v) ->{
            if (u != null && v != null)
                throw  new IllegalStateException("More than one cardId found");
            else return u == null ? v : u;
        })).get();
    }

    @GetMapping(value = "/statement")
    public List<Statement> getStatements(@RequestParam Long cardId) {
        if (cardId != null) {
            return fakeRepo.stream().filter(statement -> statement.getCardId().equals(cardId)).collect(Collectors.toList());
        }
        return null;
    }
}
