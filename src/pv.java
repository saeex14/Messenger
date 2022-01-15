import java.util.*;

public class pv {
    //obj
    private Boolean NewMessege = false;
    Scanner input = new Scanner(System.in);
    private String number;
    private Vector<String> message = new Vector<String>();
    //constructor
    public pv(){
        setNumber();
    }
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
            System.out.println("this chat is empty");
            return;
        }
        for (String mss: message){
            if (mss != null && !mss.equals("-1")) {
                System.out.println("=>" + mss);
            }
        }
    }
}