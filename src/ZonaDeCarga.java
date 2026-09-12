import java.util.LinkedList;
import java.util.Queue;

public class ZonaDeCarga {
    // Queue garantiza el orden FIFO en el despacho de pedidos
    private final Queue<Pedido> pedidos = new LinkedList<>();

    public synchronized void agregarPedido(Pedido p) {
        pedidos.add(p);
        System.out.println("[ZonaDeCarga] Ingresó: " + p);
    }

    /**
     * Retira el siguiente pedido disponible de forma atómica.
     * Si no hay pedidos disponibles, retorna null.
     */
    public synchronized Pedido retirarPedido() {
        if (!pedidos.isEmpty()) {
            return pedidos.poll();
        }
        return null;
    }

    public synchronized boolean hayPedidosPendientes() {
        return !pedidos.isEmpty();
    }
}