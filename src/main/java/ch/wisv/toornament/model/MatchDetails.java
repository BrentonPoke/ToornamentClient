package ch.wisv.toornament.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MatchDetails extends Match {

    private List<Stream> streams;
    private List<Vod> vods;
    @JsonProperty("private_note")
    private String privateNote;
    private String note;

    public List<Stream> getStreams() {
        return streams;
    }

    public void setStreams(List<Stream> streams) {
        this.streams = streams;
    }

    public List<Vod> getVods() {
        return vods;
    }

    public void setVods(List<Vod> vods) {
        this.vods = vods;
    }

    public String getPrivateNote() {
        return privateNote;
    }

    public void setPrivateNote(String privateNote) {
        this.privateNote = privateNote;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
