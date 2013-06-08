import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;

import java.io.File;

import javatraits.visitors.DefinitionVisitor;
import javatraits.visitors.MemberVisitor;
import javatraits.visitors.ResolvingVisitor;
import javatraits.visitors.ScopingVisitor;
import javatraits.visitors.TypingVisitor;

public class MainRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CompilationUnit tree = JavaParser.parse(new File("src/play.javax"));

			ScopingVisitor scoping = new ScopingVisitor();
			tree.accept(scoping, null);
			TypingVisitor typing = new TypingVisitor();
			tree.accept(typing, null);
			MemberVisitor member = new MemberVisitor();
			tree.accept(member, null);
			DefinitionVisitor definition = new DefinitionVisitor();
			tree.accept(definition, null);
			ResolvingVisitor resolving = new ResolvingVisitor();
			tree.accept(resolving, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
