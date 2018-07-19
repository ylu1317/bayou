package edu.rice.pliny.apitrans.examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Body;
import org.junit.Test;
import org.xml.sax.ContentHandler;

import static org.junit.Assert.assertEquals;


public class Word {
    String filename = "code_completion/src/test/resources/apitrans/word/foo.docx";

    @Test
    public void read_text() throws ParseException, IOException {
        FileInputStream stream = new FileInputStream(this.filename);
        XWPFDocument docx = new XWPFDocument(stream);

        //using XWPFWordExtractor Class
        XWPFWordExtractor we = new XWPFWordExtractor(docx);
        String text = we.getText();
        assertEquals("Word document", text.trim());
    }

    /*
    @Test
    public void read_text2() throws Exception {
        File f = new File(this.filename);
        TikaConfig tika = TikaConfig.getDefaultConfig();
        TikaInputStream stream = TikaInputStream.get(f);
        ContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        Parser parser = tika.getParser();
        ParseContext context = new ParseContext();
        parser.parse(stream, handler, metadata, context);
        String text = handler.toString();
        assertEquals("Word document", text.trim());
    }
    */

    @Test
    public void read_text3() throws Exception {
        File f = new File(this.filename);
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(f);
        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
        org.docx4j.wml.Document wmlDocumentEl = documentPart.getJaxbElement();
        Body body = wmlDocumentEl.getBody();
        List<Object> content_list = body.getContent();
        Object content = content_list.get(0);
        String text = content.toString();
        assertEquals("Word document", text.trim());
    }
}
