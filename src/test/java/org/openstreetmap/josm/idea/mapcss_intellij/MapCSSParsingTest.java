package org.openstreetmap.josm.idea.mapcss_intellij;

import com.intellij.openapi.project.impl.ProjectImpl;
import com.intellij.testFramework.ParsingTestCase;
import com.intellij.testFramework.ProjectExtension;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.logging.LogManager;

public class MapCSSParsingTest extends ParsingTestCase {
    public MapCSSParsingTest() throws IOException {
        super("", "mapcss", new MapCSSParserDefinition());
        LogManager.getLogManager().readConfiguration();
    }

    @Test
    public void testParsingTestData() {
        doTest(true);
    }

    @Override
    protected String getTestDataPath() {
        return "src/test/resources";
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }

    private static void testFile(String name) {
        final MapCSSParserDefinition definition = new MapCSSParserDefinition();
        definition.createParser(new ProjectExtension())
    }
}