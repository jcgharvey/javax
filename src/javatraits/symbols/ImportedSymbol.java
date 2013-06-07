package javatraits.symbols;

import japa.parser.ast.expr.NameExpr;

public class ImportedSymbol extends Symbol {
	public ImportedSymbol(NameExpr name) {
		super(name.toString(), null, 0);
	}
}
