package org.openstreetmap.josm.idea.mapcss_intellij;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static org.openstreetmap.josm.idea.mapcss_intellij.psi.MapCSSTypes.*;

%%

%{
  public _MapCSSLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _MapCSSLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

SUBSET_OR_EQUAL=[∈⊆]
UINT=[0-9]+
HEXCOLOR=#([0-9a-fA-F]{8}|[0-9a-fA-F]{6}|[0-9a-fA-F]{3})
SPACE=[ \t\n\r\f]+
BLOCK_COMMENT="/*" !([^]* "*/" [^]*) ("*/")
STRING=(['\"]).*?[^\\]\1
IDENT=[a-zA-Z_]([a-zA-Z_0-9-])*
REGEX="/".*?[^\\]"/"

%%
<YYINITIAL> {
  {WHITE_SPACE}              { return WHITE_SPACE; }

  "and"                      { return PP_AND; }
  "or"                       { return PP_OR; }
  "not"                      { return PP_NOT; }
  "@supports"                { return PP_SUPPORTS; }
  "set"                      { return SET; }
  "{"                        { return LBRACE; }
  "}"                        { return RBRACE; }
  "("                        { return LPAR; }
  ")"                        { return RPAR; }
  ","                        { return COMMA; }
  "*"                        { return STAR; }
  "/"                        { return SLASH; }
  "["                        { return LSQUARE; }
  "]"                        { return RSQUARE; }
  ">="                       { return GREATER_EQUAL; }
  "<="                       { return LESS_EQUAL; }
  ">"                        { return GREATER; }
  "<"                        { return LESS; }
  "!"                        { return EXCLAMATION; }
  "~"                        { return TILDE; }
  "::"                       { return DCOLON; }
  ":"                        { return COLON; }
  ";"                        { return SEMICOLON; }
  "|"                        { return PIPE; }
  "|z"                       { return PIPE_Z; }
  "+"                        { return PLUS; }
  "-"                        { return MINUS; }
  "&"                        { return AMPERSAND; }
  "?"                        { return QUESTION; }
  "$"                        { return DOLLAR; }
  "."                        { return FULLSTOP; }
  "°"                        { return DEG; }
  "⊈"                        { return NOT_SUBSET_OR_EQUAL; }
  "⊇"                        { return SUPERSET_OR_EQUAL; }
  "⊉"                        { return NOT_SUPERSET_OR_EQUAL; }
  "⧉"                        { return CROSSING; }
  "%"                        { return PERCENT; }
  "node"                     { return NODE; }
  "way"                      { return WAY; }
  "relation"                 { return RELATION; }
  "meta"                     { return META; }
  "area"                     { return AREA; }
  "canvas"                   { return CANVAS; }
  "line"                     { return LINE; }
  "setting"                  { return SETTING; }
  "@"                        { return AT; }
  "\\\\[dDsSwWbBAGZz]"       { return PREDEFINED; }
  "QUOTED"                   { return QUOTED; }

  {SUBSET_OR_EQUAL}          { return SUBSET_OR_EQUAL; }
  {UINT}                     { return UINT; }
  {HEXCOLOR}                 { return HEXCOLOR; }
  {SPACE}                    { return SPACE; }
  {BLOCK_COMMENT}            { return BLOCK_COMMENT; }
  {STRING}                   { return STRING; }
  {IDENT}                    { return IDENT; }
  {REGEX}                    { return REGEX; }

}

[^] { return BAD_CHARACTER; }
