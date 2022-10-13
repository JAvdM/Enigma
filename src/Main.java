public class Main {
    public static void main(String[] args) {
        // Based on Michael C. Koss' Paper Enigma Machine (mike@mckoss.com)

        Rotors rotors = new Rotors();

        rotors.daySetting();

        rotors.setRotorMechanism();

        System.out.println();

        rotors.messageInput();

        rotors.message();

        System.out.println(rotors.outputToString());

    }
}