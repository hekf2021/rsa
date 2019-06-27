package com.mytt.rsacode.rsa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Base Data Structure.
 * @author carver.gu
 * @since 1.0, Apr 11, 2010
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RsaRuquest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String appId;
	private String bizContent;
	private String charset;
	private String timestamp;
	private String sdkVersion;
	private String randomKeyEncrypt;
	private String sign;

}