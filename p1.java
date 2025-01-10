import java.util.Scanner;

public class p1 {
    public static void main(String[] args) {
        int i;
        int a[]=new int[20];
        int sent,revc, buckrem=0, buckcap=4,rate=3;

        Scanner sc=new Scanner(System.in);

        System.out.println("enter the r");
        int n= sc.nextInt();
          
        System.out.println("enter the packets");
        for(i=0;i<n;i++){
        a[i] =sc.nextInt();
        }
           for(i=0;i<n;i++){
            if(a[i]!=0){
                if(buckrem+a[i] > buckcap){
                   revc =-1;
                }else{
                    recv= a[i];
                    buckrem=0;
                }else{
                 revc =  0;
                }
            }
            if(buckrem !=0){
                if(buckrem<rate){
                    sent = buckrem;
                    buck =  0;

                }else{
                    sent= rate;
                    buckrem -= 1;
                }else{
                    sent = 0;
                }
                if(revc == -1){
                    System.out.println(a[i]);
                }
            }
           }         

    }
    
}
