# SpeedFast - Sistema Concurrente de Coordinación de Entregas

Proyecto desarrollado para la actividad formativa **Semana 5: "Sincronizando procesos en sistemas concurrentes"**.

---

## 📌 Descripción del Caso

En la empresa **SpeedFast**, múltiples repartidores realizan entregas en paralelo. Para resolver problemas de concurrencia donde dos o más repartidores intentaban acceder simultáneamente a la zona de despacho (provocando duplicidad o errores de entrega), se implementó un sistema orientado a objetos con control de concurrencia en Java.

El diseño garantiza que:
- Cada pedido sea retirado de forma atómica y exclusiva por un único repartidor.
- No existan condiciones de carrera (*race conditions*) al acceder a la zona común.
- El ciclo de vida de los pedidos transite de forma segura entre estados.

---

## ⚙️ Arquitectura y Componentes

El proyecto se compone de 5 piezas clave:

1. **`EstadoPedido` (Enum):** Controla con seguridad de tipos los estados del ciclo de vida:
    - `PENDIENTE`: Ingresado en la zona de carga.
    - `EN_REPARTO`: Tomado por un repartidor y en tránsito.
    - `ENTREGADO`: Entrega completada exitosamente.

2. **`Pedido`:** Modela la entidad con identificador (`id`), dirección (`direccionEntrega`) y estado actual (`estado`). Cuenta con validación y sobrecarga para actualización de estado.

3. **`ZonaDeCarga` (Recurso Compartido):** Actúa como monitor de concurrencia. Contiene una estructura en cola protegida mediante métodos `synchronized` (`agregarPedido` y `retirarPedido`), garantizando que la extracción sea una operación atómica y segura entre hilos.

4. **`Repartidor` (Implementa `Runnable`):** Representa al hilo de ejecución. Cada repartidor:
    - Retira pedidos de la `ZonaDeCarga` mientras existan unidades disponibles.
    - Cambia el estado a `EN_REPARTO`.
    - Simula el tiempo de traslado y entrega mediante `Thread.sleep()`.
    - Marca el pedido como `ENTREGADO`.

5. **`Main`:** Inicializa la zona de carga con pedidos iniciales, instancia los hilos de los repartidores, coordina la ejecución concurrente y utiliza `join()` para sincronizar la finalización de todas las entregas antes del cierre.

---

## 🛠️ Requisitos y Tecnologías

- **Lenguaje:** Java 11 o superior (compatible con Java 17 / 21)
- **IDE Recomendado:** IntelliJ IDEA
- **Control de Versiones:** Git / GitHub

---

## 🚀 Ejecución del Proyecto

1. Clona el repositorio:
   ```bash
   git clone <URL_REPOSITORIO>