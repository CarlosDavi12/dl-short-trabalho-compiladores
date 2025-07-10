package inter.stmt;

import inter.expr.Id;
import llvm.LLVMEmitter;

public class Decl extends Stmt {
	private Id id;

	public Decl(Id i) {
		id = i;
		addChild(id);
	}

	@Override
	public String toString() {
		return "DECL";
	}

	@Override
	public void genLLVM(LLVMEmitter emitter) {
		// Aloca espaço para a variável no LLVM e inicializa com 0
		emitter.emitAssign(id.op().lexeme(), "0");
	}
}
