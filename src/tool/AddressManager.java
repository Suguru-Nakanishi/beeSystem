package tool;

public class AddressManager {
private String address;
private char spacer;

private void setAddress(String address) {this.address = address;}

private void setSpacer(char spacer) {this.spacer = spacer;}

public String getAddress() {return address;}

public char getSpacer() {return spacer;}

	public String addressFromat(String address, char spacer) {
		if (address == null || address.equals("")) return "";
		setAddress(address);
		setSpacer(spacer);
		address = address.replace("" + spacer, "").replace("" + (char) 0x20, "").replace("" + (char) 0x3000, "");
		String str = "";
		for (int i = 0; i < address.length(); i++) {
			int code = address.charAt(i), code1 = 0;
			if (i > 0) code1 = address.charAt(i - 1);
			if (code == 0x533a || code == 0x5e02 || code == 0x5e9c || code == 0x6751 || code == 0x753a || code == 0x770c || code == 0x9053 || code == 0x90fd || code == 0x20) str += ("" + (char) code + (char) spacer);
			else if ((code > 0x2f && code < 0x3a || code > 0xff0f && code < 0xff1a) && !(code1 > 0x2f && code1 < 0x3a || code1 > 0xff0f && code1 < 0xff1a)) str += ("" + (char) spacer + (char) code);
			else str += (char) code;
		}
		return str.replace("" + (char) spacer + (char) spacer, "" + (char) spacer).replace("  ", " ");
	}
}