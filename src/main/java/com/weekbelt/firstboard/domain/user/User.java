package com.weekbelt.firstboard.domain.user;

import com.weekbelt.firstboard.domain.BaseTimeEntity;
import com.weekbelt.firstboard.domain.board.Board;
import com.weekbelt.firstboard.domain.reply.Reply;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Reply> replies = new ArrayList<>();

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
