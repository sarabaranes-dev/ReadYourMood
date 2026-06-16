package it.uniroma3.siw.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.service.LibroService;
import java.util.List;

@RestController
@RequestMapping("/rest")
@CrossOrigin(origins = "http://localhost:5174") 
public class LibroRestController {

    @Autowired 
    private LibroService libroService;

    @GetMapping("/libri")
    public List<Libro> getLibri() {
       
        return libroService.findAll();
    }
}