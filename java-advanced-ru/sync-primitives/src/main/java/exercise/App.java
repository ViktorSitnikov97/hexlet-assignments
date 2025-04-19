package exercise;

class App {

    public static void main(String[] args) {
        // BEGIN
        SafetyList safetyList = new SafetyList();
        Thread thread1 = new Thread(new ListThread(safetyList));
        Thread thread2 = new Thread(new ListThread(safetyList));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            System.out.println("Size list : " + safetyList.getSize());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // END
    }
}

