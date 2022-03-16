import java.util.*;
public class groupChat {
    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_RED    = "\u001B[31m";
    //obj
    Scanner input = new Scanner(System.in);
    private String nameGroup;
    private Boolean NewMessage = false;
    private Vector<String> numbers = new Vector<String>();
    private Vector<String> message = new Vector<String>();
    //constructor
    public groupChat(Vector<account>member ,String num ,String nameGroup){
        this.nameGroup = nameGroup;
        setNumbers(member);
        numbers.add(num);
    }
    //setter
    public void setNumbers(Vector<account> member){
        System.out.println("How many member do you have?");
        int n = input.nextInt();
        for (int i = 0; i < n - 1 ; i++){
            System.out.print("enter number of member:");
            String num = input.next();
            boolean exist = false;
            //if num exist add,nor add next num
            for (account account : member) {
                if (Objects.equals(account.getNumber(), num)) {
                    exist = true;
                    break;
                }
            }
            if(exist)
                numbers.add(num);
            else
                i--;
        }
    }
    public void setNameGroup(String name){nameGroup = name;}
    //getter
    public String getNameGroup(){return nameGroup;}
    public Vector<String> getMessage(){return message;}
    public Vector<String> getNumbers(){return numbers;}
    public Boolean getNewMessage(){return NewMessage;}
    public String getMessage(int i ){return message.get(i);}

    //options: show and send message to another member
    //when you want update message
    public void UpdateMesseger(Vector<String>temp){
        NewMessage  = true;
        message = temp;
    }
    //when you want only add one message
    public void addMessage(String mss){
        NewMessage = true;
        this.message.add(mss);
    }
    //change the message that you will select
    public void ChangeMessage(String Myname){
        System.out.println("which line do you want ot change?");
        int n = input.nextInt();
        System.out.println("enter changed:");
        String temp = input.nextLine();
        temp = input.nextLine();
        message.set(n - 1,Myname + ": " + temp);
    }
    //show all message
    public void ShowMessage(){
        NewMessage = false;
        int i = 1 ;
        if (message.isEmpty())
            System.out.println(ANSI_RED + "this Group is empty" + ANSI_RESET);
        else
            for (String mss: message){
                if (mss != null && !mss.equals("-1")) {
                    System.out.println(ANSI_RED + i + ". " + ANSI_RESET + mss);
                }
                i++;
            }
    }
    //sending message
    public void sendMessage(String Myname){
        NewMessage = true;
        String temp = "";
        while (!temp.equals("-1")){
            temp = input.nextLine();
            if(!Objects.equals(temp, "-1"))
                message.add(Myname +": "+ temp);
        }
    }
}