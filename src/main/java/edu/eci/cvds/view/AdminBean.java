/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.persistance.PersistenceException;
import edu.eci.cvds.samples.entities.Horario;
import edu.eci.cvds.samples.entities.Recurso;
import edu.eci.cvds.samples.entities.TipoUsuario;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.exceptions.ExcepcionServiciosBiblioteca;
import edu.eci.cvds.samples.services.ServiciosBiblioteca;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.shiro.authc.UsernamePasswordToken;
import edu.eci.cvds.security.ApacheShiroLogger;
import edu.eci.cvds.security.IniciarSesion;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

/**
 *
 * @author 2146516
 */
@SuppressWarnings("deprecation")
@ManagedBean(name="recursosBean")
@SessionScoped

public class AdminBean extends BasePageBean{
    @Inject
    private ServiciosBiblioteca serviciosBiblioteca;
    private Recurso selectedRec;
    private List<Recurso> recursos;
    private List<String> horariosSeleccionados;
    private String nombre,ubicacion,tipo,estado;
    private int capacidad;
    private Time horaInicio;
    private Time horaFin;
    
    ArrayList<String> tipos = new ArrayList<String>() { 
            {
                add("Tipo");
                add("Dispositivo multimedia"); 
                add("Libro"); 
                add("Sala de estudio"); 
            } 
        }; 
    ArrayList<String> estados = new ArrayList<String>() { 
            {
                add("Disponible");
                add("Dañado"); 
                
            } 
        }; 
    private ArrayList<String> horarios = new ArrayList<String>() { 
            {
                add("7:00-8:30");
                add("8:30-10:00"); 
                add("10:00-11:30");
                add("11:30-13:00");
                add("13:00-14:30");
                add("14:30-16:00");
                add("16:00-17:30");
                add("17:30-19:00");
            } 
    };

    public ServiciosBiblioteca getServiciosBiblioteca() {
        return serviciosBiblioteca;
    }

    public void setServiciosBiblioteca(ServiciosBiblioteca serviciosBiblioteca) {
        this.serviciosBiblioteca = serviciosBiblioteca;
    }

    public List<Recurso> getRecursos() {
        
        try {
            return serviciosBiblioteca.consultarRecursos();
        } catch (PersistenceException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setRecursos(List<Recurso> recursos) {
        this.recursos = recursos;
    }

    public Recurso getSelectedRec() {
        return selectedRec;
    }

    public void setSelectedRec(Recurso selectedRec) {
        this.selectedRec = selectedRec;
    }
    public void registrarRecurso() throws PersistenceException{
        
        Recurso newRec = new Recurso(0,"Disponible",this.nombre,this.ubicacion,this.tipo,this.capacidad,this.horaInicio,this.horaFin);
        serviciosBiblioteca.addRecurso(newRec);        
        this.clear();
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    private void clear() {
        this.capacidad =0;
        this.nombre = "";
        this.tipo ="";
        this.ubicacion ="";
    }

    public ArrayList<String> getTipos() {
        return tipos;
    }

    public void setTipos(ArrayList<String> tipos) {
        this.tipos = tipos;
    }

       

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<String> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<String> estados) {
        this.estados = estados;
    }
    public void modificarEstado(int id) throws PersistenceException{
        
        serviciosBiblioteca.cambiarEstadoRecurso(id,this.estado);
        
    }
    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Cambiado",  "Estado cambiado a: " + estado) );
        
    }

    public List<String> getHorariosSeleccionados() {
        return horariosSeleccionados;
    }

    public void setHorariosSeleccionados(List<String> horariosSeleccionados) {
        this.horariosSeleccionados = horariosSeleccionados;
    }

    public ArrayList<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<String> horarios) {
        this.horarios = horarios;
    }

    
    
    
}