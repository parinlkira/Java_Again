package assignments;

/*
I've successfully created the ReaderWriterProblem.java file in the assignments directory. The implementation uses semaphores for synchronization:

A readSemaphore (mutex) to protect the reader count.
A writeSemaphore to ensure exclusive writer access.
Multiple readers can read simultaneously when no writer is active.
Writers have exclusive access, blocking all readers during writes.
The program was compiled and run, demonstrating correct behavior:

Readers access the shared data concurrently.
Writers update the data exclusively, with readers blocked during writes.
The output shows interleaved reader operations and sequential writer operations, confirming the synchronization works as expected.
*/

import java.util.concurrent.Semaphore;

class ReaderWriterProblem {
    private final Semaphore readSemaphore = new Semaphore(1);
    private final Semaphore writeSemaphore = new Semaphore(1);
    private int readerCount = 0;
    private String data = "Initial Data";

    public void read(int readerId) {
        try {
            readSemaphore.acquire();
            readerCount++;
            if (readerCount == 1) {
                writeSemaphore.acquire();
            }
            readSemaphore.release();

            // Reading
            System.out.println("Reader " + readerId + " is reading: " + data);
            Thread.sleep(100); // Simulate reading time

            readSemaphore.acquire();
            readerCount--;
            if (readerCount == 0) {
                writeSemaphore.release();
            }
            readSemaphore.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void write(int writerId, String newData) {
        try {
            writeSemaphore.acquire();

            // Writing
            data = newData;
            System.out.println("Writer " + writerId + " is writing: " + data);
            Thread.sleep(100); // Simulate writing time

            writeSemaphore.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        ReaderWriterProblem rwp = new ReaderWriterProblem();

        // Create reader threads
        for (int i = 1; i <= 5; i++) {
            final int id = i;
            new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    rwp.read(id);
                    try {
                        Thread.sleep(50); // Simulate delay between reads
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
        }

        // Create writer threads
        for (int i = 1; i <= 2; i++) {
            final int id = i;
            new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    rwp.write(id, "Data from Writer " + id + " - " + j);
                    try {
                        Thread.sleep(50); // Simulate delay between writes
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
        }
    }
}