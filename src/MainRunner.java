import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;

import java.io.File;

import javatraits.visitors.DefinitionBro;
import javatraits.visitors.MemberVisitor;
import javatraits.visitors.ResolvingVisitor;
import javatraits.visitors.ScopingBro;
import javatraits.visitors.TypingBro;

public class MainRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CompilationUnit tree = JavaParser.parse(new File("src/play.javax"));

			ScopingBro scoping = new ScopingBro();
			tree.accept(scoping, null);
			TypingBro typing = new TypingBro();
			tree.accept(typing, null);
			MemberVisitor member = new MemberVisitor();
			tree.accept(member, null);
			DefinitionBro definition = new DefinitionBro();
			tree.accept(definition, null);
			ResolvingVisitor resolving = new ResolvingVisitor();
			tree.accept(resolving, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
