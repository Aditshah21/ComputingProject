import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;

public class WebSearchEngine
{


	public static void main(String[] args) throws IOException
	{
		int z = 0;
		do {
			
				/*********************    input from user    ***********************/
				Scanner sc = new Scanner(System.in);  
				System.out.println("                                                                           WELCOME TO OUR WEB SEARCH ENGINE           ");
				System.out.println("\n----> Type Anything to Search:\n");
				String name = sc.nextLine();
				String input = name.toLowerCase();
				
				String[] words = input.split("\\s"); //splitting words in user input
				
				/********************************************************************/
	 	       	
				/*********************    read 100 web URLs from given path    ***********************/
	 	       	
				FileReader file = new FileReader("urls_list/urls.txt");    //specify file path and file name 
		 	    BufferedReader br = new BufferedReader(file);
		 	    
		 	   /*********************    Accessing 200 parsed text files from given path    ***********************/
		 	    
		 	    File text_file = new File("urls_list/text"); //path of a folder contains .txt files
		 	    File[] text_files = text_file.listFiles(); //saving name of .txt files present in 'Text' folder
		 	    
		 	    File text_file1 =new File ("urls_list/Texts");//path of a folder contains .txt files
		 	    File[] text_files1 = text_file.listFiles(); //saving name of .txt files present in 'Text' folder
		 	    
		 	   /*************************   Jsoup Parsing   ******************************/
		 	    
		 	    int i = 0;
		 	    String text;
		 	    while ((text = br.readLine()) != null) //read .txt file line by line
			    {
		 	    	if (text_files.length < 100 && text_files1.length < 100) 
		 	    	{
		 	    		HTMLJsoup ht = new HTMLJsoup(text, i);//pass HTML web links and web link number to HTMLJsoup parameter
		 	    		HTMLJsoup1 ht1 = new HTMLJsoup1(text, i);//pass HTML web links and web link number to HTMLJsoup parameter
		 	    	}
		 	    	i++;
			    }
		 	    

		 	   /*********************************************************************/
		 	   
		 	    System.out.println("\n *******************   O U T P U T   ********************** \n");
		 	  
		 	   /*********************  TST (generate word sequence)   *******************************/
		 	    	boolean check_flag = false;
		 	    	
		 	    	long [] count_arr = new long[i]; //highest priority array
		 	    	long [] count_arr1=new long[i];//lowest priority array
		 	    	long [] count_arr2 = new long[i];//high
		 	    	long [] count_arr3 = new long[i];//low
		 	    	
		 	    	
		 	    	File file1 = new File("urls_list/text"); //path of a folder contains .txt files
		 			File[] listOfFiles1 = file1.listFiles(); //saving name of .txt files present in 'Text' folder
		 			
		 			
		 		    for (int l = 0; l < listOfFiles1.length; l++)//loop over 100 .txt files
		 		    {
		 		    	if (listOfFiles1[l].isFile())
		 			      {
		 		    			TST<Integer> st = new TST<Integer>(); //TST object
		 		    			
		 		    			File txt = new File("urls_list/text/"+(l)+".txt");//getting the .txt file with name according to the links number in urls.txt
		 		    			Scanner scan = new Scanner(txt);
		 		    			String strings = "";
		 	        
		 		    			List<String> lst = new ArrayList<String>();
		 	        
		 		    			while(scan.hasNext())//scan words in given file
		 		    			{
		 		    				strings = scan.next();
		 		    				
		 		    				Pattern pp = Pattern.compile("[a-zA-Z0-9]");
		 		    				Matcher mm = pp.matcher(strings);
		 		    				if(mm.find())
		 		    				{
		 		    					String trim_string = strings.replaceAll("[^a-zA-Z0-9]", "");//removing special characters or junks before making TST
		 		    					String lower = trim_string.toLowerCase();
		 		    					lst.add(lower);//append words in lst
		 		    				}
		 	        	
		 		    			}
		 		    			scan.close();

		 		    			String[] arr = lst.toArray(new String[0]);//converting lst into array and adding all words to arr[]
		 	      
		 		    			for (int j = 0; j < arr.length; j++)//loop over arr[]
		 		    			{
		 		    				st.put(arr[j], j); //creating TST
		 		    			}
		 		    			
		 		    			
		 		    			for(int k = 0; k < words.length; k++) //loop for each words in given user input
		 		    			{
		 		    				boolean flag = false;
		 		    				
		 		    				String trim_word = words[k].replaceAll("[^a-zA-Z0-9]", ""); //removing all special characters or junks from the words in user input
		 		    				
		 		    				if(st.get(trim_word) != null) //if word is available in TST
		 		    				{
		 		    					check_flag = true;
		 		    					
		 		    					/*****************   create inverted index    ********************/
		 		    					for (int j = 0; j < arr.length; j++)//loop over arr[]
		 		    					{
		 		    						
		 		    						if(trim_word.equals(arr[j]))
		 		    						{
		 		    							if(!flag)
		 		    							{
		 		    								
		 		    								count_arr[l] = count_arr[l] + 1;//creating highest priority array where index is the web site number according to files in text folder
		 	        		
		 		    							}
		 		    						}
		 	 	        	
		 		    					}
		 		    				}
		 		    				else
		 		    				{	
		 		    					flag = true;
		 		    					count_arr1[l] = count_arr[l];//creating lowest priority array where index is the web site number according to the files in text folder
		 		    					count_arr[l] = 0;
		 		    				
		 		    				}
		 	        
		 	     
		 		    			}/* for */
		 	       	
		 			      }/* if */
		 		    	}/* for */
		 		    
		 		    
		 		    
		 		   
		 			File file2=new File("urls_list/Texts");//path of a folder contains .txt files
		 			File[] listofFiles2=file2.listFiles();//saving name of .txt files present in 'Text' folder
		 		    
		 		    for (int l = 0; l < listofFiles2.length; l++) //loop over 100 .txt files
		 		    {
		 		    	if (listofFiles2[l].isFile())
		 			      {
		 		    			TST<Integer> st = new TST<Integer>(); //TST object
		 		    			File txt = new File("urls_list/Texts/"+listofFiles2[l].getName());//getting the .txt file with name according to the links number in urls.txt
		 		    			Scanner scan = new Scanner(txt);	
		 		    			String strings = "";
		 	        
		 		    			List<String> lst = new ArrayList<String>();
		 	        
		 		    			while(scan.hasNext())//scan words in given file
		 		    			{
		 		    				strings = scan.next();
		 		    				
		 		    				Pattern pp = Pattern.compile("[a-zA-Z0-9]");
		 		    				Matcher mm = pp.matcher(strings);
		 		    				if(mm.find())
		 		    				{
		 		    					String trim_string = strings.replaceAll("[^a-zA-Z0-9]", "");//removing special characters or junks before making TST
		 		    					String lower = trim_string.toLowerCase();
		 		    					lst.add(lower);//append words in lst
		 		    				}
		 	        	
		 		    			}
		 		    			scan.close();

		 		    			String[] arr = lst.toArray(new String[0]);//converting lst into array and adding all words to arr[]
		 	      
		 		    			for (int j = 0; j < arr.length; j++)//loop over arr[]
		 		    			{
		 		    				st.put(arr[j], j); //creating TST
		 		    			}
		 		    			
		 		    			
		 		    			for(int k = 0; k < words.length; k++) //loop for each words in given user input
		 		    			{
		 		    				boolean flag = false;
		 		    				
		 		    				String trim_word = words[k].replaceAll("[^a-zA-Z0-9]", ""); //removing all special characters or junks from the words in user input
		 		    				
		 		    				if(st.get(trim_word) != null) //if word is available in TST
		 		    				{
		 		    					check_flag = true;
		 		    					
		 		    					/*****************   create inverted index    ********************/
		 		    					for (int j = 0; j < arr.length; j++)//loop over arr[]
		 		    					{
		 		    						
		 		    						if(trim_word.equals(arr[j]))
		 		    						{
		 		    							if(!flag)
		 		    							{
		 		    								
		 		    								count_arr2[l] = count_arr2[l] + 1;//creating highest priority array where index is the web site number according to files in text folder
		 	        		
		 		    							}
		 		    						}
		 	 	        	
		 		    					}
		 		    				}
		 		    				else
		 		    				{	
		 		    					flag = true;
		 		    					count_arr3[l]=count_arr2[l];//creating lowest priority array where index is the web site number according to the files in text folder
		 		    					count_arr2[l]=0;
		 		    				}
		 	        
		 	     
		 		    			}/* for */
		 	       	
		 			      }/* if */
		 		    	}
		 		    
		 		    /*******************************************************************************/
		 		  if(check_flag) //if even one word of user input string matches with the word in TST
		 		  {
		 		   /***************************    Ranking the webs by Quick Sort   ****************************/
		 		   
		 		   Long[] sort_arr = new Long[i];
		 		   Long[] sort_arr1 = new Long[i];
		 		   Long[] sort_arr2 =new Long[i];
		 		   Long[] sort_arr3 =new Long[i];
		 		   
		 		   for (int c = 0; c < count_arr.length; c++)
		 		   {
		 			 
		 				   sort_arr[c] = count_arr[c];
		 			 
		 		   }
		 		  for (int c = 0; c < count_arr1.length; c++)
		 		   {
		 			 
		 				   sort_arr1[c] = count_arr1[c];
		 			 
		 		   }

		 		  for(int c=0; c<count_arr2.length;c++)
		 		  	{
		 			sort_arr2[c]=count_arr2[c];
		 		  	}


		 		  for(int c=0; c<count_arr3.length;c++)
		 		  	{
		 			sort_arr3[c]=count_arr3[c];
		 		  	}
		 		  long result=0;
		 		  long start=System.nanoTime();
		 		  Sort.quicksort(sort_arr); //sort the highest priority list which contains all the words of user input
		 		  Sort.quicksort(sort_arr1); //sort the lowest priority list which contains chunks of user input
		 		  
		 		  Sort.quicksort(sort_arr2);//high
		 		  
		 		   Sort.quicksort(sort_arr3);//low
		 		  
		 		  long end =System.nanoTime();
		 		  result=end-start;
		 		  System.out.println("Computing Time for Searching Word:"+result);
		 		 /*************************************************************************************************/
		 		 
		 		 /**************************    Show results of highest priority web links    ********************************/ 
		 		 
		 		 List<Integer> lst = new ArrayList<>( );
		 		 
		 		 // [1] , [2] , [3] , [4] are done to access the exact file. Before sorting the index of the array was .txt file number and value was number of word occurrences.
		 		 //After sorting, the index of the array according to .txt file was disturbed
	 			 //to access the old indexes we had to do [1] , [2] , [3] , [4]
		 		 for (int m = (sort_arr.length)-1; (m >= 0); m--)// [1] //loop over sorted list 'sort_arr'
				 {
		 			 
		 			 for(int n = 0; n < count_arr.length; n++)// [2] //loop over unsorted list 'count_arr'
		 			 {
		 				
		 				if (sort_arr[m] == count_arr[n] && count_arr[n] != 0) // [3]
					    {
		 			 		if(!lst.contains(n)) // [4]
		 			 		{
		 			 			lst.add(n);

		 			 			FileReader fr = new FileReader("urls_list/text/"+n+".txt");//accessing the file name one by one from Text folder 
		 			 			BufferedReader br1 = new BufferedReader(fr);
		 			 			
		 			 			Pattern regx = Pattern.compile("(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");//Regular expression for URL available in file
				    			
		 			 			String line = br1.readLine();//read fist line of parsed .txt file only which contains the URL of web site
					  	      
				    			Matcher ma = regx.matcher(line);//match URL pattern
				    			if (ma.find())//if URL found
				    			{ 
				    				System.out.println("\n"+ma.group()); //URL results shown to the user
				    			}
		 			 		}/* if */	
					      }/* if */
				    }/* for */
				 }/* for */
		 		 
		 		/**************************    Showing results of lowest priority web links    ********************************/ 
		 		List<Integer> lst2 = new ArrayList<>( );
		 		// same procedure likewise in high priority section
		 		for (int m = (sort_arr1.length)-1; (m >= 0); m--)// [1] //loop over sorted list 'sort_arr'
				 {
		 			 
		 			 for(int n = 0; n < count_arr1.length; n++)// [2] //loop over unsorted list 'count_arr'
		 			 {
		 			 	if (sort_arr1[m] == count_arr1[n] && count_arr1[n] != 0) // [3]
					    {
		 			 		if(!lst2.contains(n)) // [4]
		 			 		{
		 			 			lst2.add(n);

		 			 			FileReader fr = new FileReader("urls_list/text/"+n+".txt");//accessing the file name one by one from Text folder 
		 			 			BufferedReader br2 = new BufferedReader(fr);
		 			 			
		 			 			Pattern regx = Pattern.compile("(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");//Regular expression for URL available in file
				    			
		 			 			String line = br2.readLine();//read fist line of parsed .txt file only which contains the URL of web site
					  	      
				    			Matcher ma = regx.matcher(line);//match URL pattern
				    			if (ma.find())//if URL found
				    			{ 
				    				System.out.println("\n"+ma.group()); 
				    			}
		 			 		}/* if */	
					      }/* if */
				    }/* for */
				 }/* for */
		 		

		 			/***************************     Showing Results of Web Pages         *************************************************/
		 		
		 			/**************************      Highest Priority   ****************************/
		 		List<Integer> lst3 = new ArrayList<>( );
		 		// same procedure likewise in high priority section
		 		for (int m = (sort_arr2.length)-1; (m >= 0); m--)// [1] //loop over sorted list 'sort_arr'
				 {
		 			 for(int n = 0; n < count_arr2.length; n++)// [2] //loop over unsorted list 'count_arr'
		 			 {
		 				 if (sort_arr2[m] == count_arr2[n] && count_arr2[n] != 0) // [3]
					    {
		 			 		if(!lst3.contains(n)) // [4]
		 			 		{
		 			 			lst3.add(n);
		 			 			System.out.println(listofFiles2[n].getName());
		 			 		} /*if*/ 	
					      } /*if */
				    }/* for */
				 } /* for */
		 		
		 		
		 		//**************************      Lowest Priority   ****************************//
		 		/*List<Integer> lst4 = new ArrayList<>( );
		 		// same procedure likewise in high priority section
		 		for (int m = (sort_arr3.length)-1; (m >= 0); m--)// [1] //loop over sorted list 'sort_arr'
				 {
		 			 
		 			 for(int n = 0; n < count_arr3.length; n++)// [2] //loop over unsorted list 'count_arr'
		 			 {
		 				 if (sort_arr3[m] == count_arr3[n] && count_arr3[n] != 0) // [3]
					    {
		 			 		if(!lst4.contains(n)) // [4]
		 			 		{
		 			 			lst4.add(n);
		 			 			System.out.println(listofFiles2[n].getName());
		 			 		} if 	
					      } if 
				    } for 
				 }*/ /* for */ 
		 		 }
		 		
		 		
		 		  /******************************************************************************************/
		 		  
		 		  else
		 		  {
		 			 System.out.println("\n"+"Sorry. Your search did not match any documents!");
		 		  }
		 		  
		 		 /*********************    take new input from user after showing result for previous input    ***********************/
		 		  
		 		  System.out.println("\n");
		 		  System.out.println("Want to Search Again? Type 0 or 1 for 'Yes' or No'': ");
		 		  z = sc.nextInt();
		 		  while(z != 0 && z != 1)
		 		  {
		 			  System.out.println("Wrong input!");
		 			  z = sc.nextInt();
		 		  }
		 		 /********************************************************************/
		}while(z != 0);
		
		
		
	}/* main */

}
