package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business.Stuffy;

public class StuffyDB implements DAO<Stuffy> {

	private Connection getConnection() throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/mma";
		String username = "mma_user";
		String password = "sesame";
		Connection connection = DriverManager.getConnection(dbUrl, username, password);
		return connection;
	}

	@Override
	public Stuffy get(String code) {
		String sql = "SELECT Code, Description, Price FROM Product WHERE Code = ?";
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String description = rs.getString("Description");
				double price = rs.getDouble("Price");
				Stuffy p = new Stuffy(code, description, price);
				return p;
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
	}

	@Override
	public List<Stuffy> getAll() {
		String sql = "SELECT Code, Description, Price FROM Product";
		List<Stuffy> products = new ArrayList<>();
		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				String code = rs.getString("Code");
				String description = rs.getString("Description");
				double price = rs.getDouble("Price");

				Stuffy p = new Stuffy(code, description, price);
				products.add(p);
			}
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
		return products;
	}

	@Override
	public boolean add(Stuffy t) {
		String sql = "INSERT INTO Product (Code, Description, Price) VALUES (?, ?, ?)";
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, t.getCode());
			ps.setString(2, t.getDescription());
			ps.setDouble(3, t.getPrice());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Stuffy t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Stuffy t) {
		String sql = "DELETE FROM Product WHERE Code = ?";
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, t.getCode());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
		return true;
	}

}
