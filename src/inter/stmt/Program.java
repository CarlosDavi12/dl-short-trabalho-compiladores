package inter.stmt;

import lexer.Tag;
import lexer.Token;
import llvm.LLVMEmitter;

public class Program extends Stmt {
	private Token id;
	private Block block;

	public Program(Token i, Block b) {
		id = i;
		block = b;
		addChild(b);
	}

	@Override
	public String toString() {
		return Tag.PROGRAM.toString();
	}

	@Override
	public void genLLVM(LLVMEmitter emitter) {
		block.genLLVM(emitter); // Gera LLVM para o corpo principal
	}
}
