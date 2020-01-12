package com.weekbelt.firstboard.domain.reply;

import com.weekbelt.firstboard.domain.BaseTimeEntity;
import com.weekbelt.firstboard.domain.board.Board;
import com.weekbelt.firstboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Reply extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    @Lob
    private String replyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Reply(String replyContent) {
        this.replyContent = replyContent;
    }

    public void setUser(User user) {
        this.user = user;
        user.getReplies().add(this);
    }

    public void setBoard(Board board) {
        this.board = board;
        board.getReplies().add(this);
    }
}
