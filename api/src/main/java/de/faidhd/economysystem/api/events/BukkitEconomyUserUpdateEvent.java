package de.faidhd.economysystem.api.events;

import de.faidhd.economysystem.api.ChangeCause;
import de.faidhd.economysystem.api.IEcoUser;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BukkitEconomyUserUpdateEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    @Getter
    private final IEcoUser iEcoUser;

    @Getter
    private double newAmount;
    @Getter
    private double oldAmount;
    @Getter
    private ChangeCause changeCause;

    public BukkitEconomyUserUpdateEvent(IEcoUser iEcoUser, double newAmount, double oldAmount, ChangeCause changeCause) {
        this.iEcoUser = iEcoUser;
        this.newAmount = newAmount;
        this.oldAmount = oldAmount;
        this.changeCause = changeCause;
    }

    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

}
