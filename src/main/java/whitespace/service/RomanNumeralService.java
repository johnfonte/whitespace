package whitespace.service;

import whitespace.constants.MerchantsGuideInput;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RomanNumeralService
{
	Map<String, Integer> romanMap = new HashMap<>();
	Map<String, BigDecimal> metalMap = new HashMap<>();

	public RomanNumeralService() {
		romanMap.put("I", 1);
		romanMap.put("V", 5);
		romanMap.put("X", 10);
		romanMap.put("L", 50);
		romanMap.put("C", 100);
		romanMap.put("D", 500);
		romanMap.put("M", 1000);
	}

	public boolean containsRomanKey(String key) {
		return romanMap.containsKey(key);
	}

	public boolean containsMetalKey(String key) {
		return metalMap.containsKey(key);
	}

	public Integer getRomanValue(String numeral) {
		return romanMap.get(numeral);
	}

	public BigDecimal getMetalValue(String metal) {
		return metalMap.get(metal);
	}

	public void setMetalValue(String metal, BigDecimal value) {
		metalMap.put(metal, value);
	}

	public void replaceNumeral(String source, String target) {
		if (romanMap.containsKey(source)) {
			int value = romanMap.get(source);
			romanMap.put(target, value);
			romanMap.remove(source);
		}
	}

	private int outputCalculation(List<String> values) {
		int romanTotal = 0;
		BigDecimal metalValue = BigDecimal.valueOf(0);
		for (int i = 0; i<values.size(); i++) {
			String firstKey = values.get(i);
			if (containsRomanKey(firstKey))
			{
				int firstRoman = getRomanValue(firstKey);
				if (i + 1 < values.size()) {
					String secondKey = values.get(i + 1);
					if (containsRomanKey(secondKey)) {
						int secondRoman = getRomanValue(secondKey);
						if (firstRoman >= secondRoman) {
							romanTotal += firstRoman;
						} else {
							romanTotal += secondRoman - firstRoman;
							i++;
						}
					} else {
						romanTotal += firstRoman;
					}
				}
			} else if (containsMetalKey(firstKey)) {
				metalValue = getMetalValue(firstKey);
			}
		}
		return metalValue.multiply(BigDecimal.valueOf(romanTotal)).intValue();
	}

	private void inputCalculation(List<String> values) {
		int romanTotal = 0;
		int creditValue = 0;
		String metalKey = null;
		for (int i = 0; i<values.size(); i++) {
			String firstKey = values.get(i);
			if (containsRomanKey(firstKey))
			{
				int firstRoman = getRomanValue(firstKey);
				if (i + 1 < values.size()) {
					String secondKey = values.get(i + 1);
					if (containsRomanKey(secondKey)) {
						int secondRoman = getRomanValue(secondKey);
						if (firstRoman >= secondRoman) {
							romanTotal += firstRoman;
						} else {
							romanTotal += secondRoman - firstRoman;
							i++;
						}
					} else {
						romanTotal += firstRoman;
					}
				}
			} else if (metalKey == null) {
				metalKey = firstKey;
			} else if (creditValue == 0) {
				creditValue = Integer.parseInt(firstKey);
				BigDecimal metalValue = BigDecimal.valueOf(creditValue).setScale(2, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(romanTotal), RoundingMode.HALF_UP);
				setMetalValue(metalKey, metalValue);
			}
		}
	}

	public List<String> getInputValues(String line, String inputType) {
		line = line.replace(inputType, "").replace(MerchantsGuideInput.DEFINE_ROMAN, "");
		return Arrays.stream(line.split(" ")).map(String::trim).collect(Collectors.toList());
	}

	public void processMerchantsGuideFromFile(String filename, boolean isTestFile)
	{
		List<String> lines = FileProcessorService.getFileValues(filename, isTestFile);
		List<String> values;
		for (String line : lines)
		{
			if (line.contains(MerchantsGuideInput.QUESTION_ROMAN_METAL)) {
				values = getInputValues(line, MerchantsGuideInput.QUESTION_ROMAN_METAL);
				int total = outputCalculation(values);
				System.out.println(total);
			} else if (line.contains(MerchantsGuideInput.QUESTION_ROMAN)) {
				values = getInputValues(line, MerchantsGuideInput.QUESTION_ROMAN);
			} else if (line.contains(MerchantsGuideInput.DEFINE_METAL)) {
				values = getInputValues(line, MerchantsGuideInput.DEFINE_METAL);
				inputCalculation(values);
			} else if (line.contains(MerchantsGuideInput.DEFINE_ROMAN)) {
				values = getInputValues(line, MerchantsGuideInput.DEFINE_ROMAN);
				if (values.size() != 2) {
					continue;
				}
				String target = values.get(0);
				String source = values.get(1);
				replaceNumeral(source, target);
			} else {
				System.out.println("I have no idea what you are talking about");
			}
		}
	}

}