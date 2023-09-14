import java.util.ArrayList;

public class SemanticAnalysis {

    //REGEX variables
    private static final String TYPE = "int|string|boolean";
    private static final String OPEN_BLOCK = "[{]";
    private static final String OPEN_PARENTHESIS = "[(]";
    private static final String ADDITION_OP = "[+]";
    private static final String QUOTE = "[\"]";
    private static final String PRINT = "print";
    private static final String WHILE = "while";
    private static final String IF = "if";
    private static final String ID = "[a-z]";
    private static final String DIGIT = "[\\d]";
    private static final String BOOL_VAL = "true|false";
    private static final String SPACE = " ";
    private int arrayLoc, lines, scope;
    private String currString, lastID;
    private boolean declaration;
    private ArrayList<String> tokenStream;
    private ArrayList<String> ASTstream;
    private ArrayList<Integer> ASTdepth;
    private ArrayList<String> symbolName;
    private ArrayList<String> symbolType;
    private ArrayList<Boolean> symbolIsInit;
    private ArrayList<Boolean> symbolUsed;
    private ArrayList<Integer> symbolScope;
    private ArrayList<String> symbolScopeLetter;
    private ArrayList<Integer> scopes;
    private boolean errorFound, inString;

    public void ast() {

        tokenStream = new ArrayList<>();
        ASTstream = new ArrayList<>();
        ASTdepth = new ArrayList<>();
        symbolName = new ArrayList<>();
        symbolType = new ArrayList<>();
        symbolIsInit = new ArrayList<>();
        symbolUsed = new ArrayList<>();
        symbolScope = new ArrayList<>();
        symbolScopeLetter = new ArrayList<>();
        scopes = new ArrayList<>();
        scope = -1;
        lines = 0;
        currString = "";
        declaration = false;
        lastID = "";

    }//Parser

    public boolean semanticAnalysis(ArrayList<String> currTokenStream, int prevProgEnd, int progPosition) {

        ast();
        System.out.println("\nSEMANTIC ANALYSIS: Analyizing Program " + progPosition + "...");
        tokenStream = currTokenStream;
        arrayLoc = prevProgEnd;
        errorFound = false;
        boolean completed = false;
        parseProgram();

        if (!errorFound) {

            System.out.println("SEMANTIC ANALYSIS: Analysis completed successfully\n");
            printAST(progPosition);
            printSymbolTable(progPosition);

        }//if

        else {

            System.out.println("SEMANTIC ANALYSIS: Semantic Analysis failed with an error\n");
            System.out.println("AST and Symbol Table for program " + progPosition + ": Skipped due to Semantic Analysis error(s).\n");

        }//else

        return completed;

    }//semanticAnalysis

    public String scopeLetter(int scope) {

        String letter = "";
        int num = 0;

        for (int i = 0; i < scopes.size(); i++) {

            if (scopes.get(i) == scope) {

                num++;

            }

        }

        num--;
        letter = (char)(97 + num) + "";
        return letter;

    }

    public void printAST(int progPosition) {

        System.out.println("AST for program " + progPosition + "...");
        
        for (int i = 0; i < ASTstream.size(); i++) {
            
            if (!ASTstream.get(i).equals("<BoolVal>") && !ASTstream.get(i).equals("<IsEqual>") 
            && !ASTstream.get(i).equals("<NotEqual>") && !ASTstream.get(i).equals("<EndBlock>")) {

                for (int n = 0; n < ASTdepth.get(i); n++) {

                    System.out.print("-");

                }//for

                System.out.println(ASTstream.get(i));

            }

        }//for

    }

    public void printSymbolTable(int progPosition) {

        System.out.println("\nSymbol Table for program " + progPosition + "...");

        System.out.println("NAME | TYPE | isINIT? | isUSED? | SCOPE | SCOPE_LETTER");

        for (int i = 0; i < symbolName.size(); i++) {

            System.out.println(symbolName.get(i) + " | " + symbolType.get(i) + " | " + symbolIsInit.get(i) + " | " + 
            symbolUsed.get(i) + " | " + symbolScope.get(i) + " | " + symbolScopeLetter.get(i));            

        }

    } 

    public int inTable(ArrayList<String> iSymbolName, ArrayList<Integer> iSymbolScope, ArrayList<String> iSymbolLetter, String name, int scope, String scopeLetter) {

        for (int i = scope; i > -1; i--) {
            
            for (int j = 0; j < symbolType.size(); j++) {
                
                if (iSymbolName.get(j).equals(name) && iSymbolScope.get(j) == i && iSymbolLetter.get(j).equals(scopeLetter) && i == scope) {
                    
                    return j;
                
                } 

                else if (iSymbolName.get(j).equals(name) && iSymbolScope.get(j) == i && i != scope) {
                    
                    return j;
                
                }

            }

        }

        return -1;

    }

    public void match(String expectedToken) {

        if (tokenStream.get(arrayLoc).matches(expectedToken)) {
            
            ASTstream.add("[" + tokenStream.get(arrayLoc) + "]");
            ASTdepth.add(lines);

            if(expectedToken.equals(ID.toString())) {
                
                //not assigning ID
                if (declaration != true) {
                    
                    if (inTable(symbolName, symbolScope, symbolScopeLetter, tokenStream.get(arrayLoc), scope, scopeLetter(scope)) != - 1) {
                        
                        //exists below
                        if (symbolScope.get(inTable(symbolName, symbolScope, symbolScopeLetter, tokenStream.get(arrayLoc), scope, scopeLetter(scope))) < scope)
                        {

                            symbolUsed.set(inTable(symbolName, symbolScope, symbolScopeLetter, tokenStream.get(arrayLoc), scope, scopeLetter(scope)), true);

                        } 

                        //exists above
                        else if (symbolScope.get(inTable(symbolName, symbolScope, symbolScopeLetter, tokenStream.get(arrayLoc), scope, scopeLetter(scope))) > scope) {
                            
                            errorFound = true;
                            System.out.println("ERROR: The variable " + tokenStream.get(arrayLoc) + " isn't declared");
                            
                        } 

                        //exists parrallel
                        else {

                            symbolUsed.set(inTable(symbolName, symbolScope, symbolScopeLetter, tokenStream.get(arrayLoc), scope, scopeLetter(scope)), true);
                            
                        }
                    } 

                    else {

                        errorFound = true;
                        System.out.println("ERROR: The variable " + tokenStream.get(arrayLoc) + " isn't declared");
                        
                    }

                    lastID = tokenStream.get(arrayLoc);

                } 

                //assigning ID
                else {

                    if(inTable(symbolName, symbolScope, symbolScopeLetter, tokenStream.get(arrayLoc), scope, scopeLetter(scope)) != - 1) {

                        //exists below
                        if (symbolScope.get(inTable(symbolName, symbolScope, symbolScopeLetter, tokenStream.get(arrayLoc), scope, scopeLetter(scope))) < scope) {
                            
                            symbolType.add(tokenStream.get(arrayLoc - 1));
                            symbolName.add(tokenStream.get(arrayLoc));
                            symbolScope.add(scope);
                            symbolIsInit.set(symbolName.size() - 1, true);
                            symbolScopeLetter.set(symbolName.size() - 1, scopeLetter(scope));

                        }

                        //exists above
                        else if (symbolScope.get(inTable(symbolName, symbolScope, symbolScopeLetter, tokenStream.get(arrayLoc), scope, scopeLetter(scope))) > scope) {

                            symbolType.add(tokenStream.get(arrayLoc - 1));
                            symbolName.add(tokenStream.get(arrayLoc));
                            symbolScope.add(scope);
                            symbolIsInit.set(symbolName.size() - 1, true);
                            symbolScopeLetter.set(symbolName.size() - 1, scopeLetter(scope));

                        } 

                        //If it exists parrallel to me
                        else if (symbolScope.get(inTable(symbolName, symbolScope, symbolScopeLetter, tokenStream.get(arrayLoc), scope, scopeLetter(scope))) == scope) {

                            errorFound = true;
                            System.out.println("ERROR: The variable " + tokenStream.get(arrayLoc) + " already declared in this scope");                           

                        }
                   
                    } 

                    //If it's not there
                    else {

                        symbolType.add(tokenStream.get(arrayLoc - 1));
                        symbolName.add(tokenStream.get(arrayLoc));
                        symbolScope.add(scope);
                        symbolIsInit.add(symbolName.size() - 1, true);
                        symbolUsed.add(symbolName.size() - 1, false);
                        symbolScopeLetter.add(symbolName.size() - 1, scopeLetter(scope));

                    }

                    declaration = false;

                }

                arrayLoc++;
            } 

            else {

                arrayLoc++;

            }

        }//if

    }//match

    public void parseProgram() {

        if (!errorFound) {

            parseBlock();

            if (arrayLoc + 1 != tokenStream.size()) {

                arrayLoc++;

            }

        }//if

    }//parseProgram

    public void parseBlock() {

        if (!errorFound) {

            ASTstream.add("<Block>");
            ASTdepth.add(lines++);
            scope++;
            scopes.add(scope);

            arrayLoc++;
            parseStatementList();
            arrayLoc++;

            ASTstream.add("<EndBlock>");
            ASTdepth.add(lines);
            lines--;
            scope--;
        
        }//if

    }//parseBlock

    public void parseStatementList() {

        if (!errorFound){

            if (!errorFound && tokenStream.get(arrayLoc).matches(PRINT) || tokenStream.get(arrayLoc).matches(ID) || tokenStream.get(arrayLoc).matches(TYPE)
            || tokenStream.get(arrayLoc).matches(WHILE) || tokenStream.get(arrayLoc).matches(IF) || tokenStream.get(arrayLoc).matches(OPEN_BLOCK)) {

                parseStatement();
                parseStatementList();

            }//else

            else {

                //do nothing

            }//else
        
        }//if

    }//parseStatementList

    public void parseStatement() {

        if (!errorFound) {

            if (tokenStream.get(arrayLoc).matches(PRINT)) {

                parsePrintStatement();

            }//if

            else if (tokenStream.get(arrayLoc).matches(ID)) {

                parseAssignmentStatement();

            }//else if

            else if (tokenStream.get(arrayLoc).matches(TYPE)) {

                parseVarDecl();

            }//else if

            else if (tokenStream.get(arrayLoc).matches(WHILE)) {

                parseWhileStatement();

            }//else if

            else if (tokenStream.get(arrayLoc).matches(IF)) {

                parseIfStatement();

            }//else if

            else if (tokenStream.get(arrayLoc).matches(OPEN_BLOCK)) {

                parseBlock();

            }//else if
    
        }//if

    }//parseStatement

    public void parsePrintStatement() {

        if (!errorFound) {

            ASTstream.add("<PrintStatement>");
            ASTdepth.add(lines++);

            arrayLoc++;
            arrayLoc++;
            parseExpr();
            arrayLoc++;

            lines--;

        }//if
        
    }//parsePrintStatement

    public void parseAssignmentStatement() {

        if (!errorFound) {

            String idType;

            ASTstream.add("<AssignmentStatement>");
            ASTdepth.add(lines++);

            parseId();

            if (!errorFound) {

                if (inTable(symbolName, symbolScope, symbolScopeLetter, lastID, scope, scopeLetter(scope)) != - 1) {

                    idType = symbolType.get(inTable(symbolName, symbolScope, symbolScopeLetter, lastID, scope, scopeLetter(scope)));

                }

                else {

                    idType = null;

                }

                arrayLoc++;
                String exprTypeAS = parseExpr();

                if (!idType.equals(exprTypeAS)) {

                    errorFound = true;
                    System.out.println("ERROR: Can't assign a(n) " + exprTypeAS + " to an Id who's type is " + idType);

                }

                lines--;
            }

        }//if
        
    }//parseAssignmentStatement

    public void parseVarDecl() {

        if (!errorFound) {

            ASTstream.add("<VarDecl>");
            ASTdepth.add(lines++);

            parseType();
            
            if (!errorFound) {

                declaration = true;
                parseId();

                lines--;

            }

        }//if

    }//parseVarDecl

    public void parseWhileStatement() {

        if (!errorFound) {

            ASTstream.add("<WhileStatement>");
            ASTdepth.add(lines++);

            arrayLoc++;
            parseBooleanExpr();
            parseBlock();

            lines--;

        }//if

    }//parseWhileStatement

    public void parseIfStatement() {

        if (!errorFound) {

            ASTstream.add("<IfStatement>");
            ASTdepth.add(lines++);

            arrayLoc++;
            parseBooleanExpr();
            parseBlock();

            lines--;

        }//if

    }//parseIfStatement

    public String parseExpr() {
        
        if (!errorFound) {

            if (tokenStream.get(arrayLoc).matches(DIGIT)) {

                parseIntExpr(); 
                return "int";               

            }//if

            else if (tokenStream.get(arrayLoc).matches(QUOTE)) {

                parseStringExpr();
                return "string";

            }//else if

            else if (tokenStream.get(arrayLoc).matches(OPEN_PARENTHESIS)) {

                parseBooleanExpr();
                return "boolean";

            }//else if

            else if (tokenStream.get(arrayLoc).matches(BOOL_VAL)) {

                parseBooleanExpr();
                return "boolean";

            }//else if

            else if (tokenStream.get(arrayLoc).matches(ID)) {

                parseId();
                
                if (!errorFound) {

                    if (inTable(symbolName, symbolScope, symbolScopeLetter, lastID, scope, scopeLetter(scope)) == - 1) {
                        
                        return null;
    
                    }
    
                    return symbolType.get(inTable(symbolName, symbolScope, symbolScopeLetter, lastID, scope, scopeLetter(scope)));

                }

            }//else if

        }//if

        return null;
        
    }//parseExpr

    public void parseIntExpr() {

        if (!errorFound) {

            parseDigit();

            if (!errorFound) {

                if (tokenStream.get(arrayLoc).matches(ADDITION_OP)) {

                    ASTstream.add("Addition");
                    ASTdepth.add(lines);
                    
                    parseIntop();
                    String exprTypeIE = parseExpr(); 
    
                    if (!exprTypeIE.equals("int")) {
    
                        errorFound = true;
                        System.out.println("ERROR: Can't add a(n) " + exprTypeIE + " to an int");
    
                    }
    
                }//if

            }
            
        }//if

    }//parseIntExpr

    public void parseStringExpr() {

        if (!errorFound) {

            inString = true;

            currString = currString + "\"";
            arrayLoc++;
            parseCharList();
            arrayLoc++;

            inString = false;

        }//if

    }//parseStringExpr

    public void parseBooleanExpr() {

        if (!errorFound) {

            String exprTypeBE, exprTypeBE2;

            if (tokenStream.get(arrayLoc).matches(OPEN_PARENTHESIS)) {

                arrayLoc++;
                exprTypeBE = parseExpr();
                parseBoolop();
                exprTypeBE2 = parseExpr();

                if (!exprTypeBE.equals(exprTypeBE2)) {

                    errorFound = true;
                    System.out.println("ERROR: Can't compare a(n) " + exprTypeBE + " to a(n) " + exprTypeBE2);

                }

                arrayLoc++;

            }//if
        
            else if (tokenStream.get(arrayLoc).matches(BOOL_VAL)) {

                parseBoolval();

            }//else if

        }//if

    }//parseBooleanExpr

    public void parseId() {

        if (!errorFound) {

            parseChar();

        }//if

    }//parseId

    public void parseCharList() {

        if (!errorFound) {

            if (tokenStream.get(arrayLoc).matches(ID)) {

                parseChar();
                parseCharList();

            }//if

            else if(tokenStream.get(arrayLoc).matches(SPACE)) {

                parseSpace();
                parseCharList();

            }//else if

            else {

                currString = currString + "\"";
                ASTstream.add(currString);
                ASTdepth.add(lines);
                currString = "";

            }//else

        }//if

    }//parseCharList

    public void parseType() {

        if (!errorFound) {

            match(TYPE);

        }//if

    }//parseType

    public void parseChar() {

        if (!errorFound) {

            if (inString) {

                currString = currString + tokenStream.get(arrayLoc);
                arrayLoc++;

            }

            else {

                match(ID);

            }
            
        }//if

    }//parseChar

    public void parseSpace() {

        if (!errorFound) {

            currString = currString + tokenStream.get(arrayLoc);
            arrayLoc++;

        }//if

    }//parseSpace

    public void parseDigit() {

        if (!errorFound) {

            match(DIGIT);

        }//if

    }//parseDigit

    public void parseBoolop() {

        if (!errorFound) {

            if (tokenStream.get(arrayLoc).matches("==")) {

                ASTstream.add("<IsEqual>");
                ASTdepth.add(lines);

            }

            else if (tokenStream.get(arrayLoc).matches("!=")) {

                ASTstream.add("<NotEqual>");
                ASTdepth.add(lines);

            }
            arrayLoc++;

        }//if

    }//parseBoolop

    public void parseBoolval() {

        if (!errorFound) {

            match(BOOL_VAL);

        }//if

    }//parseBoolval

    public void parseIntop() {

        if (!errorFound) {

            arrayLoc++;

        }//if

    }//parseIntop

}//Parser