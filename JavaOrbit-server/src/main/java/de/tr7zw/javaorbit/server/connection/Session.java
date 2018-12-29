package de.tr7zw.javaorbit.server.connection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Session {
private int userId;
private String sessionToken;
private String version;
}
