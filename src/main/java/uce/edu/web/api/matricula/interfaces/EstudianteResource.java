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
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.xml.bind.annotation.XmlRootElement;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.application.representation.LinkDTO;
import uce.edu.web.api.matricula.domain.Estudiante;

@Path("/estudiantes")
@Consumes("application/json")
@Produces("application/json")
@XmlRootElement
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @Inject
    private HijoService hijoService;

    @Context
    /* Obtener la url con la que estoy trabajando ?? */
    private UriInfo info;

    @GET
    @Path("")
    // esta produciendo, por eso esta mostrando
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstudianteRepresentation> encontrarTodos() {
        System.err.println("MACUMBA MACUMBA MACUMBA");
        return this.construirLinks(this.estudianteService.listarTodos());

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EstudianteRepresentation consultarPoId(@PathParam("id") Integer idTest) {
        return this.construirLinks(this.estudianteService.consultarPorId(idTest));
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardar(EstudianteRepresentation estudiante) {
        this.estudianteService.crear(estudiante);
        return Response.status(Response.Status.CREATED).entity(estudiante).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(@PathParam("id") Integer id, EstudianteRepresentation estudiante) {
        this.estudianteService.actualizar(id, estudiante);
        return Response.status(209).entity(estudiante).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizarParcial(@PathParam("id") Integer id, EstudianteRepresentation estudiante) {
        this.estudianteService.actualizarParcial(id, estudiante);
    }

    @DELETE
    @Path("/{id}")
    public void borrar(@PathParam("id") Integer id) {
        this.estudianteService.eliminar(id);
    }

    @GET
    @Path("/provincia")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia) {
        System.err.println("B========D");
        return this.estudianteService.buscarPorProvincia(provincia);
    }

    @GET
    @Path("/provincia/genero")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudiante> buscarPorProvinciaGenero(@QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        System.out.println(":0000000000000000000000000000");
        return this.estudianteService.buscarPorProvinciaGenero(provincia, genero);
    }

    @GET
    @Path("/{id}/hijos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HijoRepresentation> buscarPorIdDelEstudiante(@PathParam("id") Integer id) {
        return this.hijoService.buscarPorIdDelEstudiante(id);
    }

    // @GET
    // @Path("/{id}/hijosV2")
    // @Produces(MediaType.APPLICATION_JSON)
    // public Hijo buscarPorIdDelEstudianteV2(@PathParam("id") Integer id) {
    // return this.hijoService.buscarPorIdDelEstudianteV2(id);
    // }

    private EstudianteRepresentation construirLinks(EstudianteRepresentation er) {
        String self = this.info.getBaseUriBuilder().path(EstudianteResource.class).path(String.valueOf(er.getId()))
            .build().toString();

        String hijos = this.info.getBaseUriBuilder().path(EstudianteResource.class).path(String.valueOf(er.getId())).path("hijos").build().toString();

        er.setLinks(List.of(new LinkDTO(self, "self"), new LinkDTO(hijos, "hijos")));
        return er;
    }

    private List<EstudianteRepresentation> construirLinks(List<EstudianteRepresentation> estudiantes) {
    for (EstudianteRepresentation er : estudiantes) {
        String self = this.info.getBaseUriBuilder()
            .path(EstudianteResource.class)
            .path(String.valueOf(er.getId()))
            .build()
            .toString();

        String hijos = this.info.getBaseUriBuilder()
            .path(EstudianteResource.class)
            .path(String.valueOf(er.getId()))
            .path("hijos")
            .build()
            .toString();

        er.setLinks(List.of(new LinkDTO(self, "self"), new LinkDTO(hijos, "hijos")));
    }
    return estudiantes;
}
}
