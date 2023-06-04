package com.matalonigarcia.clinicaodontologica.controller;

import com.matalonigarcia.clinicaodontologica.entity.Domicilio;
import com.matalonigarcia.clinicaodontologica.service.impl.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {
    private final DomicilioService domicilioService;

    @Autowired
    public DomicilioController(DomicilioService domicilioService) {
        this.domicilioService = domicilioService;
    }

    @GetMapping("/index")
    public List<Domicilio> listarTodosLosDomicilios() {
        return domicilioService.listarTodosLosDomicilios();
    }
}
