package controller;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.SubjectVO;

//학과정보컨트롤러
public class SubjectRegisterManager {
    // 학과목록 보여주는 기능구현
    public void subjectList() {
        SubjectDAO sd = new SubjectDAO();
        try {
            sd.getSubjectTotalList();
        } catch (Exception e) {
            System.err.println("예상치 못한 오류가 발생했습니다: " + e.getMessage());
        }

    }

    // 학과정보 저장하는 기능구현
    public void subjectRegister() {
        Scanner input = new Scanner(System.in);
        SubjectDAO sd = new SubjectDAO();

        try {
            System.out.println("학과 정보 리스트");
            sd.getSubjectTotalList(); // 리스트 전체를 한번 출력

            System.out.println("학과 정보 입력");
            System.out.print("학과번호 >> ");
            String s_num = input.nextLine();
            System.out.print("학과명 >> ");
            String s_name = input.nextLine();
            SubjectVO svo = new SubjectVO(s_num, s_name);

            sd.setSubjectRegister(svo);
        } catch (InputMismatchException e) {
            System.err.println("입력 형식이 잘못되었습니다. 다시 시도해주세요.");
            input.nextLine(); // 잘못된 입력 클리어
        } catch (Exception e) {
            System.err.println("예상치 못한 오류가 발생했습니다: " + e.getMessage());
        } finally {
            input.close();
        }
    }

    // 학과정보 수정하는 기능구현
    public void subjectUpdate() {
        Scanner input = new Scanner(System.in);
        SubjectDAO sd = new SubjectDAO();

        try {
            System.out.println("학과 정보 리스트");
            sd.getSubjectTotalList(); // 리스트 전체를 한번 출력

            System.out.println("수정할 학과 일련번호 입력");
            System.out.print("일련번호 >> ");
            int no = Integer.parseInt(input.nextLine()); // 형식 오류를 피하기 위해 nextLine 후에 파싱
            System.out.print("학과번호 입력 >> ");
            String s_num = input.nextLine();
            System.out.print("학과명 입력 >> ");
            String s_name = input.nextLine();

            SubjectVO svo = new SubjectVO(no, s_num, s_name);
            sd.setSubjectUpdate(svo);
        } catch (NumberFormatException e) {
            System.err.println("일련번호 입력이 잘못되었습니다. 숫자를 입력해주세요.");
        } catch (InputMismatchException e) {
            System.err.println("입력 형식이 잘못되었습니다. 다시 시도해주세요.");
            input.nextLine(); // 클리어
        } catch (Exception e) {
            System.err.println("예상치 못한 오류가 발생했습니다: " + e.getMessage());
        } finally {
            input.close();
        }
    }

    // 학과정보 삭제하는 기능구현
    public void subjectDelete() {
        Scanner input = new Scanner(System.in);
        SubjectDAO sd = new SubjectDAO();

        try {
            System.out.println("학과 정보 리스트");
            sd.getSubjectTotalList(); // 리스트 전체를 한번 출력

            System.out.println("삭제할 학과 일련번호 입력");
            System.out.print("일련번호 >> ");
            int no = Integer.parseInt(input.nextLine());

            sd.setSubjectDelete(no);
            System.out.println("학과가 성공적으로 삭제되었습니다.");
        } catch (NumberFormatException e) {
            System.err.println("일련번호 입력이 잘못되었습니다. 숫자를 입력해주세요.");
        } catch (InputMismatchException e) {
            System.err.println("입력 형식이 잘못되었습니다. 다시 시도해주세요.");
            input.nextLine(); // 클리어
        } catch (Exception e) {
            System.err.println("예상치 못한 오류가 발생했습니다: " + e.getMessage());
        } finally {
            input.close();
        }
    }
}
