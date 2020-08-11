import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    List<String> DataScan = new ArrayList<String>();
    List<String> FileScan = new ArrayList<String>();
    List<Timemap> timeList = new ArrayList<Timemap>();

    public void parse(List<String> list){
        for(int i = 0; i<list.size();i++){
            String removeP = list.get(i).substring(0, list.get(i).length()-1);
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

    public int solution(String[] lines) {
        for(int i = 0; i<lines.length; i++)
        {
            FileScan.add(lines[i]);
        }
        parse(FileScan);
        return lookup();
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
