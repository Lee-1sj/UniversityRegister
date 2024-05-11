package controller;

import java.util.Scanner;

import model.LessonVO;
import model.TraineeVO;

public class TraineeRegisterManager {
    public static Scanner sc = new Scanner(System.in);
    // 수강 신청한 목록
    public void traineeList() {
        String sd_num;      //학번
        String id;          //아이디
        String pw;          //비밀번호
        String mainMenu;    //메인메뉴
        TraineeDAO td = new TraineeDAO();
        StudentDAO sdao = new StudentDAO();
        boolean success = false;

        System.out.println("수강 신청한 목록 보기");
        
        do {
            System.out.print("아이디 : ");
            id = sc.nextLine();
            System.out.print("비밀번호 : ");
            pw = sc.nextLine();
            success = sdao.getStudentLogin(id, pw);
            if(!success) {
                System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
                System.out.print("메인 메뉴로 이동(y/n) : ");
                mainMenu = sc.nextLine();
                if(mainMenu.toLowerCase().equals("y")){
                    return;
                }
                System.out.println();
            }

        } while (!success);

        sd_num = sdao.getStudentNum(id, pw);
        System.out.println();
        System.out.println(id + "님의 수강 신청한 리스트");
        td.getTraineeTotalList(sd_num);
        System.out.println();
    }

    // 수강 신청
    public void traineeRegister() {
        TraineeDAO td = new TraineeDAO();
        TraineeVO tvo = new TraineeVO();
        LessonDAO ld = new LessonDAO();
        LessonVO lvo = new LessonVO();
        StudentDAO sdao = new StudentDAO();

        String sd_num;      //학번
        String l_abbre;     //과목약어
        String t_section;   //과목구분
        String id;          //아이디
        String pw;          //비밀번호
        String mainMenu;    //메인메뉴
        boolean success = false;

        System.out.println("수강신청가능 과목 전체 리스트");
        ld.getLessonTotalList();
        System.out.println();
        System.out.println("수강 신청을 위한 정보 입력");
        do{
            System.out.print("아이디 : ");
            id = sc.nextLine();
            System.out.print("비밀번호 : ");
            pw = sc.nextLine();
            success = sdao.getStudentLogin(id, pw);
            if(!success){
                System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
                System.out.print("메인 메뉴로 이동(y/n) : ");
                mainMenu = sc.nextLine();
                if(mainMenu.toLowerCase().equals("y")){
                    return;
                }
                System.out.println();
            } 
        } while (!success);

        sd_num = sdao.getStudentNum(id, pw);
        System.out.println("학번 : " + sd_num);
        System.out.println("과목약어(영문 대문자) : ");
        l_abbre = sc.nextLine();
        System.out.println("과목구분(교양,전공,부전공) : ");
        t_section = sc.nextLine();
        
        tvo.setSd_num(sd_num);
        tvo.setL_abbre(l_abbre);
        tvo.setT_section(t_section);

        //수강신청 등록
        td.setTraineeRegister(tvo);

        System.out.println();
        System.out.println("수강 신청한 리스트");
        td.getTraineeTotalList(sd_num);
        System.out.println();
    } //end of traineeRegister()

    // 수강 취소
    public void traineeDelete() {
        TraineeDAO td = new TraineeDAO();
        StudentDAO sdao = new StudentDAO();

        String sd_num;  //학번
        int t_no;       //수강 번호
        String id;
        String pw;  
        String mainMenu;
        boolean success = false;

        System.out.println("수강 취소를 위한 정보 입력");
        do {
            System.out.print("아이디 : ");
            id = sc.nextLine();
            System.out.print("비밀번호 : ");
            pw = sc.nextLine();
            success = sdao.getStudentLogin(id, pw);
            if(!success){
                System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
                System.out.print("메인 메뉴로 이동(y/n) : ");
                mainMenu = sc.nextLine();
                if(mainMenu.toLowerCase().equals("y")){
                    return;
                }
                System.out.println();
            } 
        } while (!success);

        sd_num = sdao.getStudentNum(id, pw);
        System.out.println();
        System.out.println("수강 신청한 리스트");
        td.getTraineeTotalList(sd_num);
        System.out.println();
        // 수강신청삭제
        System.out.println("취소할 수강 신청 일련번호 입력");
        System.out.print("일련번호 : ");
        t_no = sc.nextInt();
        sc.nextLine();
        td.setTraineeDelete(t_no);
        System.out.println();
        System.out.println("수강신청 취소 후 리스트");
        td.getTraineeTotalList(sd_num);
        System.out.println();

    } //end of traineeDelete()

}
