package com.weekbelt.firstboard.web.dto;

import com.weekbelt.firstboard.domain.board.Board;
import com.weekbelt.firstboard.domain.user.User;
import com.weekbelt.firstboard.util.TimeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardListResponseDto {

    private Long id;
    private String title;
    private Integer replyCount;
    private String boardType;
    private String nickname;
    private Integer viewCount;
    private String createDate;

    public BoardListResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getBoardTitle();
        this.replyCount = board.getReplies().size();
        this.boardType = board.getBoardType().getBoardType();

        User user = board.getUser();
        if (user != null) {
            this.nickname = board.getUser().getName();
        } else {
            this.nickname = "weekbelt"; // 임시 닉네임
        }

        this.viewCount = board.getViewCount();
        this.createDate = TimeUtil.convertLocalDateTimeToString(board.getCreateDate());
    }
}
