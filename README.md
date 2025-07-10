# dl-short-3

Este projeto é uma versão resumida da linguagem DL.  
Esta versão faz as análises léxica, sintática e semântica.  
Ela está de acordo com a gramática abaixo, incluindo os novos recursos adicionados.

---

## Novos Recursos Adicionados

### Comando `repita ... ate`

- Permite repetir um conjunto de comandos até que uma condição seja satisfeita.
- **Não exige `inicio ... fim`** para o corpo do laço.
- A condição deve estar entre parênteses e deve ser uma expressão booleana.
- **Exemplo válido**:
  ```
  repita
    a = a - 1;
    escreva(a);
  ate (a < 0);
  ```

### Suporte a Operadores Unários

- Agora são aceitas expressões com os operadores unários `+` e `-`:
  - `-expr`: nega o valor (ex: `a = -a;`)
  - `+expr`: mantém o valor (ex: `b = +a;`)

---

## Gramática Atualizada

```
PROGRAM   ::= programa ID BLOCK
BLOCK     ::= inicio STMTS fim
STMTS     ::= STMT; STMTS | ε
STMT      ::= BLOCK | DECL | ASSIGN | WRITE | IF | REPEAT

DECL      ::= TYPE ID
ASSIGN    ::= ID = EXPR
WRITE     ::= escreva(ID)
IF        ::= se (EXPR) STMT
REPEAT    ::= repita STMTS ate (EXPR)

EXPR      ::= EXPR "|" REL | REL
REL       ::= REL < ARITH | REL <= ARITH | REL > ARITH | REL >= ARITH | REL == ARITH | ARITH
ARITH     ::= ARITH + TERM | ARITH - TERM | TERM
TERM      ::= TERM * FACTOR | FACTOR
FACTOR    ::= +FACTOR | -FACTOR | (EXPR) | ID | LIT_INT | LIT_REAL | LIT_BOOL
```

---

## Definições Regulares

```
LETTER    ::= a | b | ... | z | A | B | ... Z | _
DIGIT     ::= 0 | 1 | ... | 9
ID        ::= LETTER (LETTER | DIGIT)*
LIT_INT   ::= DIGIT+
LIT_REAL  ::= DIGIT+ . DIGIT+
LIT_BOOL  ::= verdadeiro | falso
TYPE      ::= inteiro | real | booleano
```
