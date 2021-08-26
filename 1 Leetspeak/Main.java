import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.util.Map.entry;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter String to L337!FY: ");
        String userInput = input.nextLine();
        System.out.println(leetify(userInput));
    }

    public static String leetify(String input) {
        HashMap<String, String> leetMap = (HashMap<String, String>) Map.ofEntries(
                entry("A", "@"),
                entry("B", "8"),
                entry("C", "("),
                entry("D", "D"),
                entry("E", "3"),
                entry("F", "F"),
                entry("G", "6"),
                entry("H", "#"),
                entry("I", "!"),
                entry("J", "J"),
                entry("K", "K"),
                entry("L", "1"),
                entry("M", "M"),
                entry("N", "N"),
                entry("O", "0"),
                entry("P", "P"),
                entry("Q", "Q"),
                entry("R", "R"),
                entry("S", "$"),
                entry("T", "7"),
                entry("U", "U"),
                entry("V", "V"),
                entry("W", "W"),
                entry("X", "X"),
                entry("Y", "Y"),
                entry("Z", "2")
        );

        input = input.toUpperCase();
        StringBuilder leetString = new StringBuilder();
        for (char currentChar : input.toCharArray()) {
            if (leetMap.containsKey(Character.toString(currentChar))) {
                leetString.append(leetMap.get(Character.toString(currentChar)));
            } else {
                leetString.append(currentChar);
            }
        }
        return leetString.toString();
    }

}
