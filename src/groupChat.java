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
    public void setNewMessage(boolean bool){this.NewMessage = bool;}
    public void setNewNumber(String num ){ this.numbers.add(num);}
    //getter
    public String getNameGroup(){return nameGroup;}
    public Vector<String> getMessege(){return message;}
    public Vector<String> getNumbers(){return numbers;}
    public Boolean getNewMessage(){return NewMessage;}
    //options: show and send message to another member
    public void UpdateMesseger(Vector<String>temp){
        NewMessage  = true;
        message = temp;
    }
    public void ShowMessage(){
        NewMessage = false;
//        System.out.println("how many messages do you want to see? ");
//        int n = input.nextInt();
        if (message.isEmpty())
            System.out.println(ANSI_RED + "this Group is empty" + ANSI_RESET);
        else
            for (String mss: message){
                if (mss != null && !mss.equals("-1")) {
                    System.out.println("=> " + mss);
                }
            }
    }
    public void sendMessage(){
        NewMessage = true;
        String temp = "";
        while (!temp.equals("-1")){
            temp = input.nextLine();
            message.add(temp);
        }
    }
}