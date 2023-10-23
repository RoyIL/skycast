package entity;

import java.time.LocalDateTime;

public interface User {

    String getUsername();

    String getPassword();

    LocalDateTime getCreationTime();
}

