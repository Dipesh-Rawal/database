package database1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost/task1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "imeandmyself");
		Scanner input = new Scanner(System.in);

		while (true) {
			try {
				Statement stmt1 = con.createStatement();
				ResultSet result = stmt1.executeQuery("select * from person");
				while (result.next()) {
					System.out.println("ID:" + result.getInt(1) + " Name:" + result.getString(2) + " Surname:"
							+ result.getString(3) + " Age:" + result.getInt(4));
				}
			} catch (Exception e) {
				System.out.println(e);
			}

			System.out.println("a. Add person");
			System.out.println("b. Remove person");
			System.out.print("Enter: ");
			String option = input.nextLine();

			switch (option) {
			case "a":
				Scanner input2 = new Scanner(System.in);

				System.out.print("Name: ");
				String name = input2.nextLine();
				System.out.print("Surname: ");
				String surname = input2.nextLine();
				System.out.print("Age: ");
				int age = input2.nextInt();

				try {
					PreparedStatement stmt = con
							.prepareStatement("insert into person (name, surname, age) values(?,?,?)");
					stmt.setString(1, name);
					stmt.setString(2, surname);
					stmt.setInt(3, age);
					stmt.executeUpdate();
				} catch (Exception e) {
					System.out.println(e);
				}
				break;
			case "b":
				Scanner input3 = new Scanner(System.in);
				System.out.print("Remove ID: ");
				int id = input3.nextInt();
				try {
					PreparedStatement stmt = con.prepareStatement("delete from person where id=" + id);
					stmt.executeUpdate();
				} catch (Exception e) {
					System.out.println(e);
				}
				break;
			default:
				System.out.println("Invalid option!");

			}
		}
	}
}
