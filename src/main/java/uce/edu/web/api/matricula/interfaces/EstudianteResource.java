package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.domain.Estudiante;

@Path("/estudiantes")
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @GET
    @Path("/todos")
    public List<Estudiante> encontrarTodos() {
        return this.estudianteService.listarTodos();
    }

    @GET
    @Path("/consultarPorId/{id}")
    public Estudiante conssultarPoId(@PathParam("id") Integer idTest) {
        return this.estudianteService.consultarPorId(idTest);
    }

    @POST
    @Path("/crear")
    // @Consumes("application/json")
    public void guardar(Estudiante estudiante) {
        this.estudianteService.crear(estudiante);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(@PathParam("id") Integer id, Estudiante estudiante) {
        this.estudianteService.actualizar(id, estudiante);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarParcial(@PathParam("id") Integer id, Estudiante estudiante) {
        this.estudianteService.actualizarParcial(id, estudiante);
    }

    @DELETE
    @Path("/borrar/{id}")

    public void borrar(@PathParam("id") Integer id) {
        this.estudianteService.eliminar(id);
    }
}
