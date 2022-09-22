package hr.ivanakasalo.zadatak.controllers;

import hr.ivanakasalo.zadatak.podaci.generiranje.GeneriranjeOsoba;
import hr.ivanakasalo.zadatak.services.OsobaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayOutputStream;

@Controller
public class MainController {

    @Autowired
    private OsobaService service;

    private GeneriranjeOsoba generiranjeDatoteke;

    public MainController() {
        generiranjeDatoteke = new GeneriranjeOsoba();
    }

    @GetMapping("")
    public String home() {
        return "redirect:/ucitavanje";
    }

    @GetMapping("ucitavanje")
    public ModelAndView ucitavanjeDatoteke() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("osobe", service.dohvatiSve());
        modelAndView.setViewName("view/ucitavanje-view.html");

        return modelAndView;
    }

    @PostMapping("ucitavanje")
    public String ucitajDatoteku(@RequestParam MultipartFile file) {
        service.spremiOsobe(file);
        return "redirect:/ucitavanje";
    }

    @GetMapping("generiranje")
    public String generiranjeCsvDatoteke() {

        return "view/generiranje-view.html";
    }


    @PostMapping("generiranje")
    @ResponseBody
    public ResponseEntity<byte[]> getInvoice() {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"podaci.csv\"");
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
        ByteArrayOutputStream baos = generiranjeDatoteke.generiraj(100);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                .headers(headers)
                .body(baos.toByteArray());
    }
}
