package com.jdbc.basic.score;

import lombok.*;

@Setter @Getter
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드 초기화 생성자
@ToString // 클래스위에다 해야됌 전부
@Builder // 객체 생성시 생성자 역할을 대신
// 데이터베이스 score 테이블의 행데이터를 저장할 객체

// @Data // 위에꺼 다해주지만 쓰면안됌 필요없을때도 있어서 위험함
public class Score {

    private int stuNum;
    private String stuName;
    private int kor;
    private int eng;
    private int math;
    private int total;
    private double average;

    // 총점, 평균을 계산하는 메서드
    public void calc() {
        this.total = kor + eng + math;
        this.average = Math.round((total/ 3.0) * 100 / 100.0);
    }

}
