/*La entidad casa modela los datos de las casas donde las familias ofrecen alguna habitación. De
cada una de las casas se almacena la dirección (calle, numero, código postal, ciudad y país), el
periodo de disponibilidad de la casa (fecha_desde, fecha_hasta), la cantidad de días mínimo de
estancia y la cantidad máxima de días, el precio de la habitación por día y el tipo de vivienda. El
repositorio que persiste a esta entidad (CasaRepositorio) debe contener los métodos necesarios
para guardar/actualizar los datos de una vivienda, realizar consultas y eliminar.*/
package com.egg.estancias.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Casa {
   
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String calle;
    private int numero;
    private String codPostal;
    private String ciudad;
    private String pais;    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDesde;    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaHasta;    
    private Integer minDias;
    private Integer maxDias;
    private Double precio;
    private String tipoVivienda;
    @OneToOne
    private Imagen imagen;
    @OneToOne
    private Familia familia;
    
}
