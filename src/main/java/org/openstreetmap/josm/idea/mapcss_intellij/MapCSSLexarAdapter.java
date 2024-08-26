// License: GPL. For details, see LICENSE file.
// SPDX-License-Identifier: GPL-2.0-or-later
package org.openstreetmap.josm.idea.mapcss_intellij;
import com.intellij.lexer.FlexAdapter;

public class MapCSSLexarAdapter extends FlexAdapter {
    public MapCSSLexarAdapter() {
        super(new _MapCSSLexer());
    }
}
