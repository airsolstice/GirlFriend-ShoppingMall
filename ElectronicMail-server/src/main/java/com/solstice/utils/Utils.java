package com.solstice.utils;

import java.security.MessageDigest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.solstice.bean.User;

public class Utils {

	/**
	 * 自定义表单验证
	 * 
	 * @param user
	 * @return 错误map
	 */
	public static Map<String, String> verifyInput(User user) {

		Map<String, String> errors = new HashMap<String, String>();
		// 帐号的判断：可以是手机号，或者邮箱
		if (Utils.isEmpty(user.getId())) {
			errors.put("id", "用户名不能为null");
		} else if (!Utils.isEmail(user.getId())) {
			errors.put("id", "id只能为邮箱");
		}

		// 密码的判断
		if (Utils.isEmpty(user.getPwd())) {
			errors.put("pwd", "密码不能为null");
		} else if (user.getPwd().trim().length() > 18
				|| user.getPwd().trim().length() < 6) {
			errors.put("pwd", "密码长度必须介于6~18之间");
		}
		// 邮箱的判断
		if (Utils.isEmpty(user.getEmail())) {
			errors.put("email", "邮箱不能为null");
		// 邮箱格式为***@**.***
		} else if (!Utils.isEmail(user.getEmail().trim())) {
			errors.put("email", "邮箱格式不正确");
		}
		// 手机号的判断
		if (Utils.isEmpty(user.getPhone())) {
			errors.put("phone", "手机号码不能为null");
		} else if (!Utils.isPhone(user.getPhone().trim())) {
			errors.put("phone", "手机号码格式不正确");
		}

		return errors;
	}

	/**
	 * JSR-303 Hibernate-Validation表单验证
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, String> validateModel(Object obj) {

		Map<String, String> errors = new HashMap<>();

		Validator validator = Validation.buildDefaultValidatorFactory()
				.getValidator();

		// 验证某个对象,，其实也可以只验证其中的某一个属性的
		Set<ConstraintViolation<Object>> violations = validator.validate(obj);

		Iterator<ConstraintViolation<Object>> iterator = violations.iterator();
		while (iterator.hasNext()) {
			ConstraintViolation<Object> violation = iterator.next();
			errors.put(violation.getPropertyPath().toString(),
					violation.getMessage());
		}
		
		return errors;
	}

	/**
	 * MD5加密
	 * 
	 * @param 加密String
	 * @return 加密后的String
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str).toLowerCase();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		return (!(isEmpty(obj)));
	}

	/*
	 * 判断是否为null
	 */
	public static boolean isEmpty(Object o) {
		if (o == null)
			return true;
		if (o instanceof String) {
			if (((String) o).trim().length() == 0) {
				return true;
			}
		} else if (o instanceof Collection) {
			if (((Collection<?>) o).isEmpty()) {
				return true;
			}
		} else if (o.getClass().isArray()) {
			if (((Object[]) (Object[]) o).length == 0) {
				return true;
			}
		} else if (o instanceof Map) {
			if (((Map<?, ?>) o).isEmpty()) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	// 判断邮箱格式
	public static boolean isEmail(String email) {
		return email.matches("\\w+@\\w+\\.\\w+");
	}

	public static boolean isPhone(String phone) {
		return phone.matches("^[1][3458][0-9]{9}$");
	}
}
