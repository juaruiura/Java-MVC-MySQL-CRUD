/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author AlumnoTarde
 */
public class Modelo {
    public Connection con;
    public boolean conectar(){
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String strUrl = "jdbc:mysql://localhost/empleados";
            con = DriverManager.getConnection(strUrl, "root", "");
            return true;
        }catch(SQLException sqle){
            con=null;
            return false;
        }
    }
    
    public void insertar (int empno, String ename, String job, int mgr, float sal, float comm, float deptno) throws SQLException{
        this.conectar();
        PreparedStatement ps = this.con.prepareStatement(
                "INSERT INTO emp VALUES(" + empno + ", '" + ename + "', '" + job +
                        "', " + mgr + ", " + sal + ", " + comm + ", " + deptno + ")");
        ps.executeUpdate();
        this.con.close();
    }
    
    public String[] buscar(int empno) throws SQLException{
        String[] result = new String[6];
        this.conectar();
        PreparedStatement ps = this.con.prepareStatement("SELECT * FROM emp WHERE empno='" + empno + "'");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            result[0] = rs.getString("ename");
            result[1] = rs.getString("job");
            result[2] = rs.getString("mgr");
            result[3] = String.valueOf(rs.getFloat("sal"));
            result[4] = String.valueOf(rs.getFloat("comm"));
            result[5] = String.valueOf(rs.getInt("deptno"));
        }
        this.con.close();
        return result;
    }
    
    public void modificar(int empno, String ename, String job, int mgr, float sal, float comm, float deptno) throws SQLException{
        this.conectar();
        PreparedStatement ps = this.con.prepareStatement("UPDATE emp SET ename='" + ename + 
                "', job='" + job + "', mgr=" + mgr + ", sal=" + sal + ", comm=" + comm +
                ", deptno=" + deptno + "WHERE empno=" + empno);
        ps.executeUpdate();
        this.con.close();
    }
    
    public void eliminar(int empno) throws SQLException{
        this.conectar();
        PreparedStatement ps = this.con.prepareStatement("DELETE FROM emp WHERE empno=" + empno);
        ps.executeUpdate();
        this.con.close();
    }
}
