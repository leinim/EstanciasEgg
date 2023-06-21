package com.egg.estancias.controladores;

import com.egg.estancias.entidades.Casa;
import com.egg.estancias.servicios.CasaServicio;
import com.egg.estancias.servicios.ReservaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reserva")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
public class ReservaControlador {
    
    @Autowired
    private ReservaServicio reservaServicio;
    @Autowired
    private CasaServicio casaServicio;
        
    @GetMapping("/nueva")
    public String registro(ModelMap modelo){
        
        List<Casa> casas = casaServicio.listarCasas();        
                
        modelo.addAttribute("casas", casas);
        
        return "crear_reserva.html";
    }
    
    @PostMapping("/reservado")
    public String registro(@RequestParam String email){
        return "redirect:/inicio";
    }
    
    
}
