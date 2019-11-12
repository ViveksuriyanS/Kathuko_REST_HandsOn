package com.kathuko.demorest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {
	List<Alien> aliens;

	Connection con = null;

	public AlienRepository() {
		String url = "jdbc:mysql://localhost:3306/db";
		String username = "root";
		String pwd = "VMware1!";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Alien> getAliens() {
		List<Alien> listAlien = new ArrayList<Alien>();
		String qry = "select * from aliens";

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(qry);
			while (rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				listAlien.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listAlien;
	}

	public Alien getAlien(int id) {
		Alien a = null;

		try {
			Statement st = con.createStatement();
			String sql = "select * from aliens where id=" + id;
			System.out.println(id);
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return a;

	}

	public void create(Alien a1) {
		String sql = "insert into aliens values (?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, a1.getId());
			ps.setString(2, a1.getName());
			ps.setInt(3, a1.getPoints());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(Alien a1) {
		String sql = "update aliens set name=?,points=? where id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, a1.getName());
			ps.setInt(2, a1.getPoints());
			ps.setInt(3, a1.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		String sql = "delete from aliens where id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * public AlienRepository() { aliens = new ArrayList<Alien>();
	 * 
	 * Alien a1 = new Alien(); a1.setId(1); a1.setName("Vivek X"); a1.setPoints(5);
	 * 
	 * Alien a2 = new Alien(); a2.setId(2); a2.setName("Suriyan Y");
	 * a2.setPoints(4);
	 * 
	 * aliens.add(a1); aliens.add(a2); }
	 * 
	 * public List<Alien> getAliens() { return aliens; }
	 * 
	 * public Alien getAlien(int id1) {
	 * 
	 * for (Alien alienx : aliens) { System.out.println(alienx); if
	 * (alienx.getId()==id1) { return alienx; } } return new Alien(); }
	 * 
	 * public void create(Alien a1) { // TODO Auto-generated method stub
	 * aliens.add(a1); }
	 */


}
