package chris;

import org.jdbi.v3.core.Jdbi;

import chris.mappers.PlaylistMapper;
import chris.mappers.SongMapper;
import chris.resources.PlaylistResource;
import chris.resources.SongResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.jdbi3.JdbiFactory;

public class Assignment6Application extends Application<Assignment6Configuration> {

    public static void main(final String[] args) throws Exception {
        new Assignment6Application().run(args);
    }

    @Override
    public String getName() {
        return "Assignment6";
    }

    @Override
    public void initialize(final Bootstrap<Assignment6Configuration> bootstrap) {
        
    }

    @Override
    public void run(final Assignment6Configuration configuration,
                    final Environment environment) {
       
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        
        jdbi.registerRowMapper(new PlaylistMapper());
        jdbi.registerRowMapper(new SongMapper());
        final PlaylistResource plr = new PlaylistResource(jdbi);
        final SongResource sr = new SongResource(jdbi); 

        environment.jersey().register(plr);
        environment.jersey().register(sr);
        
    }

}
