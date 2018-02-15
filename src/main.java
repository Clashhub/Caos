import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; //I added this

public class main extends JFrame {
// I added this	
	private CardLayout card;
	private int[] c_time;
	private int[] w_time; 
	private int[] a_time; 
	private int[] p_id;
	private int[] b_time;
	private float t_tt;
	private float t_wt;
	private int[] t_t;
	private List<int[]> ready_queue = new ArrayList<int[]>();
	private int[][] processes;  //Also known as the new queue
	private int time=0;
	private int noOfProcesses=0;
	private List<List<String>> plotGcs;
	private int[] turn_time;
	private int[] wait_time;
	private int[] start_time;
	//Memory maps
	private static Integer[] psArr;
	private static Integer[] iPsArr;
	private static Integer[] memMap;
	private static Integer[] memProcess;
	//Priority
	private int apw1[];
	private int pr[];
	private int pr1[];
	private int p[];
    private int turn[];	
	private int bt[];
	
	private static Integer[] partitionArr;
	private static int memoryPartitions;  //The number of memory partitions
	private static int memoryProcesses; //The number of memory processes
// I added this	
    public main()
    {
    	this.plotGcs = new ArrayList<List<String>>();
    	this.setTitle("CAOS Program");
    	this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    	this.setUndecorated(false);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	getContentPane().setLayout(null);
    	
    	this.card = new CardLayout();
    	this.setLayout(this.card);
  
    	this.showMainScreen();
        
    	this.setVisible(true);    	
        
    }

    
	public static void main(String[] args) {
		
		main mainGUI = new main();
	}
    
	public static void setPartitionSizeArr(Integer[] psa, Integer[] iPsa)
	{
		psArr = psa;
		iPsArr = iPsa;
	}
	
	public Integer[] getPsa() {
		
		return psArr;
	}
	
	public Integer[] getIpsa()
	{
		return iPsArr;
	}
	
	public Integer[] getMemMap()
	{
		return memMap;
	}
	
	public static void setMemMap(Integer[] mm)
	{
		memMap = mm;
	}
	
	public static void setMemProcess(Integer[] mp)
	{
		memProcess = mp;
	}
	
	public static Integer[] getMemProcess()
	{
		return memProcess;
	}
	
	public void WorstFit(){
		
		//Scanner scanner = new Scanner(System.in);
		 int noOfProcesses;
		 int noOfPartition;
		 
		// blockSize[],processSize[];
		 System.out.println("Enter the number of Partitions: ");	 
		 Integer[] InitialPartitionSizeArray = partitionArr; 
		 noOfPartition= InitialPartitionSizeArray.length;
		 Integer[] PartitionSizeArray = new Integer[noOfPartition];
		 for(int p = 0 ; p<noOfPartition; p++)
		    PartitionSizeArray[p] = InitialPartitionSizeArray[p];
		 
		 //Integer[] PartitionSizeArray = new Integer[noOfPartition]; 
		 
		 
		 System.out.println("Enter the number of Processes: ");
		 Integer[] ProcessSizeArray = getMemProcess(); 
		 noOfProcesses= ProcessSizeArray.length;
		 
		 Integer[] memoryMap = new Integer[noOfProcesses]; //process memory map 
		 
	/*	 int partitionSize;
		 for(int x=0;x<noOfPartition;x++){
			 System.out.println("Please enter Partition Size for Partition "+(x+1));
			 partitionSize = Integer.parseInt(scanner.next());
			 PartitionSizeArray[x] = partitionSize;
			 InitialPartitionSizeArray[x] = partitionSize;
		 }*/
		 
		/* for(int x=0;x<noOfProcesses;x++){
			 System.out.println("Please enter Process Size for Process "+(x+1));
			 ProcessSizeArray[x] = Integer.parseInt(scanner.next());
		 }*/
		 
		 
		 System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		 boolean allProcessAllocated=true;
		 for(int x=0;x<noOfProcesses;x++){
		           int max = PartitionSizeArray[0];
		           int pos = 0;
		             
		           for(int y=0;y<noOfPartition;y++){
		            if(max < PartitionSizeArray[y]) 
		             { max = PartitionSizeArray[y]; 
		                pos = y;
		             } 
		           }
		            if(max >= ProcessSizeArray[x])
		            {
		            	PartitionSizeArray[pos] = PartitionSizeArray[pos]-ProcessSizeArray[x];
		            	memoryMap[x] = pos;
		                System.out.println("\nProcess no. "+(x+1)+" (Process Size : "+ProcessSizeArray[x]+") is allocated to Partition "+(pos+1)+" (Current Partition Size : "+PartitionSizeArray[pos]+"/"+InitialPartitionSizeArray[pos]+")");		            
		            }
		            else{
		            System.out.println("\n\n\nProcess no. "+(x+1)+" (Process Size : "+ProcessSizeArray[x]+") can't be allocated");    
		            memoryMap[x] = -1;
		            allProcessAllocated=false;
		            }
		  }
		 System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		 
		 
		 System.out.println("\nAll Process allocated = "+allProcessAllocated+"\n");
		 
		 System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		 System.out.println("Partition Usage:");
		 for(int x=0;x<noOfPartition;x++){
			 System.out.println("Partition no. "+(x+1)+" : "+PartitionSizeArray[x]+"/"+InitialPartitionSizeArray[x]);
		 }
		 
		 setPartitionSizeArr(PartitionSizeArray, InitialPartitionSizeArray);
		 setMemMap(memoryMap);
		 setMemProcess(ProcessSizeArray);
		 
		 System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		 
		
		 
		 int currentPartitionSize,initialPartitionSize;
		 int internalFrag=0,externalFrag=0;
		 
		 for(int x=0;x<noOfPartition;x++){
			 currentPartitionSize=PartitionSizeArray[x];
			 initialPartitionSize=InitialPartitionSizeArray[x];
			if(currentPartitionSize!=initialPartitionSize){
				System.out.println("internal frag on partition "+(x+1));
				internalFrag=internalFrag+currentPartitionSize;
			}else{
				externalFrag=externalFrag+currentPartitionSize;
			}
		 }
		 
		 if(allProcessAllocated==true){
			externalFrag=0;
		 }
		 System.out.println("\n\n");
		 System.out.println("Internal Frag = "+internalFrag);
		 System.out.println("External Frag = "+externalFrag);
	}
	
    public void BF(){
    	 //Scanner scanner = new Scanner(System.in);
 	     int x,y,noOfProcesses,noOfPartition,temp,lowest=10000;
 		
      ///User input no. of processes and size for each partition/////////////////////////////////////////////
 	
 	     //System.out.println("Please input no. of Partitions:");
 	 	 //noOfPartition=Integer.parseInt(scanner.next());
 		 Integer[] PartitionSizeArray= partitionArr;
 		 noOfPartition = PartitionSizeArray.length;
 			
 		 //for(x=0;x<noOfPartition;x++)
 		 //{
 	  		//System.out.println("Please input partition size for partition "+x);
 			//PartitionSizeArray[x]=Integer.parseInt(scanner.next());
   		 //}
 			///////////////////////////////////////////////////////////////////////////////////////////////////////
 					
 			
 		 //User input no. of processes and size for each process///////////////////////////////////////////////
 		 //System.out.println("Please input no. of Processes:");
 		 //noOfProcesses=Integer.parseInt(scanner.next());
	 
 		 ArrayList<Integer> ProcessSizeList = new ArrayList<Integer>(Arrays.asList(getMemProcess()));//ArrayList for Sorting 
 			
 		 //for(x=0;x<noOfProcesses;x++)
 		 //{
 			 //System.out.println("Please input Process size for process "+(x+1));
 			 //ProcessSizeList = ;
 			 noOfProcesses = ProcessSizeList.size();
 		 //}
 			
 		 //Sort List in ascending order
 		 Collections.sort(ProcessSizeList);
 			
 		 //create array to store the processes
 		 Integer[] tempArray=new Integer[noOfProcesses];
 			
 		 //assign list to array
 		 ProcessSizeList.toArray(tempArray);
 			
 		 Integer[] ProcessSizeArray= new Integer[noOfProcesses];
 			
 		for(x=0;x<noOfProcesses;x++)
 		{
 			ProcessSizeArray[x]=tempArray[x];
 		}
 			///////////////////////////////////////////////////////////////////////////////////////////////////////
 			
 			
 			//Check output////////////////////////////////////////////////////////////////////////////////////////
 			System.out.println("\n\nNo of Partitions : "+noOfPartition);
 			for( x=0;x<noOfPartition;x++){
 				System.out.println("Partition no."+(x+1)+":\tPartition Size:"+PartitionSizeArray[x]);
 			}
 			System.out.println("\nNo of processes : "+noOfProcesses);
 			for( x=0;x<noOfProcesses;x++){
 				System.out.println("Process no:"+(x+1)+":\tProcess Size:"+ProcessSizeArray[x]);
 			}
 			///////////////////////////////////////////////////////////////////////////////////////////////////////
 			
 			
 			//Initialize boolean to check if process has been loaded into memory
 			Boolean Loaded=false;
 			
 			//Initialize boolean array to store loaded status of each processes 
 			Boolean[] ProcessLoaded = new Boolean[noOfProcesses];
 			
 			
 			//Initialize partition array to store status of partition weather a process has been assigned to it (1: assigned, 0= not assigned)
 			int[] PartitionStatus= new int[noOfPartition];
 			
 			//Initialize array to store the index of a block that is used by a file
 		    Integer[] ff= new Integer[noOfProcesses];
 			
 			//Initialize array to store the fragment of a partition
 			Integer[] frag= new Integer[noOfPartition];
 			
 		
 			
 			//Assign Processes to partitions//////////////////////////////////////////////////////////////////////
 			 for(x=0;x<noOfProcesses;x++)
 			 {
 				 
 				  for(y=0;y<noOfPartition;y++)
 				  {
 					   if(PartitionStatus[y]!=1)
 					   {
 						    temp=PartitionSizeArray[y]-ProcessSizeArray[x];
 						    if(temp>=0){
 							    if(lowest>temp)
 							    {
 								     ff[x]=y;
 								     lowest=temp;
 								     Loaded=true;
 							    }
 						    }
 					   }
 				  }
 			  ///////////////////////////////////////////////////////////////////////////////////////////////////////  
 			  
 				  
 				  
 			  //If process has been loaded into partition, Set true for the process/////////////////////////////////
 			  if(Loaded==true){
 				  ProcessLoaded[x]=true;
 			  }else{
 				  ProcessLoaded[x]=false;
 			  }
 			  ///////////////////////////////////////////////////////////////////////////////////////////////////////	
 			  
 			  frag[x+1]=lowest;
 			  PartitionStatus[ff[x]]=1;
 			  lowest=10000;
 			  Loaded=false;
 			 }
 			 
 			
 			 ///////////////////////////////////////////////////////////////////////////////////////////////////////
 			 int totalInternalFrag=0;
 			 
 			 System.out.println("\n\nProcess No. \tProcess size \tPartition no. \tPartition size\tFragment ");
 			 for(x=0;x<noOfProcesses && ff[x]!=0;x++) {//line edited on 23/09/2013
 			 System.out.println("\n"+(x+1)+"\t\t"+ProcessSizeArray[x]+"\t\t"+ff[x]+"\t\t"+PartitionSizeArray[ff[x]]+"\t\t"+frag[x]);
 			 totalInternalFrag=totalInternalFrag+frag[x+1];
 			 }
 			 
 			 setPartitionSizeArr(frag,PartitionSizeArray);
 			 setMemMap(ff);
 			 setMemProcess(ProcessSizeArray);
 			 
 			 for(x=0;x<noOfPartition;x++){
 				 System.out.println("Partition no. "+(x+1)+" allocated? :"+PartitionStatus[x]);
 			 }
 			 System.out.println("\n\n");
 			 
 			 
 			 int totalProcessLoaded=0;
 			 for(x=0;x<noOfProcesses;x++){
 				 if(ProcessLoaded[x]==false){
 					 System.out.println("Process "+(x+1)+" with size of "+ProcessSizeArray[x]+" not loaded into memory");
 				 }else{
 					 totalProcessLoaded++;
 				 }
 			 }
 			 
 			 int totalExternalFrag=0;
 			 if(totalProcessLoaded==noOfProcesses){
 				 System.out.println("\nAll processes allocated");
 				 System.out.println("External Fragmentation = 0");
 			 }else{
 				 System.out.println("\nNot all processes allocated");
 				 for(x=0;x<noOfPartition;x++){
 					 if(PartitionStatus[x]==0){
 					 totalExternalFrag=totalExternalFrag+PartitionSizeArray[x];
 					 }
 				 }
 				 System.out.println("External Fragmentation = "+totalExternalFrag);
 			 }
 			 
 			 System.out.println("\nInternal Fragmentation = "+totalInternalFrag);
 			 /////////////////////////////////////////////////////////////////////////////////////////////////////////
 	
	}
	public int[][] getPriorityArrs()
	{
		int[][] priorityArr = new int[6][noOfProcesses];
		priorityArr[0] = apw1;
		priorityArr[1] = pr;
		priorityArr[2] = pr1;
		priorityArr[3] = p;
	    priorityArr[4] = turn;	
		priorityArr[5] = bt;
		return priorityArr;
	}
	public void setPriority(int[] prio)
	{
		pr = prio;
	}
	
	public void showEPriority(int noOfpro)
	{
		enterPriority p6 = new enterPriority(this, noOfpro);
		this.add(p6, "epriority");
		this.card.show(getContentPane(), "epriority");		
	}
	
	public void nonPreemptivePriority()
	{
		//Scanner s = new Scanner(System.in);
         // Average process waiting time
             //
        int i=0,n,n1;
        
        int[][] pro = getProcesses(); // I added this
        
        //System.out.print("Enter the number of process: ");
        n = pro.length;
        bt = new int[n]; //Initialize bt
        
        for(int a=0; a<n; a++)
        {
        	bt[a] = pro[a][1];
        }

       /* for (i=0; i<n; i++){
            System.out.print("Enter Burst time for each process: p"+(i+1)+" ");
            bt[i] = s.nextInt();
        }*/
        n1 = n;
        //pr = new int[n];  //Priority
        pr1 = new int[n];
        float t = 0;
        int temp;
        turn = new int[n];
        p=new int[n];
        apw1=new int[n+1];
        apw1[0]=0;
        float tu=0;

       /* for (i=0; i<n; i++){
            System.out.print("Enter the priority for process: p"+(i+1)+" ");
            pr[i]=s.nextInt();
        }*/

        for (i=0; i<n; i++){
            pr1[i]=pr[i];
        }

        for( i=0;i<n;i++)
            for(int j=i+1;j<n;j++)
                if(pr1[i]>pr1[j])
                {
                    temp=pr1[i];
                    pr1[i]=pr1[j];
                    pr1[j]=temp;
                }
        for( i=0;i<n;i++)
            for(int j=0;j<n;j++)
                if(pr1[i]==pr[j])
                    p[i]=j+1;


        for(i=0;i<n;i++)
        {
            int k=p[i];
            apw1[i+1]=bt[k-1]+apw1[i];
        }

        for( i=0;i<n;i++)
        {
            System.out.println("indivisual waiting time for process p"+p[i]+" is "+apw1[i]+" ");
        }

        for( i=0;i<n;i++)
            t+=apw1[i];
        float avg=t/n;
        System.out.println("average waiting time is: "+avg);

        /*for( i=0;i<n;i++)
			System.out.println("response for process p"+p[i]+"is"+apw1[i]+" ");*/

        for( i=0;i<n;i++)
        {
            int k=p[i];
            turn[i]=bt[k-1]+apw1[i];
            System.out.println("turnaround time for process p"+p[i]+" is "+turn[i]+" ");
        }

        for( i=0;i<n;i++)
            tu+=turn[i];

        float avg1=tu/n;
        System.out.println("average turn-around time is: "+avg1);

        System.out.print("\n\nProcess \t Burst Time \t Wait Time \t Turn Around Time \t Priority \n");
        for (i=0;i<n;i++)
        {
            System.out.print("\n   "+p[i]+"\t\t   "+bt[p[i]-1]+"\t\t     "+apw1[i]+"\t\t     "+turn[i]+"\t\t     "+pr[i]+"   \n");
        }

	}
	
	public int[] getWaitTime()
	{
		return wait_time;
	}
	
	public int[] getTurnTime()
	{
		return turn_time;
	}
	

	public void setProcesses(int[][] processes)
	{
		this.processes = processes;
	}
	
	public int[][] getProcesses()
	{
		return this.processes;
	}
	
	public void setNoOfprocesses(int x)
	{
		this.noOfProcesses = x;
	}
	
	public int getNoOfProcesses()
	{
		return this.noOfProcesses;
	}
	
	public void setPartitions(Integer[] partitions)
	{	
       partitionArr = partitions;
	}
	
	
	public List<List<String>> getNoGCS()
	{
		return this.plotGcs;
	}
	
	public void showEnterNoOfpartitionsAndProcesses(int opt)
	{
		enterNoOfPartitionsAndProcesses ep9 = new enterNoOfPartitionsAndProcesses(this,opt);
		this.add(ep9, "partitionprocess");
		this.card.show(getContentPane(), "partitionprocess");
	}
	
	public void showEnterMemProcesses(int processes, int opt)
	{
		enterMemProcesses ep8 = new enterMemProcesses(this, processes, opt);
		this.add(ep8, "memoryprocess");
		this.card.show(getContentPane(), "memoryprocess");
	}
	public void showEnterPartitions(int partitions,int processes, int opt)
	{
		enterPartitions ep7 = new enterPartitions(this,partitions,processes, opt);
		this.add(ep7, "partitions");
		this.card.show(getContentPane(), "partitions");
	}
	public void showMemoryMapScreen()
	{
		memoryMap m4 = new memoryMap(this);
		this.add(m4, "memory");
		this.card.show(getContentPane(), "memory");
	}
	
	public void showPriorityScreen()
	{
		priorityScreen s3 = new priorityScreen(this);
		this.add(s3, "priority");
		this.card.show(getContentPane(), "priority");
	}
	public void showSJFP()
	{
		SJFPScreen s2 = new SJFPScreen(this);
		this.add(s2, "sjfp");
		this.card.show(this.getContentPane(), "sjfp");
	}
	
    public void showGCS()
	{
	   FCFSScreen s1 = new FCFSScreen(this, this.c_time, this.w_time, this.p_id, this.a_time, this.b_time, this.t_tt, this.t_wt, this.t_t);
	   this.add(s1, "fcfs");
	   this.card.show(this.getContentPane(), "fcfs");
	}
    public void showMainScreen()
    {
       Mainscreen ms = new Mainscreen(this);
       this.add(ms, "mainScreen");
       this.card.show(this.getContentPane(), "mainScreen");
    }

    public void showEnterProcesses(int noOfprocess, int opt)
    {
    	enterProcesses ep = new enterProcesses(this, noOfprocess, opt );
    	this.add(ep, "enterProcesses");
    	this.card.show(this.getContentPane(), "enterProcesses");
    }
    
    public void showInputNo(int opt)
    {
    	inputNoOfProcesses np = new inputNoOfProcesses(this, opt);
    	this.add(np, "inputNo");
    	this.card.show(this.getContentPane(), "inputNo");
    }
	public void FCFS(){
		
		//Scanner scanner = new Scanner(System.in);
		
		int noOfProcesses= getNoOfProcesses();
		
		System.out.println("input no. of processes");
		//noOfProcesses= Integer.parseInt(scanner.next());
		System.out.println("No. of Processes : "+noOfProcesses);
		float TTT=0;//Total turn around time
		float TWT=0;//Total wait time
		int AT[]= new int[noOfProcesses];// arrival time
		int BT[]= new int[noOfProcesses];//burst time
		int WT[]= new int[noOfProcesses];//waiting time = start time- arrival time
		int TT[]= new int[noOfProcesses];//Turnaround time = burst time + waiting time
		int PID[]= new int[noOfProcesses];//Process ID
		int CT[]= new int[noOfProcesses];//Completion Time=
		int temp;
		
				
		for(int x=0;x<noOfProcesses;x++){
			//System.out.println("Please enter arrival time for process "+x);
			AT[x]=processes[x][0];
			PID[x]= processes[x][2];
		}
		for(int x=0;x<noOfProcesses;x++){
			//System.out.println("Please enter burst time for process "+x);
			BT[x]=processes[x][1];
		}
		
		for(int x=0;x<noOfProcesses;x++){
			System.out.println("Process no : "+x+"\tArrival Time : "+AT[x]+"\tBurst Time : "+BT[x]);
		}
		
		//Order processes according to arrival times
		for(int x=0;x<noOfProcesses;x++){
			for(int z=0;z<(noOfProcesses-(x+1));z++){
				if(AT[z]>AT[z+1]){
					temp=AT[z];
					AT[z]=AT[z+1];
					AT[z+1]=temp;
					temp= BT[z];
					BT[z]=BT[z+1];
					BT[z+1]=temp;
					temp=PID[z];
					PID[z]=PID[z+1];
					PID[z+1]=temp;
				}
			}
		}
		
		//Finding completion time of each process
		for(int x=0;x<noOfProcesses;x++){
			if(x==0){
				CT[x]=AT[x]+BT[x];
			}else{
				if(AT[x]>CT[x-1]){ 
					CT[x]=AT[x]+BT[x];
				}else{
					CT[x]=CT[x-1]+BT[x];
				}
			}
			TT[x]=CT[x]-AT[x];
			WT[x]=TT[x]-BT[x];
			TWT+=WT[x];
			TTT+=TT[x];
			
		}
		System.out.println("\n\nPID\tAT\tBT\tCT\tTT\tWT");
		for(int x=0;x<noOfProcesses;x++){
			
			System.out.println(PID[x]+"\t"+AT[x]+"\t"+BT[x]+"\t"+CT[x]+"\t"+TT[x]+"\t"+WT[x]);
		}
		System.out.println("Total turn around time : "+TTT+"\tAverage turnaround time : "+(TTT/noOfProcesses));
		System.out.println("Total waiting time : "+TWT+"\tAverage waitng time : "+(TWT/noOfProcesses));
		
		// I added this
		this.c_time = CT;
	    this.w_time = WT;
		this.p_id = PID;
		this.a_time = AT;
		this.b_time = BT;
		this.t_tt = TTT;
		this.t_wt = TWT;
		this.t_t = TT;
		// I added this
		
	}
	public int processesEmpty()
	{
		int empty = 0;
		int c = 0;
		for(int p=0; p< processes.length; p++)
		{
			if(processes[p][2] == 0)
			{
			   c++;
			}
		}
		
		if (c == processes.length)
		{
			empty = 1;
		}
		return empty;
	}
	
	public void checkReadyQueue()
	{
		if(ready_queue.size() == 0)
		{
           for(int p=0;p<this.processes.length;p++)
           {
        	 if(this.processes[p][2] == 0)
        		 continue;
        	 else if(this.processes[p][0]<=time)
        	   {
        		   int temp[] = new int[3];
        		   temp[0] = processes[p][0];
        		   temp[1] = processes[p][1];
        		   temp[2] = processes[p][2];
        		   ready_queue.add(temp);    		   
        		   processes[p][2] = 0; //Setting the PID 0 signifies that the process is deleted
        	   }
           }
		}
		if(ready_queue.size() == 0)
		{
			plotGcs.add(new ArrayList<String>());
			plotGcs.get(plotGcs.size()-1).add(0,"IDLE");
			plotGcs.get(plotGcs.size()-1).add(1,Integer.toString(time));
			time++;	
			plotGcs.get(plotGcs.size()-1).add(2,Integer.toString(time));
		}
	}
	
	public int checkNewQueue(int bt)
	{
		int change = 0;		
		for(int p = 0; p<processes.length; p++)
		{
		  if(processes[p][2] == 0)
			continue;	  
		  else if(processes[p][0] <= time)
		  {
		    if(processes[p][1] == 0)
	    	  continue;
			else if(processes[p][1] < bt  )
			{
     		  int temp[] = new int[3];
     		  temp[0] = processes[p][0];
     		  temp[1] = processes[p][1];
     		  temp[2] = processes[p][2];
              ready_queue.add(0, temp);
              processes[p][2] = 0;  // setting the pid 0 signifies the process is deleted
              change = 1;
			}		
		  }
		}
		return change;
	}
	public void sjfPreemptive()
	{
		int o_time = 0;
		int x=0;
		int breakout =0;
		int[] temp;
		int current_min_ind = 0;
		int empty;
		int current_min = -1;
		plotGcs.clear();

	  do{
		x=0;
		current_min_ind = 0;
		checkReadyQueue();
		while(current_min_ind != ready_queue.size() - 1 && ready_queue.size() != 0)
		{			
			for(int i=current_min_ind; i< ready_queue.size(); i++)
			{
			    current_min = ready_queue.get(current_min_ind)[1];
				if(ready_queue.get(i)[1] < current_min)
				{
					temp = ready_queue.get(current_min_ind);
					ready_queue.set(current_min_ind, ready_queue.get(i));
					ready_queue.set(i,temp);
				}
			}
			current_min_ind++;	
		}
		
		while( x != ready_queue.size())
		{
			breakout = 0;
			for(int p=0; p<ready_queue.size();p++)
			{
				o_time = time;
				int pid = ready_queue.get(p)[2];
				while( ready_queue.get(p)[1] != 0)
				{
					int change = checkNewQueue(ready_queue.get(p)[1]);
					if(change == 0)
					{
						time++;
						ready_queue.get(p)[1]--;
					}
					else if (change == 1)
					{
						x = 0;
						breakout=1;
						plotGcs.add(new ArrayList<String>());
						plotGcs.get(plotGcs.size()-1).add(0,Integer.toString(pid));
						plotGcs.get(plotGcs.size()-1).add(1, Integer.toString(o_time));
						plotGcs.get(plotGcs.size()-1).add(2,Integer.toString(time));
						break;
					}
				}
				if(breakout==0)
				{
					x++;
					plotGcs.add(new ArrayList<String>());
					plotGcs.get(plotGcs.size()-1).add(0,Integer.toString(pid));
					plotGcs.get(plotGcs.size()-1).add(1, Integer.toString(o_time));
					plotGcs.get(plotGcs.size()-1).add(2,Integer.toString(time));
				}
				if(breakout==1)
					break;
			}
		}
		ready_queue.clear();
		empty = processesEmpty();		
	  }while(empty == 0);
	  
	  for(int a = 0; a<plotGcs.size(); a++)
	  {
		  System.out.println("[" + plotGcs.get(a).get(1) + " " + plotGcs.get(a).get(2) + "]");
		  System.out.println();
	  }
	  
	  turn_time = new int[noOfProcesses];
	  wait_time = new int[noOfProcesses];
	  start_time = new int[noOfProcesses];

	  for(int y=0; y<noOfProcesses; y++)
	  {
		  wait_time[y] = -1;

		  for(int o=0; o<plotGcs.size(); o++)
		  {
			  if(Integer.parseInt(plotGcs.get(o).get(1)) == Integer.parseInt(plotGcs.get(o).get(2)))
			  {
				  continue;
			  }
			  else if(plotGcs.get(o).get(0).equals("IDLE"))
				  continue;
			  else if(Integer.parseInt(plotGcs.get(o).get(0)) == y+1)
			  {
				  start_time[y] = Integer.parseInt(plotGcs.get(o).get(1));
				  break;
			  }
		  }
		  
		  for(int c=plotGcs.size()-1; c>=0; c--)
		  {
			  if(Integer.parseInt(plotGcs.get(c).get(1)) == Integer.parseInt(plotGcs.get(c).get(2)))
			  {
				  continue;
			  }
			  else if(plotGcs.get(c).get(0).equals("IDLE"))
			  {
				  continue;
			  }
			  else if(Integer.parseInt(plotGcs.get(c).get(0)) == y+1)
			  {
				  turn_time[y] = Integer.parseInt(plotGcs.get(c).get(2)) - processes[y][0];
				  break;
			  }
		  }
		  
		  
		  for(int i=plotGcs.size()-1; i>=0; i--)
		  {
			 int j = Integer.parseInt(plotGcs.get(i).get(1));
	         int k = Integer.parseInt(plotGcs.get(i).get(2));
			 if( j == k)
				 continue;
			 else if(plotGcs.get(i).get(0).equals("IDLE"))
				 continue;
			 else
			 {
			  if(Integer.parseInt(plotGcs.get(i).get(0)) == y+1 )
			  {
			    if(wait_time[y] == -1)
			    {
			      wait_time[y] = Integer.parseInt(plotGcs.get(i).get(1));
			    }
			    else
			    {
				  wait_time[y] = wait_time[y] - Integer.parseInt(plotGcs.get(i).get(2));
			    }
			  }			  
		     }
			  if(i==0)
			  {
			   if(start_time[y] != processes[y][0])	  
			   	wait_time[y] = wait_time[y] -  processes[y][0];
			   if(wait_time[y] == start_time[y])
				   wait_time[y] = wait_time[y] - start_time[y];
			  }
		  }
	  }
	  time = 0;
	  for(int a =0; a<noOfProcesses; a++)
	  {
		  System.out.println(wait_time[a]);
	  }
	  for(int a = 0; a<noOfProcesses ; a++)
		  System.out.println(start_time[a]);
	  
	  for(int a=0; a<noOfProcesses; a++)
		  System.out.println(turn_time[a]);
	}

}
