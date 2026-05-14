
package dao;

import java.sql.*;
import java.util.*;
import model.Player;

public class PlayerDao {

    // DB Connection (MySQL)
    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Trying to connect...");
           con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/ipl?useSSL=false&allowPublicKeyRetrieval=true",
    "root",
    "12092005"
);
            
            if(con != null){
    System.out.println("DB Connected ✅");
} else {
    System.out.println("DB Failed ❌");
}
        
        }catch(Exception e){ 
            System.out.println(e);
        }
        return con;
    }

    // SAVE PLAYER
    public static int save(Player p){
        int status = 0;
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                "insert into players(name,role,country,team) values (?,?,?,?)");

            ps.setString(1, p.getName());
            ps.setString(2, p.getRole());
            ps.setString(3, p.getCountry());
            ps.setString(4, p.getTeam());

            status = ps.executeUpdate();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    
    
    public static List<Player> getRecords(int start, int total){
    List<Player> list = new ArrayList<Player>();

    try{
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(
            "select * from players limit "+(start-1)+","+total);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Player p = new Player();
            p.setId(rs.getInt(1));
            p.setName(rs.getString(2));
            p.setRole(rs.getString(3));
            p.setCountry(rs.getString(4));
            p.setTeam(rs.getString(5));

            list.add(p);
        }

        con.close();
    }catch(Exception e){
        e.printStackTrace();
    }

    return list;
}

    // UPDATE PLAYER
    public static int update(Player p){
        int status = 0;
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                "update players set name=?, role=?, country=?, team=? where id=?");

            ps.setString(1, p.getName());
            ps.setString(2, p.getRole());
            ps.setString(3, p.getCountry());
            ps.setString(4, p.getTeam());
            ps.setInt(5, p.getId());

            status = ps.executeUpdate();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }

    // DELETE PLAYER
    public static int delete(int id){
        int status = 0;
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                "delete from players where id=?");

            ps.setInt(1, id);
            status = ps.executeUpdate();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    public static void resetAutoIncrement(){
    try{
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(
            "ALTER TABLE players AUTO_INCREMENT = 1");
        ps.executeUpdate();
        con.close();
    }catch(Exception e){
        e.printStackTrace();
    }
}

    // GET PLAYER BY ID
    public static Player getPlayerById(int id){
        Player p = new Player();
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                "select * from players where id=?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setRole(rs.getString(3));
                p.setCountry(rs.getString(4));
                p.setTeam(rs.getString(5));
            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return p;
    }

    // GET ALL PLAYERS
    public static List<Player> getAllPlayers(){
        List<Player> list = new ArrayList<Player>();

        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                "select * from players");

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Player p = new Player();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setRole(rs.getString(3));
                p.setCountry(rs.getString(4));
                p.setTeam(rs.getString(5));

                list.add(p);
            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}