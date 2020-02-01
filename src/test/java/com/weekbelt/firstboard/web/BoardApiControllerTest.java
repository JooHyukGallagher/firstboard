package com.weekbelt.firstboard.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weekbelt.firstboard.config.auth.dto.SessionUser;
import com.weekbelt.firstboard.domain.board.BoardType;
import com.weekbelt.firstboard.web.dto.BoardSaveRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class BoardApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("게시글이 정상적으로 저장")
    @Test
    public void save() throws Exception {
        //given
        SessionUser sessionUser = SessionUser.builder()
                .email("vfrvfr4207@gmail.com")
                .name("김주혁")
                .build();

        BoardSaveRequestDto boardSaveRequestDto = BoardSaveRequestDto.builder()
                .boardTitle("자유")
                .boardContent("자유 입니다.")
                .boardType(BoardType.FREE.name())
                .viewCount(0)
                .build();

        //when
        mockMvc.perform(post("/api/board")
                .param("user", objectMapper.writeValueAsString(sessionUser))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(boardSaveRequestDto)))
                .andDo(print())
                .andExpect(status().isCreated())
        ;
    }
}