import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Coordinator 
{
   public static void main(String[] args) throws InterruptedException 
   {
      ArrayList<Doctor> doc = new ArrayList<>();
      ArrayList<Patient> pat1 = new ArrayList<>();
      int dcnt = 0;
      for (int i = 0; i < 10; i++) 
      {
         pat1.add(new Patient());
      }
      for (int i = 0; i < 3; i++) 
      {
         doc.add(new Doctor(++dcnt));
      }
      ExecutorService patientThreads = Executors.newCachedThreadPool();
      for (int i = 0; i < pat1.size(); i++)
      {
         final int ind = i;
         patientThreads.execute(
            new Runnable()
            {
               @Override
               public void run() 
               {
                  pat1.get(ind).enters();
               }
            });
      }
      patientThreads.shutdown();
      ExecutorService dt = Executors.newCachedThreadPool();
      ExecutorService reg = Executors.newCachedThreadPool();
      while (true) 
      {
         for (int i = 0; i < pat1.size(); i++) 
         {
            final Patient patient11 = pat1.get(i);
            reg.execute(
               new Runnable() 
               {
                  @Override
                  public void run() 
                  {
                     Reception.get_Reception().register_patient(patient11);
                  }
               });
            dt.execute(
               new Runnable() 
               {
                  @Override
                  public void run() 
                  {
                     if (!doc.get(0).busyval()) 
                     {
                        try 
                        {
                           doc.get(0).advise(patient11);
                        } catch (InterruptedException e) 
                        {
                           e.printStackTrace();
                        }
                     } else if (!doc.get(1).busyval()) 
                     {
                        try 
                        {
                           doc.get(1).advise(patient11);
                        } catch (InterruptedException e) 
                        {
                           e.printStackTrace();
                        }
                     } else if (!doc.get(2).busyval()) 
                     {
                        try 
                        {
                           doc.get(2).advise(patient11);
                        } catch (InterruptedException e) 
                        {
                           e.printStackTrace();
                        }
                     } else
                     {
                        return;
                     }
                  }
               });
         }
         if (allPatientsEntered(pat1) && allPatientsAdvised(pat1))
         {
            break;
         }
         Thread.sleep(2000);
      }
      reg.shutdown();
      dt.shutdown();
      patientThreads.awaitTermination(1, TimeUnit.DAYS);
   }
   private static boolean allPatientsAdvised(ArrayList<Patient> pat1) 
   {
      for (Patient patient11 : pat1) 
      {
         if (!patient11.is_advice()) 
         {
            return false;
         }
      }
      return true;
   }
   private static boolean allPatientsEntered(ArrayList<Patient> pat1) 
   {
      for (Patient patient11 : pat1) 
      {
         if (!patient11.ishas_enter()) 
         {
            return false;
         }
      }
      return true;
   }
}