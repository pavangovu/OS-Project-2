# Hospital-Simulation
This application simulates a simple hospital system consisting of patients, doctors, and receptionists. Threads are used to represent each patient, receptionist, and doctor. Coordination between the threads is facilitated by semaphores. Solution is implemented using Java.

## Patients
The patient enters into the receptionist room, waiting to be registered. If the only receptionist is available, the patient will be registered, otherwise, he or she needs to wait in a queue(regQueue in the program) for the receptionist to be available.

## Receptionists
Once the receptionist is available, the receptionist will get the patient from the regQueue and register that patient. After finishing registration, the receptionist will signal the patient, and the patient will leave the receptionist and sit in the waiting room. After that, the receptionist will signal the nurse and tell him/her that a patient is ready.

## Doctors
Then, if at least one doctor is available, the nurse will take one patient to the doctor’s room. After receiving the signal from nurse, the patient will come to the doctor’s room. The doctor will wait until the patient arrive at his/her room. Next, the doctor will listen to symptoms told by the patient and give advices to the patient. At last, the patient will leave the room and the doctor is free to serve the next patient.
