package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.LessonVO;
import model.SubjectVO;
import model.TraineeVO;

public class TraineeDAO {
    //수강 신청
    public void setTraineeRegister(TraineeVO tvo) {
        String sql = "INSERT INTO trainee(no, sd_num, l_abbre, t_section, t_date) VALUES (trainee_seq.nextval, ?, ?, ?, sysdate)";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, tvo.getSd_num());
            pstmt.setString(2, tvo.getL_abbre());
            pstmt.setString(3, tvo.getT_section());

            int i = pstmt.executeUpdate();
            if (i == 1) {
                System.out.println("수강 신청 성공");
            } else {
                System.out.println("수강 신청 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } //end of setTraineeRegister()

    //수강 신청 삭제
    public void setTraineeDelete(int no) {
        String sql = "DELETE FROM trainee WHERE no = ?";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, no);

            int i = pstmt.executeUpdate();
            if (i == 1) {
                System.out.println("수강 신청 취소 완료");
            } else {
                System.out.println("수강 신청 취소 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } //end of setTraineeDelete()

    //개인의 수강신청 전체 목록 출력
    public void getTraineeTotalList(String sd_num) {
        String sql = "SELECT tr.no as no, tr.sd_num as sd_num, tr.l_abbre as l_abbre, le.l_name as l_name, st.sd_name as sd_name, t_section, t_date\r\n" + //
                        "FROM trainee tr, lesson le, student st WHERE tr.sd_num = ? AND tr.l_abbre = le.l_abbre AND tr.sd_num = st.sd_num\r\n" + //
                        "ORDER BY t_date";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TraineeVO tVo = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, sd_num);
            rs = pstmt.executeQuery();

            System.out.println("일련번호\t학생번호\t\t과목약어\t과목명\t\t학생이름\t과목구분\t등록일");

            while (rs.next()) {
                tVo = new TraineeVO();
                tVo.setNo(rs.getInt("no"));
                tVo.setSd_num(rs.getString("sd_num"));
                tVo.setL_abbre(rs.getString("l_abbre"));
                tVo.setT_section(rs.getString("t_section"));
                tVo.setT_date(rs.getString("t_date"));

                System.out.println(tVo.getNo() + "\t\t" + tVo.getSd_num() + "\t\t" + tVo.getL_abbre() + "\t\t" + rs.getString("l_name") + "\t\t"
                + rs.getString("sd_name") + "\t\t" + tVo.getT_section() + "\t\t" + tVo.getT_date());
            }
        } catch (SQLException se) {
            System.out.println(se);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se) {
            }
        }
    } //end of getTraineeTotalList()

    //선택한 과목명의 과목번호
    public String getLessonNum(String lessonName) {
        String sql = "SELECT l_abbre FROM lesson WHERE l_name = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String l_abbre = null;
        
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, lessonName);
            rs = pstmt.executeQuery();

            if (rs.next()){
                l_abbre = rs.getString("l_abbre");
            } else {
                System.out.println("수강 과목의 과목 번호");
                System.out.println("선택한 " + lessonName + " 과목의 과목번호가 없습니다.");
                System.out.println("과목 검색 실패");
            }
        } catch (SQLException se) {
            System.out.println(se);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se) {
            }
        }
        return l_abbre;
    } //end of getLessonNum

}
