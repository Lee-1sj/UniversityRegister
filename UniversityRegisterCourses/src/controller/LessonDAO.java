package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.LessonVO;
import model.StudentVO;

public class LessonDAO {
    //과목 목록
    public void getLessonTotalList() {
        String sql = "SELECT * FROM lesson ORDER BY no";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LessonVO lVo = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            System.out.println("일련번호\t과목약어\t과목명");

            while (rs.next()){
                lVo = new LessonVO();
                lVo.setNo(rs.getInt("no"));
                lVo.setL_abbre(rs.getString("l_abbre"));
                lVo.setL_name(rs.getString("l_name"));

                System.out.println(lVo.getNo() + "\t\t" + lVo.getL_abbre() + "\t\t" + lVo.getL_name());
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
    } //end of getLessonTotalList()

    //과목 등록
    public void setLessonRegister(LessonVO lvo) {
        String sql = "INSERT INTO lesson(no, l_abbre, l_name) VALUES (lesson_seq.nextval, ?, ?)";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, lvo.getL_abbre());
            pstmt.setString(2, lvo.getL_name());

            int i = pstmt.executeUpdate();
            if (i == 1) {
                System.out.println(lvo.getL_name() + " 과목 등록 성공");
            } else {
                System.out.println("과목 등록 실패");
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
    } //end of setLessonRegister

    //과목 수정
    public void setLessonUpdate(LessonVO lvo) {
        String sql = "UPDATE lesson SET l_abbre=?, l_name=? WHERE no=?";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, lvo.getL_abbre());
            pstmt.setString(2, lvo.getL_name());
            pstmt.setInt(3, lvo.getNo());

            int i = pstmt.executeUpdate();
            if (i == 1) {
                System.out.println(lvo.getL_name() + " 과목 수정 성공");
            } else {
                System.out.println("과목 수정 실패");
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
    } //end of setLessonUpdate()

    //과목 삭제
    public void setLessonDelete(int no) {
        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM lesson WHERE no = ?");
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, no);

            int i = pstmt.executeUpdate();
            if (i == 1) {
                System.out.println("과목 삭제 완료");
            } else {
                System.out.println("과목 삭제 실패");
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
    } //end of setLessonDelete()

}
