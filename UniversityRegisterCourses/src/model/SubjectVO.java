package model;

//학과 vo 클래스
public class SubjectVO {
    private int no; // 학과 일련번호
    private String s_num; // 학과 번호
    private String s_name; // 학과명

    public SubjectVO() {

    }

    public SubjectVO(int no, String s_num, String s_name) {
        this.no = no;
        this.s_num = s_num;
        this.s_name = s_name;
    }

    public SubjectVO(String s_num, String s_name) {
        this.s_num = s_num;
        this.s_name = s_name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getS_num() {
        return s_num;
    }

    public void setS_num(String s_num) {
        this.s_num = s_num;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    @Override
    public String toString() {
        return "일련번호 : " + getNo() + ", 학과명 : " + getS_name() + ", 학과번호 : " + getS_num() + "]";
    }
}
