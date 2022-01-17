import java.util.*;

public class pv {
    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_RED    = "\u001B[31m";
    //obj
    private Boolean NewMessege = false;
    Scanner input = new Scanner(System.in);
    private String number;
    private Vector<String> message = new Vector<String>();
    //constructor
    public pv(String num){
        number = num;
    }
    //getter
    public String getNumber(){ return number;}
    public Boolean getNewMessege(){return NewMessege;}
    //setter
    public void setNumber(){
        System.out.println("enter num of person:");
        number = input.nextLine();
    }
    public void setMessage(Vector<String> update){
        message = update;
        NewMessege = true;
    }
    //options: send and show message to person
    public Vector<String> sendMessage(){
        String temp = "";
        while (!temp.equals("-1")){
            temp = input.nextLine();
            if(!Objects.equals(temp, "-1"))
               message.add(temp);
        }
        return message;
    }
    public void ShowMessage(){
        NewMessege = false;
        if (message.size() == 0) {
            System.out.println(ANSI_RED + "this chat is empty" + ANSI_RESET);
            return;
        }
        for (String mss: message){
            if (mss != null && !mss.equals("-1")) {
                System.out.println(ANSI_RED + "=>" +ANSI_RESET + mss);
            }
        }
    }
}