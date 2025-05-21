package com.littleangels.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.http.Part;

/**
 * Utility class for handling image file uploads via HTTP multipart requests.
 * Provides methods to extract the file name from the Part header, save the
 * uploaded image to a predefined directory, and retrieve the save path.
 * 
 * @author Aarya Gautam
 */
public class ImageUtil {

	/**
	 * Absolute path on the server where uploaded images will be saved. Update this
	 * path as needed for different deployment environments.
	 */
	private static final String SAVE_PATH = "C:/Users/NITRO/eclipse-workspace/Little Angels/src/main/webapp/uploads";

	/**
	 * Extracts the submitted file name from the Part's Content-Disposition header.
	 * Handles both Windows-style and Unix-style path separators.
	 *
	 * @param part The Part object representing the uploaded file.
	 * @return The file name without path information, or "default.png" if none
	 *         found.
	 */
	public String getImageNameFromPart(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		String imageName = null;

		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				// Remove the leading key and quotes
				imageName = s.substring(s.indexOf('=') + 1).trim().replace("\"", "");
				// Strip path information for Windows
				imageName = imageName.substring(imageName.lastIndexOf('\\') + 1);
				// Strip path information for Unix/Linux
				imageName = imageName.substring(imageName.lastIndexOf('/') + 1);
			}
		}

		// Fallback to default image if none provided
		return (imageName == null || imageName.isEmpty()) ? "default.png" : imageName;
	}

	/**
	 * Saves the uploaded image Part to the server file system under SAVE_PATH.
	 * Creates the upload directory if it does not exist.
	 *
	 * @param part The Part object containing the image data.
	 * @return true if the file was saved successfully; false otherwise.
	 */
	public boolean uploadImage(Part part) {
		File uploadDir = new File(SAVE_PATH);
		// Ensure the upload directory exists
		if (!uploadDir.exists() && !uploadDir.mkdirs()) {
			System.err.println("Could not create directory: " + SAVE_PATH);
			return false;
		}

		String imageName = getImageNameFromPart(part);
		File targetFile = new File(uploadDir, imageName);

		// Stream the uploaded file to disk
		try (InputStream in = part.getInputStream(); FileOutputStream out = new FileOutputStream(targetFile)) {

			byte[] buffer = new byte[8192];
			int len;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			return true;

		} catch (IOException e) {
			e.printStackTrace(); // Log any I/O errors
			return false;
		}
	}

	/**
	 * Returns the configured directory path where images are saved.
	 *
	 * @return The absolute save path as a String.
	 */
	public String getSavePath() {
		return SAVE_PATH;
	}
}
