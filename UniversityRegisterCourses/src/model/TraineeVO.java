package model;

public class TraineeVO {
    private int no; // 수강 일련번호
    private String sd_num; // 학번
    private String l_abbre; // 과목약어
    private String t_section; // 과목구분
    private String t_date; // 등록일

    public TraineeVO() {
    }

    public TraineeVO(int no, String sd_num, String l_abbre, String t_section, String t_date) {
        this.no = no;
        this.sd_num = sd_num;
        this.l_abbre = l_abbre;
        this.t_section = t_section;
        this.t_date = t_date;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getSd_num() {
        return sd_num;
    }

    public void setSd_num(String sd_num) {
        this.sd_num = sd_num;
    }

    public String getL_abbre() {
        return l_abbre;
    }

    public void setL_abbre(String l_abbre) {
        this.l_abbre = l_abbre;
    }

    public String getT_section() {
        return t_section;
    }

    public void setT_section(String t_section) {
        this.t_section = t_section;
    }

    public String getT_date() {
        return t_date;
    }

    public void setT_date(String t_date) {
        this.t_date = t_date;
    }
}