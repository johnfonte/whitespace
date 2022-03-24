package whitespace.service;

import org.apache.commons.lang3.StringUtils;
import whitespace.constants.FileProcessorPaths;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileProcessorService
{
	public static List<String> getFileValues(String filename, boolean isTestFile) {
		List<String> values = new ArrayList<>();
		String relativePath = ((isTestFile)? FileProcessorPaths.TEST_RESOURCES_RELATIVE_PATH : FileProcessorPaths.RESOURCES_RELATIVE_PATH);
		File file = new File(relativePath + filename);
		try (FileReader fr = new FileReader(file.getAbsolutePath());
			BufferedReader in = new BufferedReader(fr)) {

			String line;
			while ((line = in.readLine()) != null)
			{
				if (StringUtils.isBlank(line)) {
					continue;
				}
				values.add(line.trim());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return values;
	}
}
