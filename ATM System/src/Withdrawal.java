//Represents a withdrawal ATM transaction
public class Withdrawal extends Transaction{
    private int accountNumber;
    private int amount;
    private final static int CANCELED = 6; //constant corresponding to menu option to cancel
    private Screen screen;
    private Keypad keypad;
    private CashDispenser cashDispenser;
    private BankDatabase bankDatabase;

    public Withdrawal(int accountNumber, Screen screen, BankDatabase bankDatabase, Keypad keypad, CashDispenser cashDispenser) {
        super(accountNumber, screen, bankDatabase);
        this.accountNumber = accountNumber;
        this.screen = screen;
        this.keypad = keypad;
        this.cashDispenser = cashDispenser;
        this.bankDatabase = bankDatabase;
    }

    @Override
    public void execute(){
        boolean cashDispensed = false;
        double availableBalance;
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        do {
            amount = displayMenuOfAmounts();
            if (amount != CANCELED) {
                availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
                if (amount <= availableBalance){
                    if (cashDispenser.isSufficientCashAvailable(amount)){
                    bankDatabase.debit(getAccountNumber(), amount);
                    cashDispenser.dispenseCash(amount);
                    cashDispensed = true;
                    screen.displayMessageLine("\n Your cash has been dispensed. Please take your cash now.");
                    } else{
                        screen.displayMessageLine("\nInsufficient amount available in the ATM.\n\nPlease choose a smaller amount.");
                    }
                } else {
                screen.displayMessageLine("\nInsufficient funds in you account.\n\nPlease choose a smaller amount.");
                }
            } else {
                screen.displayMessageLine("\nCancelling transaction...");
                return;
            }
        } while (!cashDispensed);

    }

    private int displayMenuOfAmounts() {
    int userChoice = 0;
    Screen screen = getScreen();
    int[] amounts = {0, 500, 1000, 2000, 5000, 10000};

    while (userChoice == 0){
        screen.displayMessageLine("\nWithdrawal menu:");
        screen.displayMessageLine("1 - Ksh 500");
        screen.displayMessageLine("2 - Ksh 1,000");
        screen.displayMessageLine("3 - Ksh 2,000");
        screen.displayMessageLine("4 - Ksh 5,000");
        screen.displayMessageLine("5 - Ksh 10,000");
        screen.displayMessageLine("6 - Cancel transaction");
        screen.displayMessage("\nChoose a withdrawal amount:");

        int input = keypad.getInput();

        switch (input){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                userChoice = amounts[input];
                break;
            case CANCELED:
                userChoice = CANCELED;
            default:
                screen.displayMessageLine("\nInvalid choice. Try again.");
        }
    }
    return userChoice;
    }
}