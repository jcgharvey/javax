package javatraits.visitors;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.EnumDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import javatraits.scopes.Scope;
import javatraits.symbols.BuiltInTypeSymbol;
import javatraits.symbols.ClassSymbol;
import javatraits.symbols.ConstructorSymbol;
import javatraits.symbols.ImportedSymbol;
import javatraits.symbols.MethodSymbol;

/**
 * First Visitor
 * Class, interfaces, enums, inner classes
 * @author Jourdan Harvey
 *
 */
public class TypingVisitor extends VoidVisitorAdapter<Scope>{

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
		arg = n.getJTScope();
		System.out.println("Found Class " + n.getName() + " in scope " + arg.getEnclosingScope().getClass().toString());
		ClassSymbol symbol = new ClassSymbol(n.getName(), n.getModifiers());
		arg.addSymbol(symbol);
		super.visit(n, null);
	}
	
	@Override
	public void visit(ConstructorDeclaration n, Scope arg) {
		arg = n.getJTScope();
		System.out.println("Found constructor " + n.getName() + " in scope " + arg.getEnclosingScope().getClass().toString());
		ConstructorSymbol symbol = new ConstructorSymbol(n.getName(), n.getModifiers(), n.getParameters());
		arg.addSymbol(symbol);
		super.visit(n, arg);
	}
	
	@Override
	public void visit(EnumDeclaration n, Scope arg) {
		arg = n.getJTScope();
		System.out.println("Found enum " + n.getName() + " in scope " + arg.getEnclosingScope().getClass().toString());
		ClassSymbol symbol = new ClassSymbol(n.getName(), n.getModifiers());
		Scope scope = n.getJTScope();
		scope.addSymbol(symbol);
		super.visit(n, null);
	}
	
	@Override
	public void visit(ImportDeclaration n, Scope arg) {
		arg = n.getJTScope();
		System.out.println("Found import " + n.getName() + " in scope " + arg.getEnclosingScope().getClass().toString());
		ImportedSymbol symbol = new ImportedSymbol(n.getName());
		Scope scope = n.getJTScope();
		scope.addSymbol(symbol);
		super.visit(n, null);
	}
	
	@Override
	public void visit(MethodDeclaration n, Scope arg) {
		arg = n.getJTScope();
		System.out.println("Found method " + n.getName() + " in scope " + arg.getClass().toString());
		MethodSymbol symbol = new MethodSymbol(n.getName(), n.getType(), n.getModifiers(), n.getParameters());
		Scope scope = n.getJTScope();
		scope.addSymbol(symbol);
		super.visit(n, null);
	}
	
	@Override
	public void visit(PackageDeclaration n, Scope arg) {
		arg = n.getJTScope();
		System.out.println("Found package " + n.getName() + " in scope " + arg.getClass().toString());
		ImportedSymbol symbol = new ImportedSymbol(n.getName());
		Scope scope = n.getJTScope();
		scope.addSymbol(symbol);
		super.visit(n, null);
	}
	
	
}
