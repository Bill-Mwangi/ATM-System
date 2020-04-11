//Represents the cash dispenser of the ATM
public class CashDispenser {
    private static final int INITIAL_COUNT = 2000; //default initial number of KShs 500 notes
    private int count; //Number of notes remaining

    public CashDispenser(){
        count = INITIAL_COUNT;
    }

    public void dispenseCash(int amount){
        int notesRequired = amount/500;
        count -= notesRequired;
    }

    public boolean isSufficientCashAvailable(int amount){
        int notesRequired = amount/500;
        if (count >= notesRequired)
            return true;
        else
            return false;
    }
}
