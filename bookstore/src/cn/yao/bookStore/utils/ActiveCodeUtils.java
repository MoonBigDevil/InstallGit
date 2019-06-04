package cn.yao.bookStore.utils;

import java.util.Random;


public class ActiveCodeUtils {
	public static String createActiveCode() {
		String code = null;
		Random random = new Random();
		
		code = String.valueOf(random.nextInt(10000));
		return code;
	}
}
		