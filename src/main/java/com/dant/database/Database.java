package com.dant.database;

import java.util.*;
import java.util.ArrayList;
import com.dant.entity.User;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import com.mysql.jdbc.ResultSetMetaData;
import java.util.ArrayList;

public class Database {

    private static String url = "jdbc:mysql://localhost:3306/mydb";
    private static String utilisateur = "root";
    private static String motDePasse = "root";

    static Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, utilisateur, motDePasse);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
