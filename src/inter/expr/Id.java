package inter.expr;

import lexer.Tag;
import lexer.Token;
import llvm.LLVMEmitter;

public class Id extends Expr {

	public Id(Token op, Tag type) {
		super(op, type);
	}

	@Override
	public String toString() {
		return "%" + op.lexeme();
	}

	@Override
	public String getLLVM(LLVMEmitter emitter) {
		String temp = emitter.newTemp();
		emitter.emitLoad(temp, op.lexeme()); // Carrega o valor de %<variável>
		return temp;
	}
}
