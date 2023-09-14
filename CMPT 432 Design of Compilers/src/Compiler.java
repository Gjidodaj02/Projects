import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compiler {

    private static Parser parser = new Parser();
    private static SemanticAnalysis semantic = new SemanticAnalysis();

    //REGEX variables
    private static final String EOP = "[$]";
    private static final String TYPE = "int|string|boolean";
    private static final String OPEN_BLOCK = "[{]";
    private static final String CLOSE_BLOCK = "[}]";
    private static final String OPEN_PARENTHESIS = "[(]";
    private static final String CLOSE_PARENTHESIS = "[)]";
    private static final String ASSIGNMENT_OP = "[=]";
    private static final String EQUALITY_OP = "(==)";
    private static final String INEQUALITY_OP = "(!=)";
    private static final String ADDITION_OP = "[+]";
    private static final String QUOTE = "[\"]";
    private static final String PRINT = "print";
    private static final String WHILE = "while";
    private static final String IF = "if";
    private static final String ID = "[a-z]";
    private static final String DIGIT = "[\\d]";
    private static final String TRUE = "true";
    private static final String FALSE = "false";
    private static final String SPACE = " ";
    private static final String BEGIN_COMM = "/\\*";
    private static final String END_COMM = "\\*/";

    public static void main(String[] args) {

        //Return error if there is no data in the input file
        if (args.length != 1) {

            System.err.println("ERROR Command Line - Please input the file name into the command line.");
            System.exit(1);
            
        }//if

        //Variables
        String filename = args[0];
        Boolean inQuotes = false, inComment = false;
        int nonTokenPosTrack, lineTracker = 1, prevProgramPosition = 0, programPosition = 1, lastEnd = 0, tokenTracker = 0, nonTokenTracker = 0, errors = 0, progStart = 0;
        String currNonToken;
        ArrayList<String> tokens = new ArrayList<>();
        ArrayList<String> tokenTypes = new ArrayList<>();
        ArrayList<Integer> tokenRowPositions = new ArrayList<>();
        ArrayList<Integer> tokenColPositions = new ArrayList<>();
        ArrayList<String> nonTokens = new ArrayList<>();
        ArrayList<Integer> nonTokenRowPositions = new ArrayList<>();
        ArrayList<Integer> nonTokenColPositions = new ArrayList<>();

        //Regex groups
        String[] regexes = { EOP, TYPE, OPEN_BLOCK, CLOSE_BLOCK, OPEN_PARENTHESIS, CLOSE_PARENTHESIS, EQUALITY_OP, ASSIGNMENT_OP,
            INEQUALITY_OP, PRINT, WHILE, IF, TRUE, FALSE, ID, QUOTE, ADDITION_OP, DIGIT, SPACE, BEGIN_COMM, END_COMM};
        
        //Read file
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;
            
            //Pattern
            Pattern pattern = Pattern.compile(String.format("(%s)", String.join(")|(", regexes)));
    
            //Loop while there is a next line
            while ((line = reader.readLine()) != null) {

                //Use Matcher to find tokens and non-tokens in the line
                Matcher matcher = pattern.matcher(line);

                if (inQuotes) {

                    System.out.println(lastEnd);
                    nonTokens.add("\"");
                    nonTokenRowPositions.add(lastEnd + 1);
                    nonTokenColPositions.add(lineTracker - 1);
                    inQuotes = !inQuotes;

                }//if

                lastEnd = 0;

                //Loop while there is another token
                while (matcher.find()) {

                    //Set matchedToken as the next found token
                    String matchedToken = matcher.group();

                    //if it is an open comment, it is in a comment
                    if (matchedToken.matches(BEGIN_COMM)){

                        inComment = true;

                    }//if

                    //if its an end comment, it is no longer a comment
                    if (matchedToken.matches(END_COMM)){
                        
                        inComment = false;
                        lastEnd = matcher.end();

                    }//if 

                    //Store any non-token text between the last match and this current one
                    if (matcher.start() > lastEnd && !inComment && !matchedToken.matches(BEGIN_COMM) && !matchedToken.matches(END_COMM)) {

                        currNonToken = line.substring(lastEnd, matcher.start()).trim();
                        nonTokenPosTrack = lastEnd + 1;

                        //Loop through the substring
                        for (int i = 0; i < currNonToken.length(); i++) {

                            //If its not a space, add it to errors
                            if (currNonToken.charAt(i) != ' ') {

                                nonTokens.add(String.valueOf(currNonToken.charAt(i)));
                                nonTokenRowPositions.add(nonTokenPosTrack);
                                nonTokenColPositions.add(lineTracker);
                                nonTokenPosTrack++;
                                lastEnd++;

                            }//if

                        }//for

                    }//if      

                    //If it isn't a quote or comment and is in quotes
                    // the reason for making sure it isn't a quote is because it will never find the next quote
                    if (!matchedToken.matches(QUOTE) && inQuotes && !inComment) {

                        //If its an ID (letter) add it to the token list
                        if (matchedToken.matches(ID)) {

                            tokens.add(matchedToken);
                            tokenTypes.add("CHAR");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();  
                            
                        }//if

                        //If its an ID (letter) add it to the token list
                        else if (matchedToken.matches(SPACE)) {

                            tokens.add(matchedToken);
                            tokenTypes.add("CHAR");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();  
                            
                        }//if

                        //If its a type
                        else if (matchedToken.matches(TYPE)) {

                            //Set row as the first char position in the current type
                            int row = matcher.start() + 1;

                            //Loop through the token string, and add each character as its own CHAR token
                            for (int i = 0; i < matchedToken.length(); i++) {

                                tokens.add(String.valueOf(matchedToken.charAt(i)));
                                tokenTypes.add("CHAR");
                                tokenRowPositions.add(row);
                                tokenColPositions.add(lineTracker);
                                lastEnd = matcher.end(); 
                                row++;

                            }//for

                        }//else if

                    }//if 

                    //If its not in quotes, or a comment, go through the tokens as normal
                    else if (!matchedToken.matches(SPACE) && !inComment && !matchedToken.matches(BEGIN_COMM) && !matchedToken.matches(END_COMM)){

                        //Add the token
                        tokens.add(matchedToken);

                        //Check if the token appears in the regex pattern
//TODO- input enum to clean this junk up

                        //I unfortunetly had to add each arrayList add into the different if's
                        // or else it would screw up the placement of all the tokens/nonTokens
                        if (matchedToken.matches(EOP)) {
                            tokenTypes.add("EOP");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();    
                        }//if
                        else if (matchedToken.matches(TYPE)) {
                            tokenTypes.add("TYPE");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();    
                        }//else-if
                        else if (matchedToken.matches(OPEN_BLOCK)) {
                            tokenTypes.add("OPEN_BLOCK");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();    
                        }//else-if
                        else if (matchedToken.matches(CLOSE_BLOCK)) {
                            tokenTypes.add("CLOSE_BLOCK");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();    
                        }//else-if
                        else if (matchedToken.matches(OPEN_PARENTHESIS)) {
                            tokenTypes.add("OPEN_PARENTHESIS");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();    
                        }//else-if
                        else if (matchedToken.matches(CLOSE_PARENTHESIS)) {
                            tokenTypes.add("CLOSE_PARENTHESIS");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();    
                        }//else-if
                        else if (matchedToken.matches(EQUALITY_OP)) {
                            tokenTypes.add("EQUALITY_OP");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();    
                        }//else-if
                        else if (matchedToken.matches(ASSIGNMENT_OP)) {
                            tokenTypes.add("ASSIGNMENT_OP");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();    
                        }//else-if
                        else if (matchedToken.matches(INEQUALITY_OP)) {
                            tokenTypes.add("INEQUALITY_OP");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();    
                        }//else-if
                        else if (matchedToken.matches(PRINT)) {
                            tokenTypes.add("PRINT");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();    
                        }//else-if
                        else if (matchedToken.matches(WHILE)) {
                            tokenTypes.add("WHILE");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();    
                        }//else-if
                        else if (matchedToken.matches(IF)) {
                            tokenTypes.add("IF");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();    
                        }//else-if
                        else if (matchedToken.matches(TRUE)) {
                            tokenTypes.add("TRUE");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();    
                        }//else-if
                        else if (matchedToken.matches(FALSE)) {
                            tokenTypes.add("FALSE");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();    
                        }//else-if
                        else if (matchedToken.matches(ID)) {
                            tokenTypes.add("ID");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();    
                        }//else-if
                        else if (matchedToken.matches(QUOTE)) {
                            tokenTypes.add("QUOTE");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();    
                            inQuotes = !inQuotes;                 
                        }//else-if
                        else if (matchedToken.matches(ADDITION_OP)) {
                            tokenTypes.add("ADDITION_OP");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();    
                        }//else-if
                        else if (matchedToken.matches(DIGIT)) {
                            tokenTypes.add("DIGIT");
                            tokenRowPositions.add(matcher.end());
                            tokenColPositions.add(lineTracker);
                            lastEnd = matcher.end();    
                        }//else-if
                    
                    }//else                                  

                }//while

                //Store any remaining non-tokens after the last match
                if (lastEnd < line.length() && !inComment) {

                    //Create a substring from the last match, to the end of the line
                    // triming off white-space
                    currNonToken = line.substring(lastEnd).trim();
                    nonTokenPosTrack = lastEnd + 1;

                    for (int i = 0; i < currNonToken.length(); i++) {

                        nonTokens.add(String.valueOf(currNonToken.charAt(i)));
                        nonTokenRowPositions.add(nonTokenPosTrack);
                        nonTokenColPositions.add(lineTracker);
                        nonTokenPosTrack++;
                        lastEnd++;

                    }//for

                }//if
                    
                //keep track of line
                lineTracker++;

            }//while

        }//try
        
        //Catch is there is an error when reading the input file
        catch (IOException e) {

            System.err.println("ERROR Input File - Error reading file: " + e.getMessage());
            System.exit(1);

        }//catch

        //If the program doesn't end with a $
        if (tokenTracker == tokens.size() && tokens.get(tokens.size() - 1).charAt(0) != '$') {

            System.out.println("WARNING Lexer - Missing end of program symbol \"$\".");
            System.out.println("INFO Lexer - Lex completed with " + errors + " error(s) and 1 warning.");

        }//if

        //Loop until there aren't any tokens left
        // we don't have to make sure i < any other array list
        // since the code adds to all the respected lists when they are found
        while (tokenTracker < tokens.size() || nonTokenTracker < nonTokens.size()) {

            //Print a new program 
            if (programPosition != prevProgramPosition) {

                System.out.println("\nINFO Lexer - Lexing program " + programPosition + "...");
                prevProgramPosition++;

            }//if

                //Deal with printing errors in correct position
                if (nonTokenTracker < nonTokens.size() && tokenColPositions.get(tokenTracker) == nonTokenColPositions.get(nonTokenTracker)) {

                    //If the token appears before the error
                    // print the token
                    if (tokenRowPositions.get(tokenTracker) < nonTokenRowPositions.get(nonTokenTracker)){

                        System.out.println("DEBUG Lexer - " + tokenTypes.get(tokenTracker) + " [ " + tokens.get(tokenTracker) + " ] found at (" + tokenColPositions.get(tokenTracker) + ":" + tokenRowPositions.get(tokenTracker) + ")");
                        tokenTracker++;

                    }//if

                    //Else, print the error
                    else{

                        System.err.println("ERROR Lexer - Error:" +  nonTokenColPositions.get(nonTokenTracker) + ":" + nonTokenRowPositions.get(nonTokenTracker) + " Unrecognized Token: " + nonTokens.get(nonTokenTracker));
                        errors++;
                        nonTokenTracker++;                   

                    }//else

                    //If the error was at the end of the line, and isn't at the end of the program
                    // print the error
                    // the thrid && is in place to ensure that it prints only when the previous column was the same
                    // as the column the error appears on
                    if (tokenTracker < tokens.size() && nonTokenTracker < nonTokens.size() && tokenColPositions.get(tokenTracker) != nonTokenColPositions.get(nonTokenTracker) && tokenColPositions.get(tokenTracker-1) == nonTokenColPositions.get(nonTokenTracker)) {

                        while (nonTokenTracker < nonTokens.size() && nonTokenColPositions.get(nonTokenTracker) == tokenColPositions.get(tokenTracker-1)) {

                            if (nonTokens.get(nonTokenTracker).equals("\"")) { 

                                System.err.println("ERROR Lexer - Error:" +  nonTokenColPositions.get(nonTokenTracker) + ":" + nonTokenRowPositions.get(nonTokenTracker) + " Unterminated Quote.");
                                errors++;
                                nonTokenTracker++;

                            }//if

                            else {

                                System.err.println("ERROR Lexer - Error:" +  nonTokenColPositions.get(nonTokenTracker) + ":" + nonTokenRowPositions.get(nonTokenTracker) + " Unrecognized Token: " + nonTokens.get(nonTokenTracker));
                                errors++;
                                nonTokenTracker++;

                            }//else

                        }//while

                   }//if

                }//if

                //If there aren't errors on the same column
                else {

                    System.out.println("DEBUG Lexer - " + tokenTypes.get(tokenTracker) + " [ " + tokens.get(tokenTracker) + " ] found at (" + tokenColPositions.get(tokenTracker) + ":" + tokenRowPositions.get(tokenTracker) + ")");
                    tokenTracker++;

                }//else

                //If the error appears at the very end of the program
                if (tokenTracker >= tokens.size() && nonTokenTracker < nonTokens.size()) {

                    if (nonTokens.get(nonTokenTracker).equals("\"")) { 

                        System.err.println("ERROR Lexer - Error:" +  nonTokenColPositions.get(nonTokenTracker) + ":" + nonTokenRowPositions.get(nonTokenTracker) + " Unterminated Quote.");
                        errors++;
                        nonTokenTracker++;

                    }//if

                    else {

                        System.err.println("ERROR Lexer - Error:" +  nonTokenColPositions.get(nonTokenTracker) + ":" + nonTokenRowPositions.get(nonTokenTracker) + " Unrecognized Token: " + nonTokens.get(nonTokenTracker));
                        errors++;
                        nonTokenTracker++;

                    }//else

                }//if

            
            //If the program ends, update the program counter, and print how many errors it finished with
            if (tokenTracker > 0 && tokens.get(tokenTracker - 1).charAt(0) == '$') {

                System.out.println("INFO Lexer - Lex completed with " + errors + " error(s)\n");
                if (errors == 0) {

                    if (parser.parse(tokens, progStart, programPosition)){

                        semantic.semanticAnalysis(tokens, progStart, programPosition);
                        
                    }

                    else {

                        System.out.println("SEMANTIC ANALYSIS: Skipped due to Parser error(s)\n");  

                    }
                    progStart = tokenTracker;

                }//if

                else {
                    System.out.println("PARSER: Skipped due to LEXER error(s)\n");
                    System.out.println("CST for program " + programPosition + ": Skipped due to Lexer error(s).\n");
                }//else

                programPosition++;
                errors = 0;

            }//if

        }//while

    }//main

}//Compiler