
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***************************************************************
*
*  Compilation:  javac Brtue.java
*  Execution:    java Brute pattern text
*
*  Reads in two strings, the pattern and the input text, and
*  searches for the pattern in the input text using brute force.
*
*  % java Brute abracadabra abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:               abracadabra          
*
*  % java Brute rab abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:         rab                         
* 
*  % java Brute rabrabracad abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad
*  pattern:                        rabrabracad

*
*  % java Brute bcara abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:                                   bcara
* 
*  % java Brute abacad abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad
*  pattern: abacad
*
***************************************************************/

public class BruteForceMatch {

  /***************************************************************************
   *  String versions
   ***************************************************************************/

   // return offset of first match or N if no match
   public static int search1(String pat, String txt) {
       int M = pat.length();
       int N = txt.length();

       for (int i = 0; i <= N - M; i++) {
           int j;
           for (j = 0; j < M; j++) {
               if (txt.charAt(i+j) != pat.charAt(j))
                   break;
           }
           if (j == M) return i;            // found at offset i
       }
       return N;                            // not found
   }

   // return offset of first match or N if no match
   public static int search2(String pat, String txt) {
       int M = pat.length();
       int N = txt.length();
       int i, j;
       for (i = 0, j = 0; i < N && j < M; i++) {
           if (txt.charAt(i) == pat.charAt(j)) j++;
           else { i -= j; j = 0;  }
       }
       if (j == M) return i - M;    // found
       else        return N;        // not found
   }


  /***************************************************************************
   *  char[] array versions
   ***************************************************************************/

   // return offset of first match or N if no match
   public static int search1(char[] pattern, char[] text) {
       int M = pattern.length;
       int N = text.length;

       for (int i = 0; i <= N - M; i++) {
           int j;
           for (j = 0; j < M; j++) {
               if (text[i+j] != pattern[j])
                   break;
           }
           if (j == M) return i;            // found at offset i
       }
       return N;                            // not found
   }

   // return offset of first match or N if no match
   public static int search2(char[] pattern, char[] text) { 
       int M = pattern.length;
       int N = text.length;
       int i, j;
       for (i = 0, j = 0; i < N && j < M; i++) {
           if (text[i] == pattern[j]) j++;
           else { i -= j; j = 0;  }
       }
       if (j == M) return i - M;    // found
       else        return N;        // not found
   } 


   // test client
   public static void main(String[] args) {
	   In file = new In("harddisk.txt");
	   String text = file.readAll();
	   double start=0, end=0;
	   
	   start = System.currentTimeMillis();
	   for (int i=0; i<100; i++) {
    	   Integer[] occurences; 
    	   
    	   occurences = find_occurences("hard", text);	
    	   StdOut.println(occurences.length+" occurence/s of \"hard\" are found. Offsets below:");
    	   StdOut.println(Arrays.toString(occurences));
    	   
	       occurences = find_occurences("hard drive", text);
	       StdOut.println(occurences.length+" occurence/s of \"hard drive\" are found. Offsets below:");
	       StdOut.println(Arrays.toString(occurences));
	       
	       occurences = find_occurences("hard disk", text);
	       StdOut.println(occurences.length+" occurence/s of \"hard disk\" are found. Offsets below:");
	       StdOut.println(Arrays.toString(occurences));
	       
	       occurences = find_occurences("hard dist", text);
	       StdOut.println(occurences.length+" occurence/s of \"hard dist\" are found. Offsets below:");
	       StdOut.println(Arrays.toString(occurences));
	       
	       occurences = find_occurences("xltpru", text);
	       StdOut.println(occurences.length+" occurence/s of \"xltpru\" are found. Offsets below:");
	       StdOut.println(Arrays.toString(occurences));
       }
	   end = System.currentTimeMillis();
	   
	   System.out.println("Average Time: "+(end-start)/100);
   }
   public static Integer[] find_occurences(String pat, String nexttext) {
	   int offset=0;
       int nextoff;
       
       List<Integer> lst = new ArrayList<>( );
      
       int original_offset = 0;
       
       for( int i=0; i<nexttext.length(); i++ ) {
    	   offset = search1(pat, nexttext);
    	   original_offset+= offset;
    	   
    	   if (offset >= nexttext.length()) {
    		   break;
    	   } 
    	   else {
    		   lst.add(original_offset);
	     	   nextoff = offset+pat.length();
	     	   original_offset+=pat.length();
	     	   
	     	   nexttext = nexttext.substring(nextoff, nexttext.length());
       		}
 
       }
       
       Integer []a = new Integer[lst.size()];
       lst.toArray( a );
       
       return a;
   }
}