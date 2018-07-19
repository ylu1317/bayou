package edu.rice.pliny.apitrans.examples;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class PDF {
    String filename = "code_completion/src/test/resources/apitrans/pdf/foo.pdf";

    @Test
    public void read_text() throws ParseException, IOException {
        File infile = new File(this.filename);
        PDDocument document = PDDocument.load(infile);
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();
        assertEquals("Word document", text.trim());
    }

    @Test
    public void read_text2() throws Exception {
        PdfReader reader = new PdfReader(this.filename);
        // pageNumber = 1
        String text = PdfTextExtractor.getTextFromPage(reader, 1);
        reader.close();
        assertEquals("Word document", text.trim());
    }
}
