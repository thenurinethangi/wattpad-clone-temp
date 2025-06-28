package org.example.wattpadclone1.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserDTO {

    private String username;
    private String fullName;
    private String email;
    private String password;
    private String role;
    private String imagePath;

}
