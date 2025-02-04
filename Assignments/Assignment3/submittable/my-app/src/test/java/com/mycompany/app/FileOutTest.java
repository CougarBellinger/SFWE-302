package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import com.itextpdf.text.ExceptionConverter;

/**
 * Unit test for simple App.
 */
public class FileOutTest {
    private String goodFile = "sample.csv";
    private String privateDir = "/home/cougarbellinger/private/file.txt";
    private String dneFile = "fakefile.csv";

    private String nullString = null;

    @Test
    public void pdfPassGoodFile() {
        assertDoesNotThrow(() -> FileOut.PDF(goodFile));
    }

    @Test
    public void pdfPassDNEFile() {
        assertThrows(FileNotFoundException.class, () -> FileOut.PDF(dneFile));
    }

    @Test
    public void pdfPassNullFileName() {
        assertThrows(NullPointerException.class, () -> FileOut.PDF(nullString));
    }

    @Test
    public void pdfPassInvalidFile() {
        assertThrows(ExceptionConverter.class, () -> FileOut.PDF(privateDir));
    }

    @Test
    public void xlsPassGoodFile() {
        assertDoesNotThrow(() -> FileOut.XLS(goodFile));
    }

    @Test
    public void xlsPassDNEFile() {
        assertThrows(FileNotFoundException.class, () -> FileOut.XLS(dneFile));
    }

    @Test
    public void xlsPassNullFileName() {
        assertThrows(NullPointerException.class, () -> FileOut.XLS(nullString));
    }
}
