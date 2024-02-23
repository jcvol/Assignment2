import java.util.*;

public class Labyrinth {

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        Random rand =  new Random();

        System.out.println("How many guests are attending the minotaur's party?:");
        var numGuests = scan.nextInt();

        // User input validation
        if (numGuests < 2){
            // One guest (or less) is not enough of a party for the guests to devise a proper strategy.
            // One guest would just eat the cupcake and tell the minotaur and the party would be over!
            throw new RuntimeException("That's not enough of a party!");
        }

        /** Number of times the minotaur has sent a guest into the labyrinth. **/
        var numTrips = 0;
        /** Current state of if there is a cupcake at the end of the labyrinth or not. **/
        var isThereCake = true;
        /** How many cupcakes have been replaced total by the cake guest. **/
        var cakesReplaced = 0;

        /** Each guests' memories of how many cakes they've eaten. **/
        var cakesEaten = new int[numGuests];
        Arrays.fill(cakesEaten, 0);

        while (true){
            /** The minotaur picks a random guest to go into the labyrinth. **/
            int guestNum = rand.nextInt(numGuests);
            numTrips++;
            Thread thread;


            // Stylizing output as each guest's internal thoughts on traveling through the maze.
            System.out.print("Guest " + (guestNum + 1) + ": ");

            if (guestNum == 0){
                // The minotaur picked the cake-tracking guest.
                var guest = new CakeGuest(cakesReplaced, isThereCake);
                // Simulate the cake-tracking guest using a thread.
                thread = new Thread(guest);
                thread.start();
                thread.join();

                // The cake-tracking guest is back from the labyrinth.
                // Store their memory for future journeys through the labyrinth.
                cakesReplaced = guest.cakesReplaced;
                if (guest.replacedCake){
                    // The cake-tracking guest saw there was no cupcake,
                    // and requested the servants to replace the cupcake.
                    isThereCake = true;
                }

                // If the cake-tracking guest has discerned that everyone has been through the maze,
                // the cake-tracking guest calls it out to the minotaur.
                if (guest.bingo(numGuests)){
                    System.out.println("Guest 1: Everyone has been through the maze!");
                    break;
                }
            } else{
                // The minotaur chooses a cupcake-eating guest to go into the labyrinth.
                var guest = new NoCakeGuest(cakesEaten[guestNum], isThereCake);

                // Simulate the guest's journey through the labyrinth.
                thread = new Thread(guest);
                thread.start();
                thread.join();

                // The guest takes a mental note of how many cupcakes they have eaten.
                cakesEaten[guestNum] = guest.cakesEaten;
                // If they ate a cupcake this time through the maze, then there is no cupcake.
                if (guest.didEatCake){
                    isThereCake = false;
                }
            }
        }
        // The total number of trips the guests took through the labyrinth.
        System.out.println("The minotaur sent guests into the labyrinth " + numTrips + " times.");

    }

}