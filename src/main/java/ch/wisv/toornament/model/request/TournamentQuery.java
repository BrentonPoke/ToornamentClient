package ch.wisv.toornament.model.request;

import ch.wisv.toornament.model.enums.DateSort;
import ch.wisv.toornament.model.enums.TournamentStatus;

import java.util.Date;

public class TournamentQuery {
    String discipline;
    TournamentStatus status;
    Boolean featured;
    Boolean online;
    String country;
    Date afterStart;
    Date beforeStart;
    Date afterEnd;
    Date beforeEnd;
    DateSort dateSort = DateSort.DATE_DESC;
    String name;
}
