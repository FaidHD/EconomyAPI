package de.faidhd.economysystem.api.events;

import de.faidhd.economysystem.api.ChangeCause;
import de.faidhd.economysystem.api.IEcoUser;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Event;

public class BungeeEconomyUserUpdateEvent extends Event {

    @Getter
    private final IEcoUser iEcoUser;

    @Getter
    private double newAmount;
    @Getter
    private double oldAmount;
    @Getter
    private ChangeCause changeCause;

    public BungeeEconomyUserUpdateEvent(IEcoUser iEcoUser, double newAmount, double oldAmount, ChangeCause changeCause) {
        this.iEcoUser = iEcoUser;
        this.newAmount = newAmount;
        this.oldAmount = oldAmount;
        this.changeCause = changeCause;
    }

}
