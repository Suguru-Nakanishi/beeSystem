package tool;

public class NumberManager {
	private int inputNo = 0, outputNo = 0, digits = 0;
	private long inputLong = 0, outputLong = 0;
	private String inStrNo = "", outStrNo = "";
	private char packCharacter = '0';

	public NumberManager(int inputNo) {
		setInputNo(inputNo);
		setInStrNo("" + inputNo);
	}

	public NumberManager(String inStrNo) {
		setInStrNo(inStrNo);
		setInputNo(toInt());
	}

	public NumberManager(int inputNo, int digits) {
		setInputNo(inputNo);
		setInStrNo("" + inputNo);
		setDigits(digits);
	}

	public NumberManager(String inStrNo, int digits) {
		setInStrNo(inStrNo);
		setInputNo(toInt());
		setDigits(digits);
	}

	public NumberManager(int inputNo, int digits, char packCharacter) {
		setInputNo(inputNo);
		setInStrNo("" + inputNo);
		setDigits(digits);
		setPackCharacter(packCharacter);
	}

	public NumberManager(String inStrNo, int digits, char packCharacter) {
		setInStrNo(inStrNo);
		setInputNo(toInt());
		setDigits(digits);
		setPackCharacter(packCharacter);
	}

	private void setInputNo(int inputNo) {this.inputNo = inputNo;}

	private void setOutputNo(int outputNo) {this.outputNo = outputNo;}

	private void setDigits(int digits) {this.digits = digits;}

	private void setInputLong(long inputLong) {this.inputLong = inputLong;}

	private void setOutputLong(long outputLong) {this.outputLong = outputLong;}

	private void setInStrNo(String inStrNo) {this.inStrNo = inStrNo;}

	private void setOutStrNo(String outStrNo) {this.outStrNo = outStrNo;}

	private void setPackCharacter(char packCharacter) {this.packCharacter = packCharacter;}

	public int getInputNo() {return inputNo;}

	public int getOutputNo() {return outputNo;}

	public int getDigits() {return digits;}

	public long getInputLong() {return inputLong;}

	public long getOutputLong() {return outputLong;}

	public String getInStrNo() {return inStrNo;}

	public String getOutStrNo() {return outStrNo;}

	public char getPackCharacter() {return packCharacter;}

	public int toInt() {
		outputNo = 0;
		for (int i = 0; i < inStrNo.length(); i++) {
			if (inStrNo.charAt(i) > 0x2f && inStrNo.charAt(i) < 0x3a) {
				outputNo *= 10;
				outputNo += inStrNo.charAt(i) - 0x30;
			}
		}
		return getOutputNo();
	}

	public long toLong() {
		outputLong = 0;
		for (int i = 0; i < inStrNo.length(); i++) {
			if (inStrNo.charAt(i) > 0x2f && inStrNo.charAt(i) < 0x3a) {
				outputLong *= 10;
				outputLong += inStrNo.charAt(i) - 0x30;
			}
		}
		return getOutputLong();
	}

	public String upper() {
		outStrNo = "";
		for (int i = 0; i < inStrNo.length(); i++) outStrNo += (inStrNo.charAt(i) > 0x60 && inStrNo.charAt(i) < 0x7b) ? (char) (inStrNo.charAt(i) - 0x20) : inStrNo.charAt(i);
		return outStrNo;
	}

	public String lower() {
		outStrNo = "";
		for (int i = 0; i < inStrNo.length(); i++) outStrNo += (inStrNo.charAt(i) > 0x40 && inStrNo.charAt(i) < 0x5b) ? (char) (inStrNo.charAt(i) + 0x20) : inStrNo.charAt(i);
		return outStrNo;
	}

	public String toDoubleByte() {
		outStrNo = "";
		for (int i = 0; i < inStrNo.length(); i++) outStrNo += (inStrNo.charAt(i) > 0x21 && inStrNo.charAt(i) < 0x7f) ? (char) (inStrNo.charAt(i) + 0xfee0) : inStrNo.charAt(i);
		return outStrNo;
	}

	public String toByteCode() {
		outStrNo = "";
		for (int i = 0; i < inStrNo.length(); i++) outStrNo += (inStrNo.charAt(i) > 0xff01 && inStrNo.charAt(i) < 0xff5f) ? (char) (inStrNo.charAt(i) - 0xfee0) : inStrNo.charAt(i);
		return outStrNo;
	}

	public String padding() {
		outStrNo = "";
		for (int i = 0; i < digits - inStrNo.length(); i++) outStrNo += packCharacter;
		return outStrNo + inStrNo;
	}

	public String prefixNo() {
		boolean flag = true;
		while (flag) {
			flag = inputNo > 25;
			outStrNo = (char) (inputNo % 26 + 0x41) + outStrNo;
			inputNo /= 26;
		}
		return outStrNo;
	}

	public int prefixDecode() {
		outputNo = 0;
		for (int i = 0; i < inStrNo.length(); i++) {
			if (inStrNo.charAt(i) > 0x41 && inStrNo.charAt(i) < 0x5b) {
				outputNo *= 26;
				outputNo += inStrNo.charAt(i) - 0x41;
			}
		}
		return outputNo;
	}

	public String user_id() {
		setDigits(6);
		setInputNo(getInputNo() * 5);
		setInStrNo("" + getInputNo());
		setPackCharacter('0');
		setOutStrNo(padding());
		return outStrNo;
	}

	public int decode_user() {
		setOutputNo(getInputNo() / 5);
		return outputNo;
	}

	public String product_no() {
		setDigits(10);
		setInputLong(getInputNo());
		setInputLong(getInputLong() * 8 + 92);
		setInStrNo("" + getInputLong());
		setPackCharacter('0');
		setOutStrNo(padding());
		return outStrNo;
	}

	public String decode_long() {
		setOutStrNo("" + (toLong() - 92) / 8);
		return outStrNo;
	}

	public String customer_no() {
		setDigits(4);
		setInStrNo("" + (getInputNo() % 100) * 100);
		setInputNo(getInputNo() / 100);
		setPackCharacter('0');
		setOutStrNo(padding());
		return prefixNo();
	}

	public int decode_customer() {
		setOutputNo(prefixDecode() * 100 + toInt() / 100);
		return getOutputNo();
	}

	public String customer_no2() {
		setDigits(4);
		setInStrNo("" + ((getInputNo() - 1) % 99 + 1) * 100);
		setInputNo((getInputNo() - 1) / 99);
		setPackCharacter('0');
		setOutStrNo(padding());
		return prefixNo();
	}

	public int decode_customer2() {
		setOutputNo(prefixDecode() * 99 + toInt() / 100);
		return getOutputNo();
	}

	public String supplier_no() {
		setDigits(6);
		setInputNo(getInputNo() * 10 + 90);
		setInStrNo("" + getInputNo());
		setPackCharacter('0');
		setOutStrNo(padding());
		return outStrNo;
	}

	public int decode_supplier() {
		setOutputNo((getInputNo() - 90) / 10);
		return outputNo;
	}

	public String en_ex_id() {
		setDigits(8);
		setInputNo(getInputNo() * 4 + 6);
		setInStrNo("" + getInputNo());
		setPackCharacter('0');
		setOutStrNo(padding());
		return outStrNo;
	}

	public int decode_en_ex() {
		setOutputNo((getInputNo() - 6) / 4);
		return outputNo;
	}

	public int passwordCheck() {
		outputNo = 0;
		if (inStrNo.length() > 7) {
			for (int i = 0; i < inStrNo.length(); i++) {
				if (outputNo % 2 != 1 && (inStrNo.charAt(i) > 0x2f && inStrNo.charAt(i) < 0x3a)) outputNo++;
				else if ((outputNo / 2) % 2 != 1 && (inStrNo.charAt(i) > 0x40 && inStrNo.charAt(i) < 0x5b)) outputNo += 2;
				else if ((outputNo / 4) % 2 != 1 && (inStrNo.charAt(i) > 0x60 && inStrNo.charAt(i) < 0x7b)) outputNo += 4;
			}
		}
		return outputNo;
	}
}