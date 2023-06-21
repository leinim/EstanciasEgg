package com.egg.estancias.controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/casa")
@PreAuthorize("hasAnyRole('ROLE_FAMILY', 'ROLE_ADMIN')")
public class CasaControlador {
    
    @GetMapping("/agregar")
    public String agregar(){
        return "crear_casa.html";
    }
    
    @PostMapping
    public String agregar(@RequestParam String calle){
        return "redirect:/inicio";
    }
    
}
