package uce.edu.web.api.matricula.infraestructure;

import javax.management.Query;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import uce.edu.web.api.matricula.domain.Materia;

@ApplicationScoped
public class MateriaRepository implements PanacheRepository<Materia> {

    @PersistenceContext
    private EntityManager entityManager;

    public Materia buscarPorNombre(String nombre) {
        TypedQuery<Materia> mQ = this.entityManager.createQuery("SELECT m FROM Materia m WHERE m.nombre = :nombre",
                Materia.class);
        mQ.setParameter("nombre", nombre);
        return mQ.getSingleResult();
    }

    public Materia buscarPorTipo(String tipo) {
        return find("tipo", tipo).firstResult();
    }
}
