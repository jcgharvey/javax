package javatraits.visitors;

import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

import javatraits.CompilationException;
import javatraits.scopes.ClassScope;
import javatraits.scopes.Scope;
import javatraits.symbols.ClassSymbol;
import javatraits.symbols.FieldSymbol;
import javatraits.symbols.Symbol;
import javatraits.symbols.VariableSymbol;

/**
 * Third Visitor 
 * Fields & Methods
 * Inheitence
 * @author Jourdan Harvey
 * 
 */
public class MemberVisitor extends VoidVisitorAdapter<Scope> {

	private String className;

	@Override
	public void visit(ClassOrInterfaceDeclaration n, Scope arg) {
		ClassScope localScope = (ClassScope) n.getJTScope();
		className = n.getName();
		System.out.println("Member visitor touched class " + n.getName());
		ClassSymbol classSymbol = (ClassSymbol) localScope.resolveType(className);
		List<ClassOrInterfaceType> coits = n.getExtends() == null ? new ArrayList<ClassOrInterfaceType>() : n.getExtends();
		for(ClassOrInterfaceType coit : coits){
			ClassSymbol symbol = (ClassSymbol) localScope.resolveType(coit.getName());
			localScope.addParentScope(classSymbol.getScope());
			((ClassScope) classSymbol.getScope()).addEnclosingScope(symbol.getScope());
			System.out.print(n.getName() + " EnclosingScopes:");
			for(Scope s : localScope.getEnclosingScopes()){
				System.out.print(s.getName() + ",");
			}
			System.out.print("\n");
		}
		super.visit(n, arg);
	}
	
	@Override
	public void visit(ConstructorDeclaration n, Scope arg) {
		Scope localScope = n.getJTScope();
		if (className.equals(n.getName())) {
			List<Parameter> parameters = n.getParameters() == null ? new ArrayList<Parameter>()
					: n.getParameters();
			for (Parameter p : parameters) {
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
			if(localScope.resolveType(n.getType().toString()) == null){
				throw new CompilationException();
			}
			Symbol symbol = new FieldSymbol(v.getId().getName(),
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
		
		Scope localScope = n.getJTScope();
		if(localScope.resolveType(n.getType().toString()) == null){
			throw new CompilationException();
		}
		for (Parameter p : parameters) {
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
