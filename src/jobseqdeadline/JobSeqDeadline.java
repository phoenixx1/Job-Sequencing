package jobseqdeadline;
/**
 *
 * @author NSTG
 */
import java.util.Scanner;

public class JobSeqDeadline {
    static Scanner scan = new Scanner(System.in);
    
    static int pr[] = new int[100];
    static int dl[] = new int[100];
    static int seq[] = new int[100];
    
    public static void main(String[]args) {
        int n;
        System.out.println("Enter number of objects: ");
        n = scan.nextInt();
        
        System.out.println("Enter profits: ");
        for(int i = 0; i < n; i++) {
        	pr[i] = scan.nextInt();
        }
   
        System.out.println("Enter deadline: ");
        for(int i = 0; i < n; i++) {
        	dl[i] = scan.nextInt();
        }
        
        for (int i = 0; i < n; i++){
        	seq[i] = i;
        }
        
        for(int i = 1; i <=n; i++) {
            for(int j = 0; j <n - i; j++) {
                if(pr[j+1] > pr[j]) {
                    int temp = pr[j+1];
                    pr[j+1] = pr[j];
                    pr[j] = temp;
                    
                    temp = dl[j+1];
                    dl[j+1] = dl[j];
                    dl[j] = temp;
        
                    temp = seq[j+1];
                    seq[j+1] = seq[j];
                    seq[j] = temp;
                }
            }
        }
        
//        for(int i = 0; i<n; i++){
//            System.out.println(pr[i]);
//        }
        
        
        jobSeq(n);
        
    }
    
      
    public static void jobSeq(int n){
        int maxDeadline = 0, optimalProfit = 0, fs = 0;
        int i, j;
        int optimalSeq[] = new int[100];
        
        for(i=0; i<n; i++){
            if(dl[i] > maxDeadline){
                maxDeadline = dl[i];
            }
        }
        
        System.out.println("\nMaximum Deadline: "+maxDeadline);
        
        for (i = 1; i <= maxDeadline; i++){
            optimalSeq[i] = -1;
        }
        
        for(i = 1; i <= n; i++) {
            j = minValue(maxDeadline, dl[i - 1]);
            while(j >= 1) {
                if(optimalSeq[j] == -1) {
                    optimalSeq[j] = i-1;
                    //optimalProfit = optimalProfit + pr[j-1];
                    fs++;
                    break;
                }
                j--;
            }

            if(fs == maxDeadline) {
                break;
            }
        }
        System.out.print("\nOptimal Sequence: ");
        for(i = 1; i<= maxDeadline; i++){
            System.out.print(seq[optimalSeq[i]]+1);
            optimalProfit += pr[optimalSeq[i]];
            if(i < maxDeadline){
                System.out.print(", ");
            }
        }
        System.out.println("\n\nOptimal Profit: "+optimalProfit+"\n");
                
    }
    
    public static int minValue(int x, int y){
	if(x<y)
            return x;
	else 
            return y;
    }
}