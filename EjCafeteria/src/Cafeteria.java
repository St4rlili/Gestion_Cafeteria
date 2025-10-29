import java.util.ArrayList;
import java.util.List;

public class Cafeteria {
    private List<Cliente> clientes = new ArrayList<>();
    private List<Cliente> clientesEnAtencion = new ArrayList<>();

    public void agregarCliente(Cliente c) {
        clientes.add(c);
    }

    public void quitarCLientes(Cliente c) {
        clientes.remove(c);
        clientesEnAtencion.remove(c);
    }

    public Cliente obtenerSiguiente() {
        for (Cliente c : clientes) {
            if (!clientesEnAtencion.contains(c) && !c.fueAtendido() && !c.estaEnProceso()) {
                c.marcarEnProceso(true);
                clientesEnAtencion.add(c);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                return c;
            }
        }
        return null;
    }

    public void liberarCliente(Cliente c) {
        c.marcarEnProceso(false);
        clientesEnAtencion.remove(c);
    }

    public boolean hayClientes() {
        return !clientes.isEmpty();
    }

    public boolean contieneCliente(Cliente c) {
        return clientes.contains(c);
    }
}