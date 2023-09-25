// SPDX-License-Identifier: GPL-2.0-or-later
package org.openstreetmap.josm.idea.mapcss_intellij;

import com.intellij.lang.Language;

public final class MapCSSLanguage extends Language {

    public static final MapCSSLanguage INSTANCE = new MapCSSLanguage();

    private MapCSSLanguage() {
        super("MapCSS");
    }
}
