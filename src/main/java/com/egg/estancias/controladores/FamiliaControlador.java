package com.egg.estancias.controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/familia")
@PreAuthorize("hasAnyRole('ROLE_FAMILY', 'ROLE_ADMIN')")
public class FamiliaControlador {
    
    @GetMapping("/registro")
    public String registro(){
        return "crear_familia.html";
    }
    
    @PostMapping("/registrado")
    public String registro(@RequestParam String alias, @RequestParam String email, @RequestParam String clave, String clave2, @RequestParam String nombre, @RequestParam int edadMin, @RequestParam int edadMax, @RequestParam int numHijos){
        
        
        return "redirect:/inicio";
    }
    
}
