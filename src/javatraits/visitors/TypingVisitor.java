package javatraits.visitors;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.EnumDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import javatraits.scopes.ClassScope;
import javatraits.scopes.Scope;
import javatraits.symbols.BuiltInTypeSymbol;
import javatraits.symbols.ClassSymbol;
import javatraits.symbols.ConstructorSymbol;
import javatraits.symbols.ImportedSymbol;
import javatraits.symbols.ParameterizedSymbol;
import javatraits.symbols.Symbol;

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
		arg.addSymbol(new BuiltInTypeSymbol(BuiltInTypeSymbol.bVoid));
		super.visit(n, null);
	}

	@Override
	public void visit(ClassOrInterfaceDeclaration n, Scope arg) {
		Scope localScope = n.getJTScope();
		System.out.println(n.getName() + " scope " + localScope.getName());
		ClassSymbol symbol = new ClassSymbol(n.getName(), n.getModifiers(), (ClassScope) localScope);
		localScope.getEnclosingScope().addSymbol(symbol);
		super.visit(n, null);
	}
	
	@Override
	public void visit(ConstructorDeclaration n, Scope arg) {
		arg = n.getJTScope();
		Symbol symbol = new ConstructorSymbol(n.getName(), n.getModifiers(), n.getParameters());
		arg.getEnclosingScope().addSymbol(symbol);
		super.visit(n, arg);
	}
	
	@Override
	public void visit(EnumDeclaration n, Scope arg) {
		arg = n.getJTScope();
		Symbol symbol = new ClassSymbol(n.getName(), n.getModifiers());
		Scope scope = n.getJTScope();
		scope.addSymbol(symbol);
		super.visit(n, null);
	}
	
	@Override
	public void visit(ImportDeclaration n, Scope arg) {
		arg = n.getJTScope();
		ImportedSymbol symbol = new ImportedSymbol(n.getName());
		Scope scope = n.getJTScope();
		scope.addSymbol(symbol);
		super.visit(n, null);
	}
	
	@Override
	public void visit(MethodDeclaration n, Scope arg) {
		arg = n.getJTScope();
		Symbol symbol = new ParameterizedSymbol(n.getName(), n.getType(), n.getModifiers(), n.getParameters());
		Scope scope = n.getJTScope();
		// add to itself for recursion
		scope.addSymbol(symbol);
		// add to enclosing scope for calls
		scope.getEnclosingScope().addSymbol(symbol);
		super.visit(n, null);
	}
	
	@Override
	public void visit(PackageDeclaration n, Scope arg) {
		arg = n.getJTScope();
		Symbol symbol = new ImportedSymbol(n.getName());
		Scope scope = n.getJTScope();
		scope.addSymbol(symbol);
		super.visit(n, null);
	}
	
	
}
