package com.droberts.rest.webservices.restfulwebservices;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoderTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		
		for (int i = 0; i < 10; i++) {
			String encodedPass = encoder.encode("password");
			System.out.println(encodedPass);
		}

		
		
	}

}
