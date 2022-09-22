package hr.ivanakasalo.zadatak.controllers;

import hr.ivanakasalo.zadatak.services.OsobaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private OsobaService service;

    @GetMapping("")
    public String home() {
        return "ucitavanje";
    }

    @GetMapping("ucitavanje")
    public String ucitavanjeDatoteke() {
        service.spremi();
        return "view/ucitavanje-view.html";
    }
}
