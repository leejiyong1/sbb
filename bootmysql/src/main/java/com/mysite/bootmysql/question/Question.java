package com.mysite.bootmysql.question;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.bootmysql.answer.Answer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Getter
@Setter
@Entity
public class Question {
	
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //번호 자동생성
    private Integer id;

    @Column(length = 200)//제목은 길이를 200으로 설정
    private String subject;

    @Column(columnDefinition = "TEXT")//내용은 속성을 TEXT,길이 제한 없는..
    private String content;

    private LocalDateTime createDate;
    
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
}
