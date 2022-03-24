package whitespace.runner;

import whitespace.service.GraphOperationsService;
import whitespace.service.RomanNumeralService;

/**
 PROBLEM THREE: MERCHANT'S GUIDE TO THE GALAXY
 <p>
 You decided to give up on earth after the latest financial collapse left 99.99% of the
 earth's population with 0.01% of the wealth. Luckily, with the scant sum of money
 that is left in your account, you are able to afford to rent a spaceship, leave earth, and
 fly all over the galaxy to sell common metals and dirt (which apparently is worth a
 lot).
 <p>
 Buying and selling over the galaxy requires you to convert numbers and units, and
 you decided to write a program to help you.
 <p>
 The numbers used for intergalactic transactions follow a similar convention to the
 roman numerals and you have painstakingly collected the appropriate translation
 between them.
 <p>
 Roman numerals are based on seven symbols:
 <p>
 Symbol Value
 <p>
 [2]I
 <p>
 [3]1
 <p>
 [4]V
 <p>
 [5]5
 <p>
 [6]X
 <p>
 [7]10
 <p>
 [8]L
 <p>
 [9]50
 <p>
 [10]C
 <p>
 [11]100
 <p>
 [12]D
 <p>
 [13]500
 <p>
 [14]M
 <p>
 [15]1,000
 <p>
 Numbers are formed by combining symbols together and adding the values. For
 example, MMVI is 1000 + 1000 + 5 + 1 = 2006. Generally, symbols are placed in order of
 value, starting with the largest values. When smaller values precede larger values,
 the smaller values are subtracted from the larger values, and the result is added to
 the total. For example MCMXLIV = 1000 + (1000 − 100) + (50 − 10) + (5 − 1) = 1944.
 <p>
 The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no
 more. (They may appear four times if the third and fourth are separated by a smaller
 value, such as XXXIX.) "D", "L", and "V" can never be repeated.
 <p>
 "I" can be subtracted from "V" and "X" only. "X" can be subtracted from "L" and "C"
 only. "C" can be subtracted from "D" and "M" only. "V", "L", and "D" can never be
 subtracted.
 <p>
 Only one small-value symbol may be subtracted from any large-value symbol.
 <p>
 A number written in [16]Arabic numerals can be broken into digits. For example, 1903
 is composed of 1, 9, 0, and 3. To write the Roman numeral, each of the non-zero digits
 should be treated separately. In the above example, 1,000 = M, 900 = CM, and 3 = III.
 <p>
 Therefore, 1903 = MCMIII.
 <p>
 (Source: Wikipedia ( [17]http://en.wikipedia.org/wiki/Roman_numerals)
 <p>
 Input to your program consists of lines of text detailing your notes on the conversion
 between intergalactic units and roman numerals.
 <p>
 You are expected to handle invalid queries appropriately.
 <p>
 Test input:
 <p>
 glob is I
 <p>
 prok is V
 <p>
 pish is X
 <p>
 tegj is L
 <p>
 glob glob Silver is 34 Credits
 <p>
 glob prok Gold is 57800 Credits
 <p>
 pish pish Iron is 3910 Credits
 <p>
 how much is pish tegj glob glob ?
 <p>
 how many Credits is glob prok Silver ?
 <p>
 how many Credits is glob prok Gold ?
 <p>
 how many Credits is glob prok Iron ?
 <p>
 how much wood could a woodchuck chuck if a woodchuck could chuck wood
 ?
 <p>
 Test Output:
 <p>
 pish tegj glob glob is 42
 <p>
 glob prok Silver is 68 Credits
 <p>
 glob prok Gold is 57800 Credits
 <p>
 glob prok Iron is 782 Credits
 <p>
 I have no idea what you are talking about
 */
public class MerchantsGuideRunner
{
	public static void main(String[] args) {
		if (args == null || args.length != 1) {
			return;
		}
		String filename = args[0];

		RomanNumeralService romanNumeralService = new RomanNumeralService();
		romanNumeralService.processMerchantsGuideFromFile("testMerchantsGuide.txt", false);
	}
}
