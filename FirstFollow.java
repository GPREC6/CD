package dccn;
import java.util.*;

public class FirstFollow {

    static char[] nonTerminals, terminals;
    static String[][] grammar;
    static String[] first, follow;
    static int ntLen;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter non-terminals (no spaces, e.g., SABC):");
        nonTerminals = sc.next().toCharArray();
        ntLen = nonTerminals.length;

        System.out.println("Enter terminals (no spaces, e.g., abc):");
        terminals = sc.next().toCharArray();

        grammar = new String[ntLen][];
        for (int i = 0; i < ntLen; i++) {
            System.out.println("Enter number of productions for " + nonTerminals[i] + ":");
            int n = sc.nextInt();
            sc.nextLine(); // consume newline
            grammar[i] = new String[n];
            System.out.println("Enter productions (use 9 for epsilon):");
            for (int j = 0; j < n; j++) {
                grammar[i][j] = sc.nextLine();
            }
        }

        first = new String[ntLen];
        for (int i = 0; i < ntLen; i++) {
            first[i] = findFirst(i);
        }

        System.out.println("\nFIRST Sets:");
        for (int i = 0; i < ntLen; i++) {
            System.out.println("FIRST(" + nonTerminals[i] + ") = { " + removeDuplicates(first[i]) + " }");
        }

        follow = new String[ntLen];
        for (int i = 0; i < ntLen; i++) {
            follow[i] = findFollow(i);
        }

        System.out.println("\nFOLLOW Sets:");
        for (int i = 0; i < ntLen; i++) {
            System.out.println("FOLLOW(" + nonTerminals[i] + ") = { " + removeDuplicates(follow[i]) + " }");
        }
    }

    static String findFirst(int index) {
        String result = "";
        for (String prod : grammar[index]) {
            for (int k = 0; k < prod.length(); k++) {
                char symbol = prod.charAt(k);
                boolean isNonTerminal = false;

                for (int l = 0; l < ntLen; l++) {
                    if (symbol == nonTerminals[l]) {
                        String temp = findFirst(l);
                        if (!temp.equals("9")) result += temp;
                        if (temp.contains("9")) continue;
                        isNonTerminal = true;
                        break;
                    }
                }

                if (!isNonTerminal) {
                    result += symbol;
                }
                break; // only take first meaningful symbol
            }
        }
        return result;
    }

    static String findFollow(int index) {
        String result = (index == 0) ? "$" : "";

        for (int j = 0; j < ntLen; j++) {
            for (String prod : grammar[j]) {
                for (int l = 0; l < prod.length(); l++) {
                    if (prod.charAt(l) == nonTerminals[index]) {
                        if (l + 1 < prod.length()) {
                            char next = prod.charAt(l + 1);
                            boolean foundNextNT = false;

                            for (int m = 0; m < ntLen; m++) {
                                if (next == nonTerminals[m]) {
                                    String firstSet = first[m];
                                    for (char ch : firstSet.toCharArray()) {
                                        if (ch != '9') result += ch;
                                        else result += findFollow(m);
                                    }
                                    foundNextNT = true;
                                    break;
                                }
                            }

                            if (!foundNextNT) {
                                result += next;
                            }
                        } else if (j != index) {
                            result += findFollow(j);
                        }
                    }
                }
            }
        }
        return result;
    }

    static String removeDuplicates(String str) {
        Set<Character> set = new LinkedHashSet<>();
        for (char c : str.toCharArray()) {
            set.add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : set) sb.append(c);
        return sb.toString();
    }
}
