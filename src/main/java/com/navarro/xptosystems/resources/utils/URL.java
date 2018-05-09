package com.navarro.xptosystems.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static String decodeParam(String s, String tipoCase) {
		try {
			if (tipoCase == "U") {
				return URLDecoder.decode(s, "UTF-8").toUpperCase();
			} else if (tipoCase == "L") {
				return URLDecoder.decode(s, "UTF-8").toLowerCase();
			} else {
				return URLDecoder.decode(s, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static List<Integer> decodeIntList(String s) {
		return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
}
