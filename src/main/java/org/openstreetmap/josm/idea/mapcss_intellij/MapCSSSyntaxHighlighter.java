// SPDX-License-Identifier: GPL-2.0-or-later
package org.openstreetmap.josm.idea.mapcss_intellij;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.openstreetmap.josm.idea.mapcss_intellij.psi.MapCSSTypes;

public class MapCSSSyntaxHighlighter extends SyntaxHighlighterBase {
    static final TextAttributesKey COMMENT = createTextAttributesKey("MAPCSS_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    static final TextAttributesKey STRING = createTextAttributesKey("MAPCSS_STRING", DefaultLanguageHighlighterColors.STRING);
    static final TextAttributesKey META = createTextAttributesKey("MAPCSS_META", DefaultLanguageHighlighterColors.KEYWORD);
    static final TextAttributesKey NODE = createTextAttributesKey("MAPCSS_NODE", DefaultLanguageHighlighterColors.KEYWORD);
    static final TextAttributesKey WAY = createTextAttributesKey("MAPCSS_WAY", DefaultLanguageHighlighterColors.KEYWORD);
    static final TextAttributesKey LINE = createTextAttributesKey("MAPCSS_LINE", DefaultLanguageHighlighterColors.KEYWORD);
    static final TextAttributesKey AREA = createTextAttributesKey("MAPCSS_AREA", DefaultLanguageHighlighterColors.KEYWORD);
    static final TextAttributesKey RELATION = createTextAttributesKey("MAPCSS_RELATION", DefaultLanguageHighlighterColors.KEYWORD);
    static final TextAttributesKey CANVAS = createTextAttributesKey("MAPCSS_CANVAS", DefaultLanguageHighlighterColors.KEYWORD);
    static final TextAttributesKey FUNCTION = createTextAttributesKey("MAPCSS_FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_CALL);

    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[] {COMMENT};
    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[] {META, NODE, WAY, LINE, AREA, RELATION, CANVAS};
    private static final TextAttributesKey[] VALUE_KEYS = new TextAttributesKey[] {STRING};
    private static final TextAttributesKey[] FUNCTION_KEYS = new TextAttributesKey[] {FUNCTION};
    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new MapCSSLexarAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (MapCSSTypes.BLOCK_COMMENT.equals(tokenType)) {
            return COMMENT_KEYS;
        } else if (MapCSSTypes.STRING.equals(tokenType)) {
            return VALUE_KEYS;
        } else if (MapCSSTypes.META.equals(tokenType) || MapCSSTypes.NODE.equals(tokenType)
                || MapCSSTypes.WAY.equals(tokenType) || MapCSSTypes.LINE.equals(tokenType) || MapCSSTypes.AREA.equals(tokenType)
                || MapCSSTypes.RELATION.equals(tokenType) || MapCSSTypes.CANVAS.equals(tokenType)) {
            return KEYWORD_KEYS;
        } else if (MapCSSTypes.FUNCTION.equals(tokenType)) {
            return FUNCTION_KEYS;
        }
        return EMPTY_KEYS;
    }
}
