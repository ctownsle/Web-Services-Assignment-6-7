package chris.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyResponse{

    @JsonProperty
    private final int code;
    
    @JsonProperty
    private final String message;

    public MyResponse(int code, String message){
        this.code = code;
        this.message = message;
    }
}