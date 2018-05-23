package chris.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jdbi.v3.core.Jdbi;

import chris.core.MyResponse;
import chris.dao.SongDAO;
import chris.db.Song;

@Path("/songs")
@Produces(MediaType.APPLICATION_JSON)
public class SongResource{

    private final SongDAO songDAO;
    public SongResource(Jdbi jdbi){
        songDAO = jdbi.onDemand(SongDAO.class);
    }

    @GET
    public Response getAllSongs(){
        return Response.ok(songDAO.get()).build();
    }

    @GET
    @Path("{id}")
    public Response getSong(@PathParam("id") long id){
        Song s = songDAO.get(id);
        if(s != null){
            return Response.ok(s).build();
        }
        return Response.status(404).entity(new MyResponse(404, "Song Not Found")).build();
    }
}