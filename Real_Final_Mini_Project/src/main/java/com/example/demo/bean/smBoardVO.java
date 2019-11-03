package com.example.demo.bean;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class smBoardVO {
			 private int idx; //게시판의 게시글 번호이자 기본키로 사용
			 private String title;
			 private String writer;
			 private String content;
			 private Date reg_date; //생성날자만 가지고 수정날자는 따로 다루지 않음
			 private int view_cnt; //조회수
			public smBoardVO(String title, String writer, String content) {
				super();
				this.title = title;
				this.writer = writer;
				this.content = content;
			}
			 
			 
}
