public class Nurseroom 
{
   private int id1;
   public Nurseroom(int id1) 
   {
      this.id1 = id1;
   }
   public int get_Id() 
   {
      return id1;
   }
   public void takes_Patient(Patient p1) 
   {
      System.out.println("Nurseroom " + this.id1 + " takes patient " + p1.get_Id() + " to doctor's office");
   }
}