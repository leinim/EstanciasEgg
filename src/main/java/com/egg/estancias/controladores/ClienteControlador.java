package com.egg.estancias.controladores;

import com.egg.estancias.errores.MiException;
import com.egg.estancias.servicios.ClienteServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cliente")
@PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_ADMIN')")
public class ClienteControlador {
    
    @Autowired
    private ClienteServicio clienteServicio;
    
    @GetMapping("/registro")
    public String registro(){
        return "crear_cliente.html";
    }
    
    @PostMapping("/registrado")
    public String registro(@RequestParam String alias, @RequestParam String clave, String clave2, @RequestParam String email, @RequestParam String nombre, @RequestParam String calle, @RequestParam int numero, @RequestParam String codPostal, @RequestParam String ciudad, @RequestParam String pais, ModelMap modelo) {
        
        try {
            clienteServicio.crear(alias, clave, clave2, email, nombre, calle, numero, codPostal, ciudad, pais);
            
            modelo.put("exito", "El usuario ha sido registrado con éxito.");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "crear_cliente.html";
        }
        
        return "redirect:/inicio";
    }
    
}
