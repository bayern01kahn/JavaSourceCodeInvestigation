package justin.interview.romaNumber;

import java.util.Scanner;

/**
 * 针对 java 笔试题  将输入数字转化为 罗马数字输出 调整版本
 * @author justin
 *
 */
public class ArabicNumberToRomaNumber {
	
	public static void main(String[] args) {
		while (true) {
			System.out.print("阿拉伯数：");
			
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			int number;
			try {
				number = Integer.parseInt(sc.next());
				if(number >= 1 && number <= 3999){
					System.out.print("罗马数字：" + arabicToRome(number) +"\n\n");
				}else{
					System.out.println("请确认输入的数字在 1-3999之间");
				}
				continue;
			} catch (NumberFormatException e) {
				System.out.println("请确认输入的都是的阿拉伯数字");
			}
		}
	}

	// I、V、X、 L、 C、  D、  M
    // 1．5、10、50、100、500、1000
	private static String arabicToRome(int num) {
		final String[][] s = { 
				{ "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },
				{ "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },
				{ "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" }, 
				{ "", "M", "MM", "MMM" } 
		};

		StringBuilder sbRoma = new StringBuilder();
		sbRoma.append(s[3][num / 1000 % 10]);
		sbRoma.append(s[2][num / 100 % 10 ]);
		sbRoma.append(s[1][num / 10 % 10]);
		sbRoma.append(s[0][num % 10]);

		return sbRoma.toString();
	}
	
}
