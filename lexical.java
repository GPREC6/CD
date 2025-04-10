package dccn;
import java.util.*;
class lexical
 {
    private static final Set<String> KEYWORDS = new HashSet<>(Arrays.asList(
        "if", "else", "while", "do", "break", "continue", "int", "double",
        "float", "return", "char", "case", "long", "short", "void", "static",
        "struct", "goto"));
    
    
    
    
    
    private static boolean isOperator(char ch) {
        return "+-*/><=".indexOf(ch) != -1;
    }
    
    
    
    private static boolean isInteger(String str) {
        return str.matches("-?\\d+");
     }
    
    
    
    
    
    private static boolean isValidIdentifier(String str) {
        return str.matches("[a-zA-Z_][a-zA-Z0-9_]*");
    }
    
    
    
    
    
    
    
    public static void parse(String input) {
        String[] tokens = input.split(" ");//\\s+|(?=[+-/*;><=(){}])|(?<=[+-/*;><=(){}])");
        
        for (String token : tokens) {
        	
            if (token.isEmpty()) continue;
            
            if (KEYWORDS.contains(token)) {
            	
                System.out.println("'" + token + "' IS A KEYWORD");
                
                
            } else if (isInteger(token)) {
                System.out.println("'" + token + "' IS AN INTEGER");
                
                
            } else if (isValidIdentifier(token)) {
                System.out.println("'" + token + "' IS A VALID IDENTIFIER");
                
            } else if (token.length() == 1 && isOperator(token.charAt(0))) {
                System.out.println("'" + token + "' IS AN OPERATOR");
            } else {
                System.out.println("'" + token + "' IS NOT A VALID IDENTIFIER");
            }
        }
    }
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter code to analyze:");
        String input = scanner.nextLine();
        parse(input);
        scanner.close();
    }
}
