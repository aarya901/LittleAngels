package com.littleangels.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utility class for encrypting and decrypting passwords using AES-GCM with keys
 * derived from a password via PBKDF2. Generates random nonces for salt and IV,
 * and handles Base64 encoding of cipher text.
 * 
 * Author: Aarya Gautam
 */
public class PasswordUtil {

	/** AES encryption algorithm with GCM and no padding */
	private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";

	/**
	 * Length of authentication tag in bits (must be one of 128, 120, 112, 104, or
	 * 96)
	 */
	private static final int TAG_LENGTH_BIT = 128;

	/** Length of IV (nonce) in bytes for GCM mode (recommended 12 bytes) */
	private static final int IV_LENGTH_BYTE = 12;

	/** Length of salt in bytes for PBKDF2 */
	private static final int SALT_LENGTH_BYTE = 16;

	/** Character set for encoding/decoding text */
	private static final Charset UTF_8 = StandardCharsets.UTF_8;

	/**
	 * Generates a secure random byte array (nonce or salt).
	 *
	 * @param numBytes Number of random bytes to generate.
	 * @return Byte array filled with secure random bytes.
	 */
	public static byte[] getRandomNonce(int numBytes) {
		byte[] nonce = new byte[numBytes];
		new SecureRandom().nextBytes(nonce);
		return nonce;
	}

	/**
	 * Generates a new AES secret key of the specified key size.
	 *
	 * @param keysize Key size in bits (e.g., 128 or 256).
	 * @return Generated AES SecretKey.
	 * @throws NoSuchAlgorithmException if AES algorithm is unavailable.
	 */
	public static SecretKey getAESKey(int keysize) throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(keysize, SecureRandom.getInstanceStrong());
		return keyGen.generateKey();
	}

	/**
	 * Derives a 256-bit AES key from a password and salt using PBKDF2 with
	 * HMAC-SHA256.
	 *
	 * @param password Character array of the password.
	 * @param salt     Salt bytes for key derivation.
	 * @return Derived AES SecretKey, or null if derivation fails.
	 */
	public static SecretKey getAESKeyFromPassword(char[] password, byte[] salt) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
			byte[] keyBytes = factory.generateSecret(spec).getEncoded();
			return new SecretKeySpec(keyBytes, "AES");
		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
			Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, "Key derivation failed", ex);
			return null;
		}
	}

	/**
	 * Encrypts the given password using AES-GCM. A random salt and IV are
	 * generated, and prefixed to the cipher text before Base64 encoding.
	 *
	 * @param employee_id Identifier (e.g., username) used as the password for key
	 *                    derivation.
	 * @param password    Plain text password to encrypt.
	 * @return Base64-encoded string containing IV, salt, and cipher text, or null
	 *         on error.
	 */
	public static String encrypt(String employee_id, String password) {
		try {
			byte[] salt = getRandomNonce(SALT_LENGTH_BYTE);
			byte[] iv = getRandomNonce(IV_LENGTH_BYTE);
			SecretKey key = getAESKeyFromPassword(employee_id.toCharArray(), salt);

			Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
			cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
			byte[] cipherText = cipher.doFinal(password.getBytes(UTF_8));

			byte[] encrypted = ByteBuffer.allocate(iv.length + salt.length + cipherText.length).put(iv).put(salt)
					.put(cipherText).array();

			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception ex) {
			Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, "Encryption failed", ex);
			return null;
		}
	}

	/**
	 * Decrypts a Base64-encoded string produced by
	 * {@link #encrypt(String, String)}. Extracts the IV and salt, derives the AES
	 * key, and decrypts the cipher text.
	 *
	 * @param encryptedPassword Base64 string containing IV, salt, and cipher text.
	 * @param username          Identifier used originally for key derivation.
	 * @return Decrypted plain text password, or null on error.
	 */
	public static String decrypt(String encryptedPassword, String username) {
		try {
			byte[] decoded = Base64.getDecoder().decode(encryptedPassword.getBytes(UTF_8));
			ByteBuffer buffer = ByteBuffer.wrap(decoded);

			byte[] iv = new byte[IV_LENGTH_BYTE];
			buffer.get(iv);

			byte[] salt = new byte[SALT_LENGTH_BYTE];
			buffer.get(salt);

			byte[] cipherText = new byte[buffer.remaining()];
			buffer.get(cipherText);

			SecretKey key = getAESKeyFromPassword(username.toCharArray(), salt);
			Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
			cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

			byte[] plainText = cipher.doFinal(cipherText);
			return new String(plainText, UTF_8);
		} catch (Exception ex) {
			Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, "Decryption failed", ex);
			return null;
		}
	}
}
