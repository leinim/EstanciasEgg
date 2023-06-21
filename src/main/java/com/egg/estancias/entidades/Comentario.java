/*La entidad comentario permite almacenar información brindada por los clientes sobre las casas en
las que ya han estado. El repositorio que persiste a esta entidad (ComentarioRepositorio) debe
contener los métodos necesarios para guardar los comentarios que realizan los clientes sobre una
determinada una vivienda.*/
package com.egg.estancias.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Comentario implements Serializable {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String descripcion;
    
    @ManyToOne
    private Casa casa;
    
}
