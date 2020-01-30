package com.weekbelt.firstboard.web.dto;

import com.weekbelt.firstboard.domain.board.Board;
import com.weekbelt.firstboard.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private Integer viewCount;
    private String boardType;
    private String nickname;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getBoardTitle();
        this.content = board.getBoardContent();
        this.viewCount = board.getViewCount();
        this.boardType = board.getBoardType().name();

        //To Do : 계정 연동 구현시 다시 작성
        User user = board.getUser();
        if (user == null) {
            this.nickname = "weekbelt";
        } else {
            this.nickname = board.getUser().getName();
        }
    }
}
