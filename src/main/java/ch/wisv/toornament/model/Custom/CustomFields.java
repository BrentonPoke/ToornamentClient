package ch.wisv.toornament.model.Custom;

import java.util.ArrayList;
import ch.wisv.toornament.model.Custom.Address;
import ch.wisv.toornament.model.Logo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomFields {
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
    private String full_name;

    /*Games*/
    private String battle_net_player_id;
    private String blood_bowl2_player_id;
    private ArrayList<String> hs_pick_choice;
    private String maniaplanet_player_id;
    private String origin_player_id;
    private String psn_player_id;
    private String smite_player_id;
    private String steam_player_id;
    private String summoner_player_id;
    private String uplay_player_id;
    private String wargaming_player_id;
    private String xbox_live_player_id;

    private boolean checkbox;
    private String custom;
    private boolean optin;
    private String website;
    private Logo logo;


}
