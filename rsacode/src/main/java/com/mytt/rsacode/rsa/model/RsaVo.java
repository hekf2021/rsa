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
public class RsaVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String appId;
	private String charset;
	private String sdkVersion;
	private String bizContent;
	private String timestamp;
	private String randomKeyEncrypt;
	private String sign;

}