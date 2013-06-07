package javatraits.visitors;

import javatraits.scopes.ClassScope;
import javatraits.scopes.GlobalScope;
import javatraits.scopes.Scope;
import javatraits.symbols.BuiltInTypeSymbol;
import javatraits.symbols.ClassSymbol;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;

/**
 * First Visitor
 * Class, interfaces, enums, inner classes
 * @author Jourdan Harvey
 *
 */
public class TypingVisitor extends VoidVisitorAdapter<Scope>{

	@Override
	public void visit(CompilationUnit n, Scope arg) {
		arg = n.getJTScope();
		arg.addSymbol(new BuiltInTypeSymbol(BuiltInTypeSymbol.bBoolean));
		arg.addSymbol(new BuiltInTypeSymbol(BuiltInTypeSymbol.bByte));
		arg.addSymbol(new BuiltInTypeSymbol(BuiltInTypeSymbol.bChar));
		arg.addSymbol(new BuiltInTypeSymbol(BuiltInTypeSymbol.bDouble));
		arg.addSymbol(new BuiltInTypeSymbol(BuiltInTypeSymbol.bFloat));
		arg.addSymbol(new BuiltInTypeSymbol(BuiltInTypeSymbol.bInt));
		arg.addSymbol(new BuiltInTypeSymbol(BuiltInTypeSymbol.bLong));
		arg.addSymbol(new BuiltInTypeSymbol(BuiltInTypeSymbol.bShort));
		arg.addSymbol(new BuiltInTypeSymbol(BuiltInTypeSymbol.bString));
		super.visit(n, null);
	}

	@Override
	public void visit(ClassOrInterfaceDeclaration n, Scope arg) {
		ClassSymbol symbol = new ClassSymbol(n.getName(), n.getModifiers());
		arg.addSymbol(symbol);
		Scope scope = n.getJTScope();
		scope.addSymbol(symbol);
		super.visit(n, null);
	}
	
	
}
