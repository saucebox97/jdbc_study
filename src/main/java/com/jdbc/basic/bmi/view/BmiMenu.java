package com.jdbc.basic.bmi.view;

import com.jdbc.basic.bmi.controller.BmiController;
import com.jdbc.basic.bmi.domain.Bmi;

import java.util.Scanner;

public class BmiMenu {

    private final BmiController controller;
    private final Scanner sc;

    public BmiMenu() {
        controller = new BmiController();
        sc = new Scanner(System.in);
    }

    public void mainMenu() {

        while (true) {
            System.out.println("\n======= 성적 관리 프로그램 ========");
            System.out.println("# 1. 신체 정보 입력");
            System.out.println("# 2. 신체 전체 조회");
            System.out.println("# 3. 신체 개별 조회");
            System.out.println("# 4. 신체 정보 수정");
            System.out.println("# 5. 신체 정보 삭제");
            System.out.println("# 9. 끝내기");

            int menu = inputN("\n메뉴 입력: ");

            switch (menu) {
                case 1:
                    insertMenu();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 9:
                    System.out.println("\n# 프로그램을 종료합니다.");
                    System.exit(0);
                    return;
                default:
                    System.out.println("\n# 메뉴를 다시 입력하세요.");
            }
        }

    }

    private void insertMenu() {

        System.out.println("\n# 신체 정보 입력을 시작합니다.");
        System.out.print("- 이름: ");
        String name = sc.next();

        double height = inputN("- 키: ");
        double weight = inputN("- 몸무게: ");

        Bmi bmi = new Bmi();
        bmi.setPerName(name);
        bmi.setHeight(height);
        bmi.setWeight(weight);
        bmi.calc();

        controller.insertPeople(bmi);

        System.out.printf("\n# %s님의 성적 정보가 저장되었습니다.\n", name);

    }

    private int inputN(String msg) {
        int n;
        while (true) {
            try {
                System.out.print(msg);
                n = sc.nextInt();
                break;
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("# 정수로만 입력하세요");
            }
        }
        return n;
    }

}
