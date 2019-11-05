/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.persistance;
import edu.eci.cvds.samples.entities.Recurso;
import java.util.List;
/**
 *
 * @author 2152805
 */


public interface RecursoDAO  {
    public void addRecurso(Recurso recurso) throws PersistenceException;
    public List<Recurso> loadRecursos() throws PersistenceException;
    public Recurso loadRecurso(int id) throws PersistenceException;
}
        