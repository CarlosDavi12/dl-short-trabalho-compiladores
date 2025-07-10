package inter.expr;

import lexer.Token;
import llvm.LLVMEmitter;

public class Un extends Expr {
	private Token op;
	private Expr expr;

	public Un(Token op, Expr expr) {
		super(op, expr.type); // aqui define o tipo corretamente
		this.op = op;
		this.expr = expr;
	}

	@Override
	public String toString() {
		return "(" + op.lexeme() + expr.toString() + ")";
	}

	@Override
	public String getLLVM(LLVMEmitter emitter) {
		String value = expr.getLLVM(emitter);

		if (op.lexeme().equals("-")) {
			String result = emitter.newTemp();
			emitter.emitNeg(result, value); // gera: result = sub i32 0, value
			return result;
		} else {
			// '+' unário: valor direto, sem alteração
			return value;
		}
	}
}
