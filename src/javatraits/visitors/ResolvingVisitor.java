package javatraits.visitors;

import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.BinaryExpr;
import japa.parser.ast.expr.BooleanLiteralExpr;
import japa.parser.ast.expr.CharLiteralExpr;
import japa.parser.ast.expr.ClassExpr;
import japa.parser.ast.expr.DoubleLiteralExpr;
import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.IntegerLiteralExpr;
import japa.parser.ast.expr.LongLiteralExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.NullLiteralExpr;
import japa.parser.ast.expr.ThisExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import javatraits.scopes.Scope;

/**
 * Fifth Visitor
 * Resolve variable assignment
 * @author Jourdan Harvey
 *
 */
public class ResolvingVisitor extends VoidVisitorAdapter<Scope>{

	@Override
	public void visit(BooleanLiteralExpr n, Scope arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
	}

	@Override
	public void visit(AssignExpr n, Scope arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
	}

	@Override
	public void visit(BinaryExpr n, Scope arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
	}

	@Override
	public void visit(CharLiteralExpr n, Scope arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
	}

	@Override
	public void visit(ClassExpr n, Scope arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
	}

	@Override
	public void visit(DoubleLiteralExpr n, Scope arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
	}

	@Override
	public void visit(FieldAccessExpr n, Scope arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
	}

	@Override
	public void visit(IntegerLiteralExpr n, Scope arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
	}

	@Override
	public void visit(LongLiteralExpr n, Scope arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
	}

	@Override
	public void visit(MethodCallExpr n, Scope arg) {
		arg = n.getJTScope();				
		arg.resolveMethod(n.getName());
		super.visit(n, arg);
	}

	@Override
	public void visit(NameExpr n, Scope arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
	}

	@Override
	public void visit(NullLiteralExpr n, Scope arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
	}

	@Override
	public void visit(ThisExpr n, Scope arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
	}
	
}
