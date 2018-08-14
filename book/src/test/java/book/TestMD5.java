package book;

import org.apache.shiro.crypto.hash.Md5Hash;

public class TestMD5 {
	public static void main(String[] args) {
		String s = "123";
		Md5Hash md5 = new Md5Hash(s);
		String pwd = md5.toString();
		System.out.println(pwd);
	}

}
