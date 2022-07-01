package com.jdbc.basic.bmi.repository;

import com.jdbc.basic.Connect;
import com.jdbc.basic.bmi.domain.Bmi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BmiOracleRepo implements BmiRepository{
    @Override
    public boolean save(Bmi bmi) {

        String sql = "INSERT INTO bmi " +
                "VALUES (seq_bmi.nextval, ?,?,?,?,?,?,?,?)";

        try (Connection conn = Connect.makeConnection()) { // 연결
            // 트랜잭션 처리
            conn.setAutoCommit(false); // 자동커밋 끄기

            PreparedStatement pstmt = conn.prepareStatement(sql); // 연결할 객체
            pstmt.setString(1, bmi.getPerName());
            pstmt.setDouble(2,bmi.getHeight());
            pstmt.setDouble(3,bmi.getWeight());
            pstmt.setDouble(4,bmi.getBmi());
            pstmt.setDouble(5,bmi.getMin());
            pstmt.setDouble(6,bmi.getMax());
            pstmt.setString(7,bmi.getResult());
            pstmt.setDouble(8,bmi.getTarget());


            int result = pstmt.executeUpdate(); // 실행

            if (result != 0) {
                conn.commit(); // 커밋 완료
                return true;
            } else {
                conn.rollback(); // 롤백
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public boolean remove(int perNum) {

        String sql = "DELETE FROM bmi WHERE per_num=?";

        try (Connection conn = Connect.makeConnection()) { // 연결
            // 트랜잭션 처리
            conn.setAutoCommit(false); // 자동커밋 설정 끄기

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, perNum);

            int result = pstmt.executeUpdate(); // 실행

            if (result != 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean modify(Bmi bmi) {
        String sql = "UPDATE bmi " +
                " SET height = ?, weight = ?, bmi = ?, min = ?, max = ?, result = ?, target = ? " +
                " WHERE per_num = ?";

        try (Connection conn = Connect.makeConnection()) { // 연결

            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, bmi.getHeight());
            pstmt.setDouble(2, bmi.getWeight());
            pstmt.setDouble(3, bmi.getBmi());
            pstmt.setString(4, bmi.getResult());
            pstmt.setDouble(5, bmi.getMin());
            pstmt.setDouble(6, bmi.getMax());
            pstmt.setDouble(7, bmi.getTarget());
            pstmt.setInt(8, bmi.getPerNum());

            int result = pstmt.executeUpdate();

            if (result != 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Map<Integer, Bmi> findAll() {

        Map<Integer, Bmi> bimMap = new HashMap<>();

        String sql = "SELECT * FROM bmi ORDER BY per_num";

        try (Connection conn = Connect.makeConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Bmi b = new Bmi(
                        rs.getInt("per_num")
                        , rs.getString("per_name")
                        , rs.getDouble("height")
                        , rs.getDouble("weight")
                        , rs.getDouble("bmi")
                        , rs.getDouble("min")
                        , rs.getDouble("max")
                        , rs.getString("result")
                        , rs.getDouble("target")
                );
                bimMap.put(b.getPerNum(), b);
            }
            return bimMap;
        }  catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    @Override
    public Bmi findOne(int perNum) {

        String sql = "SELECT * FROM bmi WHERE per_num = ?";

        try (Connection conn = Connect.makeConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, perNum);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Bmi b = new Bmi(
                        rs.getInt("per_num")
                        , rs.getString("per_name")
                        , rs.getDouble("height")
                        , rs.getDouble("weight")
                        , rs.getDouble("bmi")
                        , rs.getDouble("min")
                        , rs.getDouble("max")
                        , rs.getString("result")
                        , rs.getDouble("target")
                );
                return b;
            }

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
