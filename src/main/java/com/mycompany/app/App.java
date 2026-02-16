package com.mycompany.app;

import java.io.*;
import java.util.HashSet;

/**
 * PE05 - Auto Grade Logger with Duplicate Prevention
 * Checks for existing entries and only adds new students
 */
public class App {
    public static void main(String[] args) {
        // Define the filename for storing grades
        String filename = "grades.txt";
        
        // Define student data (2D array: name and grade)
        String[][] students = {
            {"Luna", "A"},
            {"Max", "B"},
            {"Zoe", "A-"},
            {"Leo", "B+"},
            {"Maya", "C"}
        };
        
        try {
            // STEP 1: Read existing names from file
            HashSet<String> existingStudents = new HashSet<>();
            
            // Check if file exists before trying to read it
            File file = new File(filename);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String line;
                
                while ((line = reader.readLine()) != null) {
                    // Extract the name from "Name - Grade" format
                    String[] parts = line.split(" - ");
                    if (parts.length > 0) {
                        existingStudents.add(parts[0].trim());
                    }
                }
                
                reader.close();
            }
            
            // STEP 2: Write only NEW students to file
            FileWriter writer = new FileWriter(filename, true); // append mode
            
            int newStudentsAdded = 0;
            
            for (int i = 0; i < students.length; i++) {
                String name = students[i][0];
                String grade = students[i][1];
                
                // Check if student already exists
                if (existingStudents.contains(name)) {
                    System.out.println("Skipped (already exists): " + name);
                } else {
                    // Write new student to file
                    writer.write(name + " - " + grade + "\n");
                    System.out.println("Added: " + name + " - " + grade);
                    newStudentsAdded++;
                }
            }
            
            writer.close();
            
            // STEP 3: Display completion message
            System.out.println("\nProgram finished successfully.");
            
            if (newStudentsAdded > 0) {
                System.out.println(newStudentsAdded + " new student(s) added to the file.");
            } else {
                System.out.println("No new students were added (all already exist).");
            }
            
        } catch (IOException e) {
            // Handle any file I/O errors
            System.out.println("Error: " + e.getMessage());
        }
    }
}