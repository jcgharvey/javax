package Unit;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.visitor.DumpVisitor;

import java.io.File;
import java.io.IOException;

import javatraits.visitors.TypingBro;

import junit.framework.TestCase;

import org.junit.Test;

public class TypingVisitorTest extends TestCase {
	
	private String correctFolder = "test/Unit/correct/";
	private String incorrectFolder = "test/Unit/incorrect/";
	
	public void testSingleClass() {
		CompilationUnit tree;
		String filename = correctFolder + "singleClass.javax";
		try {
			
			tree = JavaParser.parse(new File(filename));
			TypingBro visitor = new TypingBro();
			tree.accept(visitor, null);
			
		} catch (ParseException e) {
			fail("file not parsed");
			e.printStackTrace();
		} catch (IOException e) {
			fail("File " + filename + " not found");
			e.printStackTrace();
		}
	}
}
