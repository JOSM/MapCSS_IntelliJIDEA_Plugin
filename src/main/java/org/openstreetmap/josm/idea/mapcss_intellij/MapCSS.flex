// SPDX-License-Identifier: GPL-2.0-or-later
/**
* A MapCSS implementation in JFlex
*/
%%

%class MapCSSLexar
%standalone
%unicode
%line
%column

%{
  StringBuffer string = new StringBuffer();
  boolean regexStarted = false;

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace = {LineTerminator} | [ \t\f]
/* Comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comment can be the last line of the file, without line terminator.
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*" + "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

PP_And = "and"
PP_Or = "or"
PP_Not = "not"
PP_Supports = "@supports"
PP_NewlineChar = {LineTerminator}
PP_WhiteSpace = {WhiteSpace}
Set = "set"
Identifier = [a-zA-Z_](a-zA-Z_-0-9)*
UInt = \d+
String = ([ !#-\[\]-~\u0080-\uFFFF] | \\\" | \\\\)*
Predefined = [dDsSwWbBAGZz]
_RegexCharWithoutStar = [ -)+-.0-\[\]-~\u0080-\uFFFF] | \/ | \\ | \[ | \] | \+ | \. | \' | \" | \( | \) | \{ | \} | \? | \* | \^ | \$ | \| | \p | {Predefined}
Regex = "/" {_RegexCharWithoutStar} ({_RegexCharWithoutStar} | "*")* "/"
LBrace = "{"
RBrace = "}"
LPar = "("
RPar = ")"
Comma = ","
Colon = ":"
UFloat = \d+(.\d+)?
_Hex = [0-9a-fA-F]
HexColor = #({_Hex}{3}|{_Hex}{6}|{_Hex}{8})
S = [ \t\n\r\f]+
Star = "*"
Slash = "/"
LSquare = "["
RSquare = "]"
GreaterEqual = ">="
LessEqual = "<="
Greater = ">"
Lesser = "<"
Exclamation = "!"
Tilde = "~"
DColon = {Colon}{Colon}
SemiColon = ";"
Pipe = "|"
PipeZ = "|z"
Plus = "+"
Minus = "-"
Ampersand = "&"
Question = "?"
Dollar = "$"
FullStop = "."
SubsetOrEqual = [∈⊆]
NotSubsetOrEqual = "⊈"
SupersetOrEqual = "⊇"
NotSupersetOrEqual = "⊉"
Crossing = "⧉"
Percent = "%"

%state STRING
%state REGEX
%%

/** Keywords */
<YYINITIAL> "*" { return symbol(sym.ANY_PRIMITIVE); }
<YYINITIAL> "node" { return symbol(sym.NODE_PRIMITIVE); }
<YYINITIAL> "way" { return symbol(sym.WAY_PRIMITIVE); }
<YYINITIAL> "relation" { return symbol(sym.RELATION_PRIMITIVE); }
<YYINITIAL> {
    /* identifiers */
    {Identifier} {return symbol(sym.IDENTIFIER); }
    /* literals */
    {UInt} { return symbol(sym.INTEGER_LITERAL); }
    {UFloat} { return symbol(sym.FLOAT_LITERAL); }
    \" { string.setLength(0); yybegin(STRING);}
    /* Operators */
    {Plus} { return symbol(sym.PLUS); }
    {Minus} { return symbol(sym.MINUS); }
    {Star} { return symbol(sym.STAR); }
    {Slash} { return symbol(sym.SLASH); }
    {GreaterEqual} { return symbol(sym.GREATER_EQUAL); }
    {LessEqual} { return symbol(sym.LESS_EQUAL); }
    {Greater} { return symbol(sym.GREATER); }
    {Lesser} { return symbol(sym.LESS); }
    "=" { return symbol(sym.EQUAL); }
    "==" { return symbol(sym.EQUAL_EQUAL); }
    "=~" { return symbol(sym.LIST_MEMBERSHIP); }
    "=~" { string.setLength(0); yybegin(REGEX); }

    /* Comments */
    {Comment} { /* ignore */ }
    /* Whitespace */
    {WhiteSpace} { /* ignore */ }
}

<STRING> {
    \" { yybegin(YYINITIAL); return symbol(sym.STRING_LITERAL, string.toString()); }
    [^\n\r\"\\]+ { string.append(yytext()); }
    \\t { string.append('\t'); }
    \\n { string.append('\n'); }
    \\r { string.append('\r'); }
    \\\" { string.append('\"'); }
    \\ { string.append('\\'); }
}

<REGEX> {
   \/ {
        if (regexStarted) {
           yybegin(YYINITIAL);
           return symbol(sym.REGEX, java.util.regex.Pattern.compile(string.toString()));
        } else {
          regexStarted = true;
        }
      }
   [^\/]+ { string.append(yytext()); }
}

/* error fallback */
[^] { throw new Error("Illegal character <" + yytext() + ">"); }