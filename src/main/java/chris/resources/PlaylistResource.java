package chris.resources;


import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jdbi.v3.core.Jdbi;

import chris.core.MyResponse;
import chris.dao.PlaylistDAO;
import chris.dao.SongDAO;
import chris.db.Playlist;
import chris.db.Song;


@Path("/playlists")
@Produces(MediaType.APPLICATION_JSON)
public class PlaylistResource{
   // private final Jdbi jdbi;
    private final PlaylistDAO playlistDAO;
    private final SongDAO songDAO;
    
    public PlaylistResource(Jdbi jdbi){
        playlistDAO = jdbi.onDemand(PlaylistDAO.class);
        songDAO = jdbi.onDemand(SongDAO.class);
    }

    @GET
    public Response getAllPlaylists(){
        return Response.ok(playlistDAO.get()).build();
    }

    @Path("{id}")
    @GET
    public Response getPlaylist(@PathParam("id") long id){
        Playlist pl = playlistDAO.get(id);
        if(pl != null){
            return Response.ok(pl).build();
        }
        return Response.status(404).entity(new MyResponse(404, "Playlist Not Found")).build();
    }

    @POST
    public Response addPlaylist(Playlist pl){
        boolean created  = playlistDAO.post(pl.getName());
        if (created == true){
          
            Playlist play = playlistDAO.get(pl.getName());
            return Response.status(201).entity(play).build();
        }
        return Response.status(400).entity(new MyResponse(400, "Playlist Already Exists")).build();
    }

    @Path("{id}/songs")
    @POST
    public Response addSongToPlaylist(@PathParam("id") long id, Song song){
        
        boolean created = playlistDAO.postSong(id, song.getId());
        if(created == true){
            Song s = songDAO.get(song.getId());
            return Response.status(201).entity(s).build(); 
        }
        return Response.status(400).entity(new MyResponse(400, "Song already added to Playlist")).build();
    }

    @Path("{id}/songs")
    @GET
    public Response getSongsFromPlaylist(@PathParam("id") long id){
        return Response.ok(playlistDAO.getSongs(id)).build();
    }

    @Path("{id}")
    @DELETE
    public Response deletePlaylist(@PathParam("id") long id){
        playlistDAO.deleteFromPlSg(id);
        boolean deletedP = playlistDAO.delete(id);
        if(deletedP == true){
            return Response.ok(new MyResponse(200, "Playlist Deleted Successfully")).build();
        }
        return Response.status(404).entity(new MyResponse(404, "Playlist Not Found")).build();
    }

    @Path("{id}/songs/{songId}")
    @DELETE
    public Response deleteSongFromPlaylist(@PathParam("id") long pl_id, @PathParam("songId") long songId){

        boolean deleted = playlistDAO.deleteSong(pl_id, songId);
        if(deleted == true){
            return Response.ok(new MyResponse(200, "Song Removed From playlist")).build();
        }
        return Response.status(404).entity(new MyResponse(404, "Song Not Found")).build();
    }
}