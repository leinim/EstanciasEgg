/*La entidad cliente modela información de los clientes que desean mandar a sus hijos a alguna de
las casas de las familias. Esta entidad es modelada por el nombre del cliente, dirección (calle,
numero, código postal, ciudad y país) y su correo electrónico. El repositorio que persiste a esta
entidad (ClienteRepositorio) debe contener los métodos necesarios para guardar/actualizar los
datos de un cliente, realizar consultas y eliminar.*/
package com.egg.estancias.entidades;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Cliente extends Usuario {      
    
    private String nombre;
    private String calle;
    private int numero;
    private String codPostal;
    private String ciudad;
    private String pais;
    
    
}
