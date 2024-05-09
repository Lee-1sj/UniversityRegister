package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.SubjectVO;

public class SubjectDAO {
    // 학과 목록
    public void getSubjectTotalList() throws Exception {
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
                System.out.println(sVo.getNo() + "\t" + sVo.getS_num() + "\t" + sVo.getS_name());
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
    } // end of getSubjectTotalList()

} // end of class
