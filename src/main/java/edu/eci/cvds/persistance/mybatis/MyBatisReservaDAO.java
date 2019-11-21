package edu.eci.cvds.persistance.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.persistance.ReservaDAO;
import edu.eci.cvds.persistance.mybatis.mappers.ReservaMapper;
import edu.eci.cvds.samples.entities.Recurso;
import edu.eci.cvds.samples.entities.Reserva;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;

/**
 *
 * @author LEVIATAN
 */
public class MyBatisReservaDAO implements ReservaDAO{
    @Inject
    private ReservaMapper reservaMapper;
    @Override
    public void addReserva(Reserva reserva) {
        reservaMapper.addReserva(reserva);   
    }
    @Override
    public List<Reserva> loadReservas(){
        return reservaMapper.consultarReservas();
    }

    @Override
    public ArrayList<Reserva> loadReservaById(int id) {
        return reservaMapper.consultarReservasPorId(id);
    }
    
    
}
