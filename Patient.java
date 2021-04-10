import java.util.concurrent.Semaphore;

public class Patient {
   private int id;
   private boolean is_reg;
   private boolean has_enter;
   private boolean is_advice;
   private static int cnt = 0;
   private static Semaphore sem11 = new Semaphore(3, true);

   public Patient() 
   {
      this.id = ++cnt;
      this.is_reg = false;
      this.has_enter = false;
      this.is_advice = false;
   }
   
   public int get_Id() 
   {
      return id;
   }
   
   public boolean is_reg() 
   {
      return is_reg;
   }
   
   public boolean ishas_enter() 
   {
      return has_enter;
   }
   
   public void sethas_enter(boolean has_enter) 
   {
      this.has_enter = has_enter;
   }
   
   public void set_register(boolean is_reg) 
   {
      this.is_reg = is_reg;
   }
   
   public boolean is_advice() 
   {
      return is_advice;
   }
   
   public void set_advice(boolean is_advice) 
   {
      this.is_advice = is_advice;
   }
   
   public void enters() 
   {
      try {
         sem11.acquire();
         System.out.println("Patient " + this.id + " waiting room, waits for receptionist person");
         this.has_enter = true;
         Thread.sleep(1000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      } finally {
         sem11.release();
      }
   }
}