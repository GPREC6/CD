import java.util.Scanner;

public class LeftRecursionElimination {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Number of Productions: ");
        int num = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the grammar as A -> Aa / b:");
        for (int i = 0; i < num; i++) {
            String production = scanner.nextLine().trim();
            eliminateLeftRecursion(production);
        }
        scanner.close();
    }

    public static void eliminateLeftRecursion(String production) {
        String[] parts = production.split("->");
        if (parts.length != 2) {
            System.out.println("Invalid grammar format: " + production);
            return;
        }

        String lhs = parts[0].trim();
        String[] choices = parts[1].split("/");
        if (choices.length < 2) {
            System.out.println("Invalid grammar format: " + production);
            return;
        }

        System.out.println("GRAMMAR: " + production);
        boolean isLeftRecursive = false;
        String beta = "";
        String alpha = "";

        for (String choice : choices) {
            String trimmedChoice = choice.trim();
            if (trimmedChoice.startsWith(lhs)) {
                isLeftRecursive = true;
                beta = trimmedChoice.substring(lhs.length()).trim();
            } else {
                alpha = trimmedChoice.trim();
            }
        }

        if (isLeftRecursive) {
            System.out.println(lhs + " is left recursive.");
            System.out.println(lhs + " -> " + alpha + lhs + "'");
            System.out.println(lhs + "' -> " + beta + lhs + "' | epsilon");
        } else {
            System.out.println(lhs + " is not left recursive.");
        }
    }
}