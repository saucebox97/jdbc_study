package com.jdbc.basic.bmi.repository;

import com.jdbc.basic.bmi.domain.Bmi;

import java.util.Map;

public interface BmiRepository {

    // bmi 정보 저장
    boolean save(Bmi bmi);

    // bmi 정보 삭제
    boolean remove(int perNum);

    // bmi 정보 수정
    boolean modify(Bmi bmi);

    // 전제 bmi 조회
    Map<Integer, Bmi> findAll();

    // 개별 bmi 조회
    Bmi findOne(int perNum);
}
