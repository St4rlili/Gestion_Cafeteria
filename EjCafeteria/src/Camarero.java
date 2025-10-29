public class Camarero extends Thread {
    private String nombre;
    private Cafeteria cafeteria;
    private boolean enTurno = true;

    public Camarero(String nombre, Cafeteria cafeteria) {
        this.nombre = nombre;
        this.cafeteria = cafeteria;
    }

    @Override
    public void run() {
        System.out.println(nombre + " empieza a trabajar");

        while (enTurno) {
            Cliente cliente = cafeteria.obtenerSiguiente();

            if (cliente != null) {
                System.out.println(nombre + " está preparando café para " + cliente.getNombre());

                int tiempoPreparacion = (int) (Math.random() * 3000 + 2000);

                try {
                    Thread.sleep(tiempoPreparacion);

                    if (cafeteria.contieneCliente(cliente)) {
                        if (!cliente.fueAtendido()) {
                            cliente.marcarAtendido();
                            long tiempoTotal = System.currentTimeMillis() - cliente.getTiempoInicio();
                            System.out.println(cliente.getNombre() + " ha recibido su café de " + nombre +
                                    " después de " + (tiempoTotal / 1000) + " segundos de espera");
                            cafeteria.quitarCLientes(cliente);
                        } else {
                            System.out.println(nombre + ": El cliente " + cliente.getNombre() +
                                    " ya fue atendido por otro camarero");
                            cafeteria.liberarCliente(cliente);
                        }
                    } else {
                        System.out.println(nombre + ": El cliente " + cliente.getNombre() +
                                " ya se fue, café desperdiciado");
                        cafeteria.liberarCliente(cliente);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    cafeteria.liberarCliente(cliente);
                }
            } else {
                try {
                    Thread.sleep(1000);

                    if (!cafeteria.hayClientes()) {
                        Thread.sleep(2000);

                        if (!cafeteria.hayClientes()) {
                            enTurno = false;
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println(nombre + " terminó su turno");
    }
}