import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE ENTREGAS SPEEDFAST ===\n");

        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        // Cargar al menos 5 pedidos iniciales
        zonaDeCarga.agregarPedido(new Pedido(101, "Av. Libertador 1234"));
        zonaDeCarga.agregarPedido(new Pedido(102, "Calle Los Robles 567"));
        zonaDeCarga.agregarPedido(new Pedido(103, "Pasaje San Pedro 89"));
        zonaDeCarga.agregarPedido(new Pedido(104, "Av. Central 980"));
        zonaDeCarga.agregarPedido(new Pedido(105, "Camino del Valle 432"));
        zonaDeCarga.agregarPedido(new Pedido(106, "Diagonal Norte 112"));

        System.out.println("\n--- Iniciando cuadrilla de repartidores ---\n");

        // Crear 3 repartidores
        List<Thread> hilosRepartidores = new ArrayList<>();
        hilosRepartidores.add(new Thread(new Repartidor("Repartidor-Carlos", zonaDeCarga)));
        hilosRepartidores.add(new Thread(new Repartidor("Repartidor-Lucía", zonaDeCarga)));
        hilosRepartidores.add(new Thread(new Repartidor("Repartidor-Mateo", zonaDeCarga)));

        // Iniciar ejecución concurrente
        for (Thread t : hilosRepartidores) {
            t.start();
        }

        // Esperar la sincronización y término de todos los hilos (join)
        for (Thread t : hilosRepartidores) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Hilo principal interrumpido.");
            }
        }

        System.out.println("\n==================================================");
        System.out.println("Todos los pedidos han sido entregados correctamente.");
        System.out.println("==================================================");
    }
}