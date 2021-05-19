package com.example.testnizadatak.controllers;


import com.example.testnizadatak.models.Company;
import com.example.testnizadatak.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyRepository companyRepository;

    @GetMapping("/all")
    private List<Company> getAll(){
        return companyRepository.findAll();
    }
}
