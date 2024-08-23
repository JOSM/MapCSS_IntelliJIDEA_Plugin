package org.openstreetmap.josm.idea.mapcss_intellij.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import org.openstreetmap.josm.idea.mapcss_intellij.MapCSSFileType;
import org.openstreetmap.josm.idea.mapcss_intellij.MapCSSLanguage;

public class MapCSSFile extends PsiFileBase {
    public MapCSSFile(@NotNull final FileViewProvider viewProvider) {
        super(viewProvider, MapCSSLanguage.INSTANCE);
    }
    @Override
    public @NotNull FileType getFileType() {
        return MapCSSFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "MapCSS File";
    }
}
