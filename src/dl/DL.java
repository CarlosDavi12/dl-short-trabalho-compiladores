package dl;

import java.io.File;
import lexer.Lexer;
import parser.Parser;
import inter.Node;
import inter.stmt.Stmt;
import llvm.LLVMEmitter;

public class DL {
	public static void main(String[] args) {
		// Análise
		Lexer l = new Lexer(new File("prog.dl"));
		Parser p = new Parser(l);
		p.parse();

		// Imprimindo a árvore sintática
		System.out.println(p.parseTree());

		// Gerando código LLVM
		Node root = p.getRoot();
		if (root instanceof Stmt stmt) {
			System.out.println("\nCódigo LLVM gerado:\n");
			LLVMEmitter emitter = new LLVMEmitter();
			stmt.genLLVM(emitter);

			System.out.println("\n--- LLVM IR ---\n");
			for (String linha : emitter.getCode()) {
				System.out.println(linha);
			}
		}

		System.out.println("finalizado");
	}
}
