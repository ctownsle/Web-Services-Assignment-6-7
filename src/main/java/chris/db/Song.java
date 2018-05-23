package chris.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "songs")
public class Song{

    @JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty
    private String title;
    
    @JsonProperty
    private String artist;

    public Song(){
        
    }
    
    public Song(long id, String title, String artist){
        this.title = title;
        this.artist = artist;
        this.id = id;
    }

    public Song(long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public String getArtist(){
        return artist;
    }

    public long getId(){
        return id;
    }
}