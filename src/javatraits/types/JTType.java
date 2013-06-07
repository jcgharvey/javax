package javatraits.types;

import japa.parser.ast.type.Type;
import japa.parser.ast.visitor.GenericVisitor;
import japa.parser.ast.visitor.VoidVisitor;

public class JTType extends Type {
	private String name;
	
	public JTType(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		// TODO Auto-generated method stub
		
	}
}
