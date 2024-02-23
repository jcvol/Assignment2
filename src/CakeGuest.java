import java.util.*;

public class CakeGuest implements Runnable {

    /** This is the number of cakes the Cake Guest has replaced. They will know when
    everyone has visited the labyrinth when the number of cakes replaced is equal
    to the number of guests - 1. **/
    public int cakesReplaced = 0;

    /** This is the current state of whether there is a cupcake at the end of the labyrinth. **/
    public boolean isThereCake = false;

    /** True if this guest replaces the cupcake at the end, otherwise false, since there was already a cupcake **/
    public boolean replacedCake = false;

    /** True if number of cakes replaced indicates that every guest has been through the maze at least once. **/
    public boolean bingo(int numGuests){
        return cakesReplaced >= numGuests - 1;
    }

    /** Since Java doesn't allow reusing of threads, the guest needs to be reminded of how many cakes they replaced. **/
    public CakeGuest(int cakesReplaced, boolean isThereCake){
        this.cakesReplaced = cakesReplaced;
        this.isThereCake = isThereCake;
    }

    /** Simulates the guest's journey through the labyrinth and arriving at the end, before returning to the party. **/
    public void run()
    {
        Random rand = new Random();
        try {
            // Simulates the time spent navigating through the labyrinth.
            Thread.sleep(rand.nextInt(20) + 10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // The guest got to the end of the labyrinth and saw there was still a cupcake.
        if (isThereCake){
            // This guest doesn't need to do anything if there is still a cupcake.
            System.out.println("There is cake!");
        }else{
            // If there is no cupcake, this guest will request that the minotaur's servants replace the cupcake.
            System.out.println("Alas, no cake, and so much sorrow...");
            replacedCake = true;
            cakesReplaced++;
        }
    }
}