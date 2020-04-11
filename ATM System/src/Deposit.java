//Represents a deposit transaction
public class Deposit extends Transaction {
    private double amount;
    private Keypad keypad;
    private DepositSlot depositSlot;
    private static final int CANCELLED = 0;

    public Deposit(int accountNumber, Screen screen, BankDatabase bankDatabase, Keypad keypad, DepositSlot depositSlot) {
        super(accountNumber, screen, bankDatabase);
        this.keypad = keypad;
        this.depositSlot = depositSlot;
    }

    @Override
    public void execute() {
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        amount = promptForDepositAmount();

        if (amount != CANCELLED){
            screen.displayMessage("\nPlease insert a deposit envelop containing: ");
            screen.displayShillingAmount(amount);
            screen.displayMessageLine(".");

            boolean envelopeReceived = depositSlot.isEnvelopReceived();
            if (envelopeReceived){
                screen.displayMessageLine("\nYour envelop has been received.\nNOTE: The money just " +
                        "deposited will not be available until we verify the amount of any enclosed cash" +
                        "and your cheques clear.");
                bankDatabase.credit(getAccountNumber(), amount);
            } else {
                screen.displayMessageLine("\nYou did not insert an envelop.\nThe ATM has cancelled your" +
                        " transaction.");
            }
        } else {
            screen.displayMessageLine("\nCancelling transaction...");
        }
    }

    private double promptForDepositAmount() {
        Screen screen = getScreen();
        screen.displayMessageLine("Please enter a deposit amount in Shillings (or 0 to cancel):");
        int input = keypad.getInput();
        return input;
    }
}

