package br.com.ecoground.user.user_ecoground.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioIntelbras {

    @JsonProperty("UserID")
    private String userID;

    @JsonProperty("UserName")
    private String name;

    @JsonProperty("UserType")
    private Integer userType = 0;

    @JsonProperty("Authority")
    private Integer authority = 1;

    @JsonProperty("Password")
    private String password;

    @JsonProperty("Doors")
    private List<Integer> doors = List.of(0);

    @JsonProperty("TimeSections")
    private List<Integer> timeSections = List.of(255);

    @JsonProperty("ValidFrom")
    private String validFrom = "2019-01-02 00:00:00";

    @JsonProperty("ValidTo")
    private String validTo = "2037-01-02 01:00:00";
}
