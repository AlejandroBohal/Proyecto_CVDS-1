/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.persistance;

import edu.eci.cvds.samples.entities.ReporteRecurso;
import java.util.List;

/**
 *
 * @author AlejandroB
 */
public interface ReporteRecursoDAO {

    public List<ReporteRecurso> loadRecursosMasUsados();

    public List<ReporteRecurso> loadRecursosMenosUsados();
        
    
}
