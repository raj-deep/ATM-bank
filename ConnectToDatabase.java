
// import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectToDatabase {

    Statement stmt = null;
    ResultSet rs = null;
    Connection con = null;
    int CustomerID = 0;
    int Balance_money = 0;

    // Constructor -> Constructor is a block with same name as of class and have no
    // return value.
    // Constructor is used to give the initial value to the globally declared
    // variables.
    public ConnectToDatabase() {

        // We will use try and catch block to connect to the database
        try {
            // Following code will connect us to the database

            Class.forName("com.mysql.jdbc.Driver"); // This will load the driver to connect us to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?characterEncoding=latin1", "root",
                    "Shalimar@123"); // This statement will set the connection to the database

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /*
     * FOLLOWING IS THE CODE FOR ALL THE FUNCTIONALITIES IN THE ATM
     * 1. LOGIN
     * 2. CHECK BALANCE
     * 3. WITHDRAW MONEY
     * 4. DEPOSIT MONEY
     */

    public boolean login(String name, String password) {

        // We uses try and catch block because everytime we try to connect to the
        // database, it may throw a error.
        // We can also deal with this type of error by writing 'throws SQLException'
        // while initialising the block.
        try {
            stmt = con.createStatement();
        } catch (SQLException se) {
        }

        try {
            rs = stmt.executeQuery(
                    "select * from customer where CustomerName='" + name + "' and CustomerPassword='" + password + "'");
        } catch (SQLException se1) {

        }

        try {
            if (rs.next()) {
                CustomerID = rs.getInt("CustomerID");
                return true;
            } else {
                return false;
            }
        } catch (SQLException se2) {

        }

        return false; // This return is written, cause the function must return some boolean
                      // expression even after an SQLException error occurs

    }

    // CODE FOR CHECKIGN BALANCE
    public int getBalance(int id) throws SQLException {
        rs = stmt.executeQuery("select BalanceAmmount from Account where CustomerID=" + id + "");
        if (rs.next()) {
            Balance_money = rs.getInt("BalanceAmmount");
            return Balance_money;
        } else {
            return 0;
        }
    }

    // CODE FOR WITHDRAW MONEY
    public boolean withDrawMoney(int id, int amount) throws SQLException {
        if (amount > Balance_money) {
            return false;
        } else {
            stmt.executeUpdate(
                    "update Account set BalanceAmmount=" + (Balance_money - amount) + " where customerid=" + id);
            Balance_money = getBalance(id);
            return true;
        }
    }

    // CODE FOR DEPOSIT MONEY
    public boolean depositMoney(int id, int amount) throws SQLException {
        int a = stmt.executeUpdate(
                "update Account set BalanceAmmount=" + (Balance_money + amount) + " where customerid=" + id);
        if (a == 1) {
            Balance_money = getBalance(id);
            return true;
        } else {
            return false;
        }

    }
}