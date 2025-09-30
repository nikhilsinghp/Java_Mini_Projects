/*import java.util.Scanner;
class Portal 
{
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) throws Exception
	{
		Adhaar user1=new Adhaar("Nikhil",1234,"Satna",4440);
		Adhaar user2=new Adhaar("Aman",2345,"Bhopal",2345);
		Adhaar user3=new Adhaar("Rohit",3456,"Mumbai",3456);
		Adhaar user4=new Adhaar("Virat",4567,"Delhi",4567);
		
		Adhaar[] users={user1,user2,user3,user4};
		
		boolean home=true;
		do{
			System.out.println("Namaste to Adhaar Portal");
			System.out.println("Press 1 for get info\nPress 2 for set info\nPress 3 for exit");
			System.out.print("Enter your choice  : ");
			int choice=sc.nextInt();
			switch(choice)
			{
				case 1: getInfo(users);
						break;
				case 2: setInfo(users);
						break;
				case 3: home=false;
				        break;
				default : System.out.println("Invalid data");
			}
		}while(home);
		System.out.println("Thanks");
	}
	
	
	public static void getInfo(Adhaar[] users) throws Exception
	{
		boolean getPage=true;
		do
		{
			displayNames(users);	
			int choice=sc.nextInt();
			
			if(choice>=1 && choice <= users.length)
				getData(users[choice-1]);
			else if(choice == users.length+1)
				getPage=false;
			else
				System.out.println("Invalid input");
		}
		while (getPage);
	}
	
	public static void getData(Adhaar user) throws Exception
	{
		boolean getData=true;
		do
		{
			System.out.println("Press 1 for adhaar number\nPress 2 for contact\nPress 3 for address \nPress 4 for exit");
			System.out.print("Enter your choice  : ");
			int choice =sc.nextInt();
			switch(choice)
			{
				case 1 : long no=user.getAdhaarNo();	
				         System.out.println("Adhaar number is "+no);
						 break;
				case 2 : long contact=user.getContact();	
				         System.out.println("Contact number is "+contact);
						 break;
				case 3 : String address=user.getAddress();	
				         System.out.println("Address is "+address);
						 break;
				case 4 : getData=false;
				         break;
				default : System.out.println("Invalid data");
			}
		}
		while (getData);
	}
	
	public static void setInfo(Adhaar[] users)  throws Exception
	{
		boolean setPage=true;
		do
		{
			displayNames(users);	
			int choice=sc.nextInt();
			
			if(choice>=1 && choice <= users.length)
				setData(users[choice-1]);
			else if(choice == users.length+1)
				setPage=false;
			else
				System.out.println("Invalid input");
		}
		while (setPage);
	}
	
	public static void setData(Adhaar user) throws Exception
	{
		boolean setData=true;
		do
		{
			System.out.println("Press 1 for name\nPress 2 for contact\nPress 3 for address \nPress 4 for exit");
			System.out.print("Enter your choice : ");
			int choice=sc.nextInt();
			
			switch(choice)
			{
				case 1 : sc.nextLine();
						 System.out.print("Enter name : ");
						 String name = sc.nextLine();
						 user.setName(name);
						 break;
				case 2 : System.out.print("Enter contact : ");
						 long contact = sc.nextLong();
						 user.setContact(contact);
						 break;	
				case 3 : sc.nextLine();
						 System.out.print("Enter address : ");
						 String address = sc.nextLine();
						 user.setAddress(address);
						 break;
				case 4 : setData=false;
						 break;
				default : System.out.println("Invalid data");
			}
		}
		while (setData);
	}
	
	public static void displayNames(Adhaar[] users)
	{
		for(int i=0;i<users.length;i++)
		{
			System.out.println("Press "+(i+1)+ " for "+users[i].getName());
		}
		System.out.println("Press "+(users.length+1)+" for exit");
		System.out.print("Enter your choice : ");
	}
}
*/