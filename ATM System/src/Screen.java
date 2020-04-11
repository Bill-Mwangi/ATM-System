//Represent the ATM screen
public class Screen {
    //Display a message with a carriage return
    public void displayMessageLine(String s) {
        System.out.println(s);
    }

    //Display a message without a carriage return
    public void displayMessage(String s) {
        System.out.print(s);
    }

    //Display a shilling amount
    public void displayShillingAmount(double amount){
        System.out.printf("Kshs %,.2f", amount);
    }
}
