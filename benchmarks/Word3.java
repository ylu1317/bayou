package edu.rice.pliny.apitrans.examples;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Body;
import org.docx4j.wml.Document;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.Text;
import org.docx4j.wml.R;
import org.junit.Test;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class Word3 {
    String filename = "code_completion/src/test/resources/apitrans/word/created.docx";
    String content = "foobar";


    @Test
    public void create_doc() throws ParseException, IOException, Docx4JException {
        File f = new File(filename);
        WordprocessingMLPackage word_package = new WordprocessingMLPackage();
        MainDocumentPart mainDocumentPart = new MainDocumentPart();

        ObjectFactory factory = Context.getWmlObjectFactory();
        Body body = factory.createBody();
        Document doc = factory.createDocument();

        // add a new paragraph
        P paragraph = factory.createP();
        Text text = factory.createText();
        text.setValue(content);

        R run = factory.createR();
        List run_content = run.getContent();
        run_content.add(text);
        List para_content = paragraph.getContent();
        para_content.add(run);

        doc.setBody(body);
        mainDocumentPart.setJaxbElement(doc);
        List doc_content = mainDocumentPart.getContent();
        doc_content.add(0, paragraph);
        word_package.addTargetPart(mainDocumentPart);
        word_package.save(f);
    }

    @Test
    public void create_doc2() throws ParseException, IOException, Docx4JException {
        XWPFDocument document = new XWPFDocument();
        File f = new File(this.filename);
        FileOutputStream out = new FileOutputStream(f);
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(this.content);
        document.write(out);
        out.close();
    }
}
