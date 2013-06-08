package javatraits.visitors;

import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

import javatraits.CompilationException;
import javatraits.scopes.Scope;
import javatraits.symbols.ConstructorSymbol;
import javatraits.symbols.FieldSymbol;
import javatraits.symbols.MethodSymbol;
import javatraits.symbols.Symbol;
import javatraits.symbols.VariableSymbol;

/**
 * Third Visitor Fields & Methods
 * 
 * @author Jourdan Harvey
 * 
 */
public class MemberVisitor extends VoidVisitorAdapter<Scope> {

	private String className;

	@Override
	public void visit(ClassOrInterfaceDeclaration n, Scope arg) {
		className = n.getName();
		super.visit(n, arg);
	}

	@Override
	public void visit(ConstructorDeclaration n, Scope arg) {
		Scope localScope = n.getJTScope();
		System.out.println("ConstructorDecl " + n.getName());
		if (className.equals(n.getName())) {
			List<Parameter> parameters = n.getParameters() == null ? new ArrayList<Parameter>()
					: n.getParameters();
			Symbol symbol = new ConstructorSymbol(n.getName(),
					n.getModifiers(), parameters);
			localScope.addSymbol(symbol);
			for (Parameter p : parameters) {
				System.out.println("Found type name: " + p.getType().toString());
				if(localScope.resolveType(p.getType().toString()) == null){
					throw new CompilationException();
				}
				Symbol varSymbol = new VariableSymbol(p.getId().getName(),
						p.getType(), p.getModifiers());
				localScope.addSymbol(varSymbol);
			}
		} else {
			throw new CompilationException();
		}
		super.visit(n, null);
	}

	@Override
	public void visit(FieldDeclaration n, Scope arg) {
		Scope localScope = n.getJTScope();
		List<VariableDeclarator> variables = n.getVariables();
		for (VariableDeclarator v : variables) {
			System.out.println("Found a field variable declarator "
					+ v.toString() + " in scope " + localScope.getClass().toString()
					+ " of type " + n.getType());
			if(localScope.resolveType(n.getType().toString()) == null){
				throw new CompilationException();
			}
			FieldSymbol symbol = new FieldSymbol(v.getId().getName(),
					n.getType(), n.getModifiers());
			localScope.addSymbol(symbol);
		}
		super.visit(n, null);
	}

	@Override
	public void visit(MethodDeclaration n, Scope arg) {
		arg = n.getJTScope();
		// get parameters
		List<Parameter> parameters = n.getParameters() == null ? new ArrayList<Parameter>()
				: n.getParameters();
		Symbol symbol = new MethodSymbol(n.getName(), n.getType(),
				n.getModifiers(), n.getParameters());
		arg.addSymbol(symbol);
		Scope localScope = n.getJTScope();
		if(localScope.resolveType(n.getType().toString()) == null){
			throw new CompilationException();
		}
		for (Parameter p : parameters) {
			System.out.println("Param: " + p.toString() + " in scope "
					+ arg.getClass().toString());
			if(localScope.resolveType(p.getType().toString()) == null){
				throw new CompilationException();
			}
			Symbol varSymbol = new VariableSymbol(p.getId().getName(),
					p.getType(), p.getModifiers());
			localScope.addSymbol(varSymbol);
		}
		super.visit(n, null);
	}
}
