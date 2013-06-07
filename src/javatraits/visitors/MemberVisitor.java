package javatraits.visitors;

import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import javatraits.scopes.Scope;

/**
 * Third Visitor
 * Fields & methods
 * @author Jourdan Harvey
 *
 */
public class MemberVisitor extends VoidVisitorAdapter<Scope>{

	@Override
	public void visit(ConstructorDeclaration n, Scope arg) {
		System.out.println("ConstructorDec" + n.getName());
		super.visit(n, arg);
	}
	
	@Override
	public void visit(FieldDeclaration n, Scope arg) {
		System.out.println("FieldDec" + n.toString());
		super.visit(n, arg);
	}
	
	@Override
	public void visit(MethodDeclaration n, Scope arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
	}
	
	
	
}
