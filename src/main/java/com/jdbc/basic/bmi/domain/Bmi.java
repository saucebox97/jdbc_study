package com.jdbc.basic.bmi.domain;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드 초기화 생성자
@ToString // 클래스위에다 해야됌 전부
@Builder // 객체 생성시 생성자 역할을 대신
// bmi.test 는 그냥 lombok이 돼는지테스트
public class Bmi {

    private int perNum;
    private String perName;
    private double height;
    private double weight;
    private double bmi;

    public void calc() {
        this.bmi = weight/((height/100)*(height/100));
    }

}
