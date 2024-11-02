/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GTS;
import java.sql.*;
/**
 *
 * @author buikh
 */
public class XLTS implements IThisinh {
    private static Connection cn;
    @Override
    public void getcon(){
        if(cn == null){
            try {
                cn = DriverManager.getConnection("jdbc:sqlserver://BKAHUYYYYY;database=DLTS;user=sa;password=1;trustServerCertificate=true;");
                System.out.println("pass");
        } catch (SQLException e) {
                System.out.println("failed connect" + e.getMessage());
            }
        }
        
    }

    @Override
    public ResultSet getTS() {
        getcon();
        try {
            Statement st = cn.createStatement();
            return st.executeQuery("select * from tbThisinh");
        } catch (SQLException e) {
            System.out.println("loi roi" + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean insertTS(Thisinh ts) {
        getcon();
        try {
            PreparedStatement pst = cn.prepareStatement("insert into tbThisinh values (?, ?, ?, ?, ?)");
            pst.setInt(1, ts.getSobd());
            pst.setString(2, ts.getHoten());
            pst.setString(3, ts.getGioitinh());
            pst.setString(4, ts.getNganh());
            pst.setInt(5, ts.getTongdiem());
            int res = pst.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            System.out.println("deo dc" + e.getMessage());
            return false;
        }
    }
}