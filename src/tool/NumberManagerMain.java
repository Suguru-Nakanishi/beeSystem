package tool;

public class NumberManagerMain {
	public static void main(String[] args) {
		NumberManager numMana;
//		numMana = new NumberManager(" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~！＂＃＄％＆＇（）＊＋，－．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［＼］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝～");
//		System.out.println(numMana.toInt());
//		numMana = new NumberManager(" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~！＂＃＄％＆＇（）＊＋，－．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［＼］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝～");
//		System.out.println(numMana.upper());
//		numMana = new NumberManager(" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~！＂＃＄％＆＇（）＊＋，－．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［＼］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝～");
//		System.out.println(numMana.lower());
//		numMana = new NumberManager(" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~！＂＃＄％＆＇（）＊＋，－．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［＼］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝～");
//		System.out.println(numMana.toDoubleByte());
//		numMana = new NumberManager(" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~！＂＃＄％＆＇（）＊＋，－．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［＼］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝～");
//		System.out.println(numMana.toByteCode());
//		for (int i = 0; i < 100; i++) {
////			numMana = new NumberManager(i, 5);
////			System.out.println(numMana.padding());
////		}
////		for (int i = 0; i < 1000; i += 10) {
////			numMana = new NumberManager(i, 5, '*');
////			System.out.println(numMana.padding());
//		}
//		for (int i = 0; i < 100; i++) {
//			numMana = new NumberManager(i);
//			System.out.println(i + " ⇒ " + numMana.prefixNo());
//		}
//		for (int i = 0x41; i < 0x5b; i++) {
//			for (int j = 0x41; j < 0x5b; j++) {
//				numMana = new NumberManager((char) i + "" + (char) j);
//				System.out.println(((char) i + "" + (char) j) + " ⇒ " + numMana.prefixDecode());
//			}
//		}
//		System.out.println("\n＝＝＝ user_id()動作確認 ＝＝＝");
//		for (int i = 1; i < 100; i++) {
//			numMana = new NumberManager(i, 6, ' ');
//			System.out.println(numMana.padding() + " ⇒ " + numMana.user_id());
//		}
//		System.out.println("       ・\n       ・\n       ・");
//			for (int i = 199900; i < 200000; i++) {
//			numMana = new NumberManager(i, 6, ' ');
//			System.out.println(numMana.padding() + " ⇒ " + numMana.user_id());
//				}
//		System.out.println("\n＝＝＝ decode_user()動作確認 ＝＝＝");
//		for (int i = 1; i < 100; i++) {
//			numMana = new NumberManager((i < 2 ? "00000" : i < 20 ? "0000" : i < 200 ? "000" : i < 2000 ? "00" : i < 20000 ? "0" : "") + i * 5);
//			System.out.println((i < 2 ? "00000" : i < 20 ? "0000" : i < 200 ? "000" : i < 2000 ? "00" : i < 20000 ? "0" : "") + i * 5 + " ⇒ " + (numMana.decode_user() < 10 ? "     " : numMana.decode_user() < 100 ? "    " : numMana.decode_user() < 1000 ? "   " : numMana.decode_user() < 10000 ? "  " : numMana.decode_user() < 100000 ? " " : "") + numMana.decode_user());
//		}
//		System.out.println("       ・\n       ・\n       ・");
//		for (int i = 199900; i < 200000; i++) {
//			numMana = new NumberManager((i < 2 ? "00000" : i < 20 ? "0000" : i < 200 ? "000" : i < 2000 ? "00" : i < 20000 ? "0" : "") + i * 5);
//			System.out.println((i < 2 ? "00000" : i < 20 ? "0000" : i < 200 ? "000" : i < 2000 ? "00" : i < 20000 ? "0" : "") + i * 5 + " ⇒ " + (numMana.decode_user() < 10 ? "     " : numMana.decode_user() < 100 ? "    " : numMana.decode_user() < 1000 ? "   " : numMana.decode_user() < 10000 ? "  " : numMana.decode_user() < 100000 ? " " : "") + numMana.decode_user());
//		}
//		System.out.println("\n＝＝＝ product_no()動作確認 ＝＝＝");
//		for (int i = 1; i < 100; i++) {
//			numMana = new NumberManager(i, 10, ' ');
//			System.out.println(numMana.padding() + " ⇒ " + numMana.product_no());
//		}
//		System.out.println("           ・\n           ・\n           ・");
//		for (int i = 1249999889; i < 1249999989; i++) {
//			numMana = new NumberManager(i, 10, ' ');
//			System.out.println(numMana.padding() + " ⇒ " + numMana.product_no());
//		}
//		System.out.println("\n＝＝＝ decode_long()動作確認 ＝＝＝");
//		for (int i = 1; i < 100; i++) {
//			numMana = new NumberManager("" + (i * 8 + 92), 10);
//			System.out.println(numMana.padding() + " ⇒ " + (numMana.decode_long().length() < 2 ? "         " : numMana.decode_long().length() < 3 ? "        " : numMana.decode_long().length() < 4 ? "       " : numMana.decode_long().length() < 5 ? "      " : numMana.decode_long().length() < 6 ? "     " : numMana.decode_long().length() < 7 ? "    " : numMana.decode_long().length() < 8 ? "   " : numMana.decode_long().length() < 9 ? "  " : numMana.decode_long().length() < 10 ? " " : "") + numMana.decode_long());
//		}
//		System.out.println("           ・\n           ・\n           ・");
//		for (long i = 1249999889; i < 1249999989; i++) {
//			numMana = new NumberManager("" + (i * 8 + 92), 10);
//			System.out.println(numMana.padding() + " ⇒ " + (numMana.decode_long().length() < 2 ? "         " : numMana.decode_long().length() < 3 ? "        " : numMana.decode_long().length() < 4 ? "       " : numMana.decode_long().length() < 5 ? "      " : numMana.decode_long().length() < 6 ? "     " : numMana.decode_long().length() < 7 ? "    " : numMana.decode_long().length() < 8 ? "   " : numMana.decode_long().length() < 9 ? "  " : numMana.decode_long().length() < 10 ? " " : "") + numMana.decode_long());
//		}
//		System.out.println("\n＝＝＝ customer_no()動作確認 ＝＝＝");
//		for (int i = 1; i < 200; i++) {
//			numMana = new NumberManager(i, 4, ' ');
//			System.out.println(numMana.padding() + " ⇒ " + numMana.customer_no());
//		}
//		System.out.println("     ・\n     ・\n     ・");
//		for (int i = 2400; i < 2600; i++) {
//			numMana = new NumberManager(i, 4, ' ');
//			System.out.println(numMana.padding() + " ⇒ " + numMana.customer_no());
//		}
//		System.out.println("\n＝＝＝ decode_customer()動作確認 ＝＝＝");
//		for (int i = 1; i < 200; i++) {
//			numMana = new NumberManager((char) (i / 100 + 0x41) + (i < 10 ? "0" : "") + (i % 100 * 100));
//			System.out.println((char) (i / 100 + 0x41) + (i % 100 < 1 ? "000" : i % 100 < 10 ? "0" : "") + (i % 100 * 100) + " ⇒ " + (numMana.decode_customer() < 10 ? "   " : numMana.decode_customer() < 100 ? "  " : numMana.decode_customer() < 1000 ? " " : "") + numMana.decode_customer());
//		}
//		System.out.println("      ・\n      ・\n      ・");
//		for (int i = 2400; i < 2600; i++) {
//			numMana = new NumberManager((char) (i / 100 + 0x41) + (i < 10 ? "0" : "") + (i % 100 * 100));
//			System.out.println((char) (i / 100 + 0x41) + (i % 100 < 1 ? "000" : i % 100 < 10 ? "0" : "") + (i % 100 * 100) + " ⇒ " + (numMana.decode_customer() < 10 ? "   " : numMana.decode_customer() < 100 ? "  " : numMana.decode_customer() < 1000 ? " " : "") + numMana.decode_customer());
//		}
//		System.out.println("\n＝＝＝ customer_no2()動作確認 ＝＝＝");
//		for (int i = 1; i < 200; i++) {
//			numMana = new NumberManager(i, 4, ' ');
//			System.out.println(numMana.padding() + " ⇒ " + numMana.customer_no2());
//		}
//		System.out.println("     ・\n     ・\n     ・");
//		for (int i = 2400; i < 2575; i++) {
//			numMana = new NumberManager(i, 4, ' ');
//			System.out.println(numMana.padding() + " ⇒ " + numMana.customer_no2());
//		}
//		System.out.println("\n＝＝＝ decode_customer2()動作確認 ＝＝＝");
//		for (int i = 1; i < 200; i++) {
//			numMana = new NumberManager((char) ((i - 1) / 99 + 0x41) + (((i - 1) % 99 + 1) < 10 ? "0" : "") + (((i - 1) % 99 + 1) * 100));
//			System.out.println((char) ((i - 1) / 99 + 0x41) + (((i - 1) % 99 + 1) < 10 ? "0" : "") + (((i - 1) % 99 + 1) * 100) + " ⇒ " + (numMana.decode_customer2() < 10 ? "   " : numMana.decode_customer2() < 100 ? "  " : numMana.decode_customer2() < 1000 ? " " : "") + numMana.decode_customer2());
//		}
//		System.out.println("      ・\n      ・\n      ・");
//		for (int i = 2400; i < 2575; i++) {
//			numMana = new NumberManager((char) ((i - 1) / 99 + 0x41) + (((i - 1) % 99 + 1) < 10 ? "0" : "") + (((i - 1) % 99 + 1) * 100));
//			System.out.println((char) ((i - 1) / 99 + 0x41) + (((i - 1) % 99 + 1) < 10 ? "0" : "") + (((i - 1) % 99 + 1) * 100) + " ⇒ " + (numMana.decode_customer2() < 10 ? "   " : numMana.decode_customer2() < 100 ? "  " : numMana.decode_customer2() < 1000 ? " " : "") + numMana.decode_customer2());
//		}
//		System.out.println("\n＝＝＝ supplier_no()動作確認 ＝＝＝");
//		for (int i = 1; i < 100; i++) {
//			numMana = new NumberManager(i, 5, ' ');
//			System.out.println(numMana.padding() + " ⇒ " + numMana.supplier_no());
//		}
//		System.out.println("      ・\n      ・\n      ・");
//		for (int i = 99891; i < 99991; i++) {
//			numMana = new NumberManager(i, 5, ' ');
//			System.out.println(numMana.padding() + " ⇒ " + numMana.supplier_no());
//		}
//		System.out.println("\n＝＝＝ decode_supplier()動作確認 ＝＝＝");
//		for (int i = 1; i < 100; i++) {
//			numMana = new NumberManager(i * 10 + 90, 6);
//			System.out.println(numMana.padding() + " ⇒ " + (numMana.decode_supplier() < 10 ? "    " : numMana.decode_supplier() < 100 ? "   " : numMana.decode_supplier() < 1000 ? "  " : numMana.decode_supplier() < 10000 ? " " : "") + numMana.decode_supplier());
//		}
//		System.out.println("       ・\n       ・\n       ・");
//		for (int i = 99891; i < 99991; i++) {
//			numMana = new NumberManager(i * 10 + 90, 6);
//			System.out.println(numMana.padding() + " ⇒ " + (numMana.decode_supplier() < 10 ? "    " : numMana.decode_supplier() < 100 ? "   " : numMana.decode_supplier() < 1000 ? "  " : numMana.decode_supplier() < 10000 ? " " : "") + numMana.decode_supplier());
//		}
//		System.out.println("\n＝＝＝ en_ex_id()動作確認 ＝＝＝");
//		for (int i = 1; i < 100; i++) {
//			numMana = new NumberManager(i, 8, ' ');
//			System.out.println(numMana.padding() + " ⇒ " + numMana.en_ex_id());
//		}
//		System.out.println("      ・\n      ・\n      ・");
//		for (int i = 24999898; i < 24999999; i++) {
//			numMana = new NumberManager(i, 8, ' ');
//			System.out.println(numMana.padding() + " ⇒ " + numMana.en_ex_id());
//		}
//		System.out.println("\n＝＝＝ decode_en_ex()動作確認 ＝＝＝");
//		for (int i = 1; i < 100; i++) {
//			numMana = new NumberManager(i * 4 + 6, 8);
//			System.out.println(numMana.padding() + " ⇒ " + (numMana.decode_en_ex() < 10 ? "       " : numMana.decode_en_ex() < 100 ? "      " : numMana.decode_en_ex() < 1000 ? "     " : numMana.decode_en_ex() < 10000 ? "    " : numMana.decode_en_ex() < 100000 ? "   " : numMana.decode_en_ex() < 1000000 ? "  " : numMana.decode_en_ex() < 10000000 ? " " : "") + numMana.decode_en_ex());
//		}
//		System.out.println("         ・\n         ・\n         ・");
//		for (int i = 24999898; i < 24999999; i++) {
//			numMana = new NumberManager(i * 4 + 6, 8);
//			System.out.println(numMana.padding() + " ⇒ " + (numMana.decode_en_ex() < 10 ? "       " : numMana.decode_en_ex() < 100 ? "      " : numMana.decode_en_ex() < 1000 ? "     " : numMana.decode_en_ex() < 10000 ? "    " : numMana.decode_en_ex() < 100000 ? "   " : numMana.decode_en_ex() < 1000000 ? "  " : numMana.decode_en_ex() < 10000000 ? " " : "") + numMana.decode_en_ex());
//		}
//		System.out.println("\n＝＝＝ passwordCheck()動作確認 ＝＝＝");
//		String password = "de04O";
//		numMana = new NumberManager(password);
//		System.out.println(password + " ⇒ " + numMana.passwordCheck() + ":" + (numMana.passwordCheck() == 7 ? "OK" : "NG"));
//		password = "0123654789";
//		numMana = new NumberManager(password);
//		System.out.println(password + " ⇒ " + numMana.passwordCheck() + ":" + (numMana.passwordCheck() == 7 ? "OK" : "NG"));
//		password = "ABCDEFGH";
//		numMana = new NumberManager(password);
//		System.out.println(password + " ⇒ " + numMana.passwordCheck() + ":" + (numMana.passwordCheck() == 7 ? "OK" : "NG"));
//		password = "abcdefghi";
//		numMana = new NumberManager(password);
//		System.out.println(password + " ⇒ " + numMana.passwordCheck() + ":" + (numMana.passwordCheck() == 7 ? "OK" : "NG"));
//		password = "ABC12DEFGHIJ";
//		numMana = new NumberManager(password);
//		System.out.println(password + " ⇒ " + numMana.passwordCheck() + ":" + (numMana.passwordCheck() == 7 ? "OK" : "NG"));
//		password = "ABCDEFgrGHIJ";
//		numMana = new NumberManager(password);
//		System.out.println(password + " ⇒ " + numMana.passwordCheck() + ":" + (numMana.passwordCheck() == 7 ? "OK" : "NG"));
//		password = "fesi52454l";
//		numMana = new NumberManager(password);
//		System.out.println(password + " ⇒ " + numMana.passwordCheck() + ":" + (numMana.passwordCheck() == 7 ? "OK" : "NG"));
//		password = "D0pFE8kelf";
//		numMana = new NumberManager(password);
//		System.out.println(password + " ⇒ " + numMana.passwordCheck() + ":" + (numMana.passwordCheck() == 7 ? "OK" : "NG"));
	}
}