package inter.stmt;

import inter.expr.Id;
import lexer.Tag;
import llvm.LLVMEmitter;

public class Write extends Stmt {
	private Id id;

	public Write(Id i) {
		id = i;
		addChild(id);
	}

	@Override
	public String toString() {
		return Tag.WRITE.toString();
	}

	@Override
	public void genLLVM(LLVMEmitter emitter) {
		String temp = id.getLLVM(emitter); // Carrega o valor da variável
		emitter.emitPrint(temp); // Emite código para imprimir
	}
}
