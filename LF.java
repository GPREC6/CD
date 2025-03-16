import java.util.*;

public class LeftFactoring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter production (e.g., A -> abC | abD | aE):");
        String production = sc.nextLine();
        eliminateLeftFactoring(production);
        sc.close();
    }

    private static void eliminateLeftFactoring(String production) {
        String[] parts = production.split("->");
        String lhs = parts[0].trim();
        String[] rhs = parts[1].split("\\|");

        String prefix = findCommonPrefix(rhs);
        if (prefix.isEmpty()) {
            System.out.println("No left factoring found: " + production);
            return;
        }

        System.out.println(lhs + " -> " + prefix + lhs + "'");
        System.out.print(lhs + "' -> ");
        for (int i = 0; i < rhs.length; i++) {
            String suffix = rhs[i].substring(prefix.length()).trim();
            System.out.print((suffix.isEmpty() ? "ε" : suffix) + (i < rhs.length - 1 ? " | " : ""));
        }
    }

    private static String findCommonPrefix(String[] rhs) {
        String prefix = rhs[0];
        for (String r : rhs) {
            while (!r.startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}