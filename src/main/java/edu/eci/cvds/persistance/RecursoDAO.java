package edu.eci.cvds.persistance;

import edu.eci.cvds.samples.entities.Recurso;
import static java.util.Collections.list;
import java.util.List;



public interface RecursoDAO  {
    public void addRecurso(Recurso recurso) throws PersistenceException;
    public List<Recurso> loadRecursos() throws PersistenceException;
    public Recurso loadRecurso(int id) throws PersistenceException;
    public void updateEstadoRecurso(int id, String estadoNuevo) throws PersistenceException;

    public List<Recurso> loadRecursosDisponibles() throws PersistenceException;

    public void deleteUltimoRecursoTest();

}
        