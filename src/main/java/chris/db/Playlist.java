package chris.db;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "playlist")
public class Playlist {


    @JsonProperty
    @Column(name = "pl_id", updatable = false, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty
    private String name;

    public Playlist(String name){
        this.name = name;
    }

    public Playlist(){

    }
    public Playlist(final long id, final String name){
        this.name = name;
        this.id = id;
    }

    public final String getName(){
        return name;
    }

    public final long getId(){
        return id;
    }

}