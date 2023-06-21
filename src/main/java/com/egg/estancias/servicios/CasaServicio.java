package com.egg.estancias.servicios;

import com.egg.estancias.entidades.Casa;
import com.egg.estancias.repositorios.CasaRepositorio;
import com.egg.estancias.repositorios.FamiliaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CasaServicio {
    
    @Autowired
    private CasaRepositorio casaRepositorio;
    @Autowired
    private FamiliaRepositorio familiaRepositorio;    
    
    public void crear(){
        
    }
    
    public List<Casa> listarCasas(){
       List<Casa> casas = casaRepositorio.buscarTodo();
       return casas;
       
    }
    
}
