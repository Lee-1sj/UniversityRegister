package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.StudentVO;

public class StudentDAO {
    // 학생 등록
    public void setStudentRegister(StudentVO svo) {
        String sql = "INSERT INTO student VALUES (student_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, svo.getSd_num());
            pstmt.setString(2, svo.getSd_name());
            pstmt.setString(3, svo.getSd_id());
            pstmt.setString(4, svo.getSd_passwd());
            pstmt.setString(5, svo.getS_num());
            pstmt.setString(6, svo.getSd_birthday());
            pstmt.setString(7, svo.getSd_phone());
            pstmt.setString(8, svo.getSd_address());
            pstmt.setString(9, svo.getSd_email());

            int i = pstmt.executeUpdate();
            if (i == 1) {
                System.out.println(svo.getSd_name() + " 등록 성공");
            } else {
                System.out.println(" 등록 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResource(pstmt, con);
        }
    } // end of setStudentRegister()

    // 학생 정보 수정
    public void setStudentUpdate(StudentVO svo) {
        String sql = "UPDATE student SET sd_passwd = ?, sd_phone = ?, sd_address = ?, sd_email = ? WHERE sd_num = ?";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, svo.getSd_passwd());
            pstmt.setString(2, svo.getSd_phone());
            pstmt.setString(3, svo.getSd_address());
            pstmt.setString(4, svo.getSd_email());
            pstmt.setString(5, svo.getSd_num());

            int i = pstmt.executeUpdate();
            if (i == 1) {
                System.out.println(svo.getSd_name() + " 학생 정보 수정 성공");
            } else {
                System.out.println(" 학생 정보 수정 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResource(pstmt, con);
        }
    } // end of setStudentUpdate()

    // 동일 학과 학생 일련번호
    public String getStudentCount(String subjectNum) {
        String sql = "SELECT LPAD(count(*)+1, 4, '0') AS studentCount FROM student WHERE s_num = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String serialNumber = "";
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, subjectNum);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                serialNumber = rs.getString("studentCount");
            }
        } catch (SQLException se) {
            System.out.println(se);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResource(rs, pstmt, con);
        }
        return serialNumber;
    } // end of getStudentCount()

    // 학생 아이디 중복 체크
    public boolean getStudentIdOverlap(String idOverlap) {
        String sql = "SELECT * FROM student WHERE sd_id = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean idOverlapResult = false;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, idOverlap);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                idOverlapResult = true; // 중복된 아이디가 있다.
            }
        } catch (SQLException se) {
            System.out.println(se);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResource(rs, pstmt, con);
        }
        return idOverlapResult;
    } // end of getStudentIdOverlap()

    // 학생 로그인
    public boolean getStudentLogin(String id, String pw) {
        String sql = "SELECT * FROM student WHERE sd_id = ? and sd_passwd = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean loginSuccess = false;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                loginSuccess = true; // 중복
            }
        } catch (SQLException se) {
            System.out.println(se);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResource(rs, pstmt, con);
        }
        return loginSuccess;
    } // end of getStudentLogin()

    // 학생 번호
    public String getStudentNum(String id, String pw) {
        String sql = "SELECT sd_num FROM student WHERE sd_id = ? and sd_passwd = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sd_num = "";

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                sd_num = rs.getString("sd_num");
            }
        } catch (SQLException se) {
            System.out.println(se);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResource(rs, pstmt, con);
        }
        return sd_num;
    } // end of getStudentNum()

    // 학생 정보
    public void getStudent(String id, String pw) {
        String sql = "SELECT * FROM student WHERE sd_id = ? and sd_passwd = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StudentVO sVo = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            rs = pstmt.executeQuery();

            System.out.println("일련번호\t학생번호\t성명\t아이디\t비밀번호\t학과\t생년월일\t전화번호\t주소\t이메일\t등록일자");

            if (rs.next()) {
                sVo = new StudentVO();
                sVo.setNo(rs.getInt("no"));
                sVo.setSd_num(rs.getString("sd_num"));
                sVo.setSd_name(rs.getString("sd_name"));
                sVo.setSd_id(rs.getString("sd_id"));
                sVo.setSd_passwd(rs.getString("sd_passwd"));
                sVo.setS_num(rs.getString("s_num"));
                sVo.setSd_birthday(rs.getString("sd_birthday"));
                sVo.setSd_phone(rs.getString("sd_phone"));
                sVo.setSd_address(rs.getString("sd_address"));
                sVo.setSd_email(rs.getString("sd_email"));
                sVo.setSd_date(rs.getString("sd_date") + "");

                System.out.println(sVo.getNo() + "\t\t" + sVo.getSd_num() + "\t\t" + sVo.getSd_name() + "\t\t"
                        + sVo.getSd_id()
                        + "\t\t" + sVo.getSd_passwd() + "\t\t" + sVo.getS_num() + "\t\t" + sVo.getSd_birthday() + "\t\t"
                        + sVo.getSd_phone() + "\t\t" + sVo.getSd_address() + "\t\t" + sVo.getSd_email() + "\t\t"
                        + sVo.getSd_date());
            }
        } catch (SQLException se) {
            System.out.println(se);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResource(rs, pstmt, con);
        }
    } // end of getStudent

    // 학생 전체 목록
    public void getStudentTotalList() {
        String sql = "SELECT st.no AS no, sd_num, sd_name, sd_id, sd_passwd, su.s_name as s_num, sd_birthday, sd_phone, sd_phone, sd_address, sd_email, sd_date"
                + " FROM STUDENT st, SUBJECT su WHERE st.s_num = su.s_num ORDER BY no";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StudentVO sVo = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            System.out.println();
            System.out.println();
            System.out.println("                                                                             학생 정보 전체 목록");
            System.out.println(
                    "=====================================================================================================================================================================================");
            System.out.println(String.format("%-5s %-8s %-6s %-15s %-15s %-8s %-12s %-14s %-14s %-25s %-15s",
                    "일련번호", "학생번호", "성명", "아이디", "비밀번호", "학과", "생년월일", "전화번호", "주소", "이메일", "등록일자"));
            System.out.println(
                    "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                sVo = new StudentVO();
                sVo.setNo(rs.getInt("no"));
                sVo.setSd_num(rs.getString("sd_num"));
                sVo.setSd_name(rs.getString("sd_name"));
                sVo.setSd_id(rs.getString("sd_id"));
                sVo.setSd_passwd(rs.getString("sd_passwd"));
                sVo.setS_num(rs.getString("s_num"));
                sVo.setSd_birthday(rs.getString("sd_birthday"));
                sVo.setSd_phone(rs.getString("sd_phone"));
                sVo.setSd_address(rs.getString("sd_address"));
                sVo.setSd_email(rs.getString("sd_email"));
                sVo.setSd_date(rs.getString("sd_date") + "");

                System.out.println(String.format("%-10s %-11s %-5s %-16s %-18s %-8s %-15s %-14s %-15s %-25s %-15s",
                        sVo.getNo(), sVo.getSd_num(), sVo.getSd_name(), sVo.getSd_id(), sVo.getSd_passwd(),
                        sVo.getS_num(), sVo.getSd_birthday(), sVo.getSd_phone(), sVo.getSd_address(),
                        sVo.getSd_email(), sVo.getSd_date()));
            }
            System.out.println(
                    "=====================================================================================================================================================================================");
        } catch (SQLException se) {
            System.out.println(se);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResource(rs, pstmt, con);
        }
    } // end of getStudentTotalList()
}
