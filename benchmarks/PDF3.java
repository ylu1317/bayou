package edu.rice.pliny.apitrans.examples;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PDF3 {
    String filename = "code_completion/src/test/resources/apitrans/pdf/created.pdf";
    String content = "foobar";

    @Test
    public void create_pdf() throws IOException, DocumentException {
        Document doc = new Document();
        FileOutputStream out = new FileOutputStream(this.filename);
        PdfWriter.getInstance(doc, out);

        doc.open();
        int font_size = 16;
        Font font = FontFactory.getFont(FontFactory.COURIER, font_size, BaseColor.BLACK);
        Chunk chunk = new Chunk(content, font);

        doc.add(chunk);
        doc.close();
    }

    @Test
    public void create_pdf2() throws IOException, DocumentException {
        int font_size = 12;

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.setFont(PDType1Font.COURIER, font_size);
        contentStream.beginText();
        contentStream.showText(this.content);
        contentStream.endText();
        contentStream.close();
        File f = new File(this.filename);
        document.save(f);
        document.close();
    }

}
