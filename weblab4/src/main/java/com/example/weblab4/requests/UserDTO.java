package com.example.weblab4.requests;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class UserDTO {
    @NotNull private String login;
    @NotNull private String password;
}
