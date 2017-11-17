import java.util.Calendar;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Calendar_mine {
	private Calendar calendar = Calendar.getInstance();
	private String week_day[] = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
	
	
	public int get_year(){
		return calendar.get(Calendar.YEAR);
	}
	public int get_month(){
		return calendar.get(Calendar.MONTH) + 1;
	}
	public int get_day(){
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	/*
	 * A test method
	 * return value: the week day of current system time.
	 * 
	 */
	public String get_week_day(){
		int index =  calendar.get(Calendar.DAY_OF_WEEK) - 1;
		
		return week_day[index];
	}
	
	/*
	 * get which weekday the given date is
	 * 
	 * returns the weekday string 
	 * null when wrong parameters received
	 * 
	 */
	public String get_week_day(int year, int month, int day){
		int index;
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		
		return week_day[index];
	}
	
	/*
	 * get an array of integer
	 * which add 0 to the first blank weekdays
	 * and ends with the number 99
	 * 
	 * returns null when received wrong parameters
	 * 
	 */
	public int[] get_month(int year, int month){
		int[] seq = new int[38];
		int max_day;
		Calendar cal = Calendar.getInstance();
		
		cal.set(year, month-1, 1);
		max_day = cal.getActualMaximum(Calendar.DATE);
		
		// make the first blank days to number 0
		int i;
		//System.out.println("  "+year+"/"+month+" first day: "+cal.get(Calendar.DAY_OF_WEEK));
		for(i=0; i<cal.get(Calendar.DAY_OF_WEEK)-1; i++){
			seq[i] = 0;
		}
		// fill the days with their actual day number
		int j;
		for(j=0; j<max_day; j++){
			seq[i+j] = j+1;
		}
		// to end the sequence
		seq[i+j] = 99;
		
		return seq;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalendarUI ui = new CalendarUI();
		
		//ui.con_test();
		ui.conUI(); 
	}
}

class CalendarUI{
	
	// this method is just for testing...
	public void con_test(){
		Calendar_mine cc = new Calendar_mine();
		
		System.out.println("@@ the following output is just some tests @@");
		System.out.println("2017-9-27: "+cc.get_week_day(2017,9,27));
		System.out.println("2017-3-8: "+cc.get_week_day(2017,3,8));
		System.out.println("2017-6-9: "+cc.get_week_day(2017,6,9));
		System.out.println("Today: "+cc.get_week_day());
		
		for(int mm=2010; mm<2018; mm++){
			int[] s = cc.get_month(mm, 8);
			System.out.println(mm+"-8: ");
			System.out.println("Sun Mon Tue Wed Thu Fri Sat");
			int c = 0;
			for(int i:s){
				c ++;
				if(i == 0){
					System.out.print("    ");
				}else if(i>31){
					System.out.print("\n");
					break;
				}else System.out.format("%3d ",i);
				if(c == 7){
					c = 0;
					System.out.print("\n");
				}
			}
		}
		System.out.println("@@@@@@ I @@ AM @@ SEPERATOR @@@@@@");
	}
	
	// One possible solution
	// console UI
	public void conUI(){
		// complete this method
		this.displayHello();
		while(true){
			int sel = this.displayMenu();
			switch(sel){
			case 0:
				System.out.println("\nBye.");
				System.exit(0);
				break;
			case 1:
				this.displayToday();
				break;
			case 2:
				this.displayWeek();
				break;
			case 3:
				this.displayMonth();
				break;
			case 4:
				this.displayYear();
				break;
			default:
				System.out.println("#"+sel+" donesn't exists.");
			}
		}
	}
	
	// show a hello message on the screen
	private void displayHello(){
		System.out.print("********************\n"
				       + "*   Welcome ^_^    *\n"
				       + "********************\n");
		System.out.println("> Today");
		this.displayToday();
	}
	
	// show the main menu.
	private int displayMenu(){
		System.out.print("***********************\n"
				       + "* Options:            *\n"
				       + "*    1.Today          *\n"
				       + "*    2.Weekday search *\n"
				       + "*    3.Month calendar *\n"
				       + "*    4.Year calendar  *\n"
				       + "*    0.Exit           *\n"
				       + "***********************\n");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int sel=1;
		while(true){
			System.out.print("> ");
			try{
				sel = scan.nextInt();
			}catch(InputMismatchException e){
				System.out.println("[!]Wrong choice, retry.");
				scan.next();
				continue;
			}
			if(sel<0 || sel > 4){
				System.out.println("[!]Wrong choice, retry.");
				continue;
			}
			break;
		}
		
		return sel;
	}
	
	// show today's month calendar.
	private void displayToday(){
		Calendar_mine cc = new Calendar_mine();
		this.displayMonth(cc.get_year(), cc.get_month());
	}
	
	// show the monthly calendar of #
	private void displayMonth(int year, int month){
		Calendar_mine cc = new Calendar_mine();
		
		// get the whole year's calendar sequence.
		int[] ss = cc.get_month(year, month);
		
		System.out.format("*******************************\n"
				        + "*           %-4d %-2d           *\n"
		                + "* Sun Mon Tue Wed Thu Fri Sat *\n", year, month);
		int c = 0;
		for(int i:ss){
			if(c == 0){
				System.out.print("*");
			}
			c ++;
			if(i == 0){
				System.out.print("    ");
			}else if(i > 31){
				while(c++ <= 7) System.out.print("    ");
				
				System.out.print(" *\n");
				c = 0;
				System.out.print("*");
				while(c++ < 7) System.out.print("****");
				System.out.print("**\n\n");
				break;
			}else if(i == cc.get_day() && 
					 month == cc.get_month() && 
					 year == cc.get_year())
				
				System.out.format("[%2d]",i);
			else System.out.format("%3d ",i);
			if(c == 7){
				c = 0;
				System.out.print(" *\n");
			}
		}
	}
	
	// query the weekday of #
	private void displayWeek(){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int year=2000, month=1, day=1;
		
		while(true){
			System.out.print("[*]the date should be like this: yyyy mm dd. eg.2000 1 1\n");
			System.out.print("Enter the date>> ");
			try{
				year  = scan.nextInt();
				month = scan.nextInt();
				day   = scan.nextInt();
			}catch(InputMismatchException e){
				System.out.println("  Wrong input, retry.");
				scan.next();
				continue;
			}
			
			if(month < 1 || month > 12 ||
			   day   < 1 || day   > 31){
				
				System.out.println("  Wrong date, retry.");
				continue;
			}
			
			break;
		}
		
		Calendar_mine cal = new Calendar_mine();
		System.out.print(year+"/"+month+"/"+day+": "+cal.get_week_day(year, month, day)+"\n\n");
	}
	
	// query monthly calendar
	private void displayMonth(){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int year=2000, month=1;
		
		while(true){
			System.out.print("[*]the date should be like this: yyyy mm. eg.2000 1\n");
			System.out.print("Enter the date>> ");
			try{
				year  = scan.nextInt();
				month = scan.nextInt();
			}catch(InputMismatchException e){
				System.out.println("  Wrong input, retry.");
				scan.next();
				continue;
			}
			
			if(month < 1 || month > 12){
				System.out.println("  Wrong date, retry.");
				continue;
			}
			
			break;
		}
		
		this.displayMonth(year, month);
	}
	
	// query yearly calendar.
	private void displayYear(){
		// complete this method
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int year = 2000;
		
		while(true){
			System.out.print("Enter the year>> ");
			try{
				year = scan.nextInt();
			}catch(InputMismatchException e){
				System.out.println("  Wrong input, retry.");
				scan.next();
				continue;
			}
			
			break;
		}
		
		System.out.println("@@ Year:"+year);
		for(int i=1; i<=12; i++) this.displayMonth(year, i);
	}
	
}