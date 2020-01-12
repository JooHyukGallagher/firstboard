package com.weekbelt.firstboard.domain.user;

import com.weekbelt.firstboard.domain.BaseTimeEntity;
import com.weekbelt.firstboard.domain.board.Board;
import com.weekbelt.firstboard.domain.reply.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class User extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;

    private String userName;
    private String userPw;
    private String nickname;
    private String message;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Reply> replies = new ArrayList<>();

    @Builder
    public User(String userName, String userPw, String nickname, String message, String email) {
        this.userName = userName;
        this.userPw = userPw;
        this.nickname = nickname;
        this.message = message;
        this.email = email;
    }
}
