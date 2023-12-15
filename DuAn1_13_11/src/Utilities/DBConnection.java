
package Utilities;
import java.sql.*;

public class DBConnection {
    public static String Url = "jdbc:sqlserver://localhost:1433;databaseName=DB_Nhom3_09_11_V2;encrypt=true;trustServerCertificate=true";
    public static String Username = "sa";
    public static String Pass = "244456";

    static {
        try {          
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public
            static Connection getConnection(){
        Connection cn = null;
        
           try {
               cn = DriverManager.getConnection(Url, Username, Pass);
           } catch (SQLException ex) {
               java.util.logging.Logger.getLogger(DBConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
           }
        return cn;
    }
    public static void main(String[] args) {
        Connection cn = getConnection();
        if(cn != null){
            System.out.println("Kết nối thành công!");
        } else{
            System.out.println("Lỗi kết nối");
        }
    }
}
