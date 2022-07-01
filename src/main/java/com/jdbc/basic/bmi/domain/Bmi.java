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
    private double min;
    private double max;
    private String result;
    private double target;

    public void calc() {
        this.bmi = weight/((height/100)*(height/100));
        this.min = ((height/100)*(height/100))*18.5;
        this.max = ((height/100)*(height/100))*23;
        if (bmi<18.5) {
            this.result = "저체중";
            this.target = (18.5-bmi)*((height/100)*(height/100));
        }
        else if(bmi>=18.5 && bmi<23) {
            this.result = "정상";
            this.target = 0;
        }
        else if(bmi>=23 && bmi<25) {
            this.result = "과체중";
            this.target = (23-bmi)*((height/100)*(height/100));
        }
        else if(bmi>=25 && bmi<30) {
            this.result = "비만";
            this.target = (23-bmi)*((height/100)*(height/100));
        }
        else {
            this.result = "고도비만";
            this.target = (23-bmi)*((height/100)*(height/100));
        }
    }

}
