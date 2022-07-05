package com.toornament.model.Custom;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toornament.model.Logo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

/**
 * @JsonIgnoreProperties will prevent the deserialization from failing just to ignore custom fields
 * that may be optional and not filled in.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
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
    @JsonProperty("full_name")
    private FullName fullName;

    /*Games*/
    @JsonProperty("battle_net_player_id")
    private String battleNetPlayerID;
    @JsonProperty("blood_bowl2_player_id")
    private String bloodBowl2PlayerID;
    @Singular("hsPickChoice")
    @JsonProperty("hs_pick_choice")
    private ArrayList<String> hsPickChoices;
    @JsonProperty("maniaplanet_player_id")
    private String maniaplanetPlayerID;
    @JsonProperty("origin_player_id")
    private String originPlayerID;
    @JsonProperty("psn_player_id")
    private String psnPlayerID;
    @JsonProperty("smite_player_id")
    private String smitePlayerID;
    @JsonProperty("steam_player_id")
    private String steamPlayerID;
    @JsonProperty("summoner_player_id")
    private String summonerPlayerID;
    @JsonProperty("uplay_player_id")
    private String uplayPlayerID;
    @JsonProperty("wargaming_player_id")
    private String wargamingPlayerID;
    @JsonProperty("xbox_live_player_id")
    private String xboxLivePlayerID;

    /**
     * discord is not official in the spec, but like all custom fields, you will have to create
     * a custom field with the machine name "discord".
     */
    private String discord;
    private Boolean checkbox;
    private Boolean optin;
    private String website;
    private Logo logo;

    public CustomFields(String machineName, String label, String targetType, String type,
        String defaultValue,
        Boolean required, Boolean _public, Integer position, String id) {
        super(machineName, label, targetType, type, defaultValue, required, _public, position, id);
    }

    public CustomFields() {
        super();
    }

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
