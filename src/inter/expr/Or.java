package inter.expr;

import lexer.Tag;
import lexer.Token;
import llvm.LLVMEmitter;

public class Or extends Expr {
	protected Expr expr1;
	protected Expr expr2;

	public Or(Expr e1, Expr e2) {
		super(new Token(Tag.OR, "|"), Tag.BOOL);
		if (!e1.type().isBool() || !e2.type().isBool())
			error("O operador lógico | só pode ser aplicado entre tipos booleanos");
		expr1 = e1;
		expr2 = e2;
		addChild(expr1);
		addChild(expr2);
	}

	@Override
	public String getLLVM(LLVMEmitter emitter) {
		String left = expr1.getLLVM(emitter);
		String right = expr2.getLLVM(emitter);
		String result = emitter.newTemp();

		// OR lógico usando operação bit a bit
		emitter.emit(result + " = or i1 " + left + ", " + right);
		return result;
	}
}
