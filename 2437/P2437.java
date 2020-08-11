import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Collections;

public class P2437 {
    List<String> DataScan = new ArrayList<String>();
    List<String> FileScan = new ArrayList<String>();
    List<Timemap> timeList = new ArrayList<Timemap>();

    public void parse(List<String> list){
        for(int i = 0; i<list.size();i++){
            String removeP = list.get(i).substring(1, list.get(i).length()-3);
            String[] parsedArray = removeP.split(" ");
            String[] parsedTime = parsedArray[1].split(":");
            int parsesec1 = 0;
            int parsesec2 = 0;
            if(parsedTime[2].contains(".")){
                parsesec1 = Integer.parseInt(parsedTime[2].substring(0,parsedTime[2].lastIndexOf(".")));
                parsesec2 = Integer.parseInt(parsedTime[2].substring(parsedTime[2].lastIndexOf(".")+1,parsedTime[2].length()));
            }else{
                parsesec1 = Integer.parseInt(parsedTime[2]);
                parsesec2 = 0;
            }
            int endTime = Integer.parseInt(parsedTime[0])*3600000+
                        Integer.parseInt(parsedTime[1])*60000+
                        parsesec1*1000+
                        parsesec2+1000;
            timeList.add(new Timemap(endTime, 0));
            int lastTime = (int)(Float.parseFloat(parsedArray[2])*1000);
            timeList.add(new Timemap(endTime-lastTime-999,1));
        }
    }

    public int lookup(){  //O(n2)
        int log = 0;
        int maxlog = 0;
        Collections.sort(timeList);
        for(int i = 0; i<timeList.size(); i++) {
            if(timeList.get(i).getupdown()==1)  {
                log=log+1;
            }
            else if(timeList.get(i).getupdown()==0)  {
                log=log-1;
            }
            if(maxlog<log){
                maxlog = log;
            }
        }
        return maxlog;
    }

    public static void main(String[] args) {
        P2437 P = new P2437();
        Scanner sc = new Scanner(System. in);
        P
            .DataScan
            .add(sc.nextLine());
        sc.close();

        try {
            File file = new File("input.txt");
            FileReader filereader = new FileReader(file);
            BufferedReader buffreader = new BufferedReader(filereader);
            String line = "";
            while((line = buffreader.readLine())!=null){
                P.FileScan.add(line);
                System.out.println(line);
            }
            filereader.close();
            buffreader.close();
            P.parse(P.FileScan);
            System.out.println(P.lookup());
        } catch(FileNotFoundException e){
            System.out.print("File not found");
        } catch(IOException e){
            System.out.println(e);
        }
    }
}

class Timemap implements Comparable<Timemap>{
    private int time;
    private int updown;
    public Timemap(int time, int updown){
        this.time = time;
        this.updown = updown;
    }
    @Override
    public int compareTo(Timemap t){
        if(this.time>t.time) return 1;
        else if(this.time<t.time) return -1;
        else return 0;
    }

    public void settime(int time){
        this.time = time;
    }
    public int gettime(){
        return this.time;
    }
    public void setupdown(int updown){
        this.updown = updown;
    }
    public int getupdown(){
        return this.updown;
    }
}
