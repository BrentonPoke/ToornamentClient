package ch.wisv.toornament.model.request;

import java.util.Date;

import ch.wisv.toornament.model.Sort;
import ch.wisv.toornament.model.Status;

public class TournamentQuery {
    String discipline;
    Status status;
    Boolean featured;
    Boolean online;
    String country;
    Date afterStart;
    Date beforeStart;
    Date afterEnd;
    Date beforeEnd;
    Sort sort = Sort.DATE_DESC;
    String name;
}
