package com.egg.estancias.servicios;

import com.egg.estancias.entidades.Casa;
import com.egg.estancias.entidades.Familia;
import com.egg.estancias.entidades.Imagen;
import com.egg.estancias.errores.MiException;
import com.egg.estancias.repositorios.CasaRepositorio;
import com.egg.estancias.repositorios.FamiliaRepositorio;
import com.egg.estancias.repositorios.ImagenRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CasaServicio {
    
    @Autowired
    private CasaRepositorio casaRepositorio;
//    @Autowired
//    private FamiliaRepositorio familiaRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;
    
    public void crear(MultipartFile archivo, Familia familia, String calle, int numero, String codPostal, String ciudad, String pais, Date fechaDesde, Date fechaHasta, int minDias, int maxDias, double precio, String tipoVivienda) throws MiException{
        
        try {
            Casa casa = new Casa();           
            
            casa.setCalle(calle);
            casa.setNumero(numero);
            casa.setCodPostal(codPostal);
            casa.setCiudad(ciudad);
            casa.setPais(pais);
            casa.getFechaDesde();
            casa.setFechaHasta(fechaHasta);
            casa.setMinDias(minDias);
            casa.setMaxDias(maxDias);
            casa.setPrecio(precio);
            casa.setTipoVivienda(tipoVivienda);
            casa.setFamilia(familia);
            
            Imagen imagen = imagenServicio.guardar(archivo);
            
            casa.setImagen(imagen);
            
            
        } catch (MiException ex) {
            System.out.println(ex.getMessage());
        }       
    }
    
    public void actualizar(MultipartFile archivo, String idCasa, String calle, int numero, String codPostal, String ciudad, String pais, Date fechaDesde, Date fechaHasta, int minDias, int maxDias, double precio, String tipoVivienda) throws MiException {
        
        Optional<Casa> respuesta = casaRepositorio.findById(idCasa);
        
        if (respuesta.isPresent()){
            Casa casa = respuesta.get();
            
            casa.setCalle(calle);
            casa.setNumero(numero);
            casa.setCodPostal(codPostal);
            casa.setCiudad(ciudad);
            casa.setPais(pais);
            casa.getFechaDesde();
            casa.setFechaHasta(fechaHasta);
            casa.setMinDias(minDias);
            casa.setMaxDias(maxDias);
            casa.setPrecio(precio);
            casa.setTipoVivienda(tipoVivienda);
            
            String idImagen = null;
            
            if (casa.getImagen() != null){
                idImagen = casa.getImagen().getId();
            }
            
            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
            casa.setImagen(imagen);
            
            
            casaRepositorio.save(casa);
        }
        
        
    }
    
    
    
    public List<Casa> listarCasas(){
       List<Casa> casas = casaRepositorio.buscarTodo();
       return casas;
       
    }
    
}
