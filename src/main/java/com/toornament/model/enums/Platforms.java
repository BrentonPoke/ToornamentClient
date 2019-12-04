package com.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Platforms {
    PC("pc"), PS4("playstation4"), XBONE("xbox_one"), SWITCH("nintendo_switch"), MOBILE("mobile"), PS3("playstation3"), PS2("playstation2"), PSONE("playstation1"),
    VITA("ps_vita"), PSP("psp"), XBOX360("xbox360"), XBOX("xbox"), WII_U("wii_u"), WII("wii"), GAMECUBR("gamecube"), N64("nintendo64"), SNES("snes"), NES("nes"), DREAMCAST("dreamcast"), SATURN("saturn"),
    MEGADRIVE("megadrive"), MASTER("master_system"), THREE_DS("3ds"), DS("ds"), GAMEBOY("game_boy"), NEOGEO("neo_geo"), OTHER("other_platform"), NONE("not_video_game");
    private String name;
    @JsonValue
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }

    Platforms(String name) {
        this.name = name;
    }
}
