public class Repartidor implements Runnable {
    private final String nombre;
    private final ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        while (true) {
            Pedido pedido = zonaDeCarga.retirarPedido();

            // Si la cola está vacía, el repartidor finaliza su jornada
            if (pedido == null) {
                System.out.println("-> " + nombre + " no encontró más pedidos. Finalizando turno.");
                break;
            }

            try {
                // Cambiar estado a EN_REPARTO
                pedido.setEstado(EstadoPedido.EN_REPARTO);
                System.out.println("-> " + nombre + " retiró el " + pedido + ". En camino...");

                // Simular tiempo de traslado y entrega (entre 1 y 2.5 segundos)
                long tiempoEntrega = 1000 + (long) (Math.random() * 1500);
                Thread.sleep(tiempoEntrega);

                // Cambiar estado a ENTREGADO
                pedido.setEstado(EstadoPedido.ENTREGADO);
                System.out.println("✓ " + nombre + " completó la entrega del Pedido #"
                        + pedido.getId() + " en " + pedido.getDireccionEntrega()
                        + " [" + pedido.getEstado() + "]");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println(nombre + " fue interrumpido durante la entrega.");
                break;
            }
        }
    }
}