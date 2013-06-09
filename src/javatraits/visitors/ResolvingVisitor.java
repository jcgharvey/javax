package javatraits.visitors;

import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.BinaryExpr;
import japa.parser.ast.expr.BooleanLiteralExpr;
import japa.parser.ast.expr.CharLiteralExpr;
import japa.parser.ast.expr.ClassExpr;
import japa.parser.ast.expr.DoubleLiteralExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.IntegerLiteralExpr;
import japa.parser.ast.expr.LongLiteralExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.NullLiteralExpr;
import japa.parser.ast.expr.ThisExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import javatraits.scopes.Scope;
import javatraits.symbols.ClassSymbol;
import javatraits.symbols.Symbol;

/**
 * Fifth Visitor Resolve variable assignment
 * 
 * @author Jourdan Harvey
 * 
 */
public class ResolvingVisitor extends VoidVisitorAdapter<Scope> {

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
		arg = n.getJTScope();
		System.out.println("FieldAccessExpr " + n.getField());
		// arg.resolveField();
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
		Scope localScope = n.getJTScope();
		Expression exp = n.getScope();
		if (exp == null) {
			localScope.resolveMethod(n.getName(), n.getArgs(), true);
		} else {
			System.out.println("2This thang have wut " + exp.toString() + " " + n.getName() + " scope " + localScope.getEnclosingScope().getName());
			Symbol symbol = localScope.resolveVariable(exp.toString());
			System.out.println("Variable " + symbol.getName());
			ClassSymbol classSymbol = (ClassSymbol) localScope.resolveType(symbol.getType().toString());
			System.out.println("Class " + classSymbol.getName()+" Scope "+classSymbol.getScope().getName());
			classSymbol.getScope().resolveMethod(n.getName(), n.getArgs(), true);
		}
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
