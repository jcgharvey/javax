import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.visitor.DumpVisitor;

import java.io.File;
import java.io.FileReader;

import javatraits.visitors.TypingVisitor;


public class MainRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		StringReader sr = new StringReader("public class Lars {  private void foo() {}  } ");
		try {
			CompilationUnit tree = JavaParser.parse(new File("test/Unit/correct/singleClass.javax"));
			
//			DumpVisitor visitor = new DumpVisitor();
			TypingVisitor visitor = new TypingVisitor();
			tree.accept(visitor, null);
			
//			String output = visitor.getSource();
//			System.out.println(output);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

