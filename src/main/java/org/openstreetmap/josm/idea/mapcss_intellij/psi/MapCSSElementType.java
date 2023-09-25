// SPDX-License-Identifier: GPL-2.0-or-later
package org.openstreetmap.josm.idea.mapcss_intellij.psi;


import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.openstreetmap.josm.idea.mapcss_intellij.MapCSSLanguage;

public class MapCSSElementType extends IElementType {
    public MapCSSElementType(@NotNull @NonNls String debugName) {
        super(debugName, MapCSSLanguage.INSTANCE);
    }
}
