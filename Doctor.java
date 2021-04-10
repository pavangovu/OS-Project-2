import java.util.concurrent.Semaphore;

public class Doctor 
{
   private static Semaphore sem11 = new Semaphore(3, true);
   private int id = 0;
   private Nurseroom nurse11;
   private boolean busyval;
   private static int cnt = 0;
   
   public Doctor(int id1) 
   {
      this.id = ++cnt;
      this.nurse11 = new Nurseroom(cnt);
      this.busyval = false;
   }
   
   public boolean busyval() 
   {
      return busyval;
   }
   
   public void advise(Patient p1) throws InterruptedException 
   {
      if(!p1.is_reg() || p1.is_advice())
         return;
      try 
      {
         sem11.acquire();
         this.busyval = true;
         this.nurse11.takes_Patient(p1);
         Thread.sleep(100);
         System.out.println("Patient " + p1.get_Id() + " enters doctor " + this.id + "'s office");
         System.out.println("Doctor " + this.id + " listens to symptoms from patient "+ p1.get_Id());
         Thread.sleep(200);
         System.out.println("Patient " + p1.get_Id() + " receives advice from doctor" + this.id);
         System.out.println("Patient " + p1.get_Id() + " leaves");
         p1.set_advice(true);
         this.busyval = false;
      } catch (InterruptedException e) 
      {
         e.printStackTrace();
      } finally 
      {
         sem11.release();
      }
   }
}