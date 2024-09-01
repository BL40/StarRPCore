package dev.bronzylobster.localrp.Utils;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    private final String url;

    public DataBase() throws Exception {
        url = "jdbc:sqlite:plugins/LocalRP/database.db";
        Class.forName("org.sqlite.JDBC").getConstructor().newInstance();

        Connection c = getConnection();
        Statement s = c.createStatement();

        s.executeUpdate("CREATE TABLE IF NOT EXISTS effects ('player' TEXT, 'effect' TEXT, 'lvl' INTEGER)");

        s.close();
        c.close();
    }

    private Connection getConnection() throws Exception {
        return DriverManager.getConnection(url);
    }

    public void add(String nick, String eff, int lvl) {
        try {
            Connection c = getConnection();
            Statement s = c.createStatement();

            s.executeUpdate("INSERT INTO effects (player, effect, lvl) VALUES ('" + nick + "', '" + eff + "', " + lvl + ")");

            s.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getEffects(String nick) {
        try {
            ArrayList<String> result = new ArrayList<>();

            Connection c = getConnection();
            Statement s = c.createStatement();

            ResultSet rs = s.executeQuery("SELECT effect FROM effects WHERE player = '" + nick + "'");

            while(!rs.isAfterLast()) {
                result.add(rs.getString(1));
                rs.next();
            }

            s.close();
            c.close();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getEffectLVL(String nick, String eff) {
        try {
            int result;

            Connection c = getConnection();
            Statement s = c.createStatement();

            ResultSet rs = s.executeQuery("SELECT lvl FROM effects WHERE player = '" + nick + "' AND effect = '" + eff + "'");
            result = rs.getInt(1);

            s.close();
            c.close();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public void remove(String nick) {
        try {
            Connection c = getConnection();
            Statement s = c.createStatement();

            s.executeUpdate("DELETE FROM effects WHERE player = '" + nick + "'");

            s.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(String nick, String eff) {
        try {
            Connection c = getConnection();
            Statement s = c.createStatement();

            s.executeUpdate("DELETE FROM effects WHERE player = '" + nick + "' AND effect = '" + eff +"'");

            s.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

