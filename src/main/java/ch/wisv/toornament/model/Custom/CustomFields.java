package ch.wisv.toornament.model.Custom;

import java.util.ArrayList;
import ch.wisv.toornament.model.Custom.Address;
import ch.wisv.toornament.model.Logo;

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

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getSnapchat() {
        return snapchat;
    }

    public void setSnapchat(String snapchat) {
        this.snapchat = snapchat;
    }

    public String getTwitch() {
        return twitch;
    }

    public void setTwitch(String twitch) {
        this.twitch = twitch;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getVimeo() {
        return vimeo;
    }

    public void setVimeo(String vimeo) {
        this.vimeo = vimeo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getBattle_net_player_id() {
        return battle_net_player_id;
    }

    public void setBattle_net_player_id(String battle_net_player_id) {
        this.battle_net_player_id = battle_net_player_id;
    }

    public String getBlood_bowl2_player_id() {
        return blood_bowl2_player_id;
    }

    public void setBlood_bowl2_player_id(String blood_bowl2_player_id) {
        this.blood_bowl2_player_id = blood_bowl2_player_id;
    }

    public ArrayList<String> getHs_pick_choice() {
        return hs_pick_choice;
    }

    public void setHs_pick_choice(ArrayList<String> hs_pick_choice) {
        this.hs_pick_choice = hs_pick_choice;
    }

    public String getManiaplanet_player_id() {
        return maniaplanet_player_id;
    }

    public void setManiaplanet_player_id(String maniaplanet_player_id) {
        this.maniaplanet_player_id = maniaplanet_player_id;
    }

    public String getOrigin_player_id() {
        return origin_player_id;
    }

    public void setOrigin_player_id(String origin_player_id) {
        this.origin_player_id = origin_player_id;
    }

    public String getPsn_player_id() {
        return psn_player_id;
    }

    public void setPsn_player_id(String psn_player_id) {
        this.psn_player_id = psn_player_id;
    }

    public String getSmite_player_id() {
        return smite_player_id;
    }

    public void setSmite_player_id(String smite_player_id) {
        this.smite_player_id = smite_player_id;
    }

    public String getSteam_player_id() {
        return steam_player_id;
    }

    public void setSteam_player_id(String steam_player_id) {
        this.steam_player_id = steam_player_id;
    }

    public String getSummoner_player_id() {
        return summoner_player_id;
    }

    public void setSummoner_player_id(String summoner_player_id) {
        this.summoner_player_id = summoner_player_id;
    }

    public String getUplay_player_id() {
        return uplay_player_id;
    }

    public void setUplay_player_id(String uplay_player_id) {
        this.uplay_player_id = uplay_player_id;
    }

    public String getWargaming_player_id() {
        return wargaming_player_id;
    }

    public void setWargaming_player_id(String wargaming_player_id) {
        this.wargaming_player_id = wargaming_player_id;
    }

    public String getXbox_live_player_id() {
        return xbox_live_player_id;
    }

    public void setXbox_live_player_id(String xbox_live_player_id) {
        this.xbox_live_player_id = xbox_live_player_id;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public boolean isOptin() {
        return optin;
    }

    public void setOptin(boolean optin) {
        this.optin = optin;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
