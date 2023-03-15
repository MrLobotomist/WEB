package ru.student.lr_1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.student.lr_1.model.Request;

import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
public class MyController {

    @PostMapping(value="/feedback")
    public void feedback(@RequestBody Request request) {

        log.info("Входящий request : " + String.valueOf(request));

    }
}

