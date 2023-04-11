import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdateExample {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            // Establish JDBC connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/binary_db", "root", "Education1.");
            statement = connection.createStatement();

            // Enable batch mode
            statement.addBatch("INSERT INTO students (name, age) VALUES ('John', 25)");
            statement.addBatch("INSERT INTO students (name, age) VALUES ('Jane', 30)");
            statement.addBatch("INSERT INTO students (name, age) VALUES ('Tom', 35)");

            // Execute the batch
            int[] updateCounts = statement.executeBatch();

            // Process the update counts
            for (int updateCount : updateCounts) {
                System.out.println("Rows affected: " + updateCount);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the Statement and database connection
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}