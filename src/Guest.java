import java.util.Random;

public class Guest implements Runnable {
    /** Each guest is named for simulation output. **/
    public String name;

    public Guest(String name){
        this.name = name;
    }

    public void run() {
        // Use to randomly determine how long the guests wander around the showcase, and spend time in the vase room.
        Random rand = new Random();

        while (true){
            // Guests immediately check to see if the room is busy, if it is not, they enter right away
            if (!Vase.busySign){
                System.out.println(name + " has entered the vase room");
                // They flip the sign to "busy" to indicate that the room is occupied
                Vase.busySign = true;
                try {
                    // Guest stares at the vase
                    Thread.sleep(rand.nextInt(10));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(name + " is leaving the vase room");
                // The guest flips the sign back to "available" to indicate that the room is free to enter
                Vase.busySign = false;
                // The guest doesn't bother trying to go into the room again.
                break;
            }else{
                // The guest wanders around the room before checking to see if it is available again.
                System.out.println(name + " is walking around the showcase");
                try {
                    // Guest wanders around.
                    Thread.sleep(rand.nextInt(10));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
