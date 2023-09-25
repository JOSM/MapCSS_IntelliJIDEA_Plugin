// SPDX-License-Identifier: GPL-2.0-or-later
package org.openstreetmap.josm.idea.mapcss_intellij;

import javax.swing.Icon;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MapCSSFileType extends LanguageFileType {
    public static final MapCSSFileType INSTANCE = new MapCSSFileType();

    private MapCSSFileType() {
        super(MapCSSLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "MapCSS File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "MapCSS language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "mapcss";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return MapCSSIcons.FILE;
    }
}
