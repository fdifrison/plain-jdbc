package com.fdifrison.plainjdbc;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        var db = new Database();

        try (var connection = db.getConnection()) {
            System.out.println("connection.isValid(0) = " + connection.isValid(0));

            // SELECT
            var ps = connection.prepareStatement("select * from users where name = ?");
            ps.setString(1, "Sheldon");
            var rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }

            // INSERT
            var insertPs = connection.prepareStatement("insert into USERS (name) values (?)");
            insertPs.setString(1, "Howard");
            int insertCount = insertPs.executeUpdate();
            System.out.println("insertCount = " + insertCount);

            // UPDATE
            var updatePs = connection.prepareStatement("update USERS set name = ? where name = ?");
            updatePs.setString(1, "Sheldon Cooper");
            updatePs.setString(2, "Sheldon");
            int updateCount = updatePs.executeUpdate();
            System.out.println("updateCount = " + updateCount);

            // DELETE
            var deletePs = connection.prepareStatement("delete from USERS where name = ?");
            deletePs.setString(1, "Leonard");
            int deleteCount = deletePs.executeUpdate();
            System.out.println("deleteCount = " + deleteCount);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}