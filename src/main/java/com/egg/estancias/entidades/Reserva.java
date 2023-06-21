/*La entidad reserva modela los datos de las reservas y estancias realizadas por alguno de los
clientes. Cada estancia o reserva la realiza un cliente, y además, el cliente puede reservar varias
habitaciones al mismo tiempo (por ejemplo para varios de sus hijos), para un periodo determinado
(fecha_llegada, fecha_salida). El repositorio que persiste a esta entidad (ReservaRepositorio) debe
contener los métodos necesarios para realizar una reserva, actualizar los datos (por ejemplo, fecha
de la reserva), realizar consultas de las reservas realizadas para una determinada vivienda y
eliminar reserva.*/
package com.egg.estancias.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Reserva {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Casa casa;
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;    
    
    
}
