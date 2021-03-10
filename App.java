import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class App {
    static int orders;
    static int gordonOrders = 0;
    static int jamieOrders = 0;

    static int[] gordon;
    static int[] jamie;

    public static void main(String[] args) throws Exception {
		Q q = new Q();

        int priority; // 1 for gordon, 2 for jamie


        Scanner input = new Scanner(System.in);
        orders = input.nextInt();

        gordon = new int[orders];
        jamie = new int[orders];

        int gordonCounter = 0;
        int jamieCounter = 0;

        for (int i = 0; i < orders; i++) {
            if (input.nextInt() == 1){
                gordonOrders++;
                gordon[gordonCounter] = i + 1;
                gordonCounter++;
            }else {
                jamieOrders++;
                jamie[jamieCounter] = i + 1;
                jamieCounter++;
            }
        }

        input.close();

        if (gordonOrders >= jamieOrders){
            priority = 1;
        }else {
            priority = 2;
        }

        Ingredients ingredients = new Ingredients();
          

      new Producer(q, priority,ingredients, orders, gordonOrders, jamieOrders); 
      new Consumer(q, orders, gordonOrders, jamieOrders, ingredients); 

    }
}

class Q {
	int item;

	static Semaphore semCon = new Semaphore(0);

	static Semaphore semProd = new Semaphore(1);
	
	int count;
	
	void init(){
		count = 0;
	}

	void get() {
		try {
			semCon.acquire();
		} catch(InterruptedException e) {
			System.out.println("InterruptedException caught");
		}
		semProd.release();
	}

	void put(int item) {
		try {
			semProd.acquire();
		} catch(InterruptedException e) {
			System.out.println("InterruptedException caught");
		}
		
		count++;

		this.item = item;

		semCon.release();
	}
}

class Producer implements Runnable {
    public long getTime(long start, long end){
        return ((end - start) / 1000);
    }

    int priority;
    Ingredients ingredients;
    int orders;
    int gordonOrders;
    int jamieOrders;
    
	Q q;
	Producer(Q q, int priority, Ingredients ingredients, int orders, int gordonOrders, int jamieOrders) {
		this.q = q;
        this.priority = priority;
        this.ingredients = ingredients;
        this.orders = orders;
        this.gordonOrders = gordonOrders;
        this.jamieOrders = jamieOrders;

		new Thread(this).start();
	}

	public void run() {
        long start = System.currentTimeMillis();    
        int temp = (int)(System.currentTimeMillis() % 4); 

        while (App.orders != 0) {

            try {

                if (ingredients.goosht >= 1 && ingredients.goje >= 2 && ingredients.piaz >= 1 && ingredients.ketchup >= 2 && gordonOrders > 0 && App.gordonOrders > 0) {
                    q.put(1);
                }

                if (ingredients.goosht >= 2 && ingredients.piaz >= 3 && ingredients.ketchup >= 2 && ingredients.mustard >= 2 && jamieOrders > 0 && App.jamieOrders > 0){
                    q.put(2);
                }


                if (getTime(start, System.currentTimeMillis()) % 20 == 0){
                    if (App.gordonOrders >= App.jamieOrders){
                        priority = 1;
                    }else {
                        priority = 2;
                    }
                }

                int previousRandom = -1;

                if (priority == 1){ // gordon 
                  
                    if (getTime(start, System.currentTimeMillis()) % 2 == 0){
                      
                        //System.out.println("time " + getTime(start, System.currentTimeMillis()));
                       do {
                            temp = (int)(System.currentTimeMillis() % 4);
                       } while (previousRandom == temp);
                    }

                 /*    System.out.println(ConsoleColors.GREEN + temp + ConsoleColors.RESET + getTime(start, System.currentTimeMillis())
                    + ConsoleColors.BLUE + ingredients.goosht + ConsoleColors.RESET
                    + ConsoleColors.RED + ingredients.goje + ConsoleColors.RESET
                    + ConsoleColors.YELLOW + ingredients.piaz + ConsoleColors.RESET
                    + ConsoleColors.PURPLE + ingredients.ketchup + ConsoleColors.RESET
                    + ConsoleColors.CYAN + ingredients.mustard + ConsoleColors.RESET); */

                    switch (temp) {
                        case 0:
                            if (ingredients.goosht < 10){
                                ingredients.goosht += 5;
                                //System.out.println("goosht" + ingredients.goosht);
                            }
                            break;
                        case 1:
                            if (ingredients.goje < 10){
                                ingredients.goje += 5;
                                //System.out.println("goje" + ingredients.goje);
                            }
                            
                            break;
                        case 2:
                            if (ingredients.piaz < 10){
                                ingredients.piaz += 5;
                                //System.out.println("piaz" + ingredients.piaz);
                            }  
                            break;
                        case 3:
                            if (ingredients.ketchup < 10){
                                ingredients.ketchup += 5;
                                //System.out.println("ketchup" + ingredients.ketchup);
                            }
                            break;
                    
                        default:
                            break;
                    }
                }else {
                    if ( getTime(start, System.currentTimeMillis()) > 0 && getTime(start, System.currentTimeMillis()) % 2 == 0){
                      
                        //System.out.println("time " + getTime(start, System.currentTimeMillis()));
                        do {
                            temp = (int)(System.currentTimeMillis() % 4);
                       } while (previousRandom == temp);
                    }

                /*     System.out.println(ConsoleColors.GREEN + temp + ConsoleColors.RESET + getTime(start, System.currentTimeMillis())
                    + ConsoleColors.BLUE + ingredients.goosht + ConsoleColors.RESET
                    + ConsoleColors.RED + ingredients.goje + ConsoleColors.RESET
                    + ConsoleColors.YELLOW + ingredients.piaz + ConsoleColors.RESET
                    + ConsoleColors.PURPLE + ingredients.ketchup + ConsoleColors.RESET
                    + ConsoleColors.CYAN + ingredients.mustard + ConsoleColors.RESET); */

                    switch (temp) {
                        case 0:
                            if (ingredients.goosht < 10){
                                ingredients.goosht += 5;
                                //System.out.println("goosht" + ingredients.goosht);
                            }
                            break;
                        case 1:
                            if (ingredients.mustard < 10){
                                ingredients.mustard += 5;
                               // System.out.println("mustard" + ingredients.mustard);
                            }
                            
                            break;
                        case 2:
                            if (ingredients.piaz < 10){
                                ingredients.piaz += 5;
                               //System.out.println("piaz" + ingredients.piaz);
                            }  
                            break;
                        case 3:
                            if (ingredients.ketchup < 10){
                                ingredients.ketchup += 5;
                               // System.out.println("ketchup" + ingredients.ketchup);
                            }
                            break;
                    
                        default:
                            break;
                    }
                }
               /*  System.out.println();
                System.out.println(ConsoleColors.RED_BACKGROUND + "Time : " + getTime(start, System.currentTimeMillis()) +  ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED_BACKGROUND + " Priority : " + priority +  ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED_BACKGROUND + " Goosht : " + ingredients.goosht +  ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED_BACKGROUND + " Goje : " + ingredients.goje +  ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED_BACKGROUND + " Piaz : " + ingredients.piaz + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED_BACKGROUND + " Ketchup : " + ingredients.ketchup +  ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED_BACKGROUND + " Mustard : " + ingredients.mustard +  ConsoleColors.RESET);
                System.out.println(ConsoleColors.BLUE_BACKGROUND + " Random : " + temp +  ConsoleColors.RESET);
                System.out.println(); */



               // sharedQueue.put(number);
                Thread.sleep(1000);
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
    }
}
class Consumer implements Runnable {
    public long getTime(long start, long end){
        return ((end - start) / 1000);
    }

    int orders;
    int gordonOrders;
    int jamieOrders;
    Ingredients ingredients;

	Q q;
    
	Consumer(Q q,int orders, int gordonOrders, int jamieOrders, Ingredients ingredients) {
		this.q = q;
        this.orders = orders;
        this.gordonOrders = gordonOrders;
        this.jamieOrders = jamieOrders;
        this.ingredients = ingredients;
		new Thread(this).start();
	}

	public void run() {
		long start = System.currentTimeMillis();
        int i = 1;
        int gordonCounter = 0;
        int jamieCounter = 0;

        while(App.orders != 0) {
            
            try {
                q.get();
                int num = q.item;
               
                if (num == 1){
                    ingredients.goosht -= 1;
                    ingredients.goje -= 2;
                    ingredients.piaz -= 1;
                    ingredients.ketchup -= 2;

                    App.orders--;
                    App.gordonOrders--;

                    System.out.println(i + "-" + App.gordon[gordonCounter] + "-" + "Gordon Ramsay" + "-" + getTime(start, System.currentTimeMillis()));
                    i++;
                    gordonCounter++;
                } else {
                    ingredients.goosht -= 2;
                    ingredients.piaz -= 3;
                    ingredients.ketchup -= 2;
                    ingredients.mustard -= 2;
                    System.out.println(i + "-" + App.jamie[jamieCounter] + "-" + "Jamie Oliver" + "-" + getTime(start, System.currentTimeMillis()));

                    App.orders -= 1;
                    App.jamieOrders -= 1;

                    i++;
                    jamieCounter++;

                }


                if (App.orders == 0){
                    System.out.print("Goosht " + ingredients.goosht + "-");
                    System.out.print("Goje " + ingredients.goje + "-");
                    System.out.print("Piaz " + ingredients.piaz + "-");
                    System.out.print("Ketchup " + ingredients.ketchup + "-");
                    System.out.print("Mustard " + ingredients.mustard);
                    System.out.print("\n");

                }

            } catch (Exception err) {
               err.printStackTrace();
            }
        }
    }   
}


class Ingredients {
    int goosht = 2;
    int goje = 2;
    int piaz = 2;
    int ketchup = 2;
    int mustard = 2;  
}
