import java.sql.SQLException;
import java.util.Scanner;

public class ATMBank {

    Scanner sc = new Scanner(System.in);
    ConnectToDatabase ctD;

    public ATMBank() throws SQLException {
        ctD = new ConnectToDatabase();
    }

    public void Login_Customer() throws SQLException {
        System.out.println("Enter the Username: ");
        String name = sc.nextLine();
        System.out.println("Enter the password: ");
        String password = sc.nextLine();
        boolean flag = ctD.login(name, password);
        if (flag) {
            System.out.println("LOGIN SUCCESSFULL!!");
            display_Menu();
        } else {
            System.out.println("LOGIN FAILED! Try Again");
            Login_Customer();
        }
    }

    // MENU OF THE ATM BANK
    public void display_Menu() throws SQLException {
        System.out.println("********************MENU********************");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Deposit Money");
        System.out.println("4. Logout");
        System.out.println("********************************************");
        System.out.println("Select one of the above options");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                check_Balance();
                break;

            case 2:
                withDraw_Money();
                break;

            case 3:
                deposit_Money();
                break;

            case 4:
                logout();
                break;

            default:
                System.out.println("Please select a valid option");
                display_Menu();
                break;

        }
    }

    // FUNCTION FOR CHECKING BALANCE
    public void check_Balance() throws SQLException {
        int amount = ctD.getBalance(ctD.CustomerID);
        System.out.println("Total Balance in your account: " + amount);
        display_Menu();
    }

    // FUNCTION FOR WITHDRAWING MONEY
    public void withDraw_Money() throws SQLException {
        System.out.println("Enter the amount you want to withdraw: ");
        int amount = sc.nextInt();
        boolean flag = ctD.withDrawMoney(ctD.CustomerID, amount);
        if (flag) {
            System.out.println("Withdrawal has been successfull!!");
            System.out.println("Balance Left: " + ctD.Balance_money);
            display_Menu();
        } else {
            System.out.println("You do not have enough amount to withdraw. Try Again.");
            display_Menu();
        }
    }

    // FUNCTION TO DEPOSIT MONEY
    public void deposit_Money() throws SQLException {
        System.out.println("Enter the amount you want to deposit: ");
        int amount = sc.nextInt();
        boolean flag = ctD.depositMoney(ctD.CustomerID, amount);
        if (flag) {
            System.out.println("Your money has been deposited successfully!!");
            System.out.println("Total Balance: " + ctD.Balance_money);
            display_Menu();
        } else {
            System.out.println("SOME ERROR OCCURED!! :(");
            System.out.println("TRY AGAIN");
            display_Menu();
        }
    }

    public void logout() {
        System.out.println("GOOD BYE!!");
        System.exit(0);
    }

    public static void main(String[] args) throws SQLException {
        ATMBank at1 = new ATMBank();
        at1.Login_Customer();
    }
}