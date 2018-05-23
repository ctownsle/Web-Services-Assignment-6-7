package chris.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import chris.db.Playlist;
import chris.db.Song;

public interface PlaylistDAO {

    @SqlQuery("select * from playlist")
    public List<Playlist> get();

    @SqlQuery("select * from playlist where (pl_id = :pl_id) limit 1")
    public Playlist get(@Bind ("pl_id") long id);

    @SqlUpdate("insert into playlist (name) select :name where not exists" + 
        "(select name from playlist where name = :name)")
    public boolean post(@Bind("name") String name);

    @SqlUpdate("delete from playlist where (pl_id = :pl_id)")
    public boolean delete(@Bind("pl_id") long id);

    @SqlUpdate("insert into pl_sg (id, pl_id) select :id, :pl_id where not exists"
    + "(select id, pl_id from pl_sg where id = :id and pl_id = :pl_id)")
    public boolean postSong(@Bind("pl_id") long pl_id, @Bind("id") long id);

    @SqlQuery("select s.id, title, artist from songs s inner join pl_sg ps on (s.id = ps.id)" + 
    "inner join playlist p on (p.pl_id = ps.pl_id) where (p.pl_id = :pl_id)")
    public List<Song> getSongs(@Bind("pl_id") long pl_id);

    @SqlUpdate("delete from pl_sg where (pl_id = :pl_id) and (id = :id)")
    public boolean deleteSong(@Bind("pl_id") long pl_id, @Bind("id") long id);

    @SqlUpdate("delete from pl_sg where (pl_id = :pl_id)")
    public void deleteFromPlSg(@Bind("pl_id") long pl_id);

    @SqlQuery("select * from playlist where (name = :name)")
    public Playlist get(@Bind("name") String name);
}