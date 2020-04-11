//Represents a balance enquiry transaction
public class BalanceInquiry extends Transaction {
    public BalanceInquiry(int userAccountNumber, Screen screen, BankDatabase bankDatabase) {
        super(userAccountNumber, screen, bankDatabase);
    }

    @Override
    public void execute() {
        //Get references to bank database and screen
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
        double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());

        //Display balance information on the screen
        screen.displayMessageLine("\nBalance information:");
        screen.displayMessage("-Available balance: ");
        screen.displayShillingAmount(availableBalance);
        screen.displayMessage("\n-Total balance: ");
        screen.displayShillingAmount(totalBalance);
        screen.displayMessageLine("");
    }
}
