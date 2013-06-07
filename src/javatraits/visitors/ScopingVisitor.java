package javatraits.visitors;

import japa.parser.ast.BlockComment;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.LineComment;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.TypeParameter;
import japa.parser.ast.body.AnnotationDeclaration;
import japa.parser.ast.body.AnnotationMemberDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.EmptyMemberDeclaration;
import japa.parser.ast.body.EmptyTypeDeclaration;
import japa.parser.ast.body.EnumConstantDeclaration;
import japa.parser.ast.body.EnumDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.InitializerDeclaration;
import japa.parser.ast.body.JavadocComment;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.body.VariableDeclaratorId;
import japa.parser.ast.expr.ArrayAccessExpr;
import japa.parser.ast.expr.ArrayCreationExpr;
import japa.parser.ast.expr.ArrayInitializerExpr;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.BinaryExpr;
import japa.parser.ast.expr.BooleanLiteralExpr;
import japa.parser.ast.expr.CastExpr;
import japa.parser.ast.expr.CharLiteralExpr;
import japa.parser.ast.expr.ClassExpr;
import japa.parser.ast.expr.ConditionalExpr;
import japa.parser.ast.expr.DoubleLiteralExpr;
import japa.parser.ast.expr.EnclosedExpr;
import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.InstanceOfExpr;
import japa.parser.ast.expr.IntegerLiteralExpr;
import japa.parser.ast.expr.IntegerLiteralMinValueExpr;
import japa.parser.ast.expr.LongLiteralExpr;
import japa.parser.ast.expr.LongLiteralMinValueExpr;
import japa.parser.ast.expr.MarkerAnnotationExpr;
import japa.parser.ast.expr.MemberValuePair;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.NormalAnnotationExpr;
import japa.parser.ast.expr.NullLiteralExpr;
import japa.parser.ast.expr.ObjectCreationExpr;
import japa.parser.ast.expr.QualifiedNameExpr;
import japa.parser.ast.expr.SingleMemberAnnotationExpr;
import japa.parser.ast.expr.StringLiteralExpr;
import japa.parser.ast.expr.SuperExpr;
import japa.parser.ast.expr.ThisExpr;
import japa.parser.ast.expr.UnaryExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.AssertStmt;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.BreakStmt;
import japa.parser.ast.stmt.CatchClause;
import japa.parser.ast.stmt.ContinueStmt;
import japa.parser.ast.stmt.DoStmt;
import japa.parser.ast.stmt.EmptyStmt;
import japa.parser.ast.stmt.ExplicitConstructorInvocationStmt;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.ForStmt;
import japa.parser.ast.stmt.ForeachStmt;
import japa.parser.ast.stmt.IfStmt;
import japa.parser.ast.stmt.LabeledStmt;
import japa.parser.ast.stmt.ReturnStmt;
import japa.parser.ast.stmt.SwitchEntryStmt;
import japa.parser.ast.stmt.SwitchStmt;
import japa.parser.ast.stmt.SynchronizedStmt;
import japa.parser.ast.stmt.ThrowStmt;
import japa.parser.ast.stmt.TryStmt;
import japa.parser.ast.stmt.TypeDeclarationStmt;
import japa.parser.ast.stmt.WhileStmt;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.type.PrimitiveType;
import japa.parser.ast.type.ReferenceType;
import japa.parser.ast.type.VoidType;
import japa.parser.ast.type.WildcardType;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import javatraits.scopes.BasicScope;
import javatraits.scopes.ClassScope;
import javatraits.scopes.GlobalScope;
import javatraits.scopes.Scope;

public class ScopingVisitor extends VoidVisitorAdapter<Scope> {

	@Override
	public void visit(CompilationUnit n, Scope scope) {
		scope = new GlobalScope();
		n.setJTScope(scope);
		super.visit(n, scope);
	}

	@Override
	public void visit(ClassOrInterfaceDeclaration n, Scope arg) {
		Scope scope = new ClassScope(arg);
		n.setJTScope(scope);
		super.visit(n, scope);
	}

	@Override
	public void visit(BlockStmt n, Scope arg) {
		Scope scope = new BasicScope(arg);
		n.setJTScope(scope);
		super.visit(n, scope);
	}

	@Override
	public void visit(ConstructorDeclaration n, Scope arg) {
		Scope scope = new BasicScope(arg);
		n.setJTScope(scope);
		super.visit(n, scope);
	}

	@Override
	public void visit(CatchClause n, Scope arg) {
		Scope scope = new BasicScope(arg);
		n.setJTScope(scope);
		super.visit(n, scope);
	}

	@Override
	public void visit(EnumDeclaration n, Scope arg) {
		Scope scope = new ClassScope(arg);
		n.setJTScope(scope);
		super.visit(n, scope);
	}

	@Override
	public void visit(ForeachStmt n, Scope arg) {
		Scope scope = new BasicScope(arg);
		n.setJTScope(scope);
		super.visit(n, scope);
	}

	@Override
	public void visit(ForStmt n, Scope arg) {
		Scope scope = new BasicScope(arg);
		n.setJTScope(scope);
		super.visit(n, scope);
	}

	@Override
	public void visit(IfStmt n, Scope arg) {
		Scope scope = new BasicScope(arg);
		n.setJTScope(scope);
		super.visit(n, scope);
	}

	@Override
	public void visit(MethodDeclaration n, Scope arg) {
		Scope scope = new ClassScope(arg);
		n.setJTScope(scope);
		super.visit(n, scope);
	}

	@Override
	public void visit(SwitchStmt n, Scope arg) {
		Scope scope = new BasicScope(arg);
		n.setJTScope(scope);
		super.visit(n, scope);
	}

	@Override
	public void visit(TryStmt n, Scope arg) {
		Scope scope = new BasicScope(arg);
		n.setJTScope(scope);
//		n.setJTScope(arg);
		super.visit(n, scope);
	}

	// Set the scope in every visitor

	@Override
	public void visit(AnnotationDeclaration n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(AnnotationMemberDeclaration n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(ArrayAccessExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(ArrayCreationExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(ArrayInitializerExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(AssertStmt n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(AssignExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(BinaryExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(BlockComment n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(BooleanLiteralExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(BreakStmt n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(CastExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(CharLiteralExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(ClassExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(ClassOrInterfaceType n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(ConditionalExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(ContinueStmt n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(DoStmt n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(DoubleLiteralExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(EmptyMemberDeclaration n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(EmptyStmt n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(EmptyTypeDeclaration n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(EnclosedExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(EnumConstantDeclaration n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(ExplicitConstructorInvocationStmt n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(ExpressionStmt n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(FieldAccessExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(FieldDeclaration n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(ImportDeclaration n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(InitializerDeclaration n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(InstanceOfExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(IntegerLiteralExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(IntegerLiteralMinValueExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(JavadocComment n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(LabeledStmt n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(LineComment n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(LongLiteralExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(LongLiteralMinValueExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(MarkerAnnotationExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(MemberValuePair n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(MethodCallExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(NameExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(NormalAnnotationExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(NullLiteralExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(ObjectCreationExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(PackageDeclaration n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(Parameter n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(PrimitiveType n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(QualifiedNameExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(ReferenceType n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(ReturnStmt n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(SingleMemberAnnotationExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(StringLiteralExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(SuperExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(SwitchEntryStmt n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(SynchronizedStmt n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(ThisExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(ThrowStmt n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(TypeDeclarationStmt n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(TypeParameter n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(UnaryExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(VariableDeclarationExpr n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(VariableDeclarator n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(VariableDeclaratorId n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(VoidType n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(WhileStmt n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

	@Override
	public void visit(WildcardType n, Scope arg) {
		n.setJTScope(arg);
		super.visit(n, arg);
	}

}