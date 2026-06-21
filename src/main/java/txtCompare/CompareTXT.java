package txtCompare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompareTXT {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the path for the first file: ");
		String filePath1 = scanner.nextLine();

		System.out.print("Enter the path for the second file: ");
		String filePath2 = scanner.nextLine();

		try {
			List<String> file1Lines = readFile(filePath1);
			List<String> file2Lines = readFile(filePath2);

			compareFiles(file1Lines, filePath1, file2Lines, filePath2);

		} catch (IOException e) {
			System.err.println("An error occurred while reading the files: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}

	private static List<String> readFile(String filePath) throws IOException {
		List<String> lines = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		}
		return lines;
	}

	private static void compareFiles(List<String> file1Lines, String filePath1, List<String> file2Lines,
			String filePath2) {
		int maxLines = Math.max(file1Lines.size(), file2Lines.size());
		boolean differencesFound = false;

		// Extract file names without paths
		String fileName1 = extractFileName(filePath1);
		String fileName2 = extractFileName(filePath2);

		// Print table header
		System.out.printf("%-6s | %-20s | %-20s\n", "Line", fileName1, fileName2);
		System.out.println("------ | -------------------- | ---------------------");

		// Compare lines
		for (int i = 0; i < maxLines; i++) {
			String file1Line = (i < file1Lines.size()) ? file1Lines.get(i) : null;
			String file2Line = (i < file2Lines.size()) ? file2Lines.get(i) : null;

			if (file1Line != null && file2Line != null && !file1Line.equals(file2Line)) {
				differencesFound = true;
				System.out.printf("%-6d | %-20s | %-20s\n", i + 1, file1Line, file2Line);
			} else if (file1Line == null && file2Line != null) {
				differencesFound = true;
				System.out.printf("%-6d | %-20s | %-20s\n", i + 1, "", file2Line);
			} else if (file2Line == null && file1Line != null) {
				differencesFound = true;
				System.out.printf("%-6d | %-20s | %-20s\n", i + 1, file1Line, "");
			}
		}

		if (!differencesFound) {
			System.out.println("The files are identical.");
		} else {
			if (file1Lines.size() > file2Lines.size()) {
				System.out.printf("File %s has more lines than File %s.\n", fileName1, fileName2);
			} else if (file2Lines.size() > file1Lines.size()) {
				System.out.printf("File %s has more lines than File %s.\n", fileName2, fileName1);
			}
		}
	}

	private static String extractFileName(String filePath) {
		// Extract the file name for both Windows and UNIX-like systems
		int separatorIndex = Math.max(filePath.lastIndexOf("\\"), filePath.lastIndexOf("/"));
		return filePath.substring(separatorIndex + 1);
	}
}
