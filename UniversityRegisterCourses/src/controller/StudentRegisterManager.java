package controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.StudentVO;

public class StudentRegisterManager {
    public static Scanner sc = new Scanner(System.in);

    // 학생 정보 입력
    public void studentRegister() {
        SubjectDAO sdao = new SubjectDAO();
        StudentDAO sd = new StudentDAO();
        StudentVO svo = new StudentVO();

        String sd_num; // 학번
        String sd_name; // 이름
        String sd_id; // 아이디
        String sd_passwd; // 비밀번호
        String s_num; // 학과번호
        String sd_birthday; // 생년월일
        String sd_phone; // 핸드폰번호
        String sd_address; // 주소
        String sd_email; // 이메일

        boolean id_check;
        String year;

        try {
            System.out.println("학생 정보 입력");
            System.out.print("성명 : ");
            sd_name = sc.nextLine();

            do {
                System.out.print("아이디(8자 이상 12자 이내) : ");
                sd_id = sc.nextLine();
                id_check = sd.getStudentIdOverlap(sd_id);
                if (id_check) {
                    System.out.println("중복된 아이디입니다. 다시 입력하세요.");
                }
            } while (id_check);
            System.out.print("비밀번호(12자 이내) : ");
            sd_passwd = sc.nextLine();
            sdao.getSubjectTotalList();
            System.out.print("학과번호 : ");
            s_num = sc.nextLine();

            // 학생 번호는 8자리로 생성한다. (연도2자리+학과2자리+일련번호)
            SimpleDateFormat sdf = new SimpleDateFormat("yy");
            year = sdf.format(new Date());
            sd_num = year + s_num + sd.getStudentCount(s_num);

            System.out.print("생년월일(8자리) : ");
            sd_birthday = sc.nextLine();
            System.out.print("전화번호 : ");
            sd_phone = sc.nextLine();
            System.out.print("도로명 주소 : ");
            sd_address = sc.nextLine();
            System.out.print("이메일 : ");
            sd_email = sc.nextLine();

            svo.setSd_num(sd_num);
            svo.setSd_name(sd_name);
            svo.setSd_id(sd_id);
            svo.setSd_passwd(sd_passwd);
            svo.setS_num(s_num);
            svo.setSd_birthday(sd_birthday);
            svo.setSd_phone(sd_phone);
            svo.setSd_address(sd_address);
            svo.setSd_email(sd_email);
            sd.setStudentRegister(svo);

            System.out.println();
            System.out.println("등록 학생 정보");
            sd.getStudent(svo.getSd_id(), svo.getSd_passwd());
            System.out.println();
        } catch (InputMismatchException e) {
            System.err.println("입력 형식이 잘못되었습니다. 다시 시도해주세요.");
            sc.nextLine(); // 클리어
        } catch (Exception e) {
            System.err.println("예상치 못한 오류가 발생했습니다: " + e.getMessage());
        } finally {
            sc.close();
        }

    } // end of studentRegister()

    // 학생 정보 수정
    public void studentUpdate() {
        StudentDAO sdao = new StudentDAO();
        StudentVO svo = new StudentVO();

        String id;
        String pw;
        String sd_num;
        String sd_passwd;
        String sd_phone;
        String sd_address;
        String sd_email;
        boolean success = false;
        System.out.println("학생 정보 수정");

        try {
            do {
                System.out.print("아이디 : ");
                id = sc.nextLine();
                System.out.print("비밀번호 : ");
                pw = sc.nextLine();
                success = sdao.getStudentLogin(id, pw);

                if (!success) {
                    System.out.println("아이디 또는 비밀번호가 틀림. 다시 입력하세요.");
                }
            } while (!success);
            sd_num = sdao.getStudentNum(id, pw);
            System.out.println();
            System.out.println("수정할 학생");
            System.out.println("학생번호 : " + sd_num);
            System.out.print("비밀번호(12자 이내) : ");
            sd_passwd = sc.nextLine();
            System.out.print("전화번호 : ");
            sd_phone = sc.nextLine();
            System.out.print("도로명 주소 : ");
            sd_address = sc.nextLine();
            System.out.print("이메일 : ");
            sd_email = sc.nextLine();

            svo.setSd_num(sd_num);
            svo.setSd_passwd(sd_passwd);
            svo.setSd_phone(sd_phone);
            svo.setSd_address(sd_address);
            svo.setSd_email(sd_email);

            sdao.setStudentUpdate(svo);
            System.out.println();
            System.out.println("학생 정보 수정 결과");
            sdao.getStudent(id, svo.getSd_passwd());
            System.out.println();

        } catch (InputMismatchException e) {
            System.err.println("입력 형식이 잘못되었습니다. 다시 시도해주세요.");
            sc.nextLine(); // 클리어
        } catch (Exception e) {
            System.err.println("예상치 못한 오류가 발생했습니다: " + e.getMessage());
        } 

    } // end of studentUpdate()

    // 학생 전체 목록
    public void studentTotalList() {
        StudentDAO sdao = new StudentDAO();
        String pw;
        
        try {
            System.out.println();
            System.out.print("관리자 비밀번호 : ");
            pw = sc.nextLine();
    
            if (pw.equals("admin1234")) {
                sdao.getStudentTotalList();
            } else {
                System.out.println("관리자 비밀번호가 틀립니다.");
            }

        } catch (InputMismatchException e) {
            System.err.println("입력 형식이 잘못되었습니다. 다시 시도해주세요.");
            sc.nextLine(); // 클리어
        } catch (Exception e) {
            System.err.println("예상치 못한 오류가 발생했습니다: " + e.getMessage());
        }
    }
}