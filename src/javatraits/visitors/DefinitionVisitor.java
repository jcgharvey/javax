package javatraits.visitors;

import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.CatchClause;
import japa.parser.ast.stmt.ForStmt;
import japa.parser.ast.stmt.ForeachStmt;
import japa.parser.ast.stmt.IfStmt;
import japa.parser.ast.stmt.SwitchStmt;
import japa.parser.ast.stmt.WhileStmt;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

import javatraits.scopes.Scope;
import javatraits.scopes.Scope.ScopeType;
import javatraits.symbols.Symbol;
import javatraits.symbols.VariableSymbol;

/**
 * Fourth Visitor
 * Local variables
 * @author Jourdan Harvey
 *
 */
public class DefinitionVisitor extends VoidVisitorAdapter<Scope>{
	
	@Override
	public void visit(VariableDeclarator n, Scope arg) {
		super.visit(n, null);
	}
	
	@Override
	public void visit(VariableDeclarationExpr n, Scope arg) {
		arg = n.getJTScope();
		Scope enclosingScope = n.getJTScope().getEnclosingScope();
		System.out.println("Scope: " + enclosingScope.getScopeType());
		if(enclosingScope.getScopeType() == ScopeType.Class){
			System.out.println("Found some variableDecExpr in a class scope so they must be fields!");
		} else if (enclosingScope.getScopeType() == ScopeType.Local){
			List<VariableDeclarator> variables = n.getVars();
			System.out.println("VariableDeclarator " + n.toString() + " in scope " + arg.getClass().toString());
			for(VariableDeclarator v : variables){
				System.out.println("Added variable " + v.getId().getName() + " to scope " + enclosingScope.toString() + " symbol table");
				Symbol variableSymbol = new VariableSymbol(v.getId().getName(), n.getType(), n.getModifiers());
				enclosingScope.addSymbol(variableSymbol);
			}
		}
		super.visit(n, null);
	}
	
	@Override
	public void visit(BlockStmt n, Scope arg) {
		arg = n.getJTScope();
		super.visit(n, null);
	}
	
	@Override
	public void visit(CatchClause n, Scope arg) {
		arg = n.getJTScope();
		super.visit(n, null);
	}
	
	@Override
	public void visit(ForeachStmt n, Scope arg) {
		arg = n.getJTScope();
		super.visit(n, null);
	}
	
	@Override
	public void visit(ForStmt n, Scope arg) {
		arg = n.getJTScope();
		super.visit(n, null);
	}
	
	@Override
	public void visit(IfStmt n, Scope arg) {
		arg = n.getJTScope();
		super.visit(n, null);
	}
	
	@Override
	public void visit(SwitchStmt n, Scope arg) {
		arg = n.getJTScope();
		super.visit(n, null);
	}
	
	@Override
	public void visit(WhileStmt n, Scope arg) {
		arg = n.getJTScope();
		super.visit(n, null);
	}
}
