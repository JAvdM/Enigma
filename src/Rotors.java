import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Rotors {

    public final char[] ALPHABET  = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public final char[] REFLECTOR = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'D', 'I', 'J', 'K', 'G', 'M', 'K', 'M', 'I', 'E', 'B', 'F', 'T', 'C', 'V', 'V', 'J', 'A', 'T'};

    public final char[] rotorIc    = {'E', 'K', 'M', 'F', 'L', 'G', 'D', 'Q', 'V', 'Z', 'N', 'T', 'O', 'W', 'Y', 'H', 'X', 'U', 'S', 'P', 'A', 'I', 'B', 'R', 'C', 'J'};
    public final char[] rotorIIc   = {'A', 'J', 'D', 'K', 'S', 'I', 'R', 'U', 'X', 'B', 'L', 'H', 'W', 'T', 'M', 'C', 'Q', 'G', 'Z', 'N', 'P', 'Y', 'F', 'V', 'O', 'E'};
    public final char[] rotorIIIc  = {'B', 'D', 'F', 'H', 'J', 'L', 'C', 'P', 'R', 'T', 'X', 'V', 'Z', 'N', 'Y', 'E', 'I', 'W', 'G', 'A', 'K', 'M', 'U', 'S', 'Q', 'O'};

    private ArrayList<Character> message = new ArrayList<>();

    public ArrayList<Character> rotorI = new ArrayList<>();
    public ArrayList<Character> rotorII = new ArrayList<>();
    public ArrayList<Character> rotorIII = new ArrayList<>();

    public ArrayList<Character> alphabetI = new ArrayList<Character>();
    public ArrayList<Character> alphabetII = new ArrayList<Character>();
    public ArrayList<Character> alphabetIII = new ArrayList<Character>();
    public ArrayList<Character> alphabetIV = new ArrayList<Character>();

    public char[] messageArray;
    private char[] dayArray;
    private final Scanner scanner = new Scanner(System.in);

    public int counter = 0;

    private String output = "";

    private int indexSetter = 0;

    public void daySetting() {

        String userInput = "";

        System.out.println("Insert the three starting characters for the rotors:");

        while (!userInput.matches("[a-zA-Z]+") || userInput.length() != 3) {
            userInput = scanner.nextLine().toUpperCase();
        }
        setDayArray(userInput);
    }

    public void setDayArray(String userInput) {
        dayArray = userInput.toCharArray();
    }

    public void messageInput() {

        String userInput = "";

        System.out.println("Type the message you want to encrypt: (characters only and no spaces!)");

        while (!userInput.matches("[a-zA-Z]+")) {
            userInput = scanner.nextLine().toUpperCase();
        }
        setMessageArray(userInput);
    }

    public void setMessageArray(String userInput) {
        messageArray = userInput.toCharArray();
        for (char c : messageArray) {
            message.add(c);
        }
    }

    public void setRotorMechanism() {

        int c = 1;
        for (char a : dayArray) {               // loop the dayArray characters
            for (int i = 0; i < ALPHABET.length; i++) {
                if (a == ALPHABET[i]) {
                    for (int j = i; j < ALPHABET.length; j++) {
                        rotorSelection(c, j);
                    }
                    for (int j = 0; j < i; j++) {
                        rotorSelection(c, j);
                    }
                }
            }
            c++;
        }
    }

    private void rotorSelection(int id, int j) {
        if (id == 1) {
            rotorI.add(rotorIc[j]);
            alphabetI.add(ALPHABET[j]);
        } else if (id == 2) {
            rotorII.add(rotorIIc[j]);
            alphabetII.add(ALPHABET[j]);
        } else if (id == 3) {
            rotorIII.add(rotorIIIc[j]);
            alphabetIII.add(ALPHABET[j]);
        }
    }

    public void rotorClicks() {
        rotorIII.add(26, rotorIII.get(0));
        rotorIII.remove(0);
        alphabetIII.add(26, alphabetIII.get(0));
        alphabetIII.remove(0);

        if (alphabetIII.get(0) == 'W') {
            rotorII.add(26, rotorII.get(0));
            rotorII.remove(0);
            alphabetII.add(26, alphabetII.get(0));
            alphabetII.remove(0);
        }

        if (alphabetII.get(0) == 'F') {
            if (counter == 0) {
                rotorI.add(26, rotorI.get(0));
                rotorI.remove(0);
                alphabetI.add(26, alphabetI.get(0));
                alphabetI.remove(0);
            }
            counter++;
            if (counter == ALPHABET.length) {
                counter = 0;
            }
        }
    }

    public void printCombined() {
        System.out.println();
        System.out.println("ROTORS AND ALPHABETS:");
        System.out.println("1: [" + alphabetI.get(0) + "] " + alphabetI.toString());
        System.out.println("   [" + rotorI.get(0) + "] " + rotorI.toString());
        System.out.println();
        System.out.println("2: [" + alphabetII.get(0) + "] " + alphabetII.toString());
        System.out.println("   [" + rotorII.get(0) + "] " + rotorII.toString());
        System.out.println();
        System.out.println("3: [" + alphabetIII.get(0) + "] " + alphabetIII.toString());
        System.out.println("   [" + rotorIII.get(0) + "] " + rotorIII.toString());
        System.out.println();
    }

    public String messageToString() {
        return message.toString();
    }

    public void message() {
        for (char c : message) {
            rotorClicks();
            char stepI      = '\0';
            char stepII     = '\0';
            char stepIII    = '\0';
            char stepIV     = '\0';
            char stepV      = '\0';
            char stepVI     = '\0';
            char stepVII    = '\0';
            // to rotorIII
            for (int i = 0; i < ALPHABET.length; i++) {
                if (c == ALPHABET[i]) {
                    stepI = rotorIII.get(i);
                }
            }

            for (int i = 0; i < ALPHABET.length; i++) {
                if (stepI == alphabetIII.get(i)) {
                    stepII = rotorII.get(i);
                }
            }


            for (int i = 0; i < ALPHABET.length; i++) {
                if (stepII == alphabetII.get(i)) {
                    stepIII = rotorI.get(i);
                }
            }

            for (int i = 0; i < ALPHABET.length; i++) {
                if (stepIII == alphabetI.get(i)) {
                    stepIV = REFLECTOR[i];
                    setIndex(i);
                }
            }

            for (int i = 0; i < ALPHABET.length; i++) {
                if (stepIV == REFLECTOR[i]) {
                    if (i != getIndexSetter()) {
                        stepV = alphabetI.get(i);
                    }
                }
            }

            for (int i = 0; i < ALPHABET.length; i++) {
                if (stepV == rotorI.get(i)) {
                    stepVI = alphabetII.get(i);
                }
            }
            for (int i = 0; i < ALPHABET.length; i++) {
                if (stepVI == rotorII.get(i)) {
                    stepVII = alphabetIII.get(i);
                }
            }
            for (int i = 0; i < ALPHABET.length; i++) {
                if (stepVII == rotorIII.get(i)) {
                    output += ALPHABET[i];
                }
            }
        }
    }

    public String outputToString() {
        System.out.println();
        return output;
    }

    public void setIndex(int i) {
        indexSetter = i;
    }

    public int getIndexSetter() {
        return indexSetter;
    }
}