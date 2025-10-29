public class Main {
    public static void main(String[] args) {
        System.out.println("Inicio de programa");

        Cafeteria cafeteria = new Cafeteria();

        Cliente cliente1 = new Cliente("Pedro", 5, cafeteria);
        Cliente cliente2 = new Cliente("María", 10, cafeteria);
        Cliente cliente3 = new Cliente("Raquel", 3, cafeteria);
        Cliente cliente4 = new Cliente("Fernando", 15, cafeteria);

        Camarero cam1 = new Camarero("Camarero1", cafeteria);
        Camarero cam2 = new Camarero("Camarero2", cafeteria);

        cliente1.start();
        cliente2.start();
        cliente3.start();
        cliente4.start();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        cam1.start();

        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        cam2.start();

        try {
            cliente1.join();
            cliente2.join();
            cliente3.join();
            cliente4.join();

            Thread.sleep(5000);

            cam1.join();
            cam2.join();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Fin de programa");
    }
}