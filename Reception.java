import java.util.concurrent.Semaphore;

public class Reception 
{
   private static Semaphore sem11 = new Semaphore(1, true);
   private static Reception rec = new Reception();
   
   private Reception() 
   {
   }
   
   public static Reception get_Reception() 
   {
      return rec;
   }
   
   public void register_patient(Patient p1) 
   {
      if(!p1.ishas_enter() || p1.is_reg()) 
      {
         return;
      }
      try 
      {
         sem11.acquire();
      } catch (InterruptedException e) 
      {
         e.printStackTrace();
      }
      try 
      {
         doregister_patient(p1);
      } catch (InterruptedException e) 
      {
         e.printStackTrace();
      } finally {
         sem11.release();
      }
   }
   
   private void doregister_patient(Patient p1) throws InterruptedException 
   {
      System.out.println("Receptiontist registers to the patient " + p1.get_Id());
      p1.set_register(true);
      System.out.println("Patient " + p1.get_Id() + " leaves Reception ");
   }
}