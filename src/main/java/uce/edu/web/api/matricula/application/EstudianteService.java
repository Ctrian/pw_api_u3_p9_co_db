package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped
@Transactional
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos() {
        return this.estudianteRepository.listAll();
    }

    public Estudiante consultarPorId(Integer id) {
        return this.estudianteRepository.findById(id.longValue());
    }

    public void crear(Estudiante estudiante) {
        this.estudianteRepository.persist(estudiante);
    }

    @Transactional
    public void actualizar(Integer id, Estudiante estudiante) {
        Estudiante estu = this.consultarPorId(id);
        estu.setApellido(estudiante.getApellido());
        estu.setNombre(estudiante.getNombre());
        estu.setFechaNacimiento(estudiante.getFechaNacimiento());
        // dirty changes
    }

    @Transactional
    public void actualizarParcial(Integer id, Estudiante estudiante) {
        Estudiante estu = this.consultarPorId(id);
        if (estudiante.getNombre() != null) {
            estu.setNombre(estudiante.getNombre());
        }
        if (estudiante.getApellido() != null) {
            estu.setApellido(estudiante.getApellido());
        }
        if (estudiante.getFechaNacimiento() != null) {
            estu.setFechaNacimiento(estudiante.getFechaNacimiento());
        }

    }

    @Transactional
        public void eliminar (Integer id){
        this.estudianteRepository.deleteById(id.longValue());
    }

    public List<Estudiante> buscarPorProvincia(String provincia) {
         return this.estudianteRepository.find("provinciaaa", provincia).list();
       // return this.estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero).list();
    }

        public List<Estudiante> buscarPorProvinciaGenero(String provincia, String genero) {
        // return this.estudianteRepository.find("provincia", provincia).list();
        return this.estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero).list();
    }

    

}
