import java.io.File;
import java.io.FileInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Test {
    public static void main(String[] args) throws Exception{
        File file = new File(args[0]);
        FileInputStream fis = new FileInputStream(file);
        CharStream inp = CharStreams.fromStream(fis);

        ExprLexer lexer = new ExprLexer(inp);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);
        ParseTree tree = parser.prog();

        EvalVisitor eval = new EvalVisitor();
        int r = eval.visit(tree);
        System.out.println("end of calc. : " + r);
    }
}