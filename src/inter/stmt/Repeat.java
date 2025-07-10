package inter.stmt;

import inter.expr.Expr;
import llvm.LLVMEmitter;

public class Repeat extends Stmt {
	private final Stmt body;
	private final Expr condition;

	public Repeat(Stmt body, Expr condition) {
		this.body = body;
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "repita " + body.toString() + " até (" + condition.toString() + ")";
	}

	@Override
	public void genLLVM(LLVMEmitter emitter) {
		String labelStart = emitter.newLabel();
		String labelCond = emitter.newLabel();

		emitter.emitLabel(labelStart); // Início do laço
		body.genLLVM(emitter); // Corpo do laço
		emitter.emitLabel(labelCond); // Avaliação da condição
		String condResult = condition.getLLVM(emitter); // Avalia a expressão condicional
		emitter.emit("br i1 " + condResult + ", label %" + labelCond + "_end, label %" + labelStart);
		emitter.emitLabel(labelCond + "_end"); // Fim do laço se condição for verdadeira
	}
}
