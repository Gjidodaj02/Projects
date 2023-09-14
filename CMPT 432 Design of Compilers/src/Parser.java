import java.security.cert.CertStore;
import java.util.ArrayList;

public class Parser {

    //REGEX variables
    private static final String EOP = "[$]";
    private static final String TYPE = "int|string|boolean";
    private static final String OPEN_BLOCK = "[{]";
    private static final String CLOSE_BLOCK = "[}]";
    private static final String OPEN_PARENTHESIS = "[(]";
    private static final String CLOSE_PARENTHESIS = "[)]";
    private static final String ASSIGNMENT_OP = "[=]";
    private static final String BOOL_OP = "(==)|(!=)";
    private static final String ADDITION_OP = "[+]";
    private static final String QUOTE = "[\"]";
    private static final String PRINT = "print";
    private static final String WHILE = "while";
    private static final String IF = "if";
    private static final String ID = "[a-z]";
    private static final String DIGIT = "[\\d]";
    private static final String BOOL_VAL = "true|false";
    private static final String SPACE = " ";
    private int i, lines;
    private ArrayList<String> tokenStream;
    private ArrayList<String> CSTstream;
    private ArrayList<Integer> CSTdepth;
    private boolean errorFound;

    public void parser() {

        tokenStream = new ArrayList<>();
        CSTstream = new ArrayList<>();
        CSTdepth = new ArrayList<>();
        lines = 0;

    }//Parser

    public static String getTokenGrammar(String token) {

        String regex = "";

        if (token.matches(EOP) || token.equals(EOP)) {
            return regex = "[EOP]";
        }//if
        else if (token.matches(TYPE) || token.equals(TYPE)) {
            return regex = "[TYPE]";
        }//else if
        else if (token.matches(OPEN_BLOCK) || token.equals(OPEN_BLOCK)) {
            return regex = "[OPEN_BLOCK]";
        }//else if
        else if (token.matches(CLOSE_BLOCK) || token.equals(CLOSE_BLOCK)) {
            return regex = "[CLOSE_BLOCK]";
        }//else if
        else if (token.matches(OPEN_PARENTHESIS) || token.equals(OPEN_PARENTHESIS)) {
            return regex = "[OPEN_PARENTHESIS]";
        }//else if
        else if (token.matches(CLOSE_PARENTHESIS) || token.equals(CLOSE_PARENTHESIS)) {
            return regex = "[CLOSE_PARENTHESIS]";
        }//else if
        else if (token.matches(ASSIGNMENT_OP) || token.equals(ASSIGNMENT_OP)) {
            return regex = "[ASSIGNMENT_OP]";
        }//else if
        else if (token.matches(BOOL_OP) || token.equals(BOOL_OP)) {
            return regex = "[BOOL_OP]";
        }//else if
        else if (token.matches(ADDITION_OP) || token.equals(ADDITION_OP)) {
            return regex = "[ADDITION_OP]";
        }//else if
        else if (token.matches(QUOTE) || token.equals(QUOTE)) {
            return regex = "[QUOTE]";
        }//else if
        else if (token.matches(PRINT) || token.equals(PRINT)) {
            return regex = "[PRINT]";
        }//else if
        else if (token.matches(WHILE) || token.equals(WHILE)) {
            return regex = "[WHILE]";
        }//else if
        else if (token.matches(IF) || token.equals(IF)) {
            return regex = "[IF]";
        }//else if
        else if (token.matches(ID) || token.equals(ID)) {
            return regex = "[ID]";
        }//else if
        else if (token.matches(DIGIT) || token.equals(DIGIT)) {
            return regex = "[DIGIT]";
        }//else if
        else if (token.matches(BOOL_VAL) || token.equals(BOOL_VAL)) {
            return regex = "[BOOL_VAL]";
        }//else if
        else if (token.matches(SPACE) || token.equals(SPACE)) {
            return regex = "[SPACE]";
        }//else if

        return regex;

    }//getTokenRegex

    public boolean parse(ArrayList<String> currTokenStream, int prevProgEnd, int progPosition) {

        parser();
        System.out.println("PARSER: Parsing Program " + progPosition + "...");
        System.out.println("PARSER: parse()");
        tokenStream = currTokenStream;
        i = prevProgEnd;
        errorFound = false;
        parseProgram();

        if (!errorFound) {

            System.out.println("PARSER: Parse completed successfully\n");

            System.out.println("CST for program " + progPosition + "...");
            
            for (int i = 0; i < CSTstream.size(); i++) {

                for (int n = 0; n < CSTdepth.get(i); n++) {

                    System.out.print("-");

                }//for

                System.out.println(CSTstream.get(i));

            }//for

            return true;

        }//if

        else {

            System.out.println("PARSER: Parse failed with an error\n");
            System.out.println("CST for program " + progPosition + ": Skipped due to Parser error(s).\n");

            return false;

        }//else

    }//match

    public void match(String expectedToken) {

        if (tokenStream.get(i).matches(expectedToken)) {

            CSTstream.add("[" + tokenStream.get(i) + "]");
            CSTdepth.add(lines);
            i++;

        }//if

        else if (!errorFound) {

            System.out.println("PARSER: ERROR: Expected " + getTokenGrammar(expectedToken) + " got " + getTokenGrammar(tokenStream.get(i)));
            errorFound = !errorFound;

        }//else

    }//match

    public void parseProgram() {

        if (!errorFound) {

            System.out.println("PARSER: parseProgram()");

            CSTstream.add("<Program>");
            CSTdepth.add(lines++);

            parseBlock();
            match(EOP);

        }//if

    }//parseProgram

    public void parseBlock() {

        if (!errorFound) {

            System.out.println("PARSER: parseBlock()");

            CSTstream.add("<Block>");
            CSTdepth.add(lines++);

            match(OPEN_BLOCK);
            parseStatementList();
            match(CLOSE_BLOCK);
            lines--;
        
        }//if

    }//parseBlock

    public void parseStatementList() {

        if (!errorFound){

            System.out.println("PARSER: parseStatementList()");

            CSTstream.add("<Statement List>");
            CSTdepth.add(lines);

            //DIGIT, BOOL_VAL, QUOTE, ADDITION_OP, BOOL_OP, ASSIGNMENT_OP, CLOSE_PARENTHESIS, OPEN_PARENTHESIS

            if (!errorFound && tokenStream.get(i).matches(PRINT) || tokenStream.get(i).matches(ID) || tokenStream.get(i).matches(TYPE)
            || tokenStream.get(i).matches(WHILE) || tokenStream.get(i).matches(IF) || tokenStream.get(i).matches(OPEN_BLOCK)) {

                lines++;
                parseStatement();
                parseStatementList();
                lines--;

            }//else

            else if (tokenStream.get(i).matches(DIGIT) || tokenStream.get(i).matches(BOOL_VAL) || tokenStream.get(i).matches(QUOTE) 
            || tokenStream.get(i).matches(ADDITION_OP) || tokenStream.get(i).matches(BOOL_OP) || tokenStream.get(i).matches(ASSIGNMENT_OP) 
            || tokenStream.get(i).matches(OPEN_PARENTHESIS) || tokenStream.get(i).matches(CLOSE_PARENTHESIS)) {

                System.out.println("PARSER ERROR: Expected [Statement] got " + getTokenGrammar(tokenStream.get(i)));
                errorFound = !errorFound;

            }//else if

            else {

                //do nothing

            }//else
        
        }//if

    }//parseStatementList

    public void parseStatement() {

        if (!errorFound) {

            System.out.println("PARSER: parseStatement()");

            CSTstream.add("<Statement>");
            CSTdepth.add(lines++);

            if (tokenStream.get(i).matches(PRINT)) {

                parsePrintStatement();
                lines--;

            }//if

            else if (tokenStream.get(i).matches(ID)) {

                parseAssignmentStatement();
                lines--;

            }//else if

            else if (tokenStream.get(i).matches(TYPE)) {

                parseVarDecl();
                lines--;

            }//else if

            else if (tokenStream.get(i).matches(WHILE)) {

                parseWhileStatement();
                lines--;

            }//else if

            else if (tokenStream.get(i).matches(IF)) {

                parseIfStatement();
                lines--;

            }//else if

            else if (tokenStream.get(i).matches(OPEN_BLOCK)) {

                parseBlock();
                lines--;

            }//else if
    
        }//if

    }//parseStatement

    public void parsePrintStatement() {

        if (!errorFound) {

            System.out.println("PARSER: parsePrintStatement()");

            CSTstream.add("<Print Statement>");
            CSTdepth.add(lines++);

            match(PRINT);
            match(OPEN_PARENTHESIS);
            parseExpr();
            match(CLOSE_PARENTHESIS);
            lines--;

        }//if
        
    }//parsePrintStatement

    public void parseAssignmentStatement() {

        if (!errorFound) {

            System.out.println("PARSER: parseAssignmentStatement()");

            CSTstream.add("<Assignment Statement>");
            CSTdepth.add(lines++);

            parseId();
            match(ASSIGNMENT_OP);
            parseExpr();
            lines--;

        }//if
        
    }//parseAssignmentStatement

    public void parseVarDecl() {

        if (!errorFound) {
            
            System.out.println("PARSER: parseVarDecl()");

            CSTstream.add("<Variable Declaration>");
            CSTdepth.add(lines++);

            parseType();
            parseId();
            lines--;

        }//if

    }//parseVarDecl

    public void parseWhileStatement() {

        if (!errorFound) {

            System.out.println("PARSER: parseWhileStatement()");

            CSTstream.add("<While Statement>");
            CSTdepth.add(lines++);

            match(WHILE);
            parseBooleanExpr();
            parseBlock();
            lines--;

        }//if

    }//parseWhileStatement

    public void parseIfStatement() {

        if (!errorFound) {

            System.out.println("PARSER: parseIfStatement()");

            CSTstream.add("<If Statement>");
            CSTdepth.add(lines++);

            match(IF);
            parseBooleanExpr();
            parseBlock();
            lines--;

        }//if

    }//parseIfStatement

    public void parseExpr() {
        
        if (!errorFound) {

            System.out.println("PARSER: parseExpr()");

            CSTstream.add("<Expression>");
            CSTdepth.add(lines++);

            if (tokenStream.get(i).matches(DIGIT)) {

                parseIntExpr();
                lines--;

            }//if

            else if (tokenStream.get(i).matches(QUOTE)) {

                parseStringExpr();
                lines--;

            }//else if

            else if (tokenStream.get(i).matches(OPEN_PARENTHESIS) || tokenStream.get(i).matches(BOOL_VAL)) {

                parseBooleanExpr();
                lines--;

            }//else if

            else if (tokenStream.get(i).matches(ID)) {

                parseId();
                lines--;

            }//else if

            else {

                System.out.println("PARSER ERROR: Expected [EXPR] got " + getTokenGrammar(tokenStream.get(i)));
                errorFound = !errorFound;

            }//else

        }//if
        
    }//parseExpr

    public void parseIntExpr() {

        if (!errorFound) {

            System.out.println("PARSER: parseIntExpr()");

            CSTstream.add("<Integer Expression>");
            CSTdepth.add(lines++);

            parseDigit();
            lines--;


            if (tokenStream.get(i).matches(ADDITION_OP)) {

                lines++;
                parseIntop();
                parseExpr();
                lines--;

            }//if

        }//if

    }//parseIntExpr

    public void parseStringExpr() {

        if (!errorFound) {

            System.out.println("PARSER: parseStringExpr()");

            CSTstream.add("<String Expression>");
            CSTdepth.add(lines++);

            match(QUOTE);
            parseCharList();
            match(QUOTE);
            lines--;

        }//if

    }//parseStringExpr

    public void parseBooleanExpr() {
        if (!errorFound) {

            System.out.println("PARSER: parseBooleanExpr()");

            CSTstream.add("<Boolean Expression>");
            CSTdepth.add(lines++);

            if (tokenStream.get(i).matches(OPEN_PARENTHESIS)) {

                match(OPEN_PARENTHESIS);
                parseExpr();
                parseBoolop();
                parseExpr();
                match(CLOSE_PARENTHESIS);
                lines--;

            }//if
        
            else if (tokenStream.get(i).matches(BOOL_VAL)) {

                parseBoolval();
                lines--;

            }//else if

            else {

                System.out.println("PARSER ERROR: Expected [BOOLEANEXPR] got " + getTokenGrammar(tokenStream.get(i)));
                errorFound = !errorFound;

            }//else

        }//if

    }//parseBooleanExpr

    public void parseId() {

        if (!errorFound) {

            System.out.println("PARSER: parseId()");

            CSTstream.add("<ID>");
            CSTdepth.add(lines++);

            parseChar();
            lines--;

        }//if

    }//parseId

    public void parseCharList() {

        if (!errorFound) {

            System.out.println("PARSER: parseCharList()");

            CSTstream.add("<Char List>");
            CSTdepth.add(lines);

            if (tokenStream.get(i).matches(ID)) {

                lines++;
                parseChar();
                parseCharList();
                lines--;

            }//if

            else if(tokenStream.get(i).matches(SPACE)) {

                lines++;
                parseSpace();
                parseCharList();
                lines--;

            }//else if

            else {

                //do nothing

            }//else

        }//if

    }//parseCharList

    public void parseType() {

        if (!errorFound) {

            System.out.println("PARSER: parseType()");

            CSTstream.add("<Type>");
            CSTdepth.add(lines++);

            match(TYPE);
            lines--;

        }//if

    }//parseType

    public void parseChar() {

        if (!errorFound) {

            System.out.println("PARSER: parseChar()");

            CSTstream.add("<Character>");
            CSTdepth.add(lines++);

            match(ID);
            lines--;

        }//if

    }//parseChar

    public void parseSpace() {

        if (!errorFound) {

            System.out.println("PARSER: parseSpace()");

            CSTstream.add("<Space>");
            CSTdepth.add(lines++);

            match(SPACE);
            lines--;

        }//if

    }//parseSpace

    public void parseDigit() {

        if (!errorFound) {

            System.out.println("PARSER: parseDigit()");

            CSTstream.add("<Digit>");
            CSTdepth.add(lines++);

            match(DIGIT);
            lines--;

        }//if

    }//parseDigit

    public void parseBoolop() {

        if (!errorFound) {

            System.out.println("PARSER: parseBoolop()");

            CSTstream.add("<Boolean Operation>");
            CSTdepth.add(lines++);

            match(BOOL_OP);
            lines--;

        }//if

    }//parseBoolop

    public void parseBoolval() {

        if (!errorFound) {

            System.out.println("PARSER: parseBoolval()");

            CSTstream.add("<Boolean Value>");
            CSTdepth.add(lines++);

            match(BOOL_VAL);
            lines--;

        }//if

    }//parseBoolval

    public void parseIntop() {

        if (!errorFound) {

            System.out.println("PARSER: parseIntop()");

            CSTstream.add("<Integer Operation>");
            CSTdepth.add(lines++);

            match(ADDITION_OP);
            lines--;

        }//if

    }//parseIntop

}//Parser
