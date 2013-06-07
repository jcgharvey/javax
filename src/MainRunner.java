import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;

import java.io.File;

import javatraits.visitors.DefinitionBro;
import javatraits.visitors.MemberBro;
import javatraits.visitors.ResolvingBro;
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
			MemberBro member = new MemberBro();
			tree.accept(member, null);
			DefinitionBro definition = new DefinitionBro();
			tree.accept(definition, null);
			ResolvingBro resolving = new ResolvingBro();
			tree.accept(resolving, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
