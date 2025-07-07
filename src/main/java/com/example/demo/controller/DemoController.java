package com.example.demo.controller;

import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/")
public class DemoController {

    @GetMapping("/remove")
    public ResponseEntity<String> removeFirstAndLast(@RequestParam String original) {
        if (original == null || original.length() < 2) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST);
        }

        if (original.length() == 2) {
            return ResponseEntity.ok("");
        }

        String result = original.substring(1, original.length() - 1);
        return ResponseEntity.ok(result);
    }
}
