import java.util.Scanner;

class P72410{
    public static void main(String[] args){
        P72410 P = new P72410();
        Scanner sc = new Scanner(System.in);
        String new_id = sc.nextLine();
        sc.close();
        System.out.print(P.Solution(new_id));
    }

    public String Solution(String new_id){
        ansCon aCon = new ansCon(new_id.toLowerCase()); // step 1
//        String answer = new_id.toLowerCase(); //step 1

//        String temp = "";
        for(int i = 0; i<aCon.getAnswer().length();i++){
            if((((((97<=aCon.getAnswer().charAt(i)&&aCon.getAnswer().charAt(i)<=122)||
                (65<=aCon.getAnswer().charAt(i)&&aCon.getAnswer().charAt(i)<=90))||
                (48<=aCon.getAnswer().charAt(i)&&aCon.getAnswer().charAt(i)<=57))||
                aCon.getAnswer().charAt(i)==45)||
                aCon.getAnswer().charAt(i)==95)||
                aCon.getAnswer().charAt(i)==46){
                    aCon.plusTemp(aCon.getAnswer().charAt(i));
//                    temp += answer.charAt(i);
            }
            else{
                //delete
            }
        }//step 2
//        answer = temp;
//        temp = "";
        aCon.temptoAnswer();

        for(int i = 0; i<aCon.getAnswer().length()-1;i++){
            if(aCon.getAnswer().charAt(i)==46&&aCon.getAnswer().charAt(i+1)==46){
                //delete
            }
            else{
                aCon.plusTemp(aCon.getAnswer().charAt(i));
//                temp += answer.charAt(i);
            }
        }
        if(aCon.getAnswer().charAt(aCon.getAnswer().length()-1)!=46){
            aCon.plusTemp(aCon.getAnswer().charAt(aCon.getAnswer().length()-1));
//            temp+=answer.charAt(answer.length()-1);
        }//step 3
        aCon.temptoAnswer();
//        answer = temp;
//        temp = "";

        if(!aCon.getAnswer().isEmpty()&&aCon.getAnswer().charAt(0)==46){
            aCon.setAnswer(aCon.getAnswer().substring(1,aCon.getAnswer().length()));
        }
        if(!aCon.getAnswer().isEmpty()&&aCon.getAnswer().charAt(aCon.getAnswer().length()-1)==46){
            aCon.setAnswer(aCon.getAnswer().substring(0,aCon.getAnswer().length()-1));
//            answer = answer.substring(0,answer.length()-1);
        }//step 4
        if(aCon.getAnswer().isEmpty()){
            aCon.setAnswer("a");
        }//step 5
        if(aCon.getAnswer().length()>=16){
            aCon.setAnswer(aCon.getAnswer().substring(0,15));
            while(!aCon.getAnswer().isEmpty()&&aCon.getAnswer().charAt(aCon.getAnswer().length()-1)==46){
                aCon.setAnswer(aCon.getAnswer().substring(0,aCon.getAnswer().length()-1));
            }
        }//step 6
        if(aCon.getAnswer().length()==1){
            aCon.plusAnswer(aCon.getAnswer().charAt(0));
            aCon.plusAnswer(aCon.getAnswer().charAt(0));
        }
        if(aCon.getAnswer().length()==2){
            aCon.plusAnswer(aCon.getAnswer().charAt(1));
        }

        return aCon.getAnswer();
    }
}

class ansCon{
    private String temp;
    private String answer;

    ansCon(String a){
        this.temp = "";
        this.answer = a;
    }

    public void temptoAnswer(){
        this.answer = this.temp;
        this.temp = "";
    }

    public String getTemp(){
        return this.temp;
    }

    public String getAnswer(){
        return this.answer;
    }

    public void setTemp(String a){
        this.temp = a;
    }

    public void setAnswer(String a){
        this.answer = a;
    }

    public void plusTemp(char a){
        this.temp += a;
    }

    public void plusAnswer(char a){
        this.answer += a;
    }

}