package chris.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import chris.db.Playlist;

public class PlaylistMapper implements RowMapper<Playlist>{

	@Override
	public Playlist map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new Playlist(rs.getLong("pl_id"), rs.getString("name"));
	}

}