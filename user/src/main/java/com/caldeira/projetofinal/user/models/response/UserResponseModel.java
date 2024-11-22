package main.java.com.caldeira.projetofinal.user.models.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserResponseModel {
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDateTime creationDate;
}
