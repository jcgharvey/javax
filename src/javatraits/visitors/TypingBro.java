package javatraits.visitors;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.EnumDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import javatraits.scopes.Scope;
import javatraits.symbols.BuiltInTypeSymbol;
import javatraits.symbols.ClassSymbol;
import javatraits.symbols.ImportedSymbol;

/**
 * First Visitor
 * Class, interfaces, enums, inner classes
 * @author Jourdan Harvey
 *
 */
public class TypingBro extends VoidVisitorAdapter<Scope>{

	@Override
	public void visit(CompilationUnit n, Scope arg) {
		System.out.println("Created some built in symbols");
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
		System.out.println("Found Class " + n.getName());
		ClassSymbol symbol = new ClassSymbol(n.getName(), n.getModifiers());
		arg = n.getJTScope();
		arg.addSymbol(symbol);
		super.visit(n, null);
	}
	
	@Override
	public void visit(EnumDeclaration n, Scope arg) {
		System.out.println("Found enum " + n.getName());
		ClassSymbol symbol = new ClassSymbol(n.getName(), n.getModifiers());
		Scope scope = n.getJTScope();
		scope.addSymbol(symbol);
		super.visit(n, arg);
	}
	
	@Override
	public void visit(ImportDeclaration n, Scope arg) {
		System.out.println("Found import " + n.getName().toString());
		ImportedSymbol symbol = new ImportedSymbol(n.getName());
		Scope scope = n.getJTScope();
		scope.addSymbol(symbol);
		super.visit(n, arg);
	}
	
	@Override
	public void visit(PackageDeclaration n, Scope arg) {
		System.out.println("Found package " + n.getName());
		ImportedSymbol symbol = new ImportedSymbol(n.getName());
		Scope scope = n.getJTScope();
		scope.addSymbol(symbol);
		super.visit(n, arg);
	}
}
