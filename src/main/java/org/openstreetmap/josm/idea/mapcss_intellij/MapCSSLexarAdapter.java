// License: GPL. For details, see LICENSE file.
package org.openstreetmap.josm.idea.mapcss_intellij;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerPosition;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openstreetmap.josm.gui.mappaint.mapcss.parsergen.MapCSSParser;
import org.openstreetmap.josm.idea.mapcss_intellij._MapCSSLexer;
// SPDX-License-Identifier: GPL-2.0-or-later
public class MapCSSLexarAdapter extends FlexAdapter {
    public MapCSSLexarAdapter() {
        super(new _MapCSSLexer());
    }

    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {

    }

    @Override
    public int getState() {
        return 0;
    }

    @Override
    public @Nullable IElementType getTokenType() {
        return null;
    }

    @Override
    public int getTokenStart() {
        return 0;
    }

    @Override
    public int getTokenEnd() {
        return 0;
    }

    @Override
    public void advance() {

    }

    @Override
    public @NotNull LexerPosition getCurrentPosition() {
        return null;
    }

    @Override
    public void restore(@NotNull LexerPosition position) {

    }

    @Override
    public @NotNull CharSequence getBufferSequence() {
        return null;
    }

    @Override
    public int getBufferEnd() {
        return 0;
    }
}
