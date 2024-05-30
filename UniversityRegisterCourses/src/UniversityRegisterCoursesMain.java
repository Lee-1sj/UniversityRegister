import java.util.InputMismatchException;

import controller.LessonRegisterManager;
import controller.StudentRegisterManager;
import controller.SubjectRegisterManager;
import controller.TraineeRegisterManager;
import view.LESSON_CHOICE;
import view.MENU_CHOICE;
import view.MenuViewer;
import view.STUDENT_CHOICE;
import view.SUBJECT_CHOICE;
import view.TRAINEE_CHOICE;

public class UniversityRegisterCoursesMain {
    public static void main(String[] args) {
        mainMenu();
    }// end of main

    public static void mainMenu() {
        int choiceNum;

        while (true) {
            try {
                MenuViewer.mainMenuView();
                choiceNum = MenuViewer.choice.nextInt();
                MenuViewer.choice.nextLine();

                switch (choiceNum) {
                    case MENU_CHOICE.SUBJECT: // 1. 학과 정보 목록/입력/수정/삭제
                        subjectMenu();
                        break;
                    case MENU_CHOICE.STUDENT: // 2. 학생 정보 입력/수정/목록
                        studentMenu();
                        break;
                    case MENU_CHOICE.LESSON: // 3. 과목 정보 목록/입력/수정/삭제
                        lessonMenu();
                        break;
                    case MENU_CHOICE.TRAINEE: // 4. 수강 신청 목록/신청/취소
                        traineeMenu();
                        break;
                    case MENU_CHOICE.EXIT: // 5. 종료
                        System.out.println();
                        System.out.println("           프로그램을 종료합니다.");
                        System.out.println();
                        return;
                    default:
                        System.out.println("해당 메뉴 번호만 입력하세요.");
                }// end of switch
            } catch (InputMismatchException e) {
                System.err.println("잘못된 입력 형식입니다. 숫자를 입력하세요.");
                MenuViewer.choice.nextLine(); 
            } catch (Exception e) {
                System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
                return;
            }

        } // end of while
    }

    // 수강 신청 메뉴
    public static void traineeMenu() {
        int choice;

        TraineeRegisterManager traineeManager = new TraineeRegisterManager();
        MenuViewer.traineeMenuView();
        
        try {
            choice = MenuViewer.choice.nextInt();
            MenuViewer.choice.nextLine();
    
            switch (choice) {
                case TRAINEE_CHOICE.LIST:
                    System.out.println("");
                    traineeManager.traineeList(); // 1. 수강 신청 목록
                    break;
                case TRAINEE_CHOICE.INSERT:
                    System.out.println("");
                    traineeManager.traineeRegister(); // 2. 수강 신청
                    break;
                case TRAINEE_CHOICE.UPDATE:
                    System.out.println("");
                    traineeManager.traineeDelete(); // 3. 수강 취소
                    break;
                case TRAINEE_CHOICE.MAIN: // 4. 메인메뉴
                    return;
                default:
                    System.out.println("해당 메뉴 번호만 입력하세요.");
            }
        } catch (InputMismatchException e) {
            System.err.println("잘못된 입력 형식입니다. 숫자를 입력하세요.");
            MenuViewer.choice.nextLine(); 
        } catch (Exception e) {
            System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
            return;
        }

    }

    // 과목 메뉴
    public static void lessonMenu() {
        int choice;

        LessonRegisterManager lessonManager = new LessonRegisterManager();
        MenuViewer.lessonMenuView();
        
        try {
            choice = MenuViewer.choice.nextInt();
            MenuViewer.choice.nextLine();
    
            switch (choice) {
                case LESSON_CHOICE.LIST:
                    System.out.println("");
                    lessonManager.lessonList(); // 1. 과목 정보 목록
                    break;
                case LESSON_CHOICE.INSERT:
                    System.out.println("");
                    lessonManager.lessonRegister(); // 2. 과목 정보 입력
                    break;
                case LESSON_CHOICE.UPDATE:
                    System.out.println("");
                    lessonManager.lessonUpdate(); // 3. 과목 정보 수정
                    break;
                case LESSON_CHOICE.DELETE:
                    System.out.println("");
                    lessonManager.lessonDelete(); // 4. 과목 정보 삭제
                    break;
                case LESSON_CHOICE.MAIN: // 5. 메인메뉴
                    return;
                default:
                    System.out.println("해당 메뉴 번호만 입력하세요.");
            }// end of switch
        } catch (InputMismatchException e) {
            System.err.println("잘못된 입력 형식입니다. 숫자를 입력하세요.");
            MenuViewer.choice.nextLine();
        } catch (Exception e) {
            System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
            return;
        }
    }

    // 학생 메뉴
    public static void studentMenu() {
        int choice;

        StudentRegisterManager studentManager = new StudentRegisterManager();
        MenuViewer.studentMenuView();
        
        try {
            choice = MenuViewer.choice.nextInt();
            MenuViewer.choice.nextLine();
    
            switch (choice) {
                case STUDENT_CHOICE.INSERT:
                    System.out.println("");
                    studentManager.studentRegister(); // 1. 학생 정보 입력
                    break;
                case STUDENT_CHOICE.UPDATE:
                    System.out.println("");
                    studentManager.studentUpdate(); // 2. 학생 정보 수정
                    break;
                case STUDENT_CHOICE.LIST:
                    System.out.println("");
                    studentManager.studentTotalList(); // 3. 학생 전체 목록
                    break;
                case STUDENT_CHOICE.MAIN: // 4. 메인메뉴
                    return;
                default:
                    System.out.println("해당 메뉴 번호만 입력하세요.");
            }// end of switch
        } catch (InputMismatchException e) {
            System.err.println("잘못된 입력 형식입니다. 숫자를 입력하세요.");
            MenuViewer.choice.nextLine();
        } catch (Exception e) {
            System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
            return;
        }

    }

    // 학과 메뉴
    public static void subjectMenu() throws Exception {
        int choice;
        SubjectRegisterManager subjectManager = new SubjectRegisterManager();
        MenuViewer.subjectMenuView();
        
        try {
            choice = MenuViewer.choice.nextInt();
            MenuViewer.choice.nextLine();
    
            switch (choice) {
                case SUBJECT_CHOICE.LIST:
                    System.out.println("");
                    subjectManager.subjectList(); // 학과 정보 목록
                    break;
                case SUBJECT_CHOICE.INSERT:
                    System.out.println("");
                    subjectManager.subjectRegister(); // 학과 정보 입력
                    break;
                case SUBJECT_CHOICE.UPDATE:
                    System.out.println("");
                    subjectManager.subjectUpdate(); // 학과 정보 수정
                    break;
                case SUBJECT_CHOICE.DELETE:
                    System.out.println("");
                    subjectManager.subjectDelete(); // 학과 정보 삭제
                    break;
                case SUBJECT_CHOICE.MAIN: // 메인 메뉴
                    return;
                default:
                    System.out.println("해당 메뉴 번호만 입력하세요.");
            }// end of switch
        } catch (InputMismatchException e) {
            System.err.println("잘못된 입력 형식입니다. 숫자를 입력하세요.");
            MenuViewer.choice.nextLine(); 
        } catch (Exception e) {
            System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
            return;
        }
    }

} // end of class