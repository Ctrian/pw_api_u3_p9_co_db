package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @GET
    @Path("/todas")
    public List<Materia> encontrarTodas() {
        return this.materiaService.listarTodas();
    }

    @GET
    @Path("/consultarPorId/{id}")
    public Materia consultarPorId(@PathParam("id") Integer id) {
        return this.materiaService.consultarPorId(id);
    }

    @POST
    @Path("/crear")
    public void guardar(Materia materia) {
        this.materiaService.crear(materia);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizar(id, materia);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarParcial(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizarParcial(id, materia);
    }

    @DELETE
    @Path("/borrar/{id}")
    public void borrar(@PathParam("id") Integer id) {
        this.materiaService.eliminar(id);
    }

    @GET
    @Path("/encontrarPorNombre/{nombre}")
    public Materia encontrarPorNombre(@PathParam("nombre") String nombre) {
        return this.materiaService.encontrarPorNombre(nombre);
    }

    @GET
    @Path("/encontrarPorTipo/{tipo}")
    public Materia encontrarPorTipo(@PathParam("tipo") String tipo) {
        return this.materiaService.encontrarPorTipo(tipo);
    }
}
