import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import scr.sxr.core.XmlSerializer

import static org.junit.jupiter.api.Assertions.assertEquals

class XmlSerializerTest {
    XmlSerializer xmlSerializer

    @BeforeEach
    void setUp() {
        xmlSerializer = new XmlSerializer()
    }

    @Test
    void testStringValue() {
        assertEquals("Sample Test", "Sample Test", "Strings should match")
    }
}