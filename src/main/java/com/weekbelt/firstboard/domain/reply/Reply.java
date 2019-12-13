package com.weekbelt.firstboard.domain.reply;

import com.weekbelt.firstboard.domain.BaseTimeEntity;
import com.weekbelt.firstboard.domain.board.Board;
import com.weekbelt.firstboard.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
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
}
