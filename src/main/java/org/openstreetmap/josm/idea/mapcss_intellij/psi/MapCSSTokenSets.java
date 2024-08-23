package org.openstreetmap.josm.idea.mapcss_intellij.psi;

import com.intellij.psi.tree.TokenSet;

public interface MapCSSTokenSets {
    TokenSet COMMENTS = TokenSet.create(MapCSSTypes.PP_COMMENT_START); // FIXME add END
    TokenSet IDENTIFIERS = TokenSet.create(MapCSSTypes.KEY);
}
