package controller;

import java.util.Scanner;

import model.SubjectVO;

//학과정보컨트롤러
public class SubjectRegisterManager {
    // 학과목록 보여주는 기능구현
    public void subjectList() {
        SubjectDAO sd = new SubjectDAO();
        sd.getSubjectTotalList();
    }

    // 학과정보 저장하는 기능구현
    public void subjectRegister() {
        Scanner input = new Scanner(System.in);
        SubjectDAO sd = new SubjectDAO();

        System.out.println("학과 정보 리스트");
        sd.getSubjectTotalList(); // 리스트 전체를 한번 출력

        System.out.println("학과 정보 입력");
        System.out.print("학과번호 >> ");
        String s_num = input.nextLine();
        System.out.print("학과명 >> ");
        String s_name = input.nextLine();
        SubjectVO svo = new SubjectVO(s_num, s_name);

        sd.setSubjectRegister(svo);
    }

    // 학과정보 수정하는 기능구현
    public void subjectUpdate() {
        Scanner input = new Scanner(System.in);
        SubjectDAO sd = new SubjectDAO();

        System.out.println("학과 정보 리스트");
        sd.getSubjectTotalList(); // 리스트 전체를 한번 출력

        System.out.println("수정할 학과 일련번호 입력");
        System.out.print("일련번호 >> ");
        int no = Integer.parseInt(input.nextLine());
        System.out.print("학과번호 입력 >> ");
        String s_num = input.nextLine();
        System.out.print("학과명 입력 >> ");
        String s_name = input.nextLine();

        SubjectVO svo = new SubjectVO(no, s_num, s_name);
        sd.setSubjectUpdate(svo);
    }

    // 학과정보 삭제하는 기능구현
    public void subjectDelete() {
        Scanner input = new Scanner(System.in);
        SubjectDAO sd = new SubjectDAO();

        System.out.println("학과 정보 리스트");
        sd.getSubjectTotalList(); // 리스트 전체를 한번 출력

        System.out.println("삭제할 학과 일련번호 입력");
        System.out.print("일련번호 >> ");
        int no = Integer.parseInt(input.nextLine());

        sd.setSubjectDelete(no);
    }
}
