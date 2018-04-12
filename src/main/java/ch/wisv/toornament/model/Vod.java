package ch.wisv.toornament.model;

import ch.wisv.toornament.model.enums.VodCategory;

public class Vod extends Video {

    private VodCategory category;

    public VodCategory getCategory() {
        return category;
    }

    public void setCategory(VodCategory category) {
        this.category = category;
    }
}
