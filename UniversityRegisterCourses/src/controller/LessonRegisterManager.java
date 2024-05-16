package controller;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.LessonVO;

public class LessonRegisterManager {
    public static Scanner sc = new Scanner(System.in);

    // 과목 정보 목록
    public void lessonList() {
        LessonDAO ld = new LessonDAO();
        try {
            System.out.println("과목 전체 리스트");
            ld.getLessonTotalList();
            System.out.println();
        } catch (Exception e) {
            System.err.println("예상치 못한 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 과목 정보 입력
    public void lessonRegister() {
        LessonDAO ld = new LessonDAO();
        LessonVO lvo = new LessonVO();
        try {
            System.out.println("과목 전체 리스트");
            ld.getLessonTotalList();
            System.out.println();
            System.out.println("과목 정보 입력");
            System.out.print("과목약어 : ");
            String l_abbre = sc.nextLine();
            System.out.print("과목명 : ");
            String l_name = sc.nextLine();

            lvo.setL_abbre(l_abbre);
            lvo.setL_name(l_name);
            ld.setLessonRegister(lvo);
            System.out.println();
            System.out.println("등록 후 과목 전체 리스트");
            ld.getLessonTotalList();
            System.out.println();

        } catch (InputMismatchException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 과목 정보 수정
    public void lessonUpdate() {
        LessonDAO ld = new LessonDAO();
        LessonVO lvo = new LessonVO();

        try {
            System.out.println("과목 전체 리스트");
            ld.getLessonTotalList();
            System.out.println();

            System.out.println("수정할 과목 일련번호 선택");
            System.out.print("일련번호 : ");
            int l_no = sc.nextInt();
            sc.nextLine();
            System.out.println("+++선택한 과목 수정 정보 모두 입력+++");
            System.out.print("과목약어 : ");
            String l_abbre = sc.nextLine();
            System.out.print("과목명 : ");
            String l_name = sc.nextLine();

            lvo.setNo(l_no);
            lvo.setL_abbre(l_abbre);
            lvo.setL_name(l_name);
            ld.setLessonUpdate(lvo);

            System.out.println();
            System.out.println("수정된 과목 전체 리스트");
            ld.getLessonTotalList();
            System.out.println();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 과목 정보 삭제
    public void lessonDelete() {
        LessonDAO ld = new LessonDAO();
        LessonVO lvo = new LessonVO();

        try{
            System.out.println("과목 전체 리스트");
            ld.getLessonTotalList();
            System.out.println();
    
            System.out.println("삭제할 과목 일련번호 선택");
            System.out.print("일련번호 : ");
            int l_no = sc.nextInt();
            sc.nextLine();
            ld.setLessonDelete(l_no);
            System.out.println();
            System.out.println("삭제 후 학과 전체 리스트");
            ld.getLessonTotalList();
            System.out.println();
        }catch(InputMismatchException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
