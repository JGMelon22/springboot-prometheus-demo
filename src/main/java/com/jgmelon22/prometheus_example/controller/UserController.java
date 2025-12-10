package com.jgmelon22.prometheus_example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public String criarUsuario() {
        return "Usuario criado";
    }

    @GetMapping
    public String listarUsuarios() {
        return "Usuarios listados";
    }
}