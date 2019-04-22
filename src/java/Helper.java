
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patryk
 */
public class Helper {
    
    public static void writeResults(HashMap results) {
        try {
            FileWriter fw = new FileWriter("D:\\studia\\aplikacje_internetowe_java\\lab\\lab2\\src\\java\\wyniki.txt", true);
            for (Iterator it = results.keySet().iterator(); it.hasNext();) {
                String key = (String) it.next();
                fw.write(key + ":" + 0 + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Nie znaleziono pliku!");
        }
    }
    
    public static HashMap readResults() {
        String line = "";
        HashMap results = new HashMap();
        File file = new File("D:\\studia\\aplikacje_internetowe_java\\lab\\lab2\\src\\java\\wyniki.txt");
        FileInputStream fis = null;
        BufferedReader br=null;
        try {
            fis = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fis));           
            while ((line = br.readLine()) != null) {                
                String[] elem = line.split(":");
                if(results.get(elem[0])!=null){
                    int i = (Integer)results.get(elem[0])+1;
                    results.put(elem[0], i);
                }
                else{
                results.put(elem[0], 1);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku!");
        }
        catch (IOException e) {
            System.out.println("Błąd odczytu pliku!");
        }

        return results;
 }  
}
