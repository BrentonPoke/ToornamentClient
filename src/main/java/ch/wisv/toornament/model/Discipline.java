package ch.wisv.toornament.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class Discipline {

    String id;
    String name;
    String shortname;
    String fullname;
    String copyrights;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    public String getID(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getFullname(){
        return this.fullname;
    }
    public String getShortname(){
        return this.shortname;
    }
    public String getCopyrights(){
        return this.copyrights;
    }
    @Override
    public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
