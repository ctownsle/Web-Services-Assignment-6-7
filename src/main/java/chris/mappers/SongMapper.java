package chris.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import chris.db.Song;

public class SongMapper implements RowMapper<Song>{

	@Override
	public Song map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new Song(rs.getLong("id"), rs.getString("title"), rs.getString("artist"));
	}

}