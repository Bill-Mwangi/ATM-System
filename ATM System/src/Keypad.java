//Represents the keypad of the ATM
import java.util.Scanner;
public class Keypad {
    private Scanner in = new Scanner(System.in);
    public Keypad(){

    }
    public int getInput() {
        return in.nextInt();
    }
}
