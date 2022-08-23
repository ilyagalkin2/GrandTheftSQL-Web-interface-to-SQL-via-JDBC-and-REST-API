package devadmin.ilyagalkin.testtask.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;

/**
 * This class is a Controller for Web Interface.
 * Web interface is available at: http://localhost:8080/homepage
 */
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
	 * @param queryString an SQL query that is provided with a Thymeleaf
	 * template engine, can be null
	 * @return queries the database and shows it as a line of text where rows are
	 * separated with a semicolon ';'
	 */
	@RequestMapping("/homepage")
	public String query(Model model, @RequestParam(required = false, defaultValue = "select * from city;") String queryString) {

		/**
		 * Calls method and gives the <em>String</em> with an SQL query to it
		 * @see ApiForDatabase#update(String)
		 */
		String databaseAfterQuery = update(queryString);

		System.out.println("The database written to String is having following length: " + databaseAfterQuery.length());

		if (databaseAfterQuery.length() == 0) {
			databaseAfterQuery = "The table is empty, add some rows.";
		}

		model.addAttribute("databaseAfterQuery", databaseAfterQuery);

		return "homepage";
	}



	/**
	 * Connects to the database and creates a table if it doesn't exist;
	 * this method is called from another method
	 * @see ApiForDatabase#update(String)
	 *
	 * @return Connection <em>connection</em>
	 */
	private Connection connect() {

		Connection conn = null;
		/**
		 * Connects to the database and creates the table if it doesn't exist
		 */
		try {
			//conn = DriverManager.getConnection("jdbc:postgresql://localhost:8088/city", "someuser", "somepass");
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
	 * Updates the table getting an SQL command from user input on web page.
	 *
	 * @param queryString a <em>String</em> with SQL command
	 * @return columns row by row as <em>String</em> seprated with semicolon ';'
	 */
	public String update(String queryString) {

		System.out.println(queryString);

		ResultSet rs = null;
		/**
		 * Calls the method to connect
		 * @see ApiForDatabase#connect()
		 * and uses it to make a query using the <em>String</em> with SQL command
		 * that was inputted by the user on web page
		 */
		try (Connection conn = this.connect()) {
			 Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(queryString);
			System.out.println("Database updated successfully");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		/**
		 * After some SQL commands like 'insert' ResultSet can be obtained
		 * from 'select * from table_name' command (what is not affordable for
		 * large databases);
		 * if the ResultSet is still containing nothing, then the database is empty,
		 * and it prints into the console a message to "add some rows"
		 *
		 */
		if (rs == null) {
			try (Connection conn = this.connect()) {
				Statement stmt = conn.createStatement();
				rs = stmt.executeQuery("select * from city;");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else if (rs == null) {
			System.out.println("\n \n \n" + "The table is empty, add some rows." + "\n \n \n");
		}

		/**
		 * Adds the columns' values to <em>StringBuilder</em>,
		 * with the formatting we want,
		 * and then creates the <em>String</em>
		 */
		StringBuilder stringBuilder = new StringBuilder();
		try {
			while (rs.next()) {
				stringBuilder.append(
						rs.getString("name") + ", " +
						rs.getString("countryCode") + ", " +
						rs.getString("district") + ", " +
						rs.getString("population") + "; ");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		String databaseAfterQuery = stringBuilder.toString();

		System.out.println("The databaseAfterQuery: ");
		System.out.println(databaseAfterQuery);

		return databaseAfterQuery;
	}

}