package ru.student.lr_1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.student.lr_1.model.Response;
import ru.student.lr_1.model.Request;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class MyController {

    @PostMapping(value="/feedback")
    public ResponseEntity<Response> feedback(@RequestBody Request request) {

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(request.getSystemTime())
                .code("success")
                .errorCode("")
                .errorMessage("")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
