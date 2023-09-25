// SPDX-License-Identifier: GPL-2.0-or-later
package org.openstreetmap.josm.idea.mapcss_intellij.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.openstreetmap.josm.idea.mapcss_intellij.MapCSSLanguage;

public final class MapCSSTokenType extends IElementType {
    public MapCSSTokenType(@NotNull @NonNls String debugName) {
        super(debugName, MapCSSLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "MapCSSTokenType." + super.toString();
    }
}
