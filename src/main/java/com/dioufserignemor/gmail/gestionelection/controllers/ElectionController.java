package com.dioufserignemor.gmail.gestionelection.controllers;

import com.dioufserignemor.gmail.gestionelection.services.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("elections")
public class ElectionController {

    @Autowired
    private ElectionService electionService ;
}
