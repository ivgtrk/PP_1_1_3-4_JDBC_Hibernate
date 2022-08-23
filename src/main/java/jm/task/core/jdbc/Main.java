package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util u = new Util();
        u.OpenConnection("jdbc:mysql://localhost:3306/users", "root", "root");
        u.CloseConnection();
    }
}
