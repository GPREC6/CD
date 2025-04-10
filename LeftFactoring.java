package dccn;
import java.util.*;

public class LeftFactoring {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of productions:");
        int n = sc.nextInt(); 
        sc.nextLine(); // Consume newline

        String[] productions = new String[n];
        System.out.println("Enter the productions (format: A->aB|aC):");
        for (int i = 0; i < n; i++) {
            productions[i] = sc.nextLine();
        }

        eliminateLeftFactoring(productions);
    }

    // Method to eliminate left factoring
    private static void eliminateLeftFactoring(String[] productions) {
        boolean leftFactored = false;

        for (String production : productions) {
            String[] parts = production.split("->");

            if (parts.length != 2) {
                System.out.println("Invalid production: " + production);
                continue;
            }

            String lhs = parts[0].trim();
            String[] rhs = parts[1].split("\\|");

            String commonPrefix = findCommonPrefix(rhs);

            if (!commonPrefix.isEmpty()) {
                leftFactored = true;
                System.out.println(lhs + " -> " + commonPrefix + lhs + "'");

                List<String> newRHS = new ArrayList<>();
                for (String option : rhs) {
                    String suffix;
                    if (option.startsWith(commonPrefix)) {
                        suffix = option.substring(commonPrefix.length());
                    } else {
                        suffix = option;
                    }

                    if (suffix.isEmpty()) {
                        suffix = "ε"; // ε means empty (epsilon)
                    }

                    newRHS.add(suffix);
                }

                System.out.println(lhs + "' -> " + String.join(" | ", newRHS));
            }
        }

        if (!leftFactored) {
            System.out.println("No left factoring needed for the given productions.");
        }
    }

    // Method to find common prefix among strings
    private static String findCommonPrefix(String[] options) {
        if (options.length == 0) return "";

        String prefix = options[0];
        for (int i = 1; i < options.length; i++) {
            while (!options[i].startsWith(prefix)) {
                if (prefix.length() == 0) return "";
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }

        return prefix;
    }
}
