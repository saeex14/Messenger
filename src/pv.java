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
    public String getMessage(int i){
       if (message.size() >= i)
         return message.get(i);
       return null;
    }
    public Boolean getNewMessege(){return NewMessege;}
    //setter
    public void setMessage(Vector<String> update){
        message = update;
        NewMessege = true;
    }
    //options: send and show message to person
    public void addMessage(String mss){
        NewMessege = true;
        this.message.add(mss);}
    public Vector<String> sendMessage(String Myname){
        String temp = "";
        while (!temp.equals("-1")){
            temp = input.nextLine();
            if(!Objects.equals(temp, "-1"))
                message.add(Myname +": "+ temp);
        }
        return message;
    }
    public Vector<String> changeMessege(String Myname){
        System.out.println("which line do you want to change ?");
        int n = input.nextInt();
        System.out.print("enter changed:");
        String temp = input.nextLine();
        temp = input.nextLine();
        message.set(n - 1 ,Myname+": "+temp) ;
        return message;
    }
    public void ShowMessage(){
        NewMessege = false;
        if (message.size() == 0) {
            System.out.println(ANSI_RED + "this chat is empty" + ANSI_RESET);
            return;
        }
        int i = 1 ;
        for (String mss: message){
            if (mss != null && !mss.equals("-1")) {
                System.out.println(ANSI_RED + i + ". " + ANSI_RESET  + mss);
            }
            i++;
        }
    }
}