import java.util.*;

public class account{
    //obj
    Scanner input = new Scanner(System.in);
    public static final String ANSI_RESET  = "\u001B[0m";
    public static  String ANSI_GREEN  = "\u001B[32m";
    private String number;
    private String name;
    private String password;
    public  Vector<groupChat> gb = new Vector<groupChat>();
    public  Vector<pv> pvs = new Vector<pv>();
    //constructor
    public account(){
        setName();
        setNumber();
        setPassword();
    }
    public account(String name, String num, String pass){
        this.name = name ;
        number = num;
        password = pass;
    }
    //option : make pv, group chats
    public void showAccount(){
        System.out.println("Hi " + name + "  (only enter the number of duty)");
        System.out.println("Groups:");
        showGroups();
        System.out.println("Pvs:");
        showPv();
        System.out.println("1.go in pv");
        System.out.println("2.go to groups");
        System.out.println("3.make a group");
        System.out.println("4.make pv");
        System.out.println("5.change profile");
        System.out.println("6.close");
    }
    public void showProfile(){
        System.out.println("name: " + name);
        System.out.println("number: " + number);
        System.out.println("password: " + password);
        System.out.println("which one change? (name/number/password)");
        String answer = input.nextLine();
        switch (answer) {
            case "name" -> setName();
            case "number" -> setNumber();
            case "password" -> setPassword();
        }
    }
    public void showPv(){
        for (int i = 0 ; i < pvs.size(); i++){
            if (pvs.get(i).getNewMessege()) {
                System.out.println(ANSI_GREEN + pvs.elementAt(i).getNumber() + ": " +ANSI_RESET);
            }else
                System.out.println(pvs.elementAt(i).getNumber() + ": ");
        }
    }
    public void showGroups(){
        for (int i = 0 ; i < gb.size(); i++){
            System.out.println(gb.elementAt(i).getNameGroup());
        }
    }
    public void makeGroup(Vector<account> member){
        //person should slecet a special name for gb
        boolean exist;
        String nameGb;
        do{
            exist = false;
            System.out.println("enter name of group:");
            nameGb = input.nextLine();
            for (groupChat members : gb)
                if (Objects.equals(members.getNameGroup(), nameGb)) {
                    exist = true;
                    System.out.println("this name is exist,please choose again");
                }
        }while (exist);
        //make a group in groupChat we will get numbers of member group
        groupChat temp = new groupChat(member,this.number,nameGb);
        //now we should add gb to other person
        gb.add(temp);
    }
    public void makePv(Vector<account> member){
        System.out.println("please enter number person:");
        String num = input.nextLine();
        pv temp = new pv(num);
        pvs.add(temp);
        for (int i = 0; i < member.size() - 1; i++){
            if (member.elementAt(i).getNumber().equals(num)){
                pv person = new pv(number);
                member.elementAt(i).pvs.add(person);
            }
        }
    }
    public Vector<String> findGb(String nameGb){
        Vector<String> answer = new Vector<String>();
        int index = 0;
        for ( groupChat groupChat : gb){
            if (groupChat.getNameGroup().equals(nameGb)){
                answer.add("true");
                answer.add(Integer.toString(index));
            }
            index++;
        }
        return answer;
    }
    public Vector<String> findPv(String num){
        Vector<String> answer = new Vector<String>();
        for(int i = 0 ; i < pvs.size() ; i++){
            if (pvs.elementAt(i).getNumber().equals(num)){
                answer.add("true");
                answer.add(Integer.toString(i));
                return answer;
            }
        }
        answer.add("false");
        answer.add(Integer.toString(0));
        return answer;
    }
    //getter
    public String getNumber(){return  number;}
    public String getName(){return name;}
    public String getPassword(){return password;}
    //setter
    public void setNumber(){
        System.out.println("enter your number:");
        this.number = input.nextLine();
    }
    public void setName(){
        System.out.println("enter your name:");
        this.name = input.nextLine();
    }
    public void setPassword(){
        System.out.println("enter your password:");
        this.password = input.nextLine();
    }
}
