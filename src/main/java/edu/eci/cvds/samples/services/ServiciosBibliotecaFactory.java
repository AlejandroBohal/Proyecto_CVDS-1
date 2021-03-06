package edu.eci.cvds.samples.services;

import static com.google.inject.Guice.createInjector;
import com.google.inject.Injector;
import edu.eci.cvds.persistance.HorarioDAO;
import org.mybatis.guice.XMLMyBatisModule;
import java.util.Optional;

import edu.eci.cvds.persistance.mybatis.MyBatisUserDAO;
import edu.eci.cvds.samples.services.impl.ServiciosBibliotecaImpl;
import edu.eci.cvds.persistance.RecursoDAO;
import edu.eci.cvds.persistance.ReporteHorarioDAO;
import edu.eci.cvds.persistance.ReporteRecursoDAO;
import edu.eci.cvds.persistance.ReservaDAO;
import edu.eci.cvds.persistance.UserDAO;
import edu.eci.cvds.persistance.mybatis.MyBatisHorarioDAO;
import edu.eci.cvds.persistance.mybatis.MyBatisRecursoDAO;
import edu.eci.cvds.persistance.mybatis.MyBatisReporteHorarioDAO;
import edu.eci.cvds.persistance.mybatis.MyBatisReporteRecursoDAO;
import edu.eci.cvds.persistance.mybatis.MyBatisReservaDAO;
import edu.eci.cvds.security.IniciarSesion;
import edu.eci.cvds.security.ApacheShiroLogger;

public class ServiciosBibliotecaFactory {
    
   private static ServiciosBibliotecaFactory instance = new ServiciosBibliotecaFactory();
   private static Optional<Injector> optInjector;
   
   private static Injector myBatisInjector(String env, String pathResource) {
       return createInjector(new XMLMyBatisModule() {
           @Override
           protected void initialize() {
               setEnvironmentId(env);
               setClassPathResource(pathResource);
               bind(UserDAO.class).to(MyBatisUserDAO.class);
               bind(RecursoDAO.class).to(MyBatisRecursoDAO.class);
               bind(ReservaDAO.class).to(MyBatisReservaDAO.class);
               bind(HorarioDAO.class).to(MyBatisHorarioDAO.class);
               bind(ReporteRecursoDAO.class).to(MyBatisReporteRecursoDAO.class);
               bind(ReporteHorarioDAO.class).to(MyBatisReporteHorarioDAO.class);
               bind(IniciarSesion.class).to(ApacheShiroLogger.class);
               bind(ServiciosBiblioteca.class).to(ServiciosBibliotecaImpl.class);
               
           }
       });
   }

   private ServiciosBibliotecaFactory(){
       optInjector = Optional.empty();
   }

   public static ServiciosBiblioteca getServiciosBiblioteca(){
       if (!optInjector.isPresent()) {
           optInjector = Optional.of(myBatisInjector("development","mybatis-config.xml"));
       }

       return optInjector.get().getInstance(ServiciosBiblioteca.class);
   }


   public static ServiciosBiblioteca getServiciosBibliotecaTesting(){
       if (!optInjector.isPresent()) {
           optInjector = Optional.of(myBatisInjector("test","mybatis-config-h2.xml"));
       }

       return optInjector.get().getInstance(ServiciosBiblioteca.class);
   }


   public static ServiciosBibliotecaFactory getInstance(){
       return instance;
   }

}