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
    public void showSlangList(LinkedList<SlangWord> _targetSlangList)
    {
        for (SlangWord item: _targetSlangList)
        {
            System.out.println(item.toString());
        }
    }
    public LinkedList<SlangWord> findSlangByDefi(LinkedList<SlangWord> _targetSlangList, String defi){
        LinkedList<SlangWord> result = new LinkedList<>();
        for (SlangWord item: _targetSlangList)
        {
            int pos = item.getDefinition().indexOf(defi);
            if (pos == -1)
            {continue;}
            result.add(item);
        }
        return result;
    }
    public int findIndexSlang(LinkedList<SlangWord> _targetSlangList,
                              String name)
    {
        int result = -1;
        for (int i=0;i< _targetSlangList.size();i++)
        {
            String buffer = _targetSlangList.get(i).getID();
            if (buffer.equals(name)== true) {
                result = i;
                break;
            }
        }
        return result;
    }
    public LinkedList<SlangWord> findSlangByName(LinkedList<SlangWord> _targetSlangList,
                                                 String name, LinkedList<SlangWord> _targetHistory){
        LinkedList<SlangWord> result = new LinkedList<>();
        int index = findIndexSlang(_targetSlangList, name);
        if (index != -1)
        {
            result.add(_targetSlangList.get(index));
            _targetHistory.add((_targetSlangList.get(index)));
        }
        return result;
    }

    public void findHistory(LinkedList<SlangWord> _targetSlangList)
    {
        showSlangList(_targetSlangList);
    }
    public void addASlang(LinkedList<SlangWord> _targetSlangList,
                          String name, String defi){
        int index = findIndexSlang(_targetSlangList, name);
        if (index != -1)
        {
            System.out.println("This Slang has been exist. Use Edit to edit or try something else!");
        }
        else
        {
            _targetSlangList.add(new SlangWord(name, defi));
            System.out.println("Add new slang sucessfully!!");
        }
    }
    public void editASlang(LinkedList<SlangWord> _targetSlangList,
                           String name, String defi){
        int index = findIndexSlang(_targetSlangList, name);
        if (index != -1)
        {
            _targetSlangList.get(index).setDefinition(defi);
            System.out.println("Edit slang sucessfully!!");

        }
        else
        {
            System.out.println("This Slang has not been exist. Use Add to insert or try something else!");
        }
    }
    public void deleteASlang(LinkedList<SlangWord> _targetSlangList,
                            String name){
        int index = findIndexSlang(_targetSlangList, name);
        if (index != -1)
        {
            System.out.println("This is Slang you find");
            System.out.println(_targetSlangList.get(index).toString());
            System.out.println("Do you want to delete: (1)-YES & (0)-NO");
            Scanner scanner = new Scanner(System.in);
            int decision = scanner.nextInt();
            if (decision == 1)
            {
                _targetSlangList.remove(index);
                System.out.println("Delete sucessfully!");
            }
            else
            {
                System.out.println("Cancel delete!!");
            }
        }
        else
        {
            System.out.println("This Slang has not been exist. Try something else!");
        }
    }
    public void reset(LinkedList<SlangWord> _targetSlangList) {
        _targetSlangList = this.readFile("slang.txt");
        this.writeFile("mainslang.txt", _targetSlangList);
        System.out.println("This slang list has been reset sucessfully!!");
    }
    public void resetHistory(LinkedList<SlangWord> _targetSlangList)
    {
        _targetSlangList.clear();
        this.writeFile("mainhistory.txt", _targetSlangList);
        System.out.println("This history list has been reset sucessfully!!");
    }
    public SlangWord randomSlang(LinkedList<SlangWord> _targetSlangList){
        Random rng = new Random();
        int index = rng.nextInt(0, _targetSlangList.size());
        String name = _targetSlangList.get(index).getID();
        String defi = _targetSlangList.get(index).getDefinition();
        SlangWord todaySlang = new SlangWord(name,defi);
        return todaySlang;
    }
    public void quizSlang(LinkedList<SlangWord> _targetSlangList){
        Random rng= new Random();
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        boolean streak = true;
        while (streak == true)
        {
            int total = _targetSlangList.size();
            int unit = total/4;
            LinkedList<SlangWord> _test = new LinkedList<>();
            for (int i=0;i<4;i++)
            {
                int number = rng.nextInt(unit*i, unit*(i+1)-1);
                String name = _targetSlangList.get(number).getID();
                String defi = _targetSlangList.get(number).getDefinition();
                _test.add(new SlangWord(name, defi));
            }
            int answer = rng.nextInt(0,3);
            System.out.println("Question "+ (score+1) + ": ");
            System.out.print("What is the Slang of this definition: ");
            System.out.println(_test.get(answer).getDefinition());
            for (int i=0;i<4;i++)
            {
                System.out.print(i+1+". "+_test.get(i).getID());
                System.out.println();
            }
            System.out.printf("Your answer: ");
            int guess = scanner.nextInt();
            if (guess == answer+1)
            {
                System.out.println("Correct answer.");
                score++;
                System.out.println("Your score now: " + score);
            }
            else
            {
                System.out.println("Incorrect answer.");
                System.out.println("Answer: " + (answer+1) +". " +  _test.get(answer).toString());
                System.out.println("Your final score: " + score);
                streak = false;
            }
        }
    }

}
