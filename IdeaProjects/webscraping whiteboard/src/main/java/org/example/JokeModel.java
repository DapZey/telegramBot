package org.example;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties (ignoreUnknown = true)
public class JokeModel {
    private String type;
    private String setup;
    private String delivery;
    private String joke;
    public String getType() {
        return type;
    }
    public String getJoke() {
        return joke;
    }
    public String getSetup() {
        return setup;
    }
    public String getDelivery() {
        return delivery;
    }
}
