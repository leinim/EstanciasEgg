package com.egg.estancias.controladores;

import com.egg.estancias.errores.MiException;
import com.egg.estancias.servicios.FamiliaServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/familia")
public class FamiliaControlador {
    
    @Autowired
    private FamiliaServicio familiaServicio;
    
    @GetMapping("/registro")
    public String registro(){
        return "crear_familia.html";
    }
    
    @PostMapping("/registrado")
    public String registro(@RequestParam String alias, @RequestParam String email, @RequestParam String clave, String clave2, @RequestParam String nombre, @RequestParam int minEdad, @RequestParam int maxEdad, @RequestParam int numHijos){
        
        try {
            familiaServicio.crear(alias, clave, clave2, nombre, email, minEdad, maxEdad, numHijos);
        } catch (MiException ex) {
            Logger.getLogger(FamiliaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/login";
    }
    //@PreAuthorize("hasAnyRole('ROLE_FAMILY', 'ROLE_ADMIN')")
}
