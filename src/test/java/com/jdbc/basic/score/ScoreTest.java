package com.jdbc.basic.score;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    void lombokTest() {
        Score s = new Score(1, "김철수", 99, 88, 11, 300, 15);
        s.setTotal(222);

        System.out.println(s.getStuName());

        assertEquals(s.getStuName(), "김철수");

    }

    @Test
    void lombokBuilderTest() {

        Score park = new Score.ScoreBuilder() // 안넣은건 자동으로 기본값으로 넣어짐짐
               .stuNum(2)
                .stuName("박영희")
                .math(92)
                .eng(100)
                .total(192)
                .average(96).build();

        System.out.println(park);
    }

}