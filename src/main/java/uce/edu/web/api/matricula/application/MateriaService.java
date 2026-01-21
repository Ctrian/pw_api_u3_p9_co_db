package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestructure.MateriaRepository;

@ApplicationScoped
@Transactional
public class MateriaService {

    @Inject
    private MateriaRepository materiaRepository;

    public List<Materia> listarTodas() {
        return this.materiaRepository.listAll();
    }

    public Materia consultarPorId(Integer id) {
        return this.materiaRepository.findById(id.longValue());
    }

    public void crear(Materia materia) {
        this.materiaRepository.persist(materia);
    }

    @Transactional
    public void actualizar(Integer id, Materia materia) {
        Materia mat = this.consultarPorId(id);
        mat.setNombre(materia.getNombre());
        mat.setCodigo(materia.getCodigo());
        mat.setDescripcion(materia.getDescripcion());
        mat.setTipo(materia.getTipo());
    }

    @Transactional
    public void actualizarParcial(Integer id, Materia materia) {
        Materia mat = this.consultarPorId(id);
        if (materia.getNombre() != null) {
            mat.setNombre(materia.getNombre());
        }
        if (materia.getCodigo() != null) {
            mat.setCodigo(materia.getCodigo());
        }
        if (materia.getDescripcion() != null) {
            mat.setDescripcion(materia.getDescripcion());
        }
        if (materia.getTipo() != null) {
            mat.setTipo(materia.getTipo());
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        this.materiaRepository.deleteById(id.longValue());
    }

    public Materia encontrarPorNombre(String nombre) {
        return this.materiaRepository.buscarPorNombre(nombre);
    }

    public Materia encontrarPorTipo(String tipo) {
        return this.materiaRepository.buscarPorTipo(tipo);
    }

}
