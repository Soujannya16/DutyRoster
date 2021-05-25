import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.TableModel;

import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

public class calender extends JFrame 
{
	public calender(String str_date,int res,int[]day,String[]day_of_week,String[] morning_shift,String[] day_shift,String[] evening_shift,String []rest,String[]leave)
	{
		
		super("DUTY ROSTER FOR "+str_date);
		String sday[]=new String[res];
		for(int i=0;i<res;i++)
			sday[i]= Integer.toString(day[i]);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Object[][] data=new Object[res][7];
		for(int i=0;i<res;i++) 
		{
			data[i][0]=sday[i];              
			data[i][1]=day_of_week[i];
			data[i][2]=morning_shift[i];
			data[i][3]=day_shift[i];
			data[i][4]=evening_shift[i];
			data[i][5]=rest[i];
			data[i][6]=leave[i];
		}
		
		String[] ch= {"DATE","DAY","MORNING SHIFT","DAY SHIFT","EVENING SHIFT","REST","LEAVE"};
		JTable table=new JTable(data,ch);
		table.setPreferredScrollableViewportSize(getMaximumSize());

		JButton button;
		JButton button1;
		JScrollPane js=new JScrollPane(table);
		js.setBounds(20,40,1300,1500);
		button=new JButton("PRINT");
		button.setBounds(630,580,250,30);
		button1=new JButton("EXPORT TO EXCEL");
		button1.setBounds(630,630,250,30);
		getContentPane().add(js,BorderLayout.CENTER);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				MessageFormat header=new MessageFormat("DUTY ROSTER FOR "+str_date);
				MessageFormat footer=new MessageFormat("SR.ENGINEER(IT)/IT CENTRE(console operation)");
				try {
					table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showInternalMessageDialog(null, "unable to print");
				}
				
			}
		});
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{ String f=str_date.replace("/",",");
				String a=
				    	"C:\\Users\\User\\Desktop\\"+f+".xls\\";
				File file=new File(a);
             
    try{
    	
        TableModel model = table.getModel();
        FileWriter excel = new FileWriter(file);

        for(int i = 0; i < model.getColumnCount(); i++){
            excel.write(model.getColumnName(i) + "\t");
        }

        excel.write("\n");

        for(int i=0; i< model.getRowCount(); i++) {
            for(int j=0; j < model.getColumnCount(); j++) {
                excel.write(model.getValueAt(i,j).toString()+"\t");
            }
            excel.write("\n");
        }

        excel.close();

    }catch(IOException e1){ System.out.println(e1); }}
});

				
		add(button);
		add(button1);
		add(js);
		setLayout(null);
		setSize(1500,1500);
		getContentPane().setBackground(Color.decode("#bdb76b"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
private static Scanner sc;
public static int k=0;
public static File temp;
public static void main(String ar[]) throws FileNotFoundException {
	 

	 int c=0,p=0,z=0;
	  sc = new Scanner(System.in);
    Calendar now = Calendar.getInstance();
     int y1=now.get(Calendar.YEAR);
    // month start from 0 to 11
    int m1=now.get(Calendar.MONTH) + 1;                                                                         //taking the value of current month
   System.out.println("enter month number and year for required roster as mmyyyy");
    String u=sc.nextLine();
    String u1=u.substring(0,2);
    String u2=u.substring(2);
    int m2=Integer.valueOf(u1);                                                                                 //taking input of the wanted month
    int y2=Integer.valueOf(u2);
    if(m2<1 || m2>12)
    {
    	System.out.println("WRONG INPUT!!!! Month should be between 1 and 12\nExecute again");
    	System.exit(0);
    }
    
    int md,yd;
    md=m2-m1;                                                                                                   //getting the difference
    yd=y2-y1;
    md=md+yd*12;                                                                                                //calculate difference in no of months from current month taking year in consideration
    now.add(Calendar.MONTH, md);                                                                                //adding the difference in months to the current month and passing it to calender function
    
    
    Date date = now.getTime();  
    DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");  
    String strDate = dateFormat.format(date);  
    System.out.println("Calender for:"+strDate);
     
   
     now.set(Calendar.DATE,1);
    String[] strDays = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu",
        "Fri", "Sat" };
    
    System.out.print("Enter number of employees:");
    
    int l=sc.nextInt();
    String[] emp_name = new String[l];

    
    // Day_OF_WEEK starts from 1 while array index starts from 0
    int max_rest=0;
    int res = now.getActualMaximum(Calendar.DATE);
    int day[]=new int[res]; 
  
    String day_shift[]=new String[res];
    String morning_shift[]=new String[res];
    String evening_shift[]=new String[res];
    String rest[]=new String[res];
    String leave[]=new String[res];
    String[] day_of_week=new String[res]; 
    String[] shifts=new String[res] ;
    int[] holiday_list=new int[res]; 
    c=(now.get(Calendar.DAY_OF_WEEK)-1);
    z=c;
    p=c;
   
    
    String f1=strDate.replace("/",",");
	String filename1 = "roster_for_"+f1+".file";
	temp=new File(filename1);
    if(temp.exists()==false)
    {
        
        System.out.println("Assign shifts to employees\nEnter names of employees in morning shift:(Press 0 to exit)");
        int xy,cm=0;
        
        	for(int i=0;i<emp_name.length;i++) {
        		
        		String s=sc.nextLine();
        		if(s.equals("0")==false) {
        		emp_name[cm++]=s;
        		}
        		else break;
        	}
       System.out.println(cm);
        	xy=l-cm;
        	System.out.println("Enter names of employees in evening shift:");
        	int b=cm;
         for(int i=cm;i<emp_name.length;i++) 
        {
        		
        		String s=sc.nextLine();
        		emp_name[b++]=s;
        		
        }
    	
    String cm1=Integer.toString(cm);
    
    	String f=strDate.replace("/",",");
    	//String filename="roster_for_"+f+".file";
    	 String f2="emp"+f+".txt";
    	  BufferedWriter outputWriter = null;
    	  try {
			outputWriter = new BufferedWriter(new FileWriter(f2));
			outputWriter.write(cm1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	  for (int i = 0; i <l; i++) 
          {
    	    // Maybe:
    	    try {
				outputWriter.write(emp_name[i]+"");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	  
    	    try {
				outputWriter.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	  }
    	  try {
			outputWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	  try {
			outputWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	
     for(int i=0;i<res;i++)
    {
    	
   	day[i]=i+1;
   	day_of_week[i]=strDays[c];
   	if((c==6 && i<=13 && i>=7)||c==0)
   	{
   		day_shift[i]="Rest to all";
   		max_rest++;
   	}
   	else
   		day_shift[i]=" ";
   	c++;
   	if(c>6)
   		c=0;
   	 }
    hol(cm,holiday_list,day,day_shift,morning_shift,evening_shift,res,emp_name,day_of_week);
    for(int i=0;i<res;i++)
    {
    	morning_shift[i]="";
    	evening_shift[i]="";
    }
  
   for(int i=0;i<res;i++)
   {
	   if(day_shift[i]!="Rest to all")
	   {
		   for(int j=0;j<cm;j++) {
	    		
	 morning_shift[i]=emp_name[j]+"    "+morning_shift[i];
	 //System.out.println(morning_shift[i]);
	 }
		   for(int j=cm;j<l;j++) {
	 evening_shift[i]=emp_name[j]+"    "+evening_shift[i];}
	 
       }
	   else
	   {
			 morning_shift[i]="\t";
			 evening_shift[i]="\t";
			

	   }
   }
	
 
 for(int i=0;i<res;i++)
 {
	 if(z==6 && i<=13 && i>=7)
	 
	{
		 day_shift[i]=rest[i]=" ";
	}
	 z++;
	 if(z>6)z=0;
 }
 
 
 for(int i=0;i<res;i++)
 {
	 if(leave[i]==null)leave[i]="";
	 if(rest[i]==null)rest[i]="     ";
 }
//*********************************************************************************************************************
for(int i=0;i<res;i++)//changes in shifts due to leave
{
	 for(int j=0;j<emp_name.length;j++)
	 {
		 if(leave[i].equals(emp_name[j]))
		 {
			 for(int n=0;n<emp_name.length;n++) {
			 if(emp_name[j].equals(emp_name[0]))
				 morning_shift[i]=emp_name[1];
			 else if(emp_name[j].equals(emp_name[1]))
				 morning_shift[i]=emp_name[0];	 
			 else if(emp_name[j].equals(emp_name[2]))
				 evening_shift[i]=emp_name[3]+"    "+emp_name[4];
			 else if(emp_name[j].equals(emp_name[3]))
				evening_shift[i]=emp_name[2]+"    "+emp_name[4];
			 else
				 evening_shift[i]=emp_name[2]+"    "+emp_name[3];
				 
		 }
	 }
}
}

calender c1=new calender(strDate,res,day,day_of_week,morning_shift,day_shift,evening_shift,rest,leave);
c1.setVisible(true);
System.out.println("DO YOU WANT TO EDIT THE TABLE:y/n");
char v=sc.next().charAt(0);
switch(v)
{

case 'Y':
case 'y':System.out.println("EDIT TABLE");
 edit(holiday_list,max_rest,shifts,strDate, c1,emp_name,res,day,day_of_week,morning_shift,day_shift,evening_shift,rest,leave);
 break;
case 'N':
		case 'n':System.out.println("table not edited ");
		break;
	default:	System.out.println("WRONG INPUT!!!! \tExecute again");
	System.exit(0);
}	
    
save_table(strDate,res,day_of_week,day,morning_shift,day_shift,evening_shift,rest,leave)	;
	
    }
    //modify
    else {
    	
    	String f=strDate.replace("/",",");
    	String filename="roster_for_"+f+".file";
    	 String f2="emp"+f+".txt";
    		JTable table;
    		
    		//File f22=new File(f2);
    			
    			try {
    				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
    				//ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream(f2));
    				table = (JTable) ois.readObject();
    			}
    			catch(Exception e) {
    				System.out.println("Problem reading back table from file: " + filename);
    				return;
    			}
    			String[] sday=new String[res];String[][]data=new String[res][7];
    			for(int i=0;i<res;i++)
    			{
    				for(int j=0;j<7;j++)
    				{
    				data[i][j]=(String) table.getValueAt(i, j);
    				}
    			}

    			for(int i=0;i<res;i++)
    			{
    				for(int j=0;j<7;j++)
    				{
    					sday[i]=data[i][0];           
    					day_of_week[i]=data[i][1];
    					morning_shift[i]=data[i][2];
    					day_shift[i]=data[i][3];
    					evening_shift[i]=data[i][4];
    					rest[i]=data[i][5];
    					leave[i]=data[i][6];
    				}
    	
    			}
    			
    			for(int i=0;i<res;i++)
    				day[i]= Integer.valueOf(sday[i]);
    			 calender c1=new calender(strDate,res,day,day_of_week,morning_shift,day_shift,evening_shift,rest,leave);
    			 c1.setVisible(true);
    			 String token1 = "";
    			 Scanner inFile1 = new Scanner(new File(f2));
               List<String> temps = new ArrayList<String>();
                 while (inFile1.hasNext()) {
    			      
    			      token1 = inFile1.next();
    			      temps.add(token1);
    			    }
    			    inFile1.close();
                   String cm1=temps.get(0);
                    int cm = Integer.valueOf(cm1);
    			    emp_name = temps.toArray(new String[1]);

    			System.out.println("\nDUTY ROSTER EXISTS FOR SELECTED MONTH");
    			 System.out.println("Do you want to edit gazzeted holidays:y/n");
    			 String h=sc.nextLine();
    			 switch(h)
    			 {  
    			 case "Y":
    			 case "y":hol(cm,holiday_list,day,day_shift,morning_shift,evening_shift,res,emp_name,day_of_week);
    			 
    			 case "N":
    			 case "n":edit(holiday_list,max_rest,shifts,strDate, c1,emp_name,res,day,day_of_week,morning_shift,day_shift,evening_shift,rest,leave);
    			save_table(strDate,res,day_of_week,day,morning_shift,day_shift,evening_shift,rest,leave)	;
    			break;
    			default:System.out.println("WRONG INPUT!!!!:ENTER y/n");
    			System.exit(0);
    			break;
    }
    }   
}

public static void rst(int[] sat_worked,int max_rest,int res,String[] emp_name,String[] shifts,int[]day,String[] morning_shift,String[] day_shift,String[] evening_shift,String []rest,String[]leave)
{
	
	int res_des=max_rest+k;

	int[] res_taken=new int[emp_name.length];
	int yy=emp_name.length;
	int[] leave_of_each= new int[yy];
	
	for(int i=0;i<leave_of_each.length;i++)
	{
		leave_of_each[i]=0;
	}
for(int i=0;i<leave.length;i++)
{
	for(int j=0;j<emp_name.length;j++)
	{
		if(leave[i].equals(emp_name[j]))
			leave_of_each[j]++;
	}
}
for(int i=0;i<leave_of_each.length;i++)
{
	res_taken[i]=leave_of_each[i]+max_rest+k-sat_worked[i];
}
for(int i=0;i<leave_of_each.length;i++)
{
	if(res_taken[i]<res_des)
	{
		System.out.println(emp_name[i]+" has pending rest,assign rest on:");
		int dd=sc.nextInt();
		rest[dd-1]=emp_name[i];
	}
	
}
for(int i=0;i<res;i++)
{
if (morning_shift[i].contains(rest[i])) { 
	
    
    morning_shift[i] = morning_shift[i].replaceAll(rest[i], " ").trim();
    
} 
if (evening_shift[i].contains(rest[i])) { 
	  
    
    evening_shift[i] = evening_shift[i].replaceAll(rest[i], " ").trim(); 
} 
} 
}//end of res




public static void edit(int[]holiday_list,int max_rest,String[]shifts,String strDate,calender c1,String[] emp_name,int res,int[]day,String[]day_of_week,String[] morning_shift,String[] day_shift,String[] evening_shift,String []rest,String[]leave)
{calender c2;


System.out.println("ASSIGN SHIFTS TO EMPLOYEES(press 0 to exit)");
	int x=1;int[] sat_w= {0,0,0,0,0};calender c3 = null;int w=0;
	while(x!=0)
	{ 
	
		System.out.print("enter day for which shift needs to be edited: ");
		 x=sc.nextInt();
		 if(x==0)continue;
		 sc.nextLine();
		
		 if(day_of_week[x-1].equals("Sat")){
		 day_shift[x-1]=" ";
		  for(int i=0;i<res;i++)
	       {
		    
		if(rest[i]!="     ")
		{
		if(rest[i].equals(emp_name[0])||rest[i].equals(emp_name[1]))
		{morning_shift[i]=rest[i]+"    "+morning_shift[i];rest[i]="     ";}
		if(rest[i].equals(emp_name[2])||rest[i].equals(emp_name[3])||rest[i].equals(emp_name[4]))
		{evening_shift[i]=rest[i]+"    "+evening_shift[i];rest[i]="     ";}
		
		}
		
		}
		 c3=new calender(strDate,res,day,day_of_week,morning_shift,day_shift,evening_shift,rest,leave);
		 c1.setVisible(false);
		c3.setVisible(true);
		w=1;
		
	
		}
		 
		 for(int i=0;i<res;i++)
		 {
			if(day[i]==x)
			{
				 morning_shift[i]=" ";
				 day_shift[i]=" ";
				 evening_shift[i]=" ";
				 leave[i]=" ";
				 
				 
				 
				System.out.println("Enter allocation for the employees");
				System.out.println("m-Morning\td-Day\te-Evening\tl-Leave\t\tPress ENTER for no allocation");
				for(int j=0;j<emp_name.length;j++)
				{
					System.out.println("Employee name:"+emp_name[j]);	
					System.out.print("Assign shift:");
					shifts[j]=sc.nextLine();
					if(shifts[j].equals("m"))
					{
						
						morning_shift[i]=emp_name[j]+"    "+morning_shift[i];
					}
					
					if(shifts[j].equals("d"))
					{   
					     
						sat_w[j]++;
				
	                    
						day_shift[i]=emp_name[j]+"    "+day_shift[i];
					}
					if(shifts[j].equals("e"))
					{
						
						evening_shift[i]=emp_name[j]+"    "+evening_shift[i];
					}
					if(shifts[j].equals("l"))
					{
						
						leave[i]=emp_name[j]+"    "+leave[i];
					}
					
					
				}
				
			}
		 }
		
	 	 
	}
	rst(sat_w,max_rest,res,emp_name,shifts,day,morning_shift,day_shift,evening_shift,rest,leave);
	 c2=new calender(strDate,res,day,day_of_week,morning_shift,day_shift,evening_shift,rest,leave);
	c2.setVisible(true);
	if(w==1)
	c3.setVisible(false);	
	c1.setVisible(false);
}

public static void save_table(String strDate,int res,String[] day_of_week,int[]day,String[]morning_shift,String []day_shift,String[] evening_shift,String[]rest,String[]leave)
		{

    String sday[]=new String[res];
	for(int i=0;i<res;i++)
		sday[i]= Integer.toString(day[i]);
    Object[][] data=new Object[res][7];
	for(int i=0;i<res;i++) 
	{
		data[i][0]=sday[i];              
		data[i][1]=day_of_week[i];
		data[i][2]=morning_shift[i];
		data[i][3]=day_shift[i];
		data[i][4]=evening_shift[i];
		data[i][5]=rest[i];
		data[i][6]=leave[i];
	}
	
	String[] ch= {"DATE","DAY","MORNING SHIFT","DAY SHIFT","EVENING SHIFT","REST","LEAVE"};
	JTable table=new JTable(data,ch);
	String f=strDate.replace("/",",");
	String filename = "roster_for_"+f+".file";
    
	    try {
		    	
		    		            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
		    	
		    		            oos.writeObject(table);
		    	
		    		            oos.close();
		    	
		    		        }
		    	
		    		        catch(IOException e) {
		    	
		    		            System.out.println("Problem creating table file: " + e);
		    	
		    		            return;
		    	
		    		        }
		    	
		  
		   System.out.println("JTable correctly saved to file " + filename);
	
}

public static void hol(int cm,int[] holiday_list,int[]day,String[]day_shift,String[]morning_shift,String[]evening_shift,int res,String[] emp_name,String[]day_of_week) {
	 
	if(temp.exists()) {
		int b=0;
	 for(int i=0;i<res;i++)
	    {
	    	
	   	
	   		
	   			if(day_shift[i].equals("Rest to all")) {
	   				if( day_of_week[i].equals("Sun")==false) {
	   		   			
	   			holiday_list[b++]=day[i];
	   		}
	   		
	   	}
	   k=b;
	   	 }
	}
	
	 for(int i=0;i<res;i++)
	    {
	    for(int j=0;j<k;j++)
	    {
	    if(day[i]==holiday_list[j]) {
	    	day_shift[i]="\t";
	    	   for(int m=0;m<cm;m++) {
		    		
	    			 morning_shift[i]=emp_name[m]+"    "+morning_shift[i];
	    			 
	    			 }
	    				   for(int m=cm;m<emp_name.length;m++) {
	    			 evening_shift[i]=emp_name[j]+"    "+evening_shift[i];}
	    			 
	    		       
//***************************************	    	
	    }
	    }
	    }
	for(int i=0;i<k;i++)
	{
		holiday_list[i]=0;
	}
	 System.out.println("\nEnter the date of the gazetted holidays in the month: (PRESS 0 TO EXIT)");
	    
	    while(k<res)
	    {    int a=sc.nextInt();
	    	if(a!=0)
	    	{holiday_list[k++]=a;
	    	}
	    	else
	    		break;
	    }
	    for(int i=0;i<res;i++)
	    {
	    for(int j=0;j<k;j++)
	    {
	    if(day[i]==holiday_list[j]) {
	       		day_shift[i]="Rest to all";
	    morning_shift[i]="\t";
	    evening_shift[i]="\t";}
	    
	    }
	    }
	     
}

}