package com.jdbc.basic.bmi.controller;

import com.jdbc.basic.bmi.domain.Bmi;
import com.jdbc.basic.bmi.repository.BmiOracleRepo;
import com.jdbc.basic.bmi.repository.BmiRepository;
import com.jdbc.basic.score.Score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BmiController {

    // bmi 정보가 저장될 맵 ( 코드, b: bmi ) // 인덱스로하면 중간에전학가면 번호가 떙겨져서 혼란을일으킴
    private static Map<Integer, Bmi> bmiMap; // 그래서 리스트안하고 Map씀
    
    private final BmiRepository repository; 
    
    public BmiController() { this.repository = new BmiOracleRepo(); }
    
    static {
        bmiMap = new HashMap<>();
    }
    
    // 사람 신체정보 입력 가능
    
    public void insertPeople(Bmi bmi) {
        // 메모리에 저장
        bmiMap.put(bmi.getPerNum(), bmi);
        // DB에 저장
        boolean result = repository.save(bmi);
    }

    // 신체 전체 조회
    public List<Bmi> findAllpersons() {
        Map<Integer, Bmi> persons = repository.findAll();
        bmiMap = persons;
        
        List<Bmi> bmiList = new ArrayList<>();
        for (Integer bmi : bmiMap.keySet()) {
            bmiList.add(bmiMap.get(bmi));
        }
        return bmiList;
    
    }

    // 신체 개별 조회

    public Bmi findOnePerson(int perNum) {
        return repository.findOne(perNum);
    }

    // 신체 수정

    public boolean updatePerson(int perNum, double height, double weight) {
        // 1. DB에서 해당 사람을 조회
        Bmi target = findOnePerson(perNum);

        if (target != null) {
            // 2. 수정 진행
            target.setHeight(height);
            target.setWeight(weight);
            target.calc();

            // 3. DB에 수정 반영
            return repository.modify(target);
        }
        return false;
    }

    // 신체정보 삭제

    public boolean deletePerson(int perNum) { return repository.remove(perNum); }

    // 번호로 조회햇을대 사람 존재 유무
    public boolean hasBmi(int perNum) { return repository.findOne(perNum) != null; }
}






