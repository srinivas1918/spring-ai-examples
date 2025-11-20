package com.example.chaptor_two.game;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AskController {

    private BoardGameService boardGameService;

    public AskController(BoardGameService boardGameService){
        this.boardGameService = boardGameService;
    }

    @PostMapping(path="/ask", produces="application/json")
    public Answer ask(@RequestBody @Valid  Question question) {
        return boardGameService.askQuestion(question);
    }
}
