package ru.trushkov.library.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class Response {

    public static <T> ResponseEntity<T> sendOk(T response) {
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static ResponseEntity<Void> sendCreated() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public static ResponseEntity<Void> sendNoContent() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
