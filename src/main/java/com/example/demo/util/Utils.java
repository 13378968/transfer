package com.example.demo.util;

import org.apache.commons.lang3.StringUtils;

public class Utils {
   private static String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzZa0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzZa0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzZa0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzZa";
   private static int scale = 256;
   
   public static String numToStr(long num, int length) {
	   StringBuilder builder = new StringBuilder();
	   
	   num = Math.abs(num);
	   
	   int remainder = 0;
	   
	   while (num > scale - 1) {
		   remainder = Long.valueOf(num % scale).intValue();
		   builder.append(chars.charAt(remainder));
		   
		   num = num / scale;
	   }
	   
	   builder.append(chars.charAt(Long.valueOf(num).intValue()));
	   String value = builder.reverse().toString();
	   return StringUtils.leftPad(value, length, '0');
   }
}
