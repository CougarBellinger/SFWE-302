package com.mycompany.app;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) throws DocumentException, FileNotFoundException {
		Document document = new Document();

		PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));

		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk("Hello World", font);

		document.add(chunk);
		document.close();
	}
}
