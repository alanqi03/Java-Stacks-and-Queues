// Name: J4-18
// Date: 1/22/20

import java.util.*;

public class LunchRoom
{
   public static final int TIME = 1080;  //18 hrs * 60 min
   public static int waitingtime = 0;
   public static boolean sa = false;
   public static Customer current = new Customer(); 
   public static Customer c1 = new Customer(); 
   public static int maxy = 0;
   public static boolean switched = false;
   
   public static int FRserved = 0;
   public static int SOserved = 0;
   public static int JUserved = 0;
   public static int SEserved = 0;
   
   public static Queue<Integer> queFR = new LinkedList<Integer>();
   public static Queue<Integer> queSO = new LinkedList<Integer>();
   public static Queue<Integer> queJU = new LinkedList<Integer>();
   public static Queue<Integer> queSE = new LinkedList<Integer>();
   
   public static int FRlongest = 0;
   public static int SOlongest = 0;
   public static int JUlongest = 0;
   public static int SElongest = 0;
   
   public static double FRsum = 0;
   public static double SOsum = 0;
   public static double JUsum = 0;
   public static double SEsum = 0;
   
   public static int FRcount = 0;
   public static int SOcount = 0;
   public static int JUcount = 0;
   public static int SEcount = 0;
   
   public static double FRavg = 0;
   public static double SOavg = 0;
   public static double JUavg = 0;
   public static double SEavg = 0;

   public static void main(String[] args)
   {
      PriorityQueue<Customer> queue = new PriorityQueue<Customer>();
      /*  write code for the simulation   */
      System.out.println("LunchRoom(arrival chance is 0.2) with 1 Pqueue / 1 service areas");
      for(int i = 0; i < TIME; i++)
      {
         display(i, queue);
      }
      int minOT = TIME;
      if(!(current.getMin() == -1))
      {
        while(current.getWaitingTime() > 1)
        {
           current.setWaitingTime(current.getWaitingTime() - 1);
           System.out.println(minOT + ":" + queue.toString());
           System.out.println("ServiceArea#1 " + current.toString() + ":" + current.getWaitingTime());
           System.out.println();
           minOT++;
        }
        System.out.println("Customer#" + current.toString() + " leaves and his total wait time is " + (minOT - current.getMin()));
        if(current.getGrade() == 9)
        {
            queFR.add(minOT - current.getMin());
        }
        else if(current.getGrade() == 10)
        {
            queSO.add(minOT - current.getMin());
        }
        else if(current.getGrade() == 11)
        {
            queJU.add(minOT - current.getMin());
        }
        else if(current.getGrade() == 12)
        {
            queSE.add(minOT - current.getMin());
        }
      }
      while(!queue.isEmpty())
      {
         current = queue.remove(); 
         while(current.getWaitingTime() > 0)
         {
           System.out.println(minOT + ":" + queue.toString());
           System.out.println("ServiceArea#1 " + current.toString() + ":" + current.getWaitingTime());
           System.out.println();
           current.setWaitingTime(current.getWaitingTime() - 1);
           minOT++;
         }
         System.out.println("Customer#" + current.toString() + " leaves and his total wait time is " + (minOT - current.getMin()));
      } 
      System.out.println(minOT + ":" + queue.toString());
      System.out.println("ServiceArea#1 " + -1 + ":" + -1);
      if(current.getGrade() == 9)
      {
         queFR.add(minOT - current.getMin());
      }
      else if(current.getGrade() == 10)
      {
         queSO.add(minOT - current.getMin());
      }
      else if(current.getGrade() == 11)
      {
         queJU.add(minOT - current.getMin());
      }
      else if(current.getGrade() == 12)
      {
         queSE.add(minOT - current.getMin());
      }
      System.out.println();
      
      Queue<Integer> quetemp = new LinkedList<Integer>(queFR);
      while(!quetemp.isEmpty())
      {
        FRsum += quetemp.remove();
        FRcount++;
      }
      FRavg = FRsum / FRcount;
      
      Queue<Integer> quetemp2 = new LinkedList<Integer>(queSO);
      while(!quetemp2.isEmpty())
      {
        SOsum += quetemp2.remove();
        SOcount++;
      }
      SOavg = SOsum / SOcount;

      Queue<Integer> quetemp3 = new LinkedList<Integer>(queJU);
      while(!quetemp3.isEmpty())
      {
        JUsum += quetemp3.remove();
        JUcount++;
      }
      JUavg = JUsum / JUcount;

      Queue<Integer> quetemp4 = new LinkedList<Integer>(queSE);
      while(!quetemp4.isEmpty())
      {
        SEsum += quetemp4.remove();
        SEcount++;
      }
      SEavg = SEsum / SEcount;
      
      while(!queFR.isEmpty())
      {
         maxy = queFR.remove();
         if(maxy > FRlongest)
         {
            FRlongest = maxy;
         }
      }
      while(!queSO.isEmpty())
      {
         maxy = queSO.remove();
         if(maxy > SOlongest)
         {
            SOlongest = maxy;
         }
      }
      while(!queJU.isEmpty())
      {
         maxy = queJU.remove();
         if(maxy > JUlongest)
         {
            JUlongest = maxy;
         }
      }
      while(!queSE.isEmpty())
      {
         maxy = queSE.remove();
         if(maxy > SElongest)
         {
            SElongest = maxy;
         }
      }

      System.out.println("Customer\t\tTotal\t\tLongest\t\tAverage Wait");
      /* report the data  */
      System.out.println("Senior\t\t" + SEserved + "\t\t\t" + SElongest + "\t\t\t\t" + SEavg);
      System.out.println("Junior\t\t" + JUserved + "\t\t\t" + JUlongest + "\t\t\t\t" + JUavg);
      System.out.println("Sophomore\t" + SOserved + "\t\t\t" + SOlongest + "\t\t\t\t" + SOavg);
      if(FRlongest > 99)
      {
         System.out.println("Freshman\t\t" + FRserved + "\t\t\t" + FRlongest + "\t\t\t" + FRavg);
      }
      else
      {
         System.out.println("Freshman\t\t" + FRserved + "\t\t\t" + FRlongest + "\t\t\t\t" + FRavg);
      }
   
   }
   
   public static void display(int t, PriorityQueue<Customer> q)
   {
      int cust = (int)(Math.random() * 5);
      int grade = (int)(Math.random() * 4);
      int time = (int)((Math.random() * 6) + 2);
      if(cust != 1)
      {
         if(sa == true && current.getWaitingTime() > 1)
         {
            current.setWaitingTime(current.getWaitingTime()-1);
         }
         else if(current.getWaitingTime() == 1 || sa == false)
         {
           if(current.getWaitingTime() == 1)
          {
             if(current.getGrade() == 9)
             {
                queFR.add(t - current.getMin());
             }
             else if(current.getGrade() == 10)
             {
                queSO.add(t - current.getMin());
             }
             else if(current.getGrade() == 11)
             {
                queJU.add(t - current.getMin());
             }
             else if(current.getGrade() == 12)
             {
                queSE.add(t - current.getMin());
             }
             System.out.println("Customer#" + current.toString() + " leaves and his total wait time is " + (t - current.getMin()));
          }
          if(q.isEmpty())
          {
             current.setMin(-1);
             current.setGrade(-1);
             sa = false;
          }
          else
          {
             current = q.remove();
             sa = true;
          }
        }    
         System.out.println(t + ":" + q.toString());
         System.out.println("ServiceArea#1 " + current.toString() + ":" + current.getWaitingTime());
         System.out.println();
         return;
      }
      else
      {
        if(grade == 0)
        {
           c1 = new Customer(9, t, time);
           FRserved++;
        }
        else if(grade == 1)
        {
           c1 = new Customer(10, t, time);
           SOserved++;
        }
        else if(grade == 2)
        {
           c1 = new Customer(11, t, time);
           JUserved++;
        }
        else
        {
           c1 = new Customer(12, t, time);
           SEserved++;
        }  
        System.out.println("new customer " + c1.toString());
        if(sa == true && current.getWaitingTime() > 1)
        {
           current.setWaitingTime(current.getWaitingTime()-1);
        }
        else if(current.getWaitingTime() == 1 || sa == false)
        {
          if(current.getWaitingTime() == 1)
         {
            if(current.getGrade() == 9)
            {
               queFR.add(t - current.getMin());
            }
            else if(current.getGrade() == 10)
            {
               queSO.add(t - current.getMin());
            }
            else if(current.getGrade() == 11)
            {
               queJU.add(t - current.getMin());
            }
            else if(current.getGrade() == 12)
            {
               queSE.add(t - current.getMin());
            }
            System.out.println("Customer#" + current.toString() + " leaves and his total wait time is " + (t - current.getMin()));
         }
         if(q.isEmpty())
         {
            current.setMin(-1);
            current.setGrade(-1);
            sa = false;
         }
         else
         {
            current = q.remove();
            if(c1.compareTo(current) < 0)
            {
               q.add(current);
               current = c1;
               sa = true;
               switched = true;
            }
            else if(c1.compareTo(current) > 1)
            {
               sa = true;
            }
         }
       }   
       if(sa == true && switched == false)
       {
          q.add(c1);
       }
       else
       {
         current = c1; 
         sa = true;  
       }
       switched = false;
       System.out.println(t + ":" + q.toString());
       System.out.println("ServiceArea#1 " + current.toString() + ":" + current.getWaitingTime());
       System.out.println();
       return;
      }
   

   }
}

class Customer implements Comparable<Customer>
{
   private int myGrade;
   private int min;
   private int waitingTime;
   public Customer()
   {
      myGrade = 0;
      min = -1;
      waitingTime = -1;
   }
   public Customer(int grade, int m, int wt)
   {
      myGrade = grade;
      min = m;
      waitingTime = wt; 
   }
   public int getMin()
   {
      return min;
   }
   public void setMin(int m)
   {
      min = m;
   }
   public int getGrade()
   {
      return myGrade;
   }
   public void setGrade(int s)
   {
      myGrade = s;
   }
   public int getWaitingTime()
   {
      return waitingTime;
   }
   public void setWaitingTime(int w)
   {
      waitingTime = w;
   }
   public int compareTo(Customer cus)
   {
      if(myGrade > cus.myGrade)
      {
         return -1;
      }
      else if(myGrade == cus.myGrade)
      {
         if(min < cus.min)
         {
            return -1;
         }
         else if(min > cus.min)
         {  
            return 1; 
         }
         return 0; 
      }
      
      return 1;
   }
   public String toString()
   {
      if(myGrade == 9)
      {
         return min + ":Fr";
      }
      else if(myGrade == 10)
      {
         return min + ":So";
      }
      else if(myGrade == 11)
      {
         return min + ":Ju";
      }
      else if(myGrade == 12)
      {
         return min + ":Se";
      }
      return "" + min; 
   }  
}


/*-------------------------

 0: [0:So]
 1: [1:Ju, 0:So]
 2: [1:Ju, 0:So]
 3: [1:Ju, 0:So]
 4: [1:Ju, 0:So]
 5: [0:So, 5:Fr]
 6: [0:So, 5:Fr]
 7: [0:So, 5:Fr]
 8: [5:Fr]
 9: [5:Fr]
 10: [5:Fr]
 11: [5:Fr]
 12: [5:Fr]
 13: [5:Fr]
 14: [5:Fr]
 15: []
 16: []
 17: []
 18: []
 19: []
 20: [20:Fr]
 21: [21:So, 20:Fr]
 22: [21:So, 20:Fr]
 23: [20:Fr]
 24: [20:Fr]
 25: [20:Fr, 25:Fr]
 26: [26:Se, 25:Fr]
 27: [26:Se, 25:Fr, 27:Ju]
 28: [26:Se, 25:Fr, 27:Ju]
 29: [26:Se, 25:Fr, 27:Ju]
 30: [26:Se, 30:Se, 27:Ju, 25:Fr]
 31: [26:Se, 30:Se, 27:Ju, 25:Fr, 31:Fr]
 32: [30:Se, 25:Fr, 27:Ju, 31:Fr]
 33: [30:Se, 33:Ju, 27:Ju, 31:Fr, 25:Fr]
 34: [30:Se, 33:Ju, 27:Ju, 31:Fr, 25:Fr, 34:Fr]
 35: [30:Se, 33:Ju, 27:Ju, 31:Fr, 25:Fr, 34:Fr]
 36: [30:Se, 33:Ju, 27:Ju, 31:Fr, 25:Fr, 34:Fr]
 37: [27:Ju, 33:Ju, 34:Fr, 31:Fr, 25:Fr]
 38: [27:Ju, 33:Ju, 34:Fr, 31:Fr, 25:Fr]
 39: [27:Ju, 33:Ju, 39:So, 31:Fr, 25:Fr, 34:Fr]
 40: [27:Ju, 33:Ju, 39:So, 31:Fr, 25:Fr, 34:Fr]
 41: [27:Ju, 33:Ju, 39:So, 31:Fr, 25:Fr, 34:Fr]
 42: [27:Ju, 33:Ju, 39:So, 31:Fr, 25:Fr, 34:Fr]
 43: [33:Ju, 25:Fr, 39:So, 31:Fr, 34:Fr]
 44: [33:Ju, 25:Fr, 39:So, 31:Fr, 34:Fr]
 45: [45:Se, 25:Fr, 33:Ju, 31:Fr, 34:Fr, 39:So]
 46: [33:Ju, 25:Fr, 39:So, 31:Fr, 34:Fr, 46:Fr]
 47: [33:Ju, 25:Fr, 39:So, 31:Fr, 34:Fr, 46:Fr]
 48: [33:Ju, 25:Fr, 39:So, 31:Fr, 34:Fr, 46:Fr]
 49: [33:Ju, 25:Fr, 39:So, 31:Fr, 34:Fr, 46:Fr]
 50: [33:Ju, 25:Fr, 39:So, 31:Fr, 34:Fr, 46:Fr]
 51: [33:Ju, 25:Fr, 39:So, 31:Fr, 34:Fr, 46:Fr]
 52: [39:So, 25:Fr, 46:Fr, 31:Fr, 34:Fr]
 53: [39:So, 25:Fr, 46:Fr, 31:Fr, 34:Fr]
 54: [39:So, 25:Fr, 46:Fr, 31:Fr, 34:Fr]
 55: [39:So, 25:Fr, 46:Fr, 31:Fr, 34:Fr]
 56: [25:Fr, 31:Fr, 46:Fr, 34:Fr]
 57: [25:Fr, 31:Fr, 46:Fr, 34:Fr, 57:Fr]
 58: [25:Fr, 31:Fr, 46:Fr, 34:Fr, 57:Fr]
 59: [25:Fr, 31:Fr, 46:Fr, 34:Fr, 57:Fr]
 60: [31:Fr, 34:Fr, 46:Fr, 57:Fr]
 61: [31:Fr, 34:Fr, 46:Fr, 57:Fr]
 62: [62:Se, 31:Fr, 46:Fr, 57:Fr, 34:Fr]
 63: [62:Se, 31:Fr, 46:Fr, 57:Fr, 34:Fr]
 64: [62:Se, 31:Fr, 46:Fr, 57:Fr, 34:Fr]
 65: [31:Fr, 34:Fr, 46:Fr, 57:Fr]
 66: [31:Fr, 34:Fr, 46:Fr, 57:Fr]
 67: [31:Fr, 34:Fr, 46:Fr, 57:Fr]
 68: [31:Fr, 34:Fr, 46:Fr, 57:Fr]
 69: [34:Fr, 57:Fr, 46:Fr]
 70: [34:Fr, 57:Fr, 46:Fr]
 71: [34:Fr, 57:Fr, 46:Fr]
 72: [34:Fr, 57:Fr, 46:Fr]
 73: [34:Fr, 57:Fr, 46:Fr]
 74: [34:Fr, 57:Fr, 46:Fr]
 75: [46:Fr, 57:Fr]
 76: [46:Fr, 57:Fr]
 77: [46:Fr, 57:Fr]
 78: [46:Fr, 57:Fr]
 79: [57:Fr]
 80: [57:Fr]
 81: [57:Fr]
 82: [57:Fr]
 83: [57:Fr]
 84: [84:So, 57:Fr]
 85: [84:So, 57:Fr]
 86: [84:So, 57:Fr]
 87: [57:Fr]
 88: [57:Fr]
 89: [57:Fr]
 90: [57:Fr]
 91: [57:Fr, 91:Fr]
 92: [91:Fr]
 93: [91:Fr]
 94: [91:Fr]
 95: [91:Fr]
 96: [96:So, 91:Fr]
 97: [91:Fr]
 98: [98:Se, 91:Fr]
 99: [98:Se, 91:Fr]
 100: [91:Fr]
 101: [91:Fr]
 102: [91:Fr]
 103: [91:Fr]
 104: [91:Fr]
 105: [91:Fr]
 106: [106:Ju, 91:Fr]
 107: [91:Fr]
 108: [91:Fr]
 109: [109:Se, 91:Fr]
 110: [109:Se, 91:Fr]
 111: [109:Se, 91:Fr]
 112: [109:Se, 91:Fr, 112:Se]
 113: [109:Se, 91:Fr, 112:Se]
 114: [112:Se, 91:Fr]
 115: [112:Se, 91:Fr]
 116: [112:Se, 91:Fr]
 117: [112:Se, 91:Fr, 117:Fr]
 118: [112:Se, 91:Fr, 117:Fr]
 119: [112:Se, 91:Fr, 117:Fr]
 120: [112:Se, 91:Fr, 117:Fr]
 121: [112:Se, 91:Fr, 117:Fr]
 122: [91:Fr, 117:Fr]
 123: [91:Fr, 117:Fr, 123:Fr]
 124: [91:Fr, 117:Fr, 123:Fr]
 125: [117:Fr, 123:Fr]
 126: [117:Fr, 123:Fr]
 127: [117:Fr, 123:Fr]
 128: [117:Fr, 123:Fr]
 129: [117:Fr, 123:Fr]
 130: [117:Fr, 123:Fr]
 131: [123:Fr]
 132: [123:Fr]
 133: [123:Fr]
 134: [123:Fr]
 135: [123:Fr]
 136: [123:Fr]
 137: [123:Fr]
 138: []
 139: [139:Ju]
 140: [139:Ju]
 141: [139:Ju]
 142: []
 143: [143:Se]
 144: [143:Se]
 145: [143:Se]
 146: [143:Se]
 147: [143:Se]
 148: []
 149: [149:Fr]
 150: [149:Fr]
 151: [149:Fr]
 152: [149:Fr]
 153: [149:Fr]
 154: [149:Fr]
 155: []
 156: []
 157: []
 158: []
 159: []
 160: []
 161: [161:Ju]
 162: [161:Ju, 162:So]
 163: [161:Ju, 162:So]
 164: [162:So]
 165: [162:So]
 166: [162:So]
 167: [162:So]
 168: [162:So]
 169: [162:So, 169:So]
 170: [169:So]
 171: [169:So]
 172: [169:So]
 173: [169:So, 173:So]
 174: [169:So, 173:So]
 175: [173:So]
 176: [173:So]
 177: [173:So]
 178: [178:Se, 173:So]
 179: [178:Se, 173:So]
 180: [178:Se, 173:So]
 181: [173:So]
 182: [173:So]
 183: [173:So]
 184: [173:So]
 185: []
 186: []
 187: []
 188: [188:Ju]
 189: [188:Ju]
 190: [188:Ju, 190:So]
 191: [188:Ju, 190:So]
 192: [188:Ju, 190:So]
 193: [188:Ju, 190:So]
 194: [188:Ju, 190:So]
 195: [188:Ju, 190:So]
 196: [190:So]
 197: [190:So]
 198: [190:So, 198:So]
 199: [199:Ju, 198:So]
 200: [199:Ju, 198:So, 200:Ju]
 201: [199:Ju, 198:So, 200:Ju]
 202: [199:Ju, 198:So, 200:Ju]
 203: [199:Ju, 198:So, 200:Ju]
 204: [199:Ju, 198:So, 200:Ju]
 205: [199:Ju, 198:So, 200:Ju]
 206: [200:Ju, 198:So]
 207: [200:Ju, 198:So]
 208: [200:Ju, 198:So, 208:Fr]
 209: [200:Ju, 198:So, 208:Fr, 209:So]
 210: [198:So, 209:So, 208:Fr]
 211: [211:Se, 198:So, 208:Fr, 209:So]
 212: [211:Se, 198:So, 208:Fr, 209:So]
 213: [211:Se, 198:So, 208:Fr, 209:So]
 214: [211:Se, 214:Ju, 208:Fr, 209:So, 198:So]
 215: [211:Se, 214:Ju, 215:Se, 209:So, 198:So, 208:Fr]
 216: [211:Se, 214:Ju, 215:Se, 209:So, 198:So, 208:Fr]
 217: [215:Se, 214:Ju, 217:Ju, 209:So, 198:So, 208:Fr]
 218: [215:Se, 214:Ju, 218:Se, 209:So, 198:So, 208:Fr, 217:Ju]
 219: [215:Se, 214:Ju, 218:Se, 209:So, 198:So, 208:Fr, 217:Ju]
 220: [215:Se, 214:Ju, 218:Se, 209:So, 198:So, 208:Fr, 217:Ju]
 221: [218:Se, 214:Ju, 217:Ju, 209:So, 198:So, 208:Fr]
 222: [218:Se, 214:Ju, 217:Ju, 209:So, 198:So, 208:Fr]
 223: [218:Se, 214:Ju, 217:Ju, 209:So, 198:So, 208:Fr]
 224: [218:Se, 214:Ju, 217:Ju, 209:So, 198:So, 208:Fr]
 225: [214:Ju, 198:So, 217:Ju, 209:So, 208:Fr]
 226: [214:Ju, 198:So, 217:Ju, 209:So, 208:Fr]
 227: [214:Ju, 198:So, 217:Ju, 209:So, 208:Fr]
 228: [214:Ju, 198:So, 217:Ju, 209:So, 208:Fr]
 229: [214:Ju, 198:So, 217:Ju, 209:So, 208:Fr]
 230: [214:Ju, 198:So, 217:Ju, 209:So, 208:Fr]
 231: [214:Ju, 198:So, 217:Ju, 209:So, 208:Fr]
 232: [217:Ju, 198:So, 208:Fr, 209:So]
 233: [217:Ju, 198:So, 208:Fr, 209:So]
 234: [217:Ju, 198:So, 208:Fr, 209:So]
 235: [217:Ju, 198:So, 208:Fr, 209:So]
 236: [198:So, 209:So, 208:Fr, 236:Fr]
 237: [198:So, 209:So, 208:Fr, 236:Fr]
 238: [198:So, 209:So, 208:Fr, 236:Fr]
 239: [198:So, 209:So, 208:Fr, 236:Fr]
 240: [198:So, 209:So, 208:Fr, 236:Fr]
 241: [198:So, 209:So, 208:Fr, 236:Fr]
 242: [209:So, 236:Fr, 208:Fr]
 243: [209:So, 236:Fr, 208:Fr]
 244: [209:So, 236:Fr, 208:Fr]
 245: [209:So, 236:Fr, 208:Fr]
 246: [209:So, 236:Fr, 208:Fr]
 247: [209:So, 236:Fr, 208:Fr]
 248: [209:So, 236:Fr, 208:Fr]
 249: [208:Fr, 236:Fr]
 250: [208:Fr, 236:Fr]
 251: [208:Fr, 236:Fr]
 252: [208:Fr, 236:Fr]
 253: [208:Fr, 236:Fr]
 254: [236:Fr]
 255: [236:Fr]
 256: [256:Se, 236:Fr]
 257: [256:Se, 236:Fr, 257:Fr]
 258: [256:Se, 236:Fr, 257:Fr]
 259: [256:Se, 236:Fr, 257:Fr]
 260: [236:Fr, 257:Fr]
 261: [236:Fr, 257:Fr]
 262: [236:Fr, 257:Fr]
 263: [236:Fr, 257:Fr]
 264: [236:Fr, 257:Fr]
 265: [236:Fr, 257:Fr]
 266: [266:Se, 257:Fr]
 267: [266:Se, 257:Fr]
 268: [266:Se, 257:Fr, 268:So]
 269: [268:So, 257:Fr]
 270: [268:So, 257:Fr]
 271: [268:So, 257:Fr]
 272: [268:So, 257:Fr]
 273: [268:So, 257:Fr]
 274: [257:Fr]
 275: [257:Fr]
 276: [276:So, 257:Fr]
 277: [277:Se, 257:Fr, 276:So]
 278: [277:Se, 257:Fr, 276:So]
 279: [277:Se, 257:Fr, 276:So]
 280: [277:Se, 257:Fr, 276:So]
 281: [277:Se, 257:Fr, 276:So]
 282: [276:So, 257:Fr]
 283: [276:So, 257:Fr]
 284: [276:So, 257:Fr]
 285: [276:So, 257:Fr]
 286: [276:So, 257:Fr]
 287: [257:Fr]
 288: [257:Fr]
 289: [257:Fr]
 290: [290:Se, 257:Fr]
 291: [290:Se, 257:Fr]
 292: [290:Se, 257:Fr]
 293: [257:Fr]
 294: [294:So, 257:Fr]
 295: [294:So, 257:Fr, 295:So]
 296: [296:Ju, 294:So, 295:So, 257:Fr]
 297: [294:So, 257:Fr, 295:So]
 298: [294:So, 257:Fr, 295:So]
 299: [294:So, 257:Fr, 295:So]
 300: [294:So, 257:Fr, 295:So]
 301: [294:So, 257:Fr, 295:So]
 302: [295:So, 257:Fr]
 303: [295:So, 257:Fr]
 304: [295:So, 257:Fr]
 305: [295:So, 257:Fr, 305:Fr]
 306: [295:So, 257:Fr, 305:Fr]
 307: [295:So, 257:Fr, 305:Fr]
 308: [308:Ju, 305:Fr, 257:Fr]
 309: [308:Ju, 305:Fr, 257:Fr]
 310: [308:Ju, 310:Ju, 257:Fr, 305:Fr]
 311: [310:Ju, 305:Fr, 257:Fr]
 312: [310:Ju, 305:Fr, 257:Fr]
 313: [313:Se, 310:Ju, 257:Fr, 305:Fr]
 314: [313:Se, 310:Ju, 257:Fr, 305:Fr, 314:So]
 315: [313:Se, 310:Ju, 257:Fr, 305:Fr, 314:So]
 316: [313:Se, 310:Ju, 257:Fr, 305:Fr, 314:So]
 317: [310:Ju, 314:So, 257:Fr, 305:Fr]
 318: [310:Ju, 318:Ju, 257:Fr, 305:Fr, 314:So]
 319: [310:Ju, 318:Ju, 257:Fr, 305:Fr, 314:So]
 320: [310:Ju, 318:Ju, 257:Fr, 305:Fr, 314:So]
 321: [310:Ju, 318:Ju, 257:Fr, 305:Fr, 314:So]
 322: [310:Ju, 318:Ju, 257:Fr, 305:Fr, 314:So, 322:Fr]
 323: [310:Ju, 318:Ju, 257:Fr, 305:Fr, 314:So, 322:Fr]
 324: [318:Ju, 314:So, 257:Fr, 305:Fr, 322:Fr]
 325: [318:Ju, 314:So, 257:Fr, 305:Fr, 322:Fr]
 326: [318:Ju, 314:So, 257:Fr, 305:Fr, 322:Fr]
 327: [318:Ju, 314:So, 257:Fr, 305:Fr, 322:Fr]
 328: [314:So, 305:Fr, 257:Fr, 322:Fr]
 329: [314:So, 305:Fr, 257:Fr, 322:Fr]
 330: [314:So, 305:Fr, 257:Fr, 322:Fr]
 331: [314:So, 305:Fr, 257:Fr, 322:Fr]
 332: [314:So, 305:Fr, 257:Fr, 322:Fr]
 333: [314:So, 305:Fr, 257:Fr, 322:Fr]
 334: [257:Fr, 305:Fr, 322:Fr]
 335: [335:Ju, 257:Fr, 322:Fr, 305:Fr]
 336: [335:Ju, 257:Fr, 322:Fr, 305:Fr]
 337: [335:Ju, 257:Fr, 322:Fr, 305:Fr]
 338: [335:Ju, 257:Fr, 322:Fr, 305:Fr]
 339: [335:Ju, 257:Fr, 322:Fr, 305:Fr]
 340: [335:Ju, 257:Fr, 322:Fr, 305:Fr]
 341: [335:Ju, 257:Fr, 322:Fr, 305:Fr]
 342: [257:Fr, 305:Fr, 322:Fr, 342:Fr]
 343: [257:Fr, 305:Fr, 322:Fr, 342:Fr]
 344: [257:Fr, 305:Fr, 322:Fr, 342:Fr]
 345: [345:Se, 257:Fr, 322:Fr, 342:Fr, 305:Fr]
 346: [345:Se, 257:Fr, 322:Fr, 342:Fr, 305:Fr]
 347: [257:Fr, 305:Fr, 322:Fr, 342:Fr]
 348: [257:Fr, 305:Fr, 322:Fr, 342:Fr]
 349: [257:Fr, 305:Fr, 322:Fr, 342:Fr]
 350: [350:Se, 257:Fr, 322:Fr, 342:Fr, 305:Fr]
 351: [350:Se, 257:Fr, 322:Fr, 342:Fr, 305:Fr]
 352: [350:Se, 257:Fr, 322:Fr, 342:Fr, 305:Fr]
 353: [350:Se, 257:Fr, 322:Fr, 342:Fr, 305:Fr]
 354: [350:Se, 257:Fr, 322:Fr, 342:Fr, 305:Fr, 354:Fr]
 355: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr]
 356: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr]
 357: [357:So, 305:Fr, 257:Fr, 342:Fr, 354:Fr, 322:Fr]
 358: [358:Se, 305:Fr, 357:So, 342:Fr, 354:Fr, 322:Fr, 257:Fr]
 359: [358:Se, 305:Fr, 357:So, 342:Fr, 354:Fr, 322:Fr, 257:Fr, 359:Fr]
 360: [357:So, 305:Fr, 257:Fr, 342:Fr, 354:Fr, 322:Fr, 359:Fr]
 361: [357:So, 305:Fr, 257:Fr, 342:Fr, 354:Fr, 322:Fr, 359:Fr]
 362: [357:So, 305:Fr, 257:Fr, 342:Fr, 354:Fr, 322:Fr, 359:Fr]
 363: [357:So, 305:Fr, 257:Fr, 342:Fr, 354:Fr, 322:Fr, 359:Fr]
 364: [357:So, 305:Fr, 257:Fr, 342:Fr, 354:Fr, 322:Fr, 359:Fr]
 365: [365:Ju, 357:So, 257:Fr, 305:Fr, 354:Fr, 322:Fr, 359:Fr, 342:Fr]
 366: [365:Ju, 357:So, 257:Fr, 305:Fr, 354:Fr, 322:Fr, 359:Fr, 342:Fr]
 367: [365:Ju, 357:So, 257:Fr, 305:Fr, 354:Fr, 322:Fr, 359:Fr, 342:Fr]
 368: [357:So, 305:Fr, 257:Fr, 342:Fr, 354:Fr, 322:Fr, 359:Fr]
 369: [357:So, 305:Fr, 257:Fr, 342:Fr, 354:Fr, 322:Fr, 359:Fr]
 370: [357:So, 305:Fr, 257:Fr, 342:Fr, 354:Fr, 322:Fr, 359:Fr]
 371: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr]
 372: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr]
 373: [373:So, 305:Fr, 257:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr]
 374: [373:So, 305:Fr, 257:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr]
 375: [373:So, 305:Fr, 257:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr]
 376: [373:So, 305:Fr, 257:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr]
 377: [373:So, 305:Fr, 257:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr]
 378: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr]
 379: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr, 379:Fr]
 380: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr, 379:Fr]
 381: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr, 379:Fr]
 382: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr, 379:Fr]
 383: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr, 379:Fr]
 384: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr, 379:Fr]
 385: [385:Ju, 257:Fr, 322:Fr, 305:Fr, 354:Fr, 359:Fr, 379:Fr, 342:Fr]
 386: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr, 379:Fr]
 387: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr, 379:Fr]
 388: [388:Se, 257:Fr, 322:Fr, 305:Fr, 354:Fr, 359:Fr, 379:Fr, 342:Fr]
 389: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr, 379:Fr]
 390: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr, 379:Fr]
 391: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr, 379:Fr]
 392: [392:So, 257:Fr, 322:Fr, 305:Fr, 354:Fr, 359:Fr, 379:Fr, 342:Fr]
 393: [392:So, 257:Fr, 322:Fr, 305:Fr, 354:Fr, 359:Fr, 379:Fr, 342:Fr]
 394: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr, 379:Fr]
 395: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr, 379:Fr]
 396: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr, 379:Fr]
 397: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr, 379:Fr]
 398: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr, 379:Fr]
 399: [257:Fr, 305:Fr, 322:Fr, 342:Fr, 354:Fr, 359:Fr, 379:Fr]
 400: [305:Fr, 342:Fr, 322:Fr, 379:Fr, 354:Fr, 359:Fr]
 401: [401:Se, 342:Fr, 305:Fr, 379:Fr, 354:Fr, 359:Fr, 322:Fr]
 402: [401:Se, 402:Se, 305:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr, 379:Fr]
 403: [401:Se, 402:Se, 305:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr, 379:Fr]
 404: [401:Se, 402:Se, 305:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr, 379:Fr]
 405: [401:Se, 402:Se, 305:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr, 379:Fr]
 406: [401:Se, 402:Se, 305:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr, 379:Fr]
 407: [402:Se, 407:Ju, 305:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr, 379:Fr]
 408: [402:Se, 407:Ju, 305:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr, 379:Fr]
 409: [402:Se, 407:Ju, 305:Fr, 409:So, 354:Fr, 359:Fr, 322:Fr, 379:Fr, 342:Fr]
 410: [402:Se, 407:Ju, 305:Fr, 409:So, 354:Fr, 359:Fr, 322:Fr, 379:Fr, 342:Fr]
 411: [402:Se, 411:Se, 305:Fr, 409:So, 407:Ju, 359:Fr, 322:Fr, 379:Fr, 342:Fr, 354:Fr]
 412: [402:Se, 411:Se, 305:Fr, 409:So, 407:Ju, 359:Fr, 322:Fr, 379:Fr, 342:Fr, 354:Fr]
 413: [402:Se, 411:Se, 305:Fr, 409:So, 407:Ju, 359:Fr, 322:Fr, 379:Fr, 342:Fr, 354:Fr]
 414: [402:Se, 411:Se, 305:Fr, 409:So, 407:Ju, 359:Fr, 322:Fr, 379:Fr, 342:Fr, 354:Fr]
 415: [411:Se, 407:Ju, 305:Fr, 409:So, 354:Fr, 359:Fr, 322:Fr, 379:Fr, 342:Fr]
 416: [411:Se, 407:Ju, 305:Fr, 409:So, 354:Fr, 359:Fr, 322:Fr, 379:Fr, 342:Fr]
 417: [411:Se, 407:Ju, 305:Fr, 409:So, 354:Fr, 359:Fr, 322:Fr, 379:Fr, 342:Fr]
 418: [411:Se, 407:Ju, 305:Fr, 409:So, 354:Fr, 359:Fr, 322:Fr, 379:Fr, 342:Fr]
 419: [407:Ju, 409:So, 305:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr, 379:Fr]
 420: [407:Ju, 409:So, 305:Fr, 420:So, 354:Fr, 359:Fr, 322:Fr, 379:Fr, 342:Fr]
 421: [407:Ju, 409:So, 305:Fr, 420:So, 354:Fr, 359:Fr, 322:Fr, 379:Fr, 342:Fr]
 422: [407:Ju, 409:So, 305:Fr, 420:So, 354:Fr, 359:Fr, 322:Fr, 379:Fr, 342:Fr]
 423: [409:So, 420:So, 305:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr, 379:Fr]
 424: [424:Ju, 409:So, 305:Fr, 420:So, 354:Fr, 359:Fr, 322:Fr, 379:Fr, 342:Fr]
 425: [424:Ju, 409:So, 305:Fr, 420:So, 354:Fr, 359:Fr, 322:Fr, 379:Fr, 342:Fr]
 426: [424:Ju, 409:So, 305:Fr, 420:So, 354:Fr, 359:Fr, 322:Fr, 379:Fr, 342:Fr]
 427: [427:Ju, 409:So, 305:Fr, 420:So, 354:Fr, 359:Fr, 322:Fr, 379:Fr, 342:Fr]
 428: [427:Ju, 409:So, 305:Fr, 420:So, 354:Fr, 359:Fr, 322:Fr, 379:Fr, 342:Fr]
 429: [427:Ju, 409:So, 305:Fr, 420:So, 354:Fr, 359:Fr, 322:Fr, 379:Fr, 342:Fr]
 430: [427:Ju, 409:So, 305:Fr, 420:So, 354:Fr, 359:Fr, 322:Fr, 379:Fr, 342:Fr]
 431: [427:Ju, 409:So, 305:Fr, 420:So, 354:Fr, 359:Fr, 322:Fr, 379:Fr, 342:Fr]
 432: [427:Ju, 409:So, 305:Fr, 420:So, 354:Fr, 359:Fr, 322:Fr, 379:Fr, 342:Fr]
 433: [409:So, 420:So, 305:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr, 379:Fr]
 434: [409:So, 420:So, 305:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr, 379:Fr]
 435: [409:So, 420:So, 305:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr, 379:Fr]
 436: [409:So, 420:So, 305:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr, 379:Fr]
 437: [420:So, 342:Fr, 305:Fr, 379:Fr, 354:Fr, 359:Fr, 322:Fr]
 438: [420:So, 342:Fr, 305:Fr, 379:Fr, 354:Fr, 359:Fr, 322:Fr]
 439: [420:So, 342:Fr, 305:Fr, 379:Fr, 354:Fr, 359:Fr, 322:Fr]
 440: [440:Ju, 420:So, 305:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr, 379:Fr]
 441: [440:Ju, 420:So, 305:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr, 379:Fr]
 442: [440:Ju, 420:So, 305:Fr, 342:Fr, 354:Fr, 359:Fr, 322:Fr, 379:Fr]
 443: [420:So, 342:Fr, 305:Fr, 379:Fr, 354:Fr, 359:Fr, 322:Fr]
 444: [420:So, 342:Fr, 305:Fr, 379:Fr, 354:Fr, 359:Fr, 322:Fr]
 445: [420:So, 342:Fr, 305:Fr, 379:Fr, 354:Fr, 359:Fr, 322:Fr]
 446: [420:So, 342:Fr, 305:Fr, 379:Fr, 354:Fr, 359:Fr, 322:Fr]
 447: [420:So, 342:Fr, 305:Fr, 379:Fr, 354:Fr, 359:Fr, 322:Fr]
 448: [305:Fr, 342:Fr, 322:Fr, 379:Fr, 354:Fr, 359:Fr]
 449: [305:Fr, 342:Fr, 322:Fr, 379:Fr, 354:Fr, 359:Fr]
 450: [305:Fr, 342:Fr, 322:Fr, 379:Fr, 354:Fr, 359:Fr]
 451: [305:Fr, 342:Fr, 322:Fr, 379:Fr, 354:Fr, 359:Fr]
 452: [305:Fr, 342:Fr, 322:Fr, 379:Fr, 354:Fr, 359:Fr]
 453: [322:Fr, 342:Fr, 359:Fr, 379:Fr, 354:Fr]
 454: [322:Fr, 342:Fr, 359:Fr, 379:Fr, 354:Fr]
 455: [322:Fr, 342:Fr, 359:Fr, 379:Fr, 354:Fr]
 456: [322:Fr, 342:Fr, 359:Fr, 379:Fr, 354:Fr]
 457: [322:Fr, 342:Fr, 359:Fr, 379:Fr, 354:Fr]
 458: [322:Fr, 342:Fr, 359:Fr, 379:Fr, 354:Fr]
 459: [342:Fr, 354:Fr, 359:Fr, 379:Fr]
 460: [342:Fr, 354:Fr, 359:Fr, 379:Fr]
 461: [342:Fr, 354:Fr, 359:Fr, 379:Fr]
 462: [462:Ju, 342:Fr, 359:Fr, 379:Fr, 354:Fr]
 463: [462:Ju, 342:Fr, 359:Fr, 379:Fr, 354:Fr]
 464: [342:Fr, 354:Fr, 359:Fr, 379:Fr]
 465: [342:Fr, 354:Fr, 359:Fr, 379:Fr]
 466: [342:Fr, 354:Fr, 359:Fr, 379:Fr]
 467: [354:Fr, 379:Fr, 359:Fr]
 468: [354:Fr, 379:Fr, 359:Fr]
 469: [469:So, 354:Fr, 359:Fr, 379:Fr]
 470: [469:So, 354:Fr, 359:Fr, 379:Fr]
 471: [469:So, 354:Fr, 359:Fr, 379:Fr]
 472: [354:Fr, 379:Fr, 359:Fr]
 473: [354:Fr, 379:Fr, 359:Fr]
 474: [354:Fr, 379:Fr, 359:Fr]
 475: [354:Fr, 379:Fr, 359:Fr]
 476: [359:Fr, 379:Fr]
 477: [359:Fr, 379:Fr]
 478: [359:Fr, 379:Fr]
 479: [359:Fr, 379:Fr]
 480: [379:Fr]
 481: [379:Fr]
 482: [379:Fr]
 483: [379:Fr]
 484: []
 485: []
 486: []
 487: []
 488: []
 489: []
 490: []
 491: []
 492: []
 493: []
 494: []
 495: []
 496: [496:Ju]
 497: [496:Ju]
 498: [496:Ju]
 499: [496:Ju]
 500: [496:Ju]
 501: [496:Ju, 501:Ju]
 502: [496:Ju, 501:Ju]
 503: [501:Ju, 503:Fr]
 504: [501:Ju, 503:Fr]
 505: [501:Ju, 503:Fr]
 506: [501:Ju, 503:Fr, 506:So]
 507: [506:So, 503:Fr]
 508: [506:So, 503:Fr]
 509: [509:Ju, 503:Fr, 506:So]
 510: [509:Ju, 510:So, 506:So, 503:Fr]
 511: [506:So, 510:So, 503:Fr]
 512: [506:So, 510:So, 503:Fr]
 513: [506:So, 510:So, 503:Fr]
 514: [506:So, 510:So, 503:Fr]
 515: [506:So, 510:So, 503:Fr]
 516: [506:So, 510:So, 503:Fr, 516:So]
 517: [517:Ju, 510:So, 503:Fr, 516:So]
 518: [517:Ju, 510:So, 503:Fr, 516:So]
 519: [517:Ju, 510:So, 503:Fr, 516:So]
 520: [520:Se, 517:Ju, 503:Fr, 516:So, 510:So]
 521: [517:Ju, 510:So, 503:Fr, 516:So]
 522: [517:Ju, 510:So, 503:Fr, 516:So]
 523: [517:Ju, 510:So, 503:Fr, 516:So]
 524: [517:Ju, 524:Ju, 503:Fr, 516:So, 510:So]
 525: [517:Ju, 524:Ju, 503:Fr, 516:So, 510:So]
 526: [517:Ju, 524:Ju, 503:Fr, 516:So, 510:So]
 527: [517:Ju, 524:Ju, 503:Fr, 516:So, 510:So]
 528: [524:Ju, 510:So, 503:Fr, 516:So]
 529: [524:Ju, 510:So, 503:Fr, 516:So, 529:Fr]
 530: [524:Ju, 510:So, 503:Fr, 516:So, 529:Fr]
 531: [524:Ju, 510:So, 503:Fr, 516:So, 529:Fr]
 532: [524:Ju, 510:So, 503:Fr, 516:So, 529:Fr]
 533: [533:Se, 510:So, 503:Fr, 529:Fr, 516:So]
 534: [533:Se, 510:So, 503:Fr, 529:Fr, 516:So]
 535: [533:Se, 510:So, 503:Fr, 529:Fr, 516:So]
 536: [533:Se, 510:So, 503:Fr, 529:Fr, 516:So]
 537: [533:Se, 510:So, 503:Fr, 529:Fr, 516:So]
 538: [533:Se, 510:So, 538:So, 529:Fr, 516:So, 503:Fr]
 539: [510:So, 516:So, 538:So, 529:Fr, 503:Fr, 539:Fr]
 540: [540:Se, 516:So, 510:So, 529:Fr, 503:Fr, 539:Fr, 538:So]
 541: [540:Se, 516:So, 510:So, 529:Fr, 503:Fr, 539:Fr, 538:So]
 542: [540:Se, 516:So, 510:So, 529:Fr, 503:Fr, 539:Fr, 538:So]
 543: [540:Se, 516:So, 510:So, 529:Fr, 503:Fr, 539:Fr, 538:So]
 544: [510:So, 516:So, 538:So, 529:Fr, 503:Fr, 539:Fr]
 545: [510:So, 516:So, 538:So, 529:Fr, 503:Fr, 539:Fr]
 546: [510:So, 516:So, 538:So, 529:Fr, 503:Fr, 539:Fr]
 547: [510:So, 516:So, 538:So, 529:Fr, 503:Fr, 539:Fr]
 548: [510:So, 516:So, 538:So, 529:Fr, 503:Fr, 539:Fr, 548:Fr]
 549: [510:So, 516:So, 538:So, 529:Fr, 503:Fr, 539:Fr, 548:Fr]
 550: [550:Ju, 510:So, 538:So, 516:So, 503:Fr, 539:Fr, 548:Fr, 529:Fr]
 551: [550:Ju, 510:So, 538:So, 516:So, 503:Fr, 539:Fr, 548:Fr, 529:Fr]
 552: [510:So, 516:So, 538:So, 529:Fr, 503:Fr, 539:Fr, 548:Fr]
 553: [510:So, 516:So, 538:So, 529:Fr, 503:Fr, 539:Fr, 548:Fr]
 554: [510:So, 516:So, 538:So, 529:Fr, 503:Fr, 539:Fr, 548:Fr]
 555: [510:So, 516:So, 538:So, 529:Fr, 503:Fr, 539:Fr, 548:Fr]
 556: [510:So, 516:So, 538:So, 529:Fr, 503:Fr, 539:Fr, 548:Fr]
 557: [510:So, 516:So, 538:So, 529:Fr, 503:Fr, 539:Fr, 548:Fr]
 558: [510:So, 516:So, 538:So, 529:Fr, 503:Fr, 539:Fr, 548:Fr]
 559: [516:So, 503:Fr, 538:So, 529:Fr, 548:Fr, 539:Fr]
 560: [560:Ju, 503:Fr, 516:So, 529:Fr, 548:Fr, 539:Fr, 538:So]
 561: [560:Ju, 503:Fr, 516:So, 529:Fr, 548:Fr, 539:Fr, 538:So]
 562: [560:Ju, 503:Fr, 516:So, 529:Fr, 548:Fr, 539:Fr, 538:So]
 563: [560:Ju, 503:Fr, 516:So, 529:Fr, 548:Fr, 539:Fr, 538:So]
 564: [560:Ju, 503:Fr, 516:So, 529:Fr, 548:Fr, 539:Fr, 538:So]
 565: [516:So, 503:Fr, 538:So, 529:Fr, 548:Fr, 539:Fr]
 566: [516:So, 503:Fr, 538:So, 529:Fr, 548:Fr, 539:Fr]
 567: [567:Ju, 503:Fr, 516:So, 529:Fr, 548:Fr, 539:Fr, 538:So]
 568: [568:Se, 503:Fr, 516:So, 529:Fr, 548:Fr, 539:Fr, 538:So]
 569: [568:Se, 503:Fr, 516:So, 529:Fr, 548:Fr, 539:Fr, 538:So]
 570: [568:Se, 503:Fr, 516:So, 529:Fr, 548:Fr, 539:Fr, 538:So]
 571: [568:Se, 571:Ju, 516:So, 503:Fr, 548:Fr, 539:Fr, 538:So, 529:Fr]
 572: [568:Se, 571:Ju, 516:So, 503:Fr, 548:Fr, 539:Fr, 538:So, 529:Fr]
 573: [571:Ju, 503:Fr, 516:So, 529:Fr, 548:Fr, 539:Fr, 538:So]
 574: [571:Ju, 574:So, 516:So, 503:Fr, 548:Fr, 539:Fr, 538:So, 529:Fr]
 575: [571:Ju, 574:So, 516:So, 503:Fr, 548:Fr, 539:Fr, 538:So, 529:Fr]
 576: [571:Ju, 574:So, 516:So, 503:Fr, 548:Fr, 539:Fr, 538:So, 529:Fr]
 577: [516:So, 574:So, 538:So, 503:Fr, 548:Fr, 539:Fr, 529:Fr]
 578: [516:So, 574:So, 538:So, 503:Fr, 548:Fr, 539:Fr, 529:Fr]
 579: [516:So, 574:So, 538:So, 503:Fr, 548:Fr, 539:Fr, 529:Fr]
 580: [580:Ju, 516:So, 538:So, 574:So, 548:Fr, 539:Fr, 529:Fr, 503:Fr]
 581: [580:Ju, 516:So, 538:So, 574:So, 548:Fr, 539:Fr, 529:Fr, 503:Fr, 581:So]
 582: [516:So, 574:So, 538:So, 581:So, 548:Fr, 539:Fr, 529:Fr, 503:Fr]
 583: [516:So, 574:So, 538:So, 581:So, 548:Fr, 539:Fr, 529:Fr, 503:Fr]
 584: [516:So, 574:So, 538:So, 581:So, 548:Fr, 539:Fr, 529:Fr, 503:Fr]
 585: [516:So, 574:So, 538:So, 581:So, 548:Fr, 539:Fr, 529:Fr, 503:Fr]
 586: [586:Ju, 516:So, 538:So, 574:So, 548:Fr, 539:Fr, 529:Fr, 503:Fr, 581:So]
 587: [586:Ju, 516:So, 538:So, 574:So, 548:Fr, 539:Fr, 529:Fr, 503:Fr, 581:So]
 588: [586:Ju, 516:So, 538:So, 574:So, 548:Fr, 539:Fr, 529:Fr, 503:Fr, 581:So]
 589: [516:So, 574:So, 538:So, 581:So, 548:Fr, 539:Fr, 529:Fr, 503:Fr]
 590: [516:So, 574:So, 538:So, 581:So, 548:Fr, 539:Fr, 529:Fr, 503:Fr]
 591: [516:So, 574:So, 538:So, 581:So, 548:Fr, 539:Fr, 529:Fr, 503:Fr]
 592: [516:So, 574:So, 538:So, 581:So, 548:Fr, 539:Fr, 529:Fr, 503:Fr, 592:So]
 593: [593:Ju, 516:So, 538:So, 581:So, 574:So, 539:Fr, 529:Fr, 503:Fr, 592:So, 548:Fr]
 594: [516:So, 574:So, 538:So, 581:So, 548:Fr, 539:Fr, 529:Fr, 503:Fr, 592:So]
 595: [516:So, 574:So, 538:So, 581:So, 548:Fr, 539:Fr, 529:Fr, 503:Fr, 592:So]
 596: [516:So, 574:So, 538:So, 581:So, 548:Fr, 539:Fr, 529:Fr, 503:Fr, 592:So]
 597: [516:So, 574:So, 538:So, 581:So, 548:Fr, 539:Fr, 529:Fr, 503:Fr, 592:So]
 598: [516:So, 574:So, 538:So, 581:So, 598:So, 539:Fr, 529:Fr, 503:Fr, 592:So, 548:Fr]
 599: [516:So, 574:So, 538:So, 581:So, 598:So, 539:Fr, 529:Fr, 503:Fr, 592:So, 548:Fr]
 600: [516:So, 574:So, 538:So, 581:So, 598:So, 539:Fr, 529:Fr, 503:Fr, 592:So, 548:Fr]
 601: [538:So, 574:So, 529:Fr, 581:So, 598:So, 539:Fr, 548:Fr, 503:Fr, 592:So]
 602: [538:So, 574:So, 529:Fr, 581:So, 598:So, 539:Fr, 548:Fr, 503:Fr, 592:So, 602:Fr]
 603: [603:Se, 538:So, 529:Fr, 581:So, 574:So, 539:Fr, 548:Fr, 503:Fr, 592:So, 602:Fr, 598:So]
 604: [603:Se, 538:So, 529:Fr, 581:So, 574:So, 539:Fr, 548:Fr, 503:Fr, 592:So, 602:Fr, 598:So]
 605: [603:Se, 538:So, 529:Fr, 581:So, 574:So, 539:Fr, 548:Fr, 503:Fr, 592:So, 602:Fr, 598:So]
 606: [603:Se, 538:So, 529:Fr, 581:So, 574:So, 539:Fr, 548:Fr, 503:Fr, 592:So, 602:Fr, 598:So]
 607: [603:Se, 538:So, 529:Fr, 581:So, 574:So, 539:Fr, 548:Fr, 503:Fr, 592:So, 602:Fr, 598:So]
 608: [538:So, 574:So, 529:Fr, 581:So, 598:So, 539:Fr, 548:Fr, 503:Fr, 592:So, 602:Fr]
 609: [538:So, 574:So, 529:Fr, 581:So, 598:So, 539:Fr, 548:Fr, 503:Fr, 592:So, 602:Fr]
 610: [538:So, 574:So, 529:Fr, 581:So, 598:So, 539:Fr, 548:Fr, 503:Fr, 592:So, 602:Fr]
 611: [538:So, 574:So, 529:Fr, 581:So, 598:So, 539:Fr, 548:Fr, 503:Fr, 592:So, 602:Fr]
 612: [612:Se, 538:So, 529:Fr, 581:So, 574:So, 539:Fr, 548:Fr, 503:Fr, 592:So, 602:Fr, 598:So]
 613: [612:Se, 538:So, 529:Fr, 581:So, 574:So, 539:Fr, 548:Fr, 503:Fr, 592:So, 602:Fr, 598:So]
 614: [538:So, 574:So, 529:Fr, 581:So, 598:So, 539:Fr, 548:Fr, 503:Fr, 592:So, 602:Fr]
 615: [538:So, 574:So, 529:Fr, 581:So, 598:So, 539:Fr, 548:Fr, 503:Fr, 592:So, 602:Fr]
 616: [538:So, 574:So, 529:Fr, 581:So, 598:So, 539:Fr, 548:Fr, 503:Fr, 592:So, 602:Fr]
 617: [538:So, 574:So, 529:Fr, 581:So, 598:So, 539:Fr, 548:Fr, 503:Fr, 592:So, 602:Fr]
 618: [538:So, 574:So, 529:Fr, 581:So, 598:So, 539:Fr, 548:Fr, 503:Fr, 592:So, 602:Fr]
 619: [538:So, 574:So, 529:Fr, 581:So, 598:So, 539:Fr, 548:Fr, 503:Fr, 592:So, 602:Fr, 619:Fr]
 620: [574:So, 581:So, 529:Fr, 592:So, 598:So, 539:Fr, 548:Fr, 503:Fr, 619:Fr, 602:Fr]
 621: [574:So, 581:So, 529:Fr, 592:So, 598:So, 539:Fr, 548:Fr, 503:Fr, 619:Fr, 602:Fr]
 622: [574:So, 581:So, 529:Fr, 592:So, 598:So, 539:Fr, 548:Fr, 503:Fr, 619:Fr, 602:Fr]
 623: [574:So, 581:So, 529:Fr, 592:So, 598:So, 539:Fr, 548:Fr, 503:Fr, 619:Fr, 602:Fr]
 624: [574:So, 581:So, 529:Fr, 592:So, 598:So, 539:Fr, 548:Fr, 503:Fr, 619:Fr, 602:Fr]
 625: [581:So, 592:So, 529:Fr, 503:Fr, 598:So, 539:Fr, 548:Fr, 602:Fr, 619:Fr]
 626: [581:So, 592:So, 529:Fr, 503:Fr, 598:So, 539:Fr, 548:Fr, 602:Fr, 619:Fr]
 627: [581:So, 592:So, 529:Fr, 503:Fr, 598:So, 539:Fr, 548:Fr, 602:Fr, 619:Fr]
 628: [581:So, 592:So, 529:Fr, 503:Fr, 598:So, 539:Fr, 548:Fr, 602:Fr, 619:Fr]
 629: [581:So, 592:So, 529:Fr, 503:Fr, 598:So, 539:Fr, 548:Fr, 602:Fr, 619:Fr, 629:Fr]
 630: [581:So, 592:So, 529:Fr, 503:Fr, 598:So, 539:Fr, 548:Fr, 602:Fr, 619:Fr, 629:Fr]
 631: [581:So, 592:So, 529:Fr, 503:Fr, 598:So, 539:Fr, 548:Fr, 602:Fr, 619:Fr, 629:Fr]
 632: [581:So, 592:So, 529:Fr, 503:Fr, 598:So, 539:Fr, 548:Fr, 602:Fr, 619:Fr, 629:Fr]
 633: [592:So, 598:So, 529:Fr, 503:Fr, 629:Fr, 539:Fr, 548:Fr, 602:Fr, 619:Fr]
 634: [592:So, 598:So, 529:Fr, 503:Fr, 629:Fr, 539:Fr, 548:Fr, 602:Fr, 619:Fr]
 635: [592:So, 598:So, 529:Fr, 503:Fr, 629:Fr, 539:Fr, 548:Fr, 602:Fr, 619:Fr]
 636: [592:So, 598:So, 529:Fr, 503:Fr, 629:Fr, 539:Fr, 548:Fr, 602:Fr, 619:Fr]
 637: [592:So, 598:So, 529:Fr, 503:Fr, 629:Fr, 539:Fr, 548:Fr, 602:Fr, 619:Fr]
 638: [598:So, 503:Fr, 529:Fr, 602:Fr, 629:Fr, 539:Fr, 548:Fr, 619:Fr]
 639: [598:So, 503:Fr, 529:Fr, 602:Fr, 629:Fr, 539:Fr, 548:Fr, 619:Fr]
 640: [598:So, 503:Fr, 529:Fr, 602:Fr, 629:Fr, 539:Fr, 548:Fr, 619:Fr]
 641: [598:So, 503:Fr, 529:Fr, 602:Fr, 629:Fr, 539:Fr, 548:Fr, 619:Fr]
 642: [503:Fr, 602:Fr, 529:Fr, 619:Fr, 629:Fr, 539:Fr, 548:Fr, 642:Fr]
 643: [643:So, 503:Fr, 529:Fr, 602:Fr, 629:Fr, 539:Fr, 548:Fr, 642:Fr, 619:Fr]
 644: [643:So, 503:Fr, 529:Fr, 602:Fr, 629:Fr, 539:Fr, 548:Fr, 642:Fr, 619:Fr, 644:Fr]
 645: [643:So, 503:Fr, 529:Fr, 602:Fr, 629:Fr, 539:Fr, 548:Fr, 642:Fr, 619:Fr, 644:Fr]
 646: [643:So, 503:Fr, 529:Fr, 602:Fr, 629:Fr, 539:Fr, 548:Fr, 642:Fr, 619:Fr, 644:Fr]
 647: [643:So, 503:Fr, 529:Fr, 602:Fr, 629:Fr, 539:Fr, 548:Fr, 642:Fr, 619:Fr, 644:Fr]
 648: [643:So, 503:Fr, 529:Fr, 602:Fr, 629:Fr, 539:Fr, 548:Fr, 642:Fr, 619:Fr, 644:Fr]
 649: [503:Fr, 602:Fr, 529:Fr, 619:Fr, 629:Fr, 539:Fr, 548:Fr, 642:Fr, 644:Fr]
 650: [503:Fr, 602:Fr, 529:Fr, 619:Fr, 629:Fr, 539:Fr, 548:Fr, 642:Fr, 644:Fr]
 651: [503:Fr, 602:Fr, 529:Fr, 619:Fr, 629:Fr, 539:Fr, 548:Fr, 642:Fr, 644:Fr]
 652: [503:Fr, 602:Fr, 529:Fr, 619:Fr, 629:Fr, 539:Fr, 548:Fr, 642:Fr, 644:Fr]
 653: [529:Fr, 602:Fr, 539:Fr, 619:Fr, 629:Fr, 644:Fr, 548:Fr, 642:Fr]
 654: [529:Fr, 602:Fr, 539:Fr, 619:Fr, 629:Fr, 644:Fr, 548:Fr, 642:Fr, 654:Fr]
 655: [529:Fr, 602:Fr, 539:Fr, 619:Fr, 629:Fr, 644:Fr, 548:Fr, 642:Fr, 654:Fr]
 656: [529:Fr, 602:Fr, 539:Fr, 619:Fr, 629:Fr, 644:Fr, 548:Fr, 642:Fr, 654:Fr]
 657: [529:Fr, 602:Fr, 539:Fr, 619:Fr, 629:Fr, 644:Fr, 548:Fr, 642:Fr, 654:Fr]
 658: [529:Fr, 602:Fr, 539:Fr, 619:Fr, 629:Fr, 644:Fr, 548:Fr, 642:Fr, 654:Fr]
 659: [529:Fr, 602:Fr, 539:Fr, 619:Fr, 629:Fr, 644:Fr, 548:Fr, 642:Fr, 654:Fr]
 660: [539:Fr, 602:Fr, 548:Fr, 619:Fr, 629:Fr, 644:Fr, 654:Fr, 642:Fr]
 661: [539:Fr, 602:Fr, 548:Fr, 619:Fr, 629:Fr, 644:Fr, 654:Fr, 642:Fr]
 662: [539:Fr, 602:Fr, 548:Fr, 619:Fr, 629:Fr, 644:Fr, 654:Fr, 642:Fr]
 663: [539:Fr, 602:Fr, 548:Fr, 619:Fr, 629:Fr, 644:Fr, 654:Fr, 642:Fr]
 664: [548:Fr, 602:Fr, 642:Fr, 619:Fr, 629:Fr, 644:Fr, 654:Fr]
 665: [548:Fr, 602:Fr, 642:Fr, 619:Fr, 629:Fr, 644:Fr, 654:Fr]
 666: [548:Fr, 602:Fr, 642:Fr, 619:Fr, 629:Fr, 644:Fr, 654:Fr]
 667: [548:Fr, 602:Fr, 642:Fr, 619:Fr, 629:Fr, 644:Fr, 654:Fr]
 668: [548:Fr, 602:Fr, 642:Fr, 619:Fr, 629:Fr, 644:Fr, 654:Fr]
 669: [602:Fr, 619:Fr, 642:Fr, 654:Fr, 629:Fr, 644:Fr]
 670: [602:Fr, 619:Fr, 642:Fr, 654:Fr, 629:Fr, 644:Fr]
 671: [602:Fr, 619:Fr, 642:Fr, 654:Fr, 629:Fr, 644:Fr]
 672: [602:Fr, 619:Fr, 642:Fr, 654:Fr, 629:Fr, 644:Fr]
 673: [602:Fr, 619:Fr, 642:Fr, 654:Fr, 629:Fr, 644:Fr]
 674: [602:Fr, 619:Fr, 642:Fr, 654:Fr, 629:Fr, 644:Fr]
 675: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr]
 676: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr]
 677: [677:Se, 629:Fr, 619:Fr, 654:Fr, 644:Fr, 676:Fr, 642:Fr]
 678: [678:Ju, 629:Fr, 619:Fr, 654:Fr, 644:Fr, 676:Fr, 642:Fr]
 679: [678:Ju, 629:Fr, 619:Fr, 654:Fr, 644:Fr, 676:Fr, 642:Fr, 679:Fr]
 680: [678:Ju, 629:Fr, 619:Fr, 654:Fr, 644:Fr, 676:Fr, 642:Fr, 679:Fr]
 681: [678:Ju, 629:Fr, 619:Fr, 654:Fr, 644:Fr, 676:Fr, 642:Fr, 679:Fr, 681:Fr]
 682: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr]
 683: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr]
 684: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr]
 685: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr]
 686: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr]
 687: [687:Ju, 619:Fr, 642:Fr, 629:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 654:Fr]
 688: [687:Ju, 619:Fr, 642:Fr, 629:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 654:Fr]
 689: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr]
 690: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr]
 691: [691:Ju, 619:Fr, 642:Fr, 629:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 654:Fr]
 692: [691:Ju, 692:Ju, 642:Fr, 629:Fr, 619:Fr, 676:Fr, 681:Fr, 679:Fr, 654:Fr, 644:Fr]
 693: [692:Ju, 619:Fr, 642:Fr, 629:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 654:Fr, 693:Fr]
 694: [692:Ju, 694:Ju, 642:Fr, 629:Fr, 619:Fr, 676:Fr, 681:Fr, 679:Fr, 654:Fr, 693:Fr, 644:Fr]
 695: [692:Ju, 694:Ju, 642:Fr, 629:Fr, 619:Fr, 676:Fr, 681:Fr, 679:Fr, 654:Fr, 693:Fr, 644:Fr]
 696: [692:Ju, 694:Ju, 642:Fr, 629:Fr, 619:Fr, 676:Fr, 681:Fr, 679:Fr, 654:Fr, 693:Fr, 644:Fr]
 697: [692:Ju, 694:Ju, 697:So, 629:Fr, 619:Fr, 642:Fr, 681:Fr, 679:Fr, 654:Fr, 693:Fr, 644:Fr, 676:Fr]
 698: [692:Ju, 694:Ju, 697:So, 629:Fr, 619:Fr, 642:Fr, 681:Fr, 679:Fr, 654:Fr, 693:Fr, 644:Fr, 676:Fr, 698:Fr]
 699: [692:Ju, 694:Ju, 697:So, 629:Fr, 619:Fr, 642:Fr, 681:Fr, 679:Fr, 654:Fr, 693:Fr, 644:Fr, 676:Fr, 698:Fr]
 700: [694:Ju, 619:Fr, 697:So, 629:Fr, 644:Fr, 642:Fr, 681:Fr, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr]
 701: [694:Ju, 619:Fr, 697:So, 629:Fr, 644:Fr, 642:Fr, 681:Fr, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr]
 702: [702:Se, 619:Fr, 694:Ju, 629:Fr, 644:Fr, 697:So, 681:Fr, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr, 642:Fr]
 703: [702:Se, 619:Fr, 694:Ju, 629:Fr, 644:Fr, 697:So, 703:So, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr, 642:Fr, 681:Fr]
 704: [702:Se, 619:Fr, 694:Ju, 629:Fr, 644:Fr, 697:So, 703:So, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr, 642:Fr, 681:Fr]
 705: [702:Se, 619:Fr, 694:Ju, 629:Fr, 644:Fr, 697:So, 703:So, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr, 642:Fr, 681:Fr]
 706: [702:Se, 619:Fr, 706:Se, 629:Fr, 644:Fr, 697:So, 694:Ju, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr, 642:Fr, 681:Fr, 703:So]
 707: [702:Se, 619:Fr, 706:Se, 629:Fr, 644:Fr, 697:So, 694:Ju, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr, 642:Fr, 681:Fr, 703:So]
 708: [706:Se, 619:Fr, 694:Ju, 629:Fr, 644:Fr, 697:So, 703:So, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr, 642:Fr, 681:Fr]
 709: [706:Se, 619:Fr, 694:Ju, 629:Fr, 644:Fr, 697:So, 703:So, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr, 642:Fr, 681:Fr]
 710: [706:Se, 619:Fr, 694:Ju, 629:Fr, 644:Fr, 697:So, 703:So, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr, 642:Fr, 681:Fr]
 711: [706:Se, 619:Fr, 694:Ju, 629:Fr, 644:Fr, 697:So, 703:So, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr, 642:Fr, 681:Fr]
 712: [706:Se, 619:Fr, 694:Ju, 629:Fr, 644:Fr, 697:So, 703:So, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr, 642:Fr, 681:Fr, 712:Fr]
 713: [706:Se, 619:Fr, 694:Ju, 629:Fr, 644:Fr, 697:So, 703:So, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr, 642:Fr, 681:Fr, 712:Fr]
 714: [694:Ju, 619:Fr, 697:So, 629:Fr, 644:Fr, 642:Fr, 703:So, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr, 712:Fr, 681:Fr]
 715: [694:Ju, 619:Fr, 697:So, 629:Fr, 644:Fr, 642:Fr, 703:So, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr, 712:Fr, 681:Fr]
 716: [694:Ju, 619:Fr, 697:So, 629:Fr, 644:Fr, 642:Fr, 703:So, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr, 712:Fr, 681:Fr]
 717: [697:So, 619:Fr, 703:So, 629:Fr, 644:Fr, 642:Fr, 681:Fr, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr, 712:Fr]
 718: [697:So, 619:Fr, 703:So, 629:Fr, 644:Fr, 642:Fr, 681:Fr, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr, 712:Fr]
 719: [697:So, 619:Fr, 703:So, 629:Fr, 644:Fr, 642:Fr, 681:Fr, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 676:Fr, 712:Fr]
 720: [703:So, 619:Fr, 642:Fr, 629:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 712:Fr]
 721: [703:So, 619:Fr, 642:Fr, 629:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 712:Fr]
 722: [703:So, 619:Fr, 642:Fr, 629:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 712:Fr, 722:Fr]
 723: [703:So, 619:Fr, 642:Fr, 629:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 712:Fr, 722:Fr]
 724: [703:So, 619:Fr, 642:Fr, 629:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 654:Fr, 693:Fr, 698:Fr, 712:Fr, 722:Fr]
 725: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr]
 726: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr]
 727: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr]
 728: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr]
 729: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr]
 730: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr]
 731: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr]
 732: [732:So, 629:Fr, 619:Fr, 654:Fr, 644:Fr, 642:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr, 676:Fr]
 733: [733:Ju, 629:Fr, 619:Fr, 654:Fr, 644:Fr, 642:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr, 676:Fr]
 734: [733:Ju, 629:Fr, 619:Fr, 654:Fr, 644:Fr, 642:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr, 676:Fr]
 735: [733:Ju, 629:Fr, 619:Fr, 654:Fr, 644:Fr, 642:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr, 676:Fr]
 736: [733:Ju, 629:Fr, 619:Fr, 654:Fr, 644:Fr, 642:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr, 676:Fr]
 737: [733:Ju, 629:Fr, 619:Fr, 654:Fr, 644:Fr, 642:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr, 676:Fr]
 738: [733:Ju, 629:Fr, 619:Fr, 654:Fr, 644:Fr, 642:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr, 676:Fr]
 739: [733:Ju, 629:Fr, 619:Fr, 654:Fr, 644:Fr, 642:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr, 676:Fr]
 740: [733:Ju, 629:Fr, 619:Fr, 654:Fr, 644:Fr, 642:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr, 676:Fr]
 741: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr]
 742: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr]
 743: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr]
 744: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr]
 745: [619:Fr, 629:Fr, 642:Fr, 654:Fr, 644:Fr, 676:Fr, 681:Fr, 679:Fr, 722:Fr, 693:Fr, 698:Fr, 712:Fr]
 746: [629:Fr, 644:Fr, 642:Fr, 654:Fr, 693:Fr, 676:Fr, 681:Fr, 679:Fr, 722:Fr, 712:Fr, 698:Fr]
 747: [629:Fr, 644:Fr, 642:Fr, 654:Fr, 693:Fr, 676:Fr, 681:Fr, 679:Fr, 722:Fr, 712:Fr, 698:Fr]
 748: [629:Fr, 644:Fr, 642:Fr, 654:Fr, 693:Fr, 676:Fr, 681:Fr, 679:Fr, 722:Fr, 712:Fr, 698:Fr]
 749: [642:Fr, 644:Fr, 676:Fr, 654:Fr, 693:Fr, 698:Fr, 681:Fr, 679:Fr, 722:Fr, 712:Fr]
 750: [642:Fr, 644:Fr, 676:Fr, 654:Fr, 693:Fr, 698:Fr, 681:Fr, 679:Fr, 722:Fr, 712:Fr]
 751: [642:Fr, 644:Fr, 676:Fr, 654:Fr, 693:Fr, 698:Fr, 681:Fr, 679:Fr, 722:Fr, 712:Fr]
 752: [642:Fr, 644:Fr, 676:Fr, 654:Fr, 693:Fr, 698:Fr, 681:Fr, 679:Fr, 722:Fr, 712:Fr]
 753: [753:So, 642:Fr, 676:Fr, 654:Fr, 644:Fr, 698:Fr, 681:Fr, 679:Fr, 722:Fr, 712:Fr, 693:Fr]
 754: [753:So, 642:Fr, 676:Fr, 654:Fr, 644:Fr, 698:Fr, 681:Fr, 679:Fr, 722:Fr, 712:Fr, 693:Fr]
 755: [642:Fr, 644:Fr, 676:Fr, 654:Fr, 693:Fr, 698:Fr, 681:Fr, 679:Fr, 722:Fr, 712:Fr]
 756: [642:Fr, 644:Fr, 676:Fr, 654:Fr, 693:Fr, 698:Fr, 681:Fr, 679:Fr, 722:Fr, 712:Fr]
 757: [642:Fr, 644:Fr, 676:Fr, 654:Fr, 693:Fr, 698:Fr, 681:Fr, 679:Fr, 722:Fr, 712:Fr]
 758: [642:Fr, 644:Fr, 676:Fr, 654:Fr, 693:Fr, 698:Fr, 681:Fr, 679:Fr, 722:Fr, 712:Fr]
 759: [642:Fr, 644:Fr, 676:Fr, 654:Fr, 693:Fr, 698:Fr, 681:Fr, 679:Fr, 722:Fr, 712:Fr]
 760: [644:Fr, 654:Fr, 676:Fr, 679:Fr, 693:Fr, 698:Fr, 681:Fr, 712:Fr, 722:Fr]
 761: [644:Fr, 654:Fr, 676:Fr, 679:Fr, 693:Fr, 698:Fr, 681:Fr, 712:Fr, 722:Fr]
 762: [644:Fr, 654:Fr, 676:Fr, 679:Fr, 693:Fr, 698:Fr, 681:Fr, 712:Fr, 722:Fr]
 763: [763:So, 644:Fr, 676:Fr, 679:Fr, 654:Fr, 698:Fr, 681:Fr, 712:Fr, 722:Fr, 693:Fr]
 764: [644:Fr, 654:Fr, 676:Fr, 679:Fr, 693:Fr, 698:Fr, 681:Fr, 712:Fr, 722:Fr, 764:Fr]
 765: [644:Fr, 654:Fr, 676:Fr, 679:Fr, 693:Fr, 698:Fr, 681:Fr, 712:Fr, 722:Fr, 764:Fr]
 766: [644:Fr, 654:Fr, 676:Fr, 679:Fr, 693:Fr, 698:Fr, 681:Fr, 712:Fr, 722:Fr, 764:Fr]
 767: [644:Fr, 654:Fr, 676:Fr, 679:Fr, 693:Fr, 698:Fr, 681:Fr, 712:Fr, 722:Fr, 764:Fr]
 768: [654:Fr, 679:Fr, 676:Fr, 712:Fr, 693:Fr, 698:Fr, 681:Fr, 764:Fr, 722:Fr]
 769: [769:Se, 654:Fr, 676:Fr, 712:Fr, 679:Fr, 698:Fr, 681:Fr, 764:Fr, 722:Fr, 693:Fr]
 770: [769:Se, 654:Fr, 676:Fr, 712:Fr, 679:Fr, 698:Fr, 681:Fr, 764:Fr, 722:Fr, 693:Fr]
 771: [769:Se, 654:Fr, 676:Fr, 712:Fr, 679:Fr, 698:Fr, 681:Fr, 764:Fr, 722:Fr, 693:Fr]
 772: [654:Fr, 679:Fr, 676:Fr, 712:Fr, 693:Fr, 698:Fr, 681:Fr, 764:Fr, 722:Fr]
 773: [773:Se, 654:Fr, 676:Fr, 712:Fr, 679:Fr, 698:Fr, 681:Fr, 764:Fr, 722:Fr, 693:Fr]
 774: [773:Se, 774:So, 676:Fr, 712:Fr, 654:Fr, 698:Fr, 681:Fr, 764:Fr, 722:Fr, 693:Fr, 679:Fr]
 775: [773:Se, 774:So, 676:Fr, 712:Fr, 654:Fr, 698:Fr, 681:Fr, 764:Fr, 722:Fr, 693:Fr, 679:Fr]
 776: [773:Se, 774:So, 776:So, 712:Fr, 654:Fr, 676:Fr, 681:Fr, 764:Fr, 722:Fr, 693:Fr, 679:Fr, 698:Fr]
 777: [773:Se, 774:So, 777:Se, 712:Fr, 654:Fr, 776:So, 681:Fr, 764:Fr, 722:Fr, 693:Fr, 679:Fr, 698:Fr, 676:Fr]
 778: [777:Se, 774:So, 776:So, 712:Fr, 654:Fr, 676:Fr, 681:Fr, 764:Fr, 722:Fr, 693:Fr, 679:Fr, 698:Fr]
 779: [777:Se, 774:So, 776:So, 712:Fr, 654:Fr, 676:Fr, 681:Fr, 764:Fr, 722:Fr, 693:Fr, 679:Fr, 698:Fr, 779:Fr]
 780: [777:Se, 774:So, 776:So, 712:Fr, 654:Fr, 676:Fr, 681:Fr, 764:Fr, 722:Fr, 693:Fr, 679:Fr, 698:Fr, 779:Fr]
 781: [777:Se, 774:So, 776:So, 712:Fr, 654:Fr, 676:Fr, 681:Fr, 764:Fr, 722:Fr, 693:Fr, 679:Fr, 698:Fr, 779:Fr]
 782: [777:Se, 774:So, 776:So, 712:Fr, 654:Fr, 676:Fr, 681:Fr, 764:Fr, 722:Fr, 693:Fr, 679:Fr, 698:Fr, 779:Fr]
 783: [777:Se, 774:So, 776:So, 712:Fr, 654:Fr, 676:Fr, 783:So, 764:Fr, 722:Fr, 693:Fr, 679:Fr, 698:Fr, 779:Fr, 681:Fr]
 784: [777:Se, 774:So, 776:So, 712:Fr, 654:Fr, 676:Fr, 783:So, 764:Fr, 722:Fr, 693:Fr, 679:Fr, 698:Fr, 779:Fr, 681:Fr]
 785: [774:So, 654:Fr, 776:So, 712:Fr, 679:Fr, 676:Fr, 783:So, 764:Fr, 722:Fr, 693:Fr, 681:Fr, 698:Fr, 779:Fr]
 786: [774:So, 654:Fr, 776:So, 712:Fr, 679:Fr, 676:Fr, 783:So, 764:Fr, 722:Fr, 693:Fr, 681:Fr, 698:Fr, 779:Fr, 786:Fr]
 787: [774:So, 654:Fr, 776:So, 712:Fr, 679:Fr, 676:Fr, 783:So, 764:Fr, 722:Fr, 693:Fr, 681:Fr, 698:Fr, 779:Fr, 786:Fr]
 788: [774:So, 654:Fr, 776:So, 712:Fr, 679:Fr, 676:Fr, 783:So, 764:Fr, 722:Fr, 693:Fr, 681:Fr, 698:Fr, 779:Fr, 786:Fr]
 789: [774:So, 654:Fr, 776:So, 712:Fr, 679:Fr, 676:Fr, 783:So, 764:Fr, 722:Fr, 693:Fr, 681:Fr, 698:Fr, 779:Fr, 786:Fr]
 790: [776:So, 654:Fr, 783:So, 712:Fr, 679:Fr, 676:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 681:Fr, 698:Fr, 779:Fr]
 791: [776:So, 654:Fr, 783:So, 712:Fr, 679:Fr, 676:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 681:Fr, 698:Fr, 779:Fr]
 792: [776:So, 654:Fr, 783:So, 712:Fr, 679:Fr, 676:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 681:Fr, 698:Fr, 779:Fr]
 793: [776:So, 654:Fr, 783:So, 712:Fr, 679:Fr, 676:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 681:Fr, 698:Fr, 779:Fr]
 794: [783:So, 654:Fr, 676:Fr, 712:Fr, 679:Fr, 698:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 681:Fr, 779:Fr]
 795: [783:So, 654:Fr, 676:Fr, 712:Fr, 679:Fr, 698:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 681:Fr, 779:Fr]
 796: [783:So, 654:Fr, 676:Fr, 712:Fr, 679:Fr, 698:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 681:Fr, 779:Fr]
 797: [783:So, 654:Fr, 676:Fr, 712:Fr, 679:Fr, 698:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 681:Fr, 779:Fr]
 798: [783:So, 654:Fr, 676:Fr, 712:Fr, 679:Fr, 698:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 681:Fr, 779:Fr]
 799: [783:So, 654:Fr, 676:Fr, 712:Fr, 679:Fr, 698:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 681:Fr, 779:Fr]
 800: [783:So, 654:Fr, 676:Fr, 712:Fr, 679:Fr, 698:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 681:Fr, 779:Fr]
 801: [654:Fr, 679:Fr, 676:Fr, 712:Fr, 681:Fr, 698:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr]
 802: [654:Fr, 679:Fr, 676:Fr, 712:Fr, 681:Fr, 698:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr]
 803: [654:Fr, 679:Fr, 676:Fr, 712:Fr, 681:Fr, 698:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr]
 804: [654:Fr, 679:Fr, 676:Fr, 712:Fr, 681:Fr, 698:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr]
 805: [654:Fr, 679:Fr, 676:Fr, 712:Fr, 681:Fr, 698:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr]
 806: [806:Ju, 679:Fr, 654:Fr, 712:Fr, 681:Fr, 676:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr, 698:Fr]
 807: [806:Ju, 679:Fr, 654:Fr, 712:Fr, 681:Fr, 676:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr, 698:Fr]
 808: [808:Se, 679:Fr, 654:Fr, 712:Fr, 681:Fr, 676:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr, 698:Fr]
 809: [808:Se, 679:Fr, 654:Fr, 712:Fr, 681:Fr, 676:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr, 698:Fr]
 810: [808:Se, 679:Fr, 654:Fr, 712:Fr, 681:Fr, 676:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr, 698:Fr]
 811: [808:Se, 679:Fr, 654:Fr, 712:Fr, 681:Fr, 676:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr, 698:Fr, 811:Fr]
 812: [808:Se, 679:Fr, 654:Fr, 712:Fr, 681:Fr, 676:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr, 698:Fr, 811:Fr]
 813: [808:Se, 679:Fr, 654:Fr, 712:Fr, 681:Fr, 676:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr, 698:Fr, 811:Fr]
 814: [654:Fr, 679:Fr, 676:Fr, 712:Fr, 681:Fr, 698:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr, 811:Fr]
 815: [654:Fr, 679:Fr, 676:Fr, 712:Fr, 681:Fr, 698:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr, 811:Fr]
 816: [654:Fr, 679:Fr, 676:Fr, 712:Fr, 681:Fr, 698:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr, 811:Fr]
 817: [654:Fr, 679:Fr, 676:Fr, 712:Fr, 681:Fr, 698:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr, 811:Fr]
 818: [676:Fr, 679:Fr, 698:Fr, 712:Fr, 681:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr]
 819: [676:Fr, 679:Fr, 698:Fr, 712:Fr, 681:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr]
 820: [676:Fr, 679:Fr, 698:Fr, 712:Fr, 681:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr]
 821: [676:Fr, 679:Fr, 698:Fr, 712:Fr, 681:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 693:Fr, 779:Fr]
 822: [679:Fr, 681:Fr, 698:Fr, 712:Fr, 693:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr]
 823: [679:Fr, 681:Fr, 698:Fr, 712:Fr, 693:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr]
 824: [824:Ju, 679:Fr, 698:Fr, 712:Fr, 681:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr, 693:Fr]
 825: [679:Fr, 681:Fr, 698:Fr, 712:Fr, 693:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr]
 826: [679:Fr, 681:Fr, 698:Fr, 712:Fr, 693:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr]
 827: [679:Fr, 681:Fr, 698:Fr, 712:Fr, 693:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr]
 828: [828:So, 679:Fr, 698:Fr, 712:Fr, 681:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr, 693:Fr]
 829: [829:Ju, 679:Fr, 828:So, 712:Fr, 681:Fr, 698:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr, 693:Fr, 811:Fr]
 830: [829:Ju, 679:Fr, 828:So, 712:Fr, 681:Fr, 698:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr, 693:Fr, 811:Fr]
 831: [828:So, 679:Fr, 698:Fr, 712:Fr, 681:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr, 693:Fr]
 832: [828:So, 679:Fr, 698:Fr, 712:Fr, 681:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr, 693:Fr]
 833: [828:So, 679:Fr, 698:Fr, 712:Fr, 681:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr, 693:Fr]
 834: [828:So, 679:Fr, 698:Fr, 712:Fr, 681:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr, 693:Fr]
 835: [828:So, 679:Fr, 698:Fr, 712:Fr, 681:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr, 693:Fr]
 836: [828:So, 679:Fr, 698:Fr, 712:Fr, 681:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr, 693:Fr]
 837: [828:So, 679:Fr, 698:Fr, 712:Fr, 681:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr, 693:Fr, 837:Fr]
 838: [679:Fr, 681:Fr, 698:Fr, 712:Fr, 693:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr, 837:Fr]
 839: [679:Fr, 681:Fr, 698:Fr, 712:Fr, 693:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr, 837:Fr]
 840: [679:Fr, 681:Fr, 698:Fr, 712:Fr, 693:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 779:Fr, 837:Fr]
 841: [681:Fr, 693:Fr, 698:Fr, 712:Fr, 779:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 837:Fr]
 842: [681:Fr, 693:Fr, 698:Fr, 712:Fr, 779:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 837:Fr]
 843: [681:Fr, 693:Fr, 698:Fr, 712:Fr, 779:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 837:Fr]
 844: [681:Fr, 693:Fr, 698:Fr, 712:Fr, 779:Fr, 811:Fr, 786:Fr, 764:Fr, 722:Fr, 837:Fr]
 845: [693:Fr, 712:Fr, 698:Fr, 722:Fr, 779:Fr, 811:Fr, 786:Fr, 764:Fr, 837:Fr]
 846: [693:Fr, 712:Fr, 698:Fr, 722:Fr, 779:Fr, 811:Fr, 786:Fr, 764:Fr, 837:Fr]
 847: [693:Fr, 712:Fr, 698:Fr, 722:Fr, 779:Fr, 811:Fr, 786:Fr, 764:Fr, 837:Fr]
 848: [698:Fr, 712:Fr, 786:Fr, 722:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr]
 849: [849:So, 698:Fr, 786:Fr, 712:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr]
 850: [849:So, 698:Fr, 786:Fr, 712:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr]
 851: [849:So, 698:Fr, 786:Fr, 712:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr]
 852: [849:So, 698:Fr, 786:Fr, 712:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr]
 853: [849:So, 698:Fr, 786:Fr, 712:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr]
 854: [854:Se, 849:So, 786:Fr, 712:Fr, 698:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr, 779:Fr]
 855: [854:Se, 849:So, 786:Fr, 712:Fr, 698:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr, 779:Fr, 855:Fr]
 856: [849:So, 698:Fr, 786:Fr, 712:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr, 855:Fr]
 857: [849:So, 698:Fr, 786:Fr, 712:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr, 855:Fr]
 858: [858:Se, 849:So, 786:Fr, 712:Fr, 698:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr, 855:Fr, 779:Fr]
 859: [858:Se, 849:So, 786:Fr, 712:Fr, 698:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr, 855:Fr, 779:Fr]
 860: [849:So, 698:Fr, 786:Fr, 712:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr, 855:Fr]
 861: [849:So, 698:Fr, 786:Fr, 712:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr, 855:Fr]
 862: [849:So, 698:Fr, 786:Fr, 712:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr, 855:Fr]
 863: [849:So, 698:Fr, 786:Fr, 712:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr, 855:Fr]
 864: [849:So, 698:Fr, 786:Fr, 712:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr, 855:Fr]
 865: [849:So, 698:Fr, 786:Fr, 712:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr, 855:Fr]
 866: [866:Ju, 849:So, 786:Fr, 712:Fr, 698:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr, 855:Fr, 779:Fr]
 867: [849:So, 698:Fr, 786:Fr, 712:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr, 855:Fr]
 868: [849:So, 698:Fr, 786:Fr, 712:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr, 855:Fr]
 869: [849:So, 698:Fr, 786:Fr, 712:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr, 855:Fr]
 870: [849:So, 698:Fr, 786:Fr, 712:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 722:Fr, 855:Fr]
 871: [698:Fr, 712:Fr, 786:Fr, 722:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 855:Fr]
 872: [698:Fr, 712:Fr, 786:Fr, 722:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 855:Fr, 872:Fr]
 873: [698:Fr, 712:Fr, 786:Fr, 722:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 855:Fr, 872:Fr]
 874: [698:Fr, 712:Fr, 786:Fr, 722:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 855:Fr, 872:Fr]
 875: [698:Fr, 712:Fr, 786:Fr, 722:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 855:Fr, 872:Fr]
 876: [698:Fr, 712:Fr, 786:Fr, 722:Fr, 779:Fr, 811:Fr, 837:Fr, 764:Fr, 855:Fr, 872:Fr]
 877: [712:Fr, 722:Fr, 786:Fr, 764:Fr, 779:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr]
 878: [878:So, 712:Fr, 786:Fr, 764:Fr, 722:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 779:Fr]
 879: [878:So, 712:Fr, 786:Fr, 764:Fr, 722:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 779:Fr]
 880: [878:So, 712:Fr, 786:Fr, 764:Fr, 722:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 779:Fr]
 881: [881:Ju, 878:So, 786:Fr, 764:Fr, 712:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 779:Fr, 722:Fr]
 882: [881:Ju, 878:So, 786:Fr, 764:Fr, 712:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 779:Fr, 722:Fr]
 883: [878:So, 712:Fr, 786:Fr, 764:Fr, 722:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 779:Fr]
 884: [878:So, 712:Fr, 786:Fr, 764:Fr, 722:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 779:Fr, 884:Fr]
 885: [878:So, 712:Fr, 885:So, 764:Fr, 722:Fr, 786:Fr, 837:Fr, 872:Fr, 855:Fr, 779:Fr, 884:Fr, 811:Fr]
 886: [878:So, 712:Fr, 885:So, 764:Fr, 722:Fr, 786:Fr, 837:Fr, 872:Fr, 855:Fr, 779:Fr, 884:Fr, 811:Fr]
 887: [878:So, 712:Fr, 885:So, 764:Fr, 722:Fr, 786:Fr, 837:Fr, 872:Fr, 855:Fr, 779:Fr, 884:Fr, 811:Fr]
 888: [885:So, 712:Fr, 786:Fr, 764:Fr, 722:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 779:Fr, 884:Fr]
 889: [885:So, 712:Fr, 786:Fr, 764:Fr, 722:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 779:Fr, 884:Fr]
 890: [885:So, 712:Fr, 786:Fr, 764:Fr, 722:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 779:Fr, 884:Fr]
 891: [885:So, 712:Fr, 786:Fr, 764:Fr, 722:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 779:Fr, 884:Fr]
 892: [885:So, 712:Fr, 786:Fr, 764:Fr, 722:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 779:Fr, 884:Fr]
 893: [885:So, 712:Fr, 786:Fr, 764:Fr, 722:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 779:Fr, 884:Fr]
 894: [885:So, 712:Fr, 786:Fr, 764:Fr, 722:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 779:Fr, 884:Fr]
 895: [885:So, 712:Fr, 786:Fr, 764:Fr, 722:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 779:Fr, 884:Fr]
 896: [712:Fr, 722:Fr, 786:Fr, 764:Fr, 779:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 884:Fr]
 897: [712:Fr, 722:Fr, 786:Fr, 764:Fr, 779:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 884:Fr]
 898: [712:Fr, 722:Fr, 786:Fr, 764:Fr, 779:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 884:Fr]
 899: [899:Ju, 712:Fr, 786:Fr, 764:Fr, 722:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 884:Fr, 779:Fr]
 900: [712:Fr, 722:Fr, 786:Fr, 764:Fr, 779:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 884:Fr]
 901: [712:Fr, 722:Fr, 786:Fr, 764:Fr, 779:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 884:Fr]
 902: [712:Fr, 722:Fr, 786:Fr, 764:Fr, 779:Fr, 811:Fr, 837:Fr, 872:Fr, 855:Fr, 884:Fr]
 903: [722:Fr, 764:Fr, 786:Fr, 855:Fr, 779:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr]
 904: [722:Fr, 764:Fr, 786:Fr, 855:Fr, 779:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr]
 905: [722:Fr, 764:Fr, 786:Fr, 855:Fr, 779:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr]
 906: [906:Ju, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr]
 907: [906:Ju, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr]
 908: [906:Ju, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr]
 909: [722:Fr, 764:Fr, 786:Fr, 855:Fr, 779:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr]
 910: [910:Ju, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr]
 911: [910:Ju, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr]
 912: [910:Ju, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr]
 913: [910:Ju, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr]
 914: [910:Ju, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr]
 915: [915:Ju, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr]
 916: [915:Ju, 722:Fr, 916:Ju, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 811:Fr]
 917: [915:Ju, 722:Fr, 916:Ju, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 811:Fr]
 918: [915:Ju, 722:Fr, 916:Ju, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 811:Fr]
 919: [916:Ju, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr]
 920: [916:Ju, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr]
 921: [916:Ju, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr]
 922: [916:Ju, 722:Fr, 922:So, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr, 811:Fr]
 923: [916:Ju, 722:Fr, 922:So, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr, 811:Fr]
 924: [916:Ju, 722:Fr, 922:So, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr, 811:Fr]
 925: [916:Ju, 722:Fr, 922:So, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr, 811:Fr]
 926: [916:Ju, 722:Fr, 922:So, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr, 811:Fr]
 927: [922:So, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr]
 928: [922:So, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr]
 929: [922:So, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr]
 930: [922:So, 722:Fr, 930:So, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr, 811:Fr]
 931: [922:So, 722:Fr, 930:So, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr, 811:Fr]
 932: [922:So, 722:Fr, 930:So, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr, 811:Fr]
 933: [922:So, 722:Fr, 930:So, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr, 811:Fr]
 934: [930:So, 722:Fr, 934:So, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr, 811:Fr]
 935: [930:So, 722:Fr, 934:So, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr, 811:Fr]
 936: [930:So, 722:Fr, 934:So, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr, 811:Fr]
 937: [930:So, 722:Fr, 934:So, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr, 811:Fr]
 938: [930:So, 722:Fr, 934:So, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr, 811:Fr]
 939: [930:So, 722:Fr, 934:So, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr, 811:Fr]
 940: [930:So, 722:Fr, 934:So, 855:Fr, 764:Fr, 786:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr, 811:Fr]
 941: [934:So, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr]
 942: [934:So, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr]
 943: [934:So, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr]
 944: [934:So, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr]
 945: [934:So, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr]
 946: [934:So, 722:Fr, 786:Fr, 855:Fr, 764:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 779:Fr, 921:Fr]
 947: [722:Fr, 764:Fr, 786:Fr, 855:Fr, 779:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 921:Fr]
 948: [722:Fr, 764:Fr, 786:Fr, 855:Fr, 779:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 921:Fr]
 949: [722:Fr, 764:Fr, 786:Fr, 855:Fr, 779:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 903:Fr, 921:Fr]
 950: [950:Se, 764:Fr, 786:Fr, 855:Fr, 779:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 921:Fr, 903:Fr]
 951: [950:Se, 764:Fr, 786:Fr, 855:Fr, 779:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 921:Fr, 903:Fr]
 952: [950:Se, 764:Fr, 786:Fr, 855:Fr, 779:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 921:Fr, 903:Fr]
 953: [764:Fr, 779:Fr, 786:Fr, 855:Fr, 903:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 921:Fr]
 954: [764:Fr, 779:Fr, 786:Fr, 855:Fr, 903:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 921:Fr]
 955: [764:Fr, 779:Fr, 786:Fr, 855:Fr, 903:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 921:Fr]
 956: [764:Fr, 779:Fr, 786:Fr, 855:Fr, 903:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 921:Fr]
 957: [764:Fr, 779:Fr, 786:Fr, 855:Fr, 903:Fr, 811:Fr, 837:Fr, 872:Fr, 884:Fr, 921:Fr]
 958: [779:Fr, 855:Fr, 786:Fr, 872:Fr, 903:Fr, 811:Fr, 837:Fr, 921:Fr, 884:Fr]
 959: [779:Fr, 855:Fr, 786:Fr, 872:Fr, 903:Fr, 811:Fr, 837:Fr, 921:Fr, 884:Fr]
 960: [779:Fr, 855:Fr, 786:Fr, 872:Fr, 903:Fr, 811:Fr, 837:Fr, 921:Fr, 884:Fr]
 961: [779:Fr, 855:Fr, 786:Fr, 872:Fr, 903:Fr, 811:Fr, 837:Fr, 921:Fr, 884:Fr]
 962: [779:Fr, 855:Fr, 786:Fr, 872:Fr, 903:Fr, 811:Fr, 837:Fr, 921:Fr, 884:Fr]
 963: [786:Fr, 855:Fr, 811:Fr, 872:Fr, 903:Fr, 884:Fr, 837:Fr, 921:Fr]
 964: [964:Ju, 786:Fr, 811:Fr, 855:Fr, 903:Fr, 884:Fr, 837:Fr, 921:Fr, 872:Fr]
 965: [964:Ju, 786:Fr, 811:Fr, 855:Fr, 903:Fr, 884:Fr, 837:Fr, 921:Fr, 872:Fr]
 966: [964:Ju, 786:Fr, 811:Fr, 855:Fr, 903:Fr, 884:Fr, 837:Fr, 921:Fr, 872:Fr, 966:Fr]
 967: [786:Fr, 855:Fr, 811:Fr, 872:Fr, 903:Fr, 884:Fr, 837:Fr, 921:Fr, 966:Fr]
 968: [786:Fr, 855:Fr, 811:Fr, 872:Fr, 903:Fr, 884:Fr, 837:Fr, 921:Fr, 966:Fr]
 969: [786:Fr, 855:Fr, 811:Fr, 872:Fr, 903:Fr, 884:Fr, 837:Fr, 921:Fr, 966:Fr]
 970: [811:Fr, 855:Fr, 837:Fr, 872:Fr, 903:Fr, 884:Fr, 966:Fr, 921:Fr]
 971: [811:Fr, 855:Fr, 837:Fr, 872:Fr, 903:Fr, 884:Fr, 966:Fr, 921:Fr]
 972: [972:Se, 811:Fr, 837:Fr, 855:Fr, 903:Fr, 884:Fr, 966:Fr, 921:Fr, 872:Fr]
 973: [972:Se, 811:Fr, 837:Fr, 855:Fr, 903:Fr, 884:Fr, 966:Fr, 921:Fr, 872:Fr]
 974: [972:Se, 811:Fr, 837:Fr, 855:Fr, 903:Fr, 884:Fr, 966:Fr, 921:Fr, 872:Fr]
 975: [972:Se, 811:Fr, 837:Fr, 855:Fr, 903:Fr, 884:Fr, 966:Fr, 921:Fr, 872:Fr]
 976: [972:Se, 811:Fr, 837:Fr, 855:Fr, 903:Fr, 884:Fr, 966:Fr, 921:Fr, 872:Fr]
 977: [811:Fr, 855:Fr, 837:Fr, 872:Fr, 903:Fr, 884:Fr, 966:Fr, 921:Fr]
 978: [811:Fr, 855:Fr, 837:Fr, 872:Fr, 903:Fr, 884:Fr, 966:Fr, 921:Fr]
 979: [811:Fr, 855:Fr, 837:Fr, 872:Fr, 903:Fr, 884:Fr, 966:Fr, 921:Fr, 979:Fr]
 980: [811:Fr, 855:Fr, 837:Fr, 872:Fr, 903:Fr, 884:Fr, 966:Fr, 921:Fr, 979:Fr]
 981: [837:Fr, 855:Fr, 884:Fr, 872:Fr, 903:Fr, 979:Fr, 966:Fr, 921:Fr]
 982: [837:Fr, 855:Fr, 884:Fr, 872:Fr, 903:Fr, 979:Fr, 966:Fr, 921:Fr]
 983: [837:Fr, 855:Fr, 884:Fr, 872:Fr, 903:Fr, 979:Fr, 966:Fr, 921:Fr]
 984: [984:Se, 837:Fr, 884:Fr, 855:Fr, 903:Fr, 979:Fr, 966:Fr, 921:Fr, 872:Fr]
 985: [984:Se, 837:Fr, 884:Fr, 855:Fr, 903:Fr, 979:Fr, 966:Fr, 921:Fr, 872:Fr]
 986: [984:Se, 837:Fr, 884:Fr, 855:Fr, 903:Fr, 979:Fr, 966:Fr, 921:Fr, 872:Fr]
 987: [837:Fr, 855:Fr, 884:Fr, 872:Fr, 903:Fr, 979:Fr, 966:Fr, 921:Fr, 987:Fr]
 988: [837:Fr, 855:Fr, 884:Fr, 872:Fr, 903:Fr, 979:Fr, 966:Fr, 921:Fr, 987:Fr]
 989: [989:Se, 837:Fr, 884:Fr, 872:Fr, 855:Fr, 979:Fr, 966:Fr, 921:Fr, 987:Fr, 903:Fr]
 990: [989:Se, 990:So, 884:Fr, 872:Fr, 837:Fr, 979:Fr, 966:Fr, 921:Fr, 987:Fr, 903:Fr, 855:Fr]
 991: [989:Se, 990:So, 884:Fr, 872:Fr, 837:Fr, 979:Fr, 966:Fr, 921:Fr, 987:Fr, 903:Fr, 855:Fr]
 992: [989:Se, 990:So, 884:Fr, 872:Fr, 837:Fr, 979:Fr, 966:Fr, 921:Fr, 987:Fr, 903:Fr, 855:Fr]
 993: [990:So, 837:Fr, 884:Fr, 872:Fr, 855:Fr, 979:Fr, 966:Fr, 921:Fr, 987:Fr, 903:Fr]
 994: [990:So, 994:So, 884:Fr, 872:Fr, 837:Fr, 979:Fr, 966:Fr, 921:Fr, 987:Fr, 903:Fr, 855:Fr]
 995: [990:So, 994:So, 884:Fr, 872:Fr, 837:Fr, 979:Fr, 966:Fr, 921:Fr, 987:Fr, 903:Fr, 855:Fr]
 996: [990:So, 994:So, 884:Fr, 872:Fr, 837:Fr, 979:Fr, 966:Fr, 921:Fr, 987:Fr, 903:Fr, 855:Fr]
 997: [990:So, 994:So, 997:So, 872:Fr, 837:Fr, 884:Fr, 966:Fr, 921:Fr, 987:Fr, 903:Fr, 855:Fr, 979:Fr]
 998: [990:So, 994:So, 997:So, 872:Fr, 837:Fr, 884:Fr, 966:Fr, 921:Fr, 987:Fr, 903:Fr, 855:Fr, 979:Fr]
 999: [990:So, 994:So, 997:So, 872:Fr, 837:Fr, 884:Fr, 966:Fr, 921:Fr, 987:Fr, 903:Fr, 855:Fr, 979:Fr]
 1000: [994:So, 837:Fr, 997:So, 872:Fr, 855:Fr, 884:Fr, 966:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr]
 1001: [994:So, 837:Fr, 997:So, 872:Fr, 855:Fr, 1001:So, 966:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr]
 1002: [994:So, 837:Fr, 997:So, 872:Fr, 855:Fr, 1001:So, 966:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr]
 1003: [994:So, 837:Fr, 997:So, 872:Fr, 855:Fr, 1001:So, 966:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 1003:So]
 1004: [994:So, 837:Fr, 997:So, 872:Fr, 855:Fr, 1001:So, 966:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 1003:So]
 1005: [994:So, 837:Fr, 997:So, 872:Fr, 855:Fr, 1001:So, 966:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 1003:So]
 1006: [1006:Se, 837:Fr, 994:So, 872:Fr, 855:Fr, 1001:So, 997:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 1003:So, 966:Fr]
 1007: [1006:Se, 837:Fr, 1007:Ju, 872:Fr, 855:Fr, 1001:So, 994:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 1003:So, 966:Fr, 997:So]
 1008: [1007:Ju, 837:Fr, 994:So, 872:Fr, 855:Fr, 1001:So, 997:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 1003:So, 966:Fr, 1008:Fr]
 1009: [1007:Ju, 837:Fr, 994:So, 872:Fr, 855:Fr, 1001:So, 997:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 1003:So, 966:Fr, 1008:Fr]
 1010: [1007:Ju, 837:Fr, 994:So, 872:Fr, 855:Fr, 1001:So, 997:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 1003:So, 966:Fr, 1008:Fr]
 1011: [1007:Ju, 837:Fr, 994:So, 872:Fr, 855:Fr, 1001:So, 997:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 1003:So, 966:Fr, 1008:Fr]
 1012: [1007:Ju, 837:Fr, 994:So, 872:Fr, 855:Fr, 1001:So, 997:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 1003:So, 966:Fr, 1008:Fr]
 1013: [1007:Ju, 837:Fr, 994:So, 872:Fr, 855:Fr, 1001:So, 997:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 1003:So, 966:Fr, 1008:Fr]
 1014: [1007:Ju, 837:Fr, 994:So, 872:Fr, 855:Fr, 1001:So, 997:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 1003:So, 966:Fr, 1008:Fr]
 1015: [994:So, 837:Fr, 997:So, 872:Fr, 855:Fr, 1001:So, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 1003:So, 1008:Fr, 966:Fr]
 1016: [994:So, 837:Fr, 997:So, 872:Fr, 855:Fr, 1001:So, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 1003:So, 1008:Fr, 966:Fr]
 1017: [994:So, 837:Fr, 997:So, 872:Fr, 855:Fr, 1001:So, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 1003:So, 1008:Fr, 966:Fr]
 1018: [994:So, 837:Fr, 997:So, 872:Fr, 855:Fr, 1001:So, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 1003:So, 1008:Fr, 966:Fr]
 1019: [994:So, 837:Fr, 997:So, 872:Fr, 855:Fr, 1001:So, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 1003:So, 1008:Fr, 966:Fr]
 1020: [994:So, 837:Fr, 997:So, 872:Fr, 855:Fr, 1001:So, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 1003:So, 1008:Fr, 966:Fr]
 1021: [997:So, 837:Fr, 1001:So, 872:Fr, 855:Fr, 1003:So, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 966:Fr, 1008:Fr]
 1022: [997:So, 837:Fr, 1001:So, 872:Fr, 855:Fr, 1003:So, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 966:Fr, 1008:Fr]
 1023: [997:So, 837:Fr, 1001:So, 872:Fr, 855:Fr, 1003:So, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 884:Fr, 966:Fr, 1008:Fr]
 1024: [1024:Se, 837:Fr, 1001:So, 872:Fr, 855:Fr, 884:Fr, 1003:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1015:So]
 1025: [1024:Se, 837:Fr, 1001:So, 872:Fr, 855:Fr, 884:Fr, 1003:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1015:So]
 1026: [1024:Se, 837:Fr, 1001:So, 872:Fr, 855:Fr, 884:Fr, 1003:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1015:So]
 1027: [1024:Se, 837:Fr, 1001:So, 872:Fr, 855:Fr, 884:Fr, 1003:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1015:So]
 1028: [1024:Se, 837:Fr, 1001:So, 872:Fr, 855:Fr, 884:Fr, 1003:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1015:So]
 1029: [1024:Se, 837:Fr, 1001:So, 872:Fr, 855:Fr, 884:Fr, 1003:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1015:So]
 1030: [1024:Se, 837:Fr, 1001:So, 872:Fr, 855:Fr, 884:Fr, 1003:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1015:So]
 1031: [1001:So, 837:Fr, 1003:So, 872:Fr, 855:Fr, 884:Fr, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr]
 1032: [1001:So, 837:Fr, 1003:So, 872:Fr, 855:Fr, 884:Fr, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr]
 1033: [1033:Ju, 837:Fr, 1001:So, 872:Fr, 855:Fr, 884:Fr, 1003:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1015:So]
 1034: [1034:Se, 837:Fr, 1033:Ju, 872:Fr, 855:Fr, 884:Fr, 1001:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1015:So, 1003:So]
 1035: [1034:Se, 837:Fr, 1033:Ju, 872:Fr, 855:Fr, 884:Fr, 1001:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1015:So, 1003:So]
 1036: [1034:Se, 837:Fr, 1033:Ju, 872:Fr, 855:Fr, 884:Fr, 1001:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1015:So, 1003:So]
 1037: [1033:Ju, 837:Fr, 1001:So, 872:Fr, 855:Fr, 884:Fr, 1003:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1015:So, 1037:Fr]
 1038: [1033:Ju, 837:Fr, 1001:So, 872:Fr, 855:Fr, 884:Fr, 1003:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1015:So, 1037:Fr, 1038:Fr]
 1039: [1033:Ju, 837:Fr, 1001:So, 872:Fr, 855:Fr, 884:Fr, 1003:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1015:So, 1037:Fr, 1038:Fr]
 1040: [1033:Ju, 837:Fr, 1001:So, 872:Fr, 855:Fr, 884:Fr, 1003:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1015:So, 1037:Fr, 1038:Fr]
 1041: [1033:Ju, 837:Fr, 1001:So, 872:Fr, 855:Fr, 884:Fr, 1003:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1015:So, 1037:Fr, 1038:Fr]
 1042: [1001:So, 837:Fr, 1003:So, 872:Fr, 855:Fr, 884:Fr, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1037:Fr]
 1043: [1001:So, 837:Fr, 1003:So, 872:Fr, 855:Fr, 884:Fr, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1037:Fr, 1043:Fr]
 1044: [1001:So, 837:Fr, 1003:So, 872:Fr, 855:Fr, 884:Fr, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1037:Fr, 1043:Fr]
 1045: [1001:So, 837:Fr, 1003:So, 872:Fr, 855:Fr, 884:Fr, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1037:Fr, 1043:Fr]
 1046: [1001:So, 837:Fr, 1003:So, 872:Fr, 855:Fr, 884:Fr, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1037:Fr, 1043:Fr]
 1047: [1047:Ju, 1001:So, 1003:So, 837:Fr, 855:Fr, 884:Fr, 1015:So, 872:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1037:Fr, 1043:Fr, 921:Fr]
 1048: [1047:Ju, 1001:So, 1003:So, 837:Fr, 855:Fr, 884:Fr, 1015:So, 872:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1037:Fr, 1043:Fr, 921:Fr]
 1049: [1001:So, 837:Fr, 1003:So, 872:Fr, 855:Fr, 884:Fr, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1037:Fr, 1043:Fr]
 1050: [1001:So, 837:Fr, 1003:So, 872:Fr, 855:Fr, 884:Fr, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1037:Fr, 1043:Fr]
 1051: [1051:Se, 1001:So, 1003:So, 837:Fr, 855:Fr, 884:Fr, 1015:So, 872:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1037:Fr, 1043:Fr, 921:Fr]
 1052: [1051:Se, 1001:So, 1003:So, 837:Fr, 855:Fr, 884:Fr, 1015:So, 872:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1037:Fr, 1043:Fr, 921:Fr]
 1053: [1051:Se, 1001:So, 1003:So, 837:Fr, 855:Fr, 884:Fr, 1015:So, 872:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1037:Fr, 1043:Fr, 921:Fr]
 1054: [1001:So, 837:Fr, 1003:So, 872:Fr, 855:Fr, 884:Fr, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1037:Fr, 1043:Fr]
 1055: [1001:So, 837:Fr, 1003:So, 872:Fr, 855:Fr, 884:Fr, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1037:Fr, 1043:Fr]
 1056: [1001:So, 837:Fr, 1003:So, 872:Fr, 855:Fr, 884:Fr, 1015:So, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1037:Fr, 1043:Fr]
 1057: [1003:So, 837:Fr, 1015:So, 872:Fr, 855:Fr, 884:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1043:Fr]
 1058: [1003:So, 837:Fr, 1015:So, 872:Fr, 855:Fr, 884:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1043:Fr]
 1059: [1003:So, 837:Fr, 1015:So, 872:Fr, 855:Fr, 884:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1043:Fr]
 1060: [1003:So, 837:Fr, 1015:So, 872:Fr, 855:Fr, 884:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1043:Fr]
 1061: [1003:So, 837:Fr, 1015:So, 872:Fr, 855:Fr, 884:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1043:Fr]
 1062: [1003:So, 837:Fr, 1015:So, 872:Fr, 855:Fr, 884:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1043:Fr]
 1063: [1003:So, 837:Fr, 1015:So, 872:Fr, 855:Fr, 884:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 966:Fr, 1038:Fr, 1043:Fr]
 1064: [1015:So, 837:Fr, 884:Fr, 872:Fr, 855:Fr, 966:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr]
 1065: [1015:So, 837:Fr, 884:Fr, 872:Fr, 855:Fr, 966:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr]
 1066: [1066:Ju, 837:Fr, 1015:So, 872:Fr, 855:Fr, 966:Fr, 884:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr, 1037:Fr]
 1067: [1066:Ju, 837:Fr, 1015:So, 872:Fr, 855:Fr, 966:Fr, 884:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr, 1037:Fr, 1067:Fr]
 1068: [1066:Ju, 837:Fr, 1015:So, 872:Fr, 855:Fr, 966:Fr, 884:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr, 1037:Fr, 1067:Fr]
 1069: [1015:So, 837:Fr, 884:Fr, 872:Fr, 855:Fr, 966:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr, 1067:Fr]
 1070: [1015:So, 837:Fr, 884:Fr, 872:Fr, 855:Fr, 966:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr, 1067:Fr]
 1071: [1015:So, 837:Fr, 884:Fr, 872:Fr, 855:Fr, 966:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr, 1067:Fr]
 1072: [1015:So, 837:Fr, 884:Fr, 872:Fr, 855:Fr, 966:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr, 1067:Fr]
 1073: [1015:So, 1073:So, 884:Fr, 837:Fr, 855:Fr, 966:Fr, 1037:Fr, 872:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr, 1067:Fr, 921:Fr]
 1074: [1015:So, 1073:So, 884:Fr, 837:Fr, 855:Fr, 966:Fr, 1037:Fr, 872:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr, 1067:Fr, 921:Fr]
 1075: [1015:So, 1073:So, 884:Fr, 837:Fr, 855:Fr, 966:Fr, 1037:Fr, 872:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr, 1067:Fr, 921:Fr]
 1076: [1015:So, 1073:So, 884:Fr, 837:Fr, 855:Fr, 966:Fr, 1037:Fr, 872:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr, 1067:Fr, 921:Fr]
 1077: [1073:So, 837:Fr, 884:Fr, 872:Fr, 855:Fr, 966:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr, 1067:Fr]
 1078: [1073:So, 837:Fr, 884:Fr, 872:Fr, 855:Fr, 966:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr, 1067:Fr]
 1079: [1073:So, 837:Fr, 884:Fr, 872:Fr, 855:Fr, 966:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr, 1067:Fr]
 1080: [1073:So, 837:Fr, 884:Fr, 872:Fr, 855:Fr, 966:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr, 1067:Fr]
 1081: [1073:So, 837:Fr, 884:Fr, 872:Fr, 855:Fr, 966:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr, 1067:Fr]
 1082: [1073:So, 837:Fr, 884:Fr, 872:Fr, 855:Fr, 966:Fr, 1037:Fr, 921:Fr, 987:Fr, 903:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr, 1067:Fr]
 1083: [837:Fr, 855:Fr, 884:Fr, 872:Fr, 903:Fr, 966:Fr, 1037:Fr, 921:Fr, 987:Fr, 1067:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr]
 1084: [837:Fr, 855:Fr, 884:Fr, 872:Fr, 903:Fr, 966:Fr, 1037:Fr, 921:Fr, 987:Fr, 1067:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr]
 1085: [837:Fr, 855:Fr, 884:Fr, 872:Fr, 903:Fr, 966:Fr, 1037:Fr, 921:Fr, 987:Fr, 1067:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr]
 1086: [837:Fr, 855:Fr, 884:Fr, 872:Fr, 903:Fr, 966:Fr, 1037:Fr, 921:Fr, 987:Fr, 1067:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr]
 1087: [837:Fr, 855:Fr, 884:Fr, 872:Fr, 903:Fr, 966:Fr, 1037:Fr, 921:Fr, 987:Fr, 1067:Fr, 979:Fr, 1008:Fr, 1043:Fr, 1038:Fr]
 1088: [855:Fr, 872:Fr, 884:Fr, 921:Fr, 903:Fr, 966:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 979:Fr, 1008:Fr, 1043:Fr]
 1089: [855:Fr, 872:Fr, 884:Fr, 921:Fr, 903:Fr, 966:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 979:Fr, 1008:Fr, 1043:Fr]
 1090: [855:Fr, 872:Fr, 884:Fr, 921:Fr, 903:Fr, 966:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 979:Fr, 1008:Fr, 1043:Fr]
 1091: [855:Fr, 872:Fr, 884:Fr, 921:Fr, 903:Fr, 966:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 979:Fr, 1008:Fr, 1043:Fr]
 1092: [855:Fr, 872:Fr, 884:Fr, 921:Fr, 903:Fr, 966:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 979:Fr, 1008:Fr, 1043:Fr]
 1093: [855:Fr, 872:Fr, 884:Fr, 921:Fr, 903:Fr, 966:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 979:Fr, 1008:Fr, 1043:Fr]
 1094: [855:Fr, 872:Fr, 884:Fr, 921:Fr, 903:Fr, 966:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 979:Fr, 1008:Fr, 1043:Fr]
 1095: [872:Fr, 903:Fr, 884:Fr, 921:Fr, 979:Fr, 966:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 1043:Fr, 1008:Fr]
 1096: [872:Fr, 903:Fr, 884:Fr, 921:Fr, 979:Fr, 966:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 1043:Fr, 1008:Fr]
 1097: [872:Fr, 903:Fr, 884:Fr, 921:Fr, 979:Fr, 966:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 1043:Fr, 1008:Fr]
 1098: [872:Fr, 903:Fr, 884:Fr, 921:Fr, 979:Fr, 966:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 1043:Fr, 1008:Fr]
 1099: [872:Fr, 903:Fr, 884:Fr, 921:Fr, 979:Fr, 966:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 1043:Fr, 1008:Fr]
 1100: [872:Fr, 903:Fr, 884:Fr, 921:Fr, 979:Fr, 966:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 1043:Fr, 1008:Fr]
 1101: [872:Fr, 903:Fr, 884:Fr, 921:Fr, 979:Fr, 966:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 1043:Fr, 1008:Fr]
 1102: [872:Fr, 903:Fr, 884:Fr, 921:Fr, 979:Fr, 966:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 1043:Fr, 1008:Fr]
 1103: [884:Fr, 903:Fr, 966:Fr, 921:Fr, 979:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 1043:Fr]
 1104: [884:Fr, 903:Fr, 966:Fr, 921:Fr, 979:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 1043:Fr]
 1105: [884:Fr, 903:Fr, 966:Fr, 921:Fr, 979:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 1043:Fr]
 1106: [884:Fr, 903:Fr, 966:Fr, 921:Fr, 979:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 1043:Fr]
 1107: [884:Fr, 903:Fr, 966:Fr, 921:Fr, 979:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 1043:Fr]
 1108: [884:Fr, 903:Fr, 966:Fr, 921:Fr, 979:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 1043:Fr]
 1109: [884:Fr, 903:Fr, 966:Fr, 921:Fr, 979:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 1043:Fr]
 1110: [884:Fr, 903:Fr, 966:Fr, 921:Fr, 979:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 987:Fr, 1067:Fr, 1043:Fr]
 1111: [903:Fr, 921:Fr, 966:Fr, 987:Fr, 979:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 1043:Fr, 1067:Fr]
 1112: [903:Fr, 921:Fr, 966:Fr, 987:Fr, 979:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 1043:Fr, 1067:Fr]
 1113: [903:Fr, 921:Fr, 966:Fr, 987:Fr, 979:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 1043:Fr, 1067:Fr]
 1114: [903:Fr, 921:Fr, 966:Fr, 987:Fr, 979:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 1043:Fr, 1067:Fr]
 1115: [903:Fr, 921:Fr, 966:Fr, 987:Fr, 979:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 1043:Fr, 1067:Fr]
 1116: [903:Fr, 921:Fr, 966:Fr, 987:Fr, 979:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 1043:Fr, 1067:Fr]
 1117: [903:Fr, 921:Fr, 966:Fr, 987:Fr, 979:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 1043:Fr, 1067:Fr]
 1118: [921:Fr, 979:Fr, 966:Fr, 987:Fr, 1067:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 1043:Fr]
 1119: [921:Fr, 979:Fr, 966:Fr, 987:Fr, 1067:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 1043:Fr]
 1120: [921:Fr, 979:Fr, 966:Fr, 987:Fr, 1067:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 1043:Fr]
 1121: [921:Fr, 979:Fr, 966:Fr, 987:Fr, 1067:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 1043:Fr]
 1122: [921:Fr, 979:Fr, 966:Fr, 987:Fr, 1067:Fr, 1008:Fr, 1037:Fr, 1038:Fr, 1043:Fr]
 1123: [966:Fr, 979:Fr, 1008:Fr, 987:Fr, 1067:Fr, 1043:Fr, 1037:Fr, 1038:Fr]
 1124: [966:Fr, 979:Fr, 1008:Fr, 987:Fr, 1067:Fr, 1043:Fr, 1037:Fr, 1038:Fr]
 1125: [966:Fr, 979:Fr, 1008:Fr, 987:Fr, 1067:Fr, 1043:Fr, 1037:Fr, 1038:Fr]
 1126: [966:Fr, 979:Fr, 1008:Fr, 987:Fr, 1067:Fr, 1043:Fr, 1037:Fr, 1038:Fr]
 1127: [979:Fr, 987:Fr, 1008:Fr, 1038:Fr, 1067:Fr, 1043:Fr, 1037:Fr]
 1128: [979:Fr, 987:Fr, 1008:Fr, 1038:Fr, 1067:Fr, 1043:Fr, 1037:Fr]
 1129: [979:Fr, 987:Fr, 1008:Fr, 1038:Fr, 1067:Fr, 1043:Fr, 1037:Fr]
 1130: [979:Fr, 987:Fr, 1008:Fr, 1038:Fr, 1067:Fr, 1043:Fr, 1037:Fr]
 1131: [987:Fr, 1037:Fr, 1008:Fr, 1038:Fr, 1067:Fr, 1043:Fr]
 1132: [987:Fr, 1037:Fr, 1008:Fr, 1038:Fr, 1067:Fr, 1043:Fr]
 1133: [987:Fr, 1037:Fr, 1008:Fr, 1038:Fr, 1067:Fr, 1043:Fr]
 1134: [1008:Fr, 1037:Fr, 1043:Fr, 1038:Fr, 1067:Fr]
 1135: [1008:Fr, 1037:Fr, 1043:Fr, 1038:Fr, 1067:Fr]
 1136: [1008:Fr, 1037:Fr, 1043:Fr, 1038:Fr, 1067:Fr]
 1137: [1008:Fr, 1037:Fr, 1043:Fr, 1038:Fr, 1067:Fr]
 1138: [1008:Fr, 1037:Fr, 1043:Fr, 1038:Fr, 1067:Fr]
 1139: [1008:Fr, 1037:Fr, 1043:Fr, 1038:Fr, 1067:Fr]
 1140: [1037:Fr, 1038:Fr, 1043:Fr, 1067:Fr]
 1141: [1037:Fr, 1038:Fr, 1043:Fr, 1067:Fr]
 1142: [1037:Fr, 1038:Fr, 1043:Fr, 1067:Fr]
 1143: [1037:Fr, 1038:Fr, 1043:Fr, 1067:Fr]
 1144: [1037:Fr, 1038:Fr, 1043:Fr, 1067:Fr]
 1145: [1037:Fr, 1038:Fr, 1043:Fr, 1067:Fr]
 1146: [1037:Fr, 1038:Fr, 1043:Fr, 1067:Fr]
 1147: [1037:Fr, 1038:Fr, 1043:Fr, 1067:Fr]
 1148: [1038:Fr, 1067:Fr, 1043:Fr]
 1149: [1038:Fr, 1067:Fr, 1043:Fr]
 1150: [1038:Fr, 1067:Fr, 1043:Fr]
 1151: [1038:Fr, 1067:Fr, 1043:Fr]
 1152: [1038:Fr, 1067:Fr, 1043:Fr]
 1153: [1038:Fr, 1067:Fr, 1043:Fr]
 1154: [1038:Fr, 1067:Fr, 1043:Fr]
 1155: [1043:Fr, 1067:Fr]
 1156: [1043:Fr, 1067:Fr]
 1157: [1043:Fr, 1067:Fr]
 1158: [1067:Fr]
 1159: [1067:Fr]
 1160: [1067:Fr]
 1161: [1067:Fr]
 1162: [1067:Fr]
 1163: [1067:Fr]
 1164: [1067:Fr]
 Customer		Total		Longest		Average Wait
 Senior			47			12			3.4893617021276597
 Junior			56			22			5.0
 Sophomor			53			84			20.830188679245282
 Freshman			55			250			123.6    

 ******************************************/