package com.mycompany.app;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.text.DocumentException;

public class App {
	public static void main(String[] args) throws FileNotFoundException, IOException, DocumentException {
		System.out.printf("Entered:\n1: \"%s\"\n2: \"%s\n", args[0], args[1]);
		if (args[0].equalsIgnoreCase("PDF")) {
			FileOut.PDF(args[1]);
		}

		else if (args[0].equalsIgnoreCase("XLS")) {
			FileOut.XLS(args[1]);
		}

		else {
			System.out.printf("Invalid parameters\nExpected parameters: <PDF|XLS> <file name>\n");
		}
	}
}
