package org.example.wattpadclone1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    @Column(name = "full_name")
    private String fullName;

    private String email;
    private String password;
    private String role;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "joined_date")
    private LocalDate joinedDate;

    private String about;

    @Column(name = "website_link")
    private String websiteLink;

    @Column(name = "facebook_link")
    private String facebookLink;

    private String pronouns;
    private String location;

    @Column(name = "cover_image_path")
    private String coverImagePath;

    public User(String username, String fullName, String email, String password, String role, String imagePath) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.imagePath = imagePath;
    }
}
