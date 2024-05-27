package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.SubjectVO;

public class SubjectDAO {
    // 학과 목록
    public void getSubjectTotalList() {
        String sql = "SELECT * FROM subject order by no";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SubjectVO sVo = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            System.out.println("일련번호\t학과번호\t학과명");

            while (rs.next()) {
                sVo = new SubjectVO();
                sVo.setNo(rs.getInt("no"));
                sVo.setS_num(rs.getString("s_num"));
                sVo.setS_name(rs.getString("s_name"));
                System.out.println(sVo.getNo() + "\t\t" + sVo.getS_num() + "\t\t" + sVo.getS_name());
            }
        } catch (SQLException se) {
            System.out.println(se);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResource(rs, pstmt, con);
        }
    } // end of getSubjectTotalList()

    // 학과 등록
    public void setSubjectRegister(SubjectVO svo) {
        String sql = "INSERT INTO subject VALUES (subject_seq.nextval, ?, ?)";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, svo.getS_num());
            pstmt.setString(2, svo.getS_name());

            int i = pstmt.executeUpdate();
            if (i == 1) {
                System.out.println(svo.getS_name() + " 등록 성공");
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
    } // end of setSubjectRegister()

    // 학과 수정
    public void setSubjectUpdate(SubjectVO svo) {
        String sql = "UPDATE subject SET s_num = ?, s_name = ? WHERE no = ?";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, svo.getS_num());
            pstmt.setString(2, svo.getS_name());
            pstmt.setInt(3, svo.getNo());

            int i = pstmt.executeUpdate();
            if (i == 1) {
                System.out.println(svo.getS_name() + " 등록 성공");
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
    } // end of setSubjectUpdate()

    // 학과 삭제
    public void setSubjectDelete(int no) {
        String sql = "DELETE FROM subject WHERE no = ?";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, no);

            int i = pstmt.executeUpdate();
            if (i == 1) {
                System.out.println(no + "번 학과 삭제 성공");
            } else {
                System.out.println("학과 삭제 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResource(pstmt, con);
        }
    } // end of setSubjectDelete()

} // end of class
