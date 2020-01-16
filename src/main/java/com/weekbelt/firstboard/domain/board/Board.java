package com.weekbelt.firstboard.domain.board;

import com.weekbelt.firstboard.domain.BaseTimeEntity;
import com.weekbelt.firstboard.domain.reply.Reply;
import com.weekbelt.firstboard.domain.user.User;
import com.weekbelt.firstboard.web.dto.BoardSaveRequestDto;
import com.weekbelt.firstboard.web.dto.BoardUpdateRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String boardTitle;

    @Lob
    private String boardContent;

    private Integer viewCount;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board")
    private List<Reply> replies = new ArrayList<>();

    @Builder
    public Board(String boardTitle, String boardContent, Integer viewCount, String boardType) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.viewCount = viewCount;
        this.boardType = BoardType.valueOf(boardType);
    }

    public void setUser(User user) {
        this.user = user;
        user.getBoards().add(this);
    }

    public void update(BoardUpdateRequestDto boardUpdateRequestDto) {
        this.boardTitle = boardUpdateRequestDto.getBoardTitle();
        this.boardContent = boardUpdateRequestDto.getBoardContent();
        this.boardType = BoardType.valueOf(boardUpdateRequestDto.getBoardType());
    }

    public void plusViewCount() {
        this.viewCount++;
    }

}