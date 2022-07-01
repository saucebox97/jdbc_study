package com.jdbc.basic.bmi.view;

import com.jdbc.basic.bmi.controller.BmiController;
import com.jdbc.basic.bmi.domain.Bmi;

import java.util.List;
import java.util.Scanner;

public class BmiMenu {

    private final BmiController controller;
    private final Scanner sc;

    public BmiMenu() {
        controller = new BmiController();
        sc = new Scanner(System.in);
    }


    public void mainMenu() {

        String id = "0";
        String pw = "0";

        while (!id.equals("sqld")) {
            System.out.print("# 아이디를 입력하세요:");
            id = sc.nextLine();
        }

        while (!pw.equals("1234")) {
            System.out.print("# 비밀번호를 입력하세요:");
            pw = sc.nextLine();
        }

        while (true) {
            System.out.println("\n======= 성인 bmi 계산 프로그램 ========");
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
                    findAllMenu();
                    break;
                case 3:
                    findOneMenu();
                    break;
                case 4:
                    modifyMenu();
                    break;
                case 5:
                    removeMenu();
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

    // 5번 메뉴
    private void removeMenu() {

        System.out.println("\n # 수정할 사람의 번호을 입력하세요!");
        int perNum = inputN(">>> ");

        if (controller.hasBmi(perNum)) {

            boolean flag = controller.deletePerson(perNum);
            if (flag) {
                System.out.println("# 삭제가 완료되었습니다.");
            } else {
                System.out.println("# 삭제에 실패했습니다.");
            }

        } else {
            System.out.println("\n# 해당 번호는 존재하지 않습니다.");
        }

    }

    // 4번 메뉴
    private void modifyMenu() {

        System.out.println("\n # 수정할 사람의 번호을 입력하세요!");
        int perNum = inputN(">>> ");

        if (controller.hasBmi(perNum)) {

            System.out.println("# 수정할 신체를 입력하세요.");
            double height;
            while (true) {
                height = inputN("- 키: ");
                if (height >= 100 && height <= 200) break;
                else System.out.println("100이상 200이하만 입력하세요.");
            }

            double weight;
            while (true) {
                weight = inputN("- 몸무게: ");
                if (weight >= 30 && weight <= 200) break;
                else System.out.println("30이상 200이하만 입력하세요.");
            }


            boolean flag = controller.updatePerson(perNum, height, weight);
            if (flag) {
                System.out.println("# 수정이 완료되었습니다.");
            } else {
                System.out.println("# 수정이 실패했습니다.");
            }

        } else {
            System.out.println("\n# 해당 번호는 존재하지 않습니다");
        }

    }

    // 3번 메뉴
    private void findOneMenu() {

        System.out.println("\n# 조회할 사람의 번호을 입력하세요!");
        int perNum = inputN(">>> ");

        Bmi person = controller.findOnePerson(perNum);
        if (person != null) {
            System.out.println("\n========== 조회 결과 ===========");
            System.out.println("- 번호: " + person.getPerNum());
            System.out.println("- 이름: " + person.getPerName());
            System.out.printf("- 키: %.1fcm\n", person.getHeight());
            System.out.printf("- 몸무게: %.1fkg\n", person.getWeight());
            System.out.println("- Bmi: " + person.getBmi());
            System.out.printf("- 최소체중: %.1fkg\n", person.getMin());
            System.out.printf("- 최대체중: %.1fkg\n", person.getMax());
            System.out.println("- 결과: " + person.getResult());
            System.out.printf("- 목표: %5skg", (person.getTarget() > 0) ? "+" + person.getTarget() : person.getTarget());
        } else {
            System.out.println("\n# 해당 번호는 존재하지 않습니다");
        }
    }

    // 2번 메뉴
    private void findAllMenu() {

        List<Bmi> person = controller.findAllpersons();

        System.out.println("=============================== 모든 신체 정보 =================================");
        System.out.printf("%5s%5s%7s%6s%8s%8s%8s%7s%7s\n", "번호", "이름", "키", "몸무게", "BMI", "최소체중", "최대체중", "결과", "목표");
        System.out.println("------------------------------------------------------------------------------");

        for (Bmi b : person) {
            String target;
            if (b.getTarget() > 0) target = "+" + b.getTarget() + "kg";
            else if (b.getTarget() == 0) target = "없음";
            else target = String.valueOf(b.getTarget()) + "kg";

            System.out.printf("%5d %5s %5.1fcm %5.1fkg %7.1f %7.1fkg %8skg %6s %8s\n"
                    , b.getPerNum(), b.getPerName(), b.getHeight()
                    , b.getWeight(), b.getBmi(), b.getMin(), "-  " + b.getMax(), b.getResult()
                    , target);
        }

    }

    // 1번 메뉴
    private void insertMenu() {

        System.out.println("\n# 신체 정보 입력을 시작합니다.");
        System.out.print("- 이름: ");
        String name = sc.next();

        double height;
        while (true) {
            height = inputN("- 키: ");
            if (height >= 100 && height <= 200) break;
            else System.out.println("100이상 200이하만 입력하세요.");
        }

        double weight;
        while (true) {
            weight = inputN("- 몸무게: ");
            if (weight >= 30 && weight <= 200) break;
            else System.out.println("30이상 200이하만 입력하세요.");
        }

        Bmi bmi = new Bmi();
        bmi.setPerName(name);
        bmi.setHeight(height);
        bmi.setWeight(weight);
        bmi.calc();

        controller.insertPeople(bmi);

        System.out.printf("\n# %s님의 신체 정보가 저장되었습니다.\n", name);

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
