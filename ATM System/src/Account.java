//Represents a bank account
public class Account {
    private int accountNumber;
    private int pin;
    private double availableBalance; //Funds available for withdrawal
    private double totalBalance; //Available balance + pending deposits

    public Account(int accountNumber, int pin, double availableBalance, double totalBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.availableBalance = availableBalance;
        this.totalBalance = totalBalance;
    }

    public boolean validatePin(int userPin){
        return userPin == pin;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void credit(double amount){
        totalBalance += amount;
    }

    public void debit(double amount){
        availableBalance -= amount;
        totalBalance -= amount;
    }
}
