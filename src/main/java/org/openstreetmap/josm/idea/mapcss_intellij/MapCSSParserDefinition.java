package org.openstreetmap.josm.idea.mapcss_intellij;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.openstreetmap.josm.idea.mapcss_intellij.parser.MapCSSParser;
import org.openstreetmap.josm.idea.mapcss_intellij.psi.MapCSSFile;
import org.openstreetmap.josm.idea.mapcss_intellij.psi.MapCSSTokenSets;
import org.openstreetmap.josm.idea.mapcss_intellij.psi.MapCSSTokenType;
import org.openstreetmap.josm.idea.mapcss_intellij.psi.MapCSSTypes;

final class MapCSSParserDefinition implements ParserDefinition {
    private static final IFileElementType FILE = new IFileElementType(MapCSSLanguage.INSTANCE);

    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new MapCSSLexarAdapter();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new MapCSSParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return MapCSSTokenSets.COMMENTS;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return TokenSet.create(MapCSSTypes.QUOTED);
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        return MapCSSTypes.Factory.createElement(node);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new MapCSSFile(viewProvider);
    }
}
