package ch.wisv.toornament.model;

import ch.wisv.toornament.model.Custom.CustomFields;
import ch.wisv.toornament.model.enums.Result;
import ch.wisv.toornament.model.enums.ResultV2;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class Opponent {
    private int number;
    private Participant participant;
    private Object result;
    private Integer rank;
    private Integer score;
    private boolean forfeit;
    private CustomFields custom_fields;

    public CustomFields getCustom_fields() {
        return custom_fields;
    }

    public void setCustom_fields(CustomFields custom_fields) {
        this.custom_fields = custom_fields;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public boolean isForfeit() {
        return forfeit;
    }

    public void setForfeit(boolean forfeit) {
        this.forfeit = forfeit;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
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
