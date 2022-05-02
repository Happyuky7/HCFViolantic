package com.massivecraft.factions.scoreboards.sidebar;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.P;
import com.massivecraft.factions.scoreboards.FSidebarProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class FDefaultSidebar extends FSidebarProvider {

    @Override
    public String getTitle(FPlayer fplayer) {
        return replaceTags(fplayer, P.p.getInstance().getConfig().getString("scoreboard.default-title", "{name}"));
    }

    @Override
    public List<String> getLines(FPlayer fplayer) {
        if (fplayer.hasFaction()) {
            return getOutput(fplayer, "scoreboard.default");
        } else if (P.p.getInstance().getConfig().getBoolean("scoreboard.factionless-enabled", false)) {
            return getOutput(fplayer, "scoreboard.factionless");
        }
        return getOutput(fplayer, "scoreboard.default"); // no faction, factionless-board disabled
    }

    public List<String> getOutput(FPlayer fplayer, String list) {
        List<String> lines = P.p.getInstance().getConfig().getStringList(list);

        if (lines == null || lines.isEmpty()) {
            return new ArrayList<String>();
        }

        ListIterator<String> it = lines.listIterator();
        while (it.hasNext()) {
            it.set(replaceTags(fplayer, it.next()));
        }
        return lines;
    }
}