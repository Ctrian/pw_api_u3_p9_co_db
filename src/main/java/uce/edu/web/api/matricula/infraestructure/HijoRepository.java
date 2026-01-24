package uce.edu.web.api.matricula.infraestructure;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.matricula.domain.Hijo;

@ApplicationScoped
public class HijoRepository implements PanacheRepository<Hijo> {
    
    public List<Hijo> buscarPorIdDelEstudiante(Integer id) {
        return find("estudiante.id", id).list();
    }
    
    public Hijo buscarPorIdDelEstudianteV2(Integer id) {
        return find("estudiante.id", id).list().getFirst();
    }
}
