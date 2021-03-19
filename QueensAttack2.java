//link to hackerrank problem  https://www.hackerrank.com/challenges/queens-attack-2/problem
//change the file name OR class name before running if you're downloading this file

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    private int obscols[];
    private int obsrows[];
    // Complete the queensAttack function below.
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
               //for storing obstacles
        class Cell{
            public int r;
            public int c;
            Cell(int r, int c){
                this.r = r;
                this.c = c;
            }
            @Override
            public String toString(){
                String str = "[" + this.r +  ", " + this.c + "]";
                return str;
            }
        } 
        //closest obstacles so that we can just calculate the cells between queen and them
       Cell closestup = new Cell(n+1, c_q);
       Cell closestdown = new Cell(0, c_q);
       Cell closestleft = new Cell(r_q, 0);
       Cell closestright = new Cell(r_q, n+1);
       Cell closestleftup = new Cell(n+1, 0);
       Cell closestleftdown = new Cell(0, 0);
       Cell closestrightup = new Cell(n+1, n+1);
       Cell closestrightdown = new Cell(0, n+1);
       
       //first find closest obstacles
       for(int i=0; i<k; i++){
           
           //for vertical i.e same column
           if(obstacles[i][1] == c_q){
               
               //check for obstacles that lie above q
               if(obstacles[i][0] > r_q){
                   //find the closest
                   if((obstacles[i][0] - r_q) < (closestup.r - r_q)){
                       closestup.r = obstacles[i][0];
                       closestup.c = obstacles[i][1];
                   }
               }
               //check for obstacles that lie below q
               if(obstacles[i][0] < r_q){
                   //find the closest
                   if((r_q - obstacles[i][0]) < (r_q - closestdown.r)){
                       closestdown.r = obstacles[i][0];
                       closestdown.c = obstacles[i][1];
                   }
               }
           }
           
           //for horizontal obstacles i.e same row
           if(obstacles[i][0] == r_q){
               //check for obstacles on left
               if(obstacles[i][1] < c_q){
                   //find closestleft
                   if((c_q - obstacles[i][1]) < (c_q - closestleft.c)){
                       closestleft.c = obstacles[i][1];
                       closestleft.r = obstacles[i][0];
                   }
               }
               //check for obstacles on right
               if(obstacles[i][1] > c_q){
                   //find closestright
                   if((obstacles[i][1] - c_q) < (closestright.c - c_q)){
                       closestright.r = obstacles[i][0];
                       closestright.c = obstacles[i][1];
                   }
               }
           }
           
           //for obstacles which are diagonally aligned with q 
           if(Math.abs(obstacles[i][0] - r_q) == Math.abs(obstacles[i][1] - c_q)){
               //check for left up diagonal 
               if(r_q < obstacles[i][0] && c_q > obstacles[i][1]){
                   //find closest
                   if((obstacles[i][0] - r_q) < (closestleftup.r - r_q)){
                       closestleftup.r = obstacles[i][0];
                       closestleftup.c = obstacles[i][1];
                   }
               }
               //check for left down
               if(r_q > obstacles[i][0] && c_q > obstacles[i][1]){
                   //find closest
                   if((r_q - obstacles[i][0]) < (r_q - closestleftdown.r)){                   
                       closestleftdown.r = obstacles[i][0];
                       closestleftdown.c = obstacles[i][1];
                   }
               }
               //check for rightup
               if(r_q < obstacles[i][0] && c_q < obstacles[i][1]){
                   //find closest
                   if((obstacles[i][0] - r_q) < (closestrightup.r - r_q)){
                       closestrightup.r = obstacles[i][0];
                       closestrightup.c = obstacles[i][1];
                   }
               }
               //check for rightdown
               if(r_q > obstacles[i][0] && c_q < obstacles[i][1]){
                   //find closest
                   if((r_q - obstacles[i][0]) < (r_q - closestrightdown.r)){
                       closestrightdown.r = obstacles[i][0];
                       closestrightdown.c = obstacles[i][1];
                   }
               }
           }
       }    
       
       //now calculate the cells that can be traversed 
       int cellstrav = 0;
                 
       //up 
       if(r_q != n){  //if r_q == n then we cannot go up, no cells to traverse up
           cellstrav += closestup.r - r_q - 1;  
       }
       //down
       if(r_q != 0){ //if r_q ==0 then we cannot go down, no cells to traverse down
           cellstrav += r_q - closestdown.r - 1;
       }
       //left
       if(c_q != 0){
           cellstrav += c_q - closestleft.c - 1;  
       }
       //right
       if(c_q != n){
           cellstrav += closestright.c - c_q - 1;
       }
       //leftup
       if(c_q != 0 && r_q != n){
           //if no obstacle in leftup
           if(closestleftup.r == n+1) { cellstrav += Math.min(c_q-1, n - r_q);} 
           else {cellstrav += closestleftup.r - r_q - 1;}      
       }
       //leftdown
       if(c_q != 0 && r_q != 0){
        //if no obstacle in leftdown
        if(closestleftdown.r == 0) { cellstrav += Math.min(c_q-1, r_q - 1);}
        else {cellstrav += r_q - closestleftdown.r - 1;}
       }
       //rightup
       if(c_q != n && r_q != n){
           //if no obstacle in right up
           if(closestrightup.r == n+1 ){ cellstrav += Math.min(n - r_q, n - c_q);}
           else { cellstrav += closestrightup.r - r_q - 1;}
       }
       //rightdown
       if(c_q != n && r_q !=0){
           //if no obstacle in rightdown
           if(closestrightdown.r == 0){ cellstrav += Math.min(r_q - 1, n - c_q);}
           else { cellstrav += r_q - closestrightdown.r - 1; }
       }
       return cellstrav;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String[] r_qC_q = scanner.nextLine().split(" ");

        int r_q = Integer.parseInt(r_qC_q[0]);

        int c_q = Integer.parseInt(r_qC_q[1]);

        int[][] obstacles = new int[k][2];

        for (int i = 0; i < k; i++) {
            String[] obstaclesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
                obstacles[i][j] = obstaclesItem;
            }
        }

        int result = queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
