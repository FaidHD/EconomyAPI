package de.faidhd.economysystem.api;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface IEcoManager {

    /**
     * Get the IEcoUser
     * ! Dont forget to save after modifying !
     *
     * @param uuid UUID of the IEcoUser
     * @return The IEcoUser from the Database or if not exists a new IEcoUser
     */
    CompletableFuture<IEcoUser> getCoinsUser(UUID uuid);

    /**
     * Applies an action to the IEcoUser
     * Loading and saving is done by the api
     *
     * @param uuid     UUID of the IEcoUser
     * @param consumer The action you want to apply to the IEcoUser
     */
    void modifyIEcoUser(UUID uuid, Consumer<IEcoUser> consumer);

    /**
     * Saves a loaded IEcoUser
     *
     * @param iEcoUser IEcoUser to save
     */
    void saveCoinsUser(IEcoUser iEcoUser);

}
