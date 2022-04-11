package com.iuc.cs2i4.test.gestion.appli;

import com.iuc.cs2i4.test.gestion.bd.BdManager;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/bd_gestion?serverTimezone=Africa/Douala";
        String userName = "root";
        String userPassword = "";

        System.out.println("==== APPLICATION GESTION ====");
        BdManager bdManager = new BdManager(url, userName, userPassword);
        bdManager.loadDriver();
        bdManager.connectToBd();
    }
}
