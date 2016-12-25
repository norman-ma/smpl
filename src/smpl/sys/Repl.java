package smpl.sys;

import smpl.semantics.Environment;
import smpl.semantics.Evaluator;
import smpl.syntax.SMPLProgram;
import smpl.syntax.SMPLLexer;
import smpl.syntax.SMPLParser;
import java.io.*;

public class Repl {

    public static final String PROMPT = "\nEval>";

    public static void main(String args[]) {
	repl(new Environment());
    }

    public static void repl(Environment env){        
            InputStreamReader reader = new InputStreamReader(System.in);
            while (true) {
                parseEvalShow(reader, env);
            }

    }

    public static void parseEvalShow(Reader reader,
				     Environment env) {
	SMPLParser parser;
	SMPLProgram program = null;
	Evaluator interp = new Evaluator();
	System.out.print(PROMPT);
	try {
	    parser = new SMPLParser(new SMPLLexer(reader));
	    program = (SMPLProgram) parser.parse().value;
	} catch (Exception e) {
	    System.out.println("Parse Error: " + e.getMessage());
	}

	if (program != null)
	    try {
		Object result;
		result = program.visit(interp, env);
		System.out.println("\nResult: " + result);
	    } catch (SMPLException e) {
		System.out.println(e.getMessage());
	    }
    }

}
