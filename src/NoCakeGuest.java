import java.util.Random;

public class NoCakeGuest implements Runnable {
    /** Keeps track of the number of cakes this guest has eaten. This guest will never eat more than one cake. **/
    public int cakesEaten = 0;
    /** This is the current state of whether there is a cupcake at the end of the labyrinth. **/
    public boolean isThereCake = false;
    /** True if this guest ate a cake this journey through the labyrinth. **/
    public boolean didEatCake = false;

    /** The guest has to remember how many cakes they've eaten **/
    public NoCakeGuest(int cakesEaten, boolean isThereCake){
        this.cakesEaten = cakesEaten;
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
        if (isThereCake){
            // If this guest has not eaten any cakes, they will eat the cupcake
            if (cakesEaten == 0){
                System.out.println("Mmmm, a cupcake.");
                cakesEaten++;
                didEatCake = true;
            }else{
                // If this guest has already eaten a cupcake, they will not eat another cupcake.
                // This way the cake guest will know someone has visited the labyrinth and can replace the cupcake.
                System.out.println("I am too full to eat another cupcake.");
            }
        }else{
            // The cupcake has been eaten and not replaced yet, so this guest will return to the party.
            System.out.println("There is no cake! It has been eaten.");
        }
    }
}