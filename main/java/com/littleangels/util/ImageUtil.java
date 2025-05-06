package com.littleangels.util;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.http.Part;

public class ImageUtil {

    public String getImageNameFromPart(Part part) {
        // Retrieve the content-disposition header from the part
        String contentDisp = part.getHeader("content-disposition");

        // Split the header by semicolons to isolate key-value pairs
        String[] items = contentDisp.split(";");

        // Initialize imageName variable to store the extracted file name
        String imageName = null;

        // Iterate through the items to find the filename
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                // Extract the file name from the header value
                imageName = s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }

        // Check if the filename was not found or is empty
        if (imageName == null || imageName.isEmpty()) {
            // Assign a default file name if none was provided
            imageName = "download.png";
        }

        // Return the extracted or default file name
        return imageName;
    }

    public boolean uploadImage(Part part, String rootPath, String saveFolder) {
        String savePath = getSavePath(rootPath, saveFolder);
        File fileSaveDir = new File(savePath);

        // Ensure the directory exists
        if (!fileSaveDir.exists()) {
            if (!fileSaveDir.mkdirs()) {
                return false; // Failed to create the directory
            }
        }

        try {
            // Get the image name
            String imageName = getImageNameFromPart(part);
            // Create the file path
            String filePath = savePath + "/" + imageName;
            // Write the file to the server
            part.write(filePath);
            return true; // Upload successful
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception
            return false; // Upload failed
        }
    }

    public String getSavePath(String rootPath, String saveFolder) {
        // Construct the save path dynamically based on the web application root path
        return rootPath + "/images" + saveFolder + "/";
    }
}
