package pen;


public class PenDemo {
    public static void main(String[] args) {
        try {
            
            Pen gelPen = PenFactory.getPen("gel", "blue", true, true);

            
            Pen grippedGelPen = new GripPenDecorator(gelPen);

            
            grippedGelPen.start();

            
            grippedGelPen.write("Hello");
            grippedGelPen.write("Anushka");

            
            grippedGelPen.close();

            
            try {
                grippedGelPen.write("This should fail");
            } catch (Exception e) {
                System.out.println("Expected error: " + e.getMessage());
            }

            
            grippedGelPen.refill("black");

            
            grippedGelPen.start();
            grippedGelPen.write("Writing after refill");

            
            Pen fountain = PenFactory.getPen("fountain", "blue", true, false);
            fountain.start();
            fountain.write("Fountain writing");
            fountain.close();
            fountain.refill("black");  
            try {
                fountain.refill("red"); 
            } catch (Exception e) {
                System.out.println("Expected fountain error: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
