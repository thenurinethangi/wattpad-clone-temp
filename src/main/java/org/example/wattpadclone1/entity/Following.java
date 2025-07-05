package org.example.wattpadclone1.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "following")
public class Following {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "follow_id", nullable = false)
    private Follow follow;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Following(Follow follow, User user) {
        this.follow = follow;
        this.user = user;
    }
}

