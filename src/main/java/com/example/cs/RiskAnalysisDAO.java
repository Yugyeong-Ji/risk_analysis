package com.example.cs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class RiskAnalysisDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public RiskAnalysisDAO(){
        try {
            String dbURL = "jdbc:mariadb://localhost:3306/cs";
            String dbID = "root";
            String dbPassword = "1234";
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int RiskAnalysis(String time, String progress, String weather, String type, String cost, char rank){
        String SQL="SELECT * FROM apriori WHERE time = ?";
        try{
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,time);
            rs=pstmt.executeQuery();
            if(rs.next()){
                if(rs.getString(1).equals(progress)&&rs.getString(1).equals(weather)&&rs.getString(1).equals(type)&&rs.getString(1).equals(cost))
                    return rank;
                else return 'D';

            }
            return 'D';
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -2;
    }
}
