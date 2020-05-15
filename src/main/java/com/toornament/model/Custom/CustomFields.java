package com.toornament.model.Custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toornament.model.Logo;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomFields extends Custom {
    /*social*/
    private String twitter;
    private String facebook;
    private String snapchat;
    private String twitch;
    private String youtube;
    private String instagram;
    private String vimeo;

    /*personal*/
    private Address address;
    private String birth_date;
    private String country;
    private FullName full_name;

    /*Games*/
    private String battle_net_player_id;
    private String blood_bowl2_player_id;
    @Singular("hsPickChoice")
    @JsonProperty("hs_pick_choice")
    private ArrayList<String> hsPickChoices;
    private String maniaplanet_player_id;
    private String origin_player_id;
    private String psn_player_id;
    private String smite_player_id;
    private String steam_player_id;
    private String summoner_player_id;
    private String uplay_player_id;
    private String wargaming_player_id;
    private String xbox_live_player_id;

    private Boolean checkbox;
    private String custom;
    private Boolean optin;
    private String website;
    private Logo logo;
    public String toString() {
        try {
            return new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
