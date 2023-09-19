package com.Blog.Project.Blog.Helpers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;

public class HelperFunctions {
    public static String getBase64(int uid, String type) {
        String Base64Image="";
        try {
            Path path = Path.of("src/resources/uploads/"+type+"/" + Integer.toString(uid) + ".txt");
            if (Files.exists(path)) {
                String content = Files.readString(path);
                Base64Image = content;
            }
        } catch (FileNotFoundException e) {
            return "";
//            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e); //This user has no image
        }

        return Base64Image;
    }

    public static String setBase64(int uid, String img, String type) {
        try {
            Path path = Path.of("src/resources/uploads/"+type+"/");
            if (!Files.exists(path))
                Files.createDirectories(path);
            path= Paths.get(path + "/"+Integer.toString(uid) + ".txt");
            if (!Files.exists(path))
                Files.createFile(path);
            Files.writeString(path,img);
            System.out.println("Successfully wrote to the file.");

        } catch (FileAlreadyExistsException e){
            System.out.println("File exists");
            return "";

        } catch(NoSuchFileException e) {
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return "src/resources/uploads/"+type+"/"+Integer.toString(uid)+".txt";

    }
}
