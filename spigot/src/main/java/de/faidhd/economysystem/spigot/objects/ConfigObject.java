package de.faidhd.economysystem.spigot.objects;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ConfigObject {

    @Builder.Default
    private String hostname = "localhost";
    @Builder.Default
    private int port = 3306;
    @Builder.Default
    private String database = "database";
    @Builder.Default
    private String username = "username";
    @Builder.Default
    private String password = "password";

}
