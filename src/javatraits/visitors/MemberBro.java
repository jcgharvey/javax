package javatraits.visitors;

import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import javatraits.scopes.Scope;
import javatraits.symbols.ConstructorSymbol;
import javatraits.symbols.FieldSymbol;
import javatraits.symbols.MethodSymbol;
import javatraits.symbols.Symbol;

/**
 * Third Visitor
 * Fields & Methods
 * @author Jourdan Harvey
 *
 */
public class MemberBro extends VoidVisitorAdapter<Scope>{

	@Override
	public void visit(ConstructorDeclaration n, Scope arg) {
		System.out.println("ConstructorDec " + n.getName());
		System.out.println(n.getParameters());
		arg = n.getJTScope();
		Symbol symbol = new ConstructorSymbol(n.getName(), n.getModifiers());
		arg.addSymbol(symbol);
		n.getParameters();
		super.visit(n, arg);
	}
	
	@Override
	public void visit(FieldDeclaration n, Scope arg) {
		System.out.println("FieldDec " + n.toString());
		String fieldname = n.toString();
		String[] tokens = fieldname.split(" ");
		fieldname = tokens[tokens.length - 1];
		FieldSymbol symbol = new FieldSymbol(fieldname, n.getType(), n.getModifiers());
		arg.addSymbol(symbol);
		super.visit(n, arg);
	}
	
	@Override
	public void visit(MethodDeclaration n, Scope arg) {
		MethodSymbol symbol = new MethodSymbol(n.getName(), n.getType(), n.getModifiers());
		arg.addSymbol(symbol);
		super.visit(n, arg);
	}
}
