package com.practices.practice.feature.java.feature21;

public class UnnamedVarriableAndPatetrn {

    record Grade(String code, String designation) {}

    record Employee(String nom, String prenom, Grade grade) {}

    public static void main(String[] args) {
        Object obj = new Employee("nom1", "prenom1", new Grade("DEV", "developpeur"));
        // pattern matching
        if(obj instanceof Employee(var nom, var _, _)) {
            System.out.println("Employee is " + nom);
        }

    }
}
