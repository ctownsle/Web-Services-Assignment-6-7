package chris.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import chris.db.Song;

public interface SongDAO {

    @SqlQuery("select * from songs")
    public List<Song> get();

    @SqlQuery("select * from songs where (id = :id)")
    public Song get(@Bind("id") long id);
}