package ch.wisv.toornament.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Logo {

    @JsonAlias({"logo_small","icon_small"})
    private String logo_small;
    @JsonAlias({"logo_medium","icon_medium"})
    private String logo_medium;
    @JsonAlias({"logo_large","icon_large"})
    private String logo_large;
    private String original;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getLogo_small() {
        return logo_small;
    }

    public void setLogo_small(String logo_small) {
        this.logo_small = logo_small;
    }

    public String getLogo_medium() {
        return logo_medium;
    }

    public void setLogo_medium(String logo_medium) {
        this.logo_medium = logo_medium;
    }

    public String getLogo_large() {
        return logo_large;
    }

    public void setLogo_large(String logo_large) {
        this.logo_large = logo_large;
    }
}
