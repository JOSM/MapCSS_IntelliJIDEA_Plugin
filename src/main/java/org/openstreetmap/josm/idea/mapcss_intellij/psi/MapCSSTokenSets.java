package org.openstreetmap.josm.idea.mapcss_intellij.psi;

import com.intellij.psi.tree.TokenSet;

public interface MapCSSTokenSets {
    TokenSet COMMENTS = TokenSet.create(MapCSSTypes.BLOCK_COMMENT);
    TokenSet IDENTIFIERS = TokenSet.create(MapCSSTypes.IDENT);
}
