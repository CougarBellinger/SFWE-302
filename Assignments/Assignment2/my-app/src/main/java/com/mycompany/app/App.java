package com.mycompany.app;

import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

public class App {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		if (args[0] == "PDF") {
			FileOut.PDF(args[1]);
		}

		else if (args[0] == "XLS") {
			FileOut.XLS(args[1]);
		}

		else {
			System.out.printf("Invalid parameters\nExpected parameters: <PDF|XLS> <file name>\n");
		}
	}
}
