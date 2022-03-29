import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void clearScreen() {
        System.out.flush();
    }
    public static void pause()
    {
        System.out.println("Press Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
    }
    public void writeFile(String fileName, LinkedList<SlangWord> _targetSlangList){
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            for (SlangWord item : _targetSlangList) {
                String line = item.toString();
                bw.write(line);
                bw.newLine();
            }
            bw.close();
            fw.close();

        } catch (IOException e)
        {
        }
    }
    public LinkedList<SlangWord> readFile(String fileName){
        LinkedList<SlangWord> result = new LinkedList<>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            while (true) {
                String buffer = br.readLine();
                if (buffer == null) {
                    break;
                }
                String tokens[] = buffer.split("`");
                if (tokens.length!=2)
                {
                    continue;
                }
                else
                {
                    String id = tokens[0];
                    String definition = tokens[1];
                    SlangWord newslang = new SlangWord(id, definition);
                    result.add(newslang);
                }
            }
            fr.close();
            br.close();
        } catch (IOException e)
        {
        }
        return result;
    }
}