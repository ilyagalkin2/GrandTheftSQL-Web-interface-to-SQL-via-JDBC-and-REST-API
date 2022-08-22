package devadmin.ilyagalkin.testtask.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;

@Controller
public class ApiForDatabase {

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	/**
	 * Web interface that uses Thymeleaf template engine, available at
	 * http://localhost:8080/homepage
	 *
	 * @param model a stock Thymeleaf's method
	 * @param queryString an SQL query that is provided with a Thymeleaf template
	 * template engine, can be null
	 * @return queries the database and shows it as a line of text where rows are
	 * separated with a semicolon ';'
	 */
	@RequestMapping("/homepage")
	public String query(Model model, @RequestParam(required = false) String queryString) {
		System.out.println("\n \n \n" + "Controller at \"/homepage\" is working!");
		System.out.println("At \"/homepage\", queryString is: " + queryString + "\n \n \n");

		String databaseAfterQuery = update(queryString);
//
//		model.addAttribute("databaseAfterQuery", databaseAfterQuery);


		return "homepage";
	}

	/**
	 * Connects to the database and creates a table if it doesn't exist
	 * @return connection via <em>DriverManager</em>
	 */
	private Connection connect() {

		Connection conn = null;
		try {
			//conn = DriverManager.getConnection("jdbc:postgresql://localhost:8088/somedb", "someuser", "somepass");
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connection to postges:alpine image has been established.");
			conn.createStatement().executeQuery("create table IF NOT EXISTS city (\n" +
					"    id INT generated always as identity,\n" +
					"    name VARCHAR(30) not null,\n" +
					"    \"countryCode\" VARCHAR(30) not null,\n" +
					"    district VARCHAR(30) not null,\n" +
					"    population VARCHAR(30) not null,\n" +
					"    primary key (id)\n" +
					");");
			System.out.println("Table has been created.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	/**
	 * Updates the table given an SQL command.
	 * @param queryString a <em>String</em> with SQL command
	 * @return columns row by row as <em>String</em> seprated with semicolon ';'
	 */
	public String update(String queryString) {
		//String sql2 = "update \"Contacts\" set email=? where id=?";

		try (Connection conn = this.connect();
			PreparedStatement stmt = conn.prepareStatement(queryString)) {
			stmt.executeUpdate();
			System.out.println("Database updated successfully");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		String selectAllFromCity = "select * from city";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder = null;
		String databaseFormatted = "";
		try (Connection conn = this.connect()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(selectAllFromCity);
			// loop through the result set
			while (rs.next()) {
				stringBuilder.append(
						//rs.getInt("id") + "\t" +
						rs.getString("name") + "\t" +
						rs.getString("countryCode") + "\t" +
						rs.getString("district") + "\t" +
						rs.getString("population") + "; ");
			}
			databaseFormatted = stringBuilder.toString();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return databaseFormatted;
	}

}
