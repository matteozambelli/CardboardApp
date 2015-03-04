package com.example.fabio.cardboardpb.DB;

import java.sql.*;

/**
 * Created by fabio on 04/03/2015.
 */
public class DBConnect {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DBConnect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //cs.unibg.it:3306/cardboard","root","nW9PQe9bb6VFF2L"
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cardboard","tutorial","slipknot4691");
            if(con!=null)
            {
                System.out.println("Con effettuata");
            }
            else
            {
                System.out.println("Con NON effettuata");
            }
            st = con.createStatement();
        }
        catch(Exception ex){
            System.out.println("Error "+ex);
        }
    }

    public void getData(){
        try{
            String query = "select * from users";
            rs = st.executeQuery(query);
            System.out.println("Records from Database");
            while(rs.next())
            {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                System.out.println("firstname:"+firstname+" lastname:"+lastname);
            }

        }
        catch(Exception ex){
            System.out.println("Error "+ex);
        }
    }

    /**public static void main(String [ ] args){
        DBConnect connect = new DBConnect();
        connect.getData();
    }**/



}
