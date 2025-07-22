package controllers;

import java.util.*;

public class MaquinaController {

    public static Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral) {
        Stack<Maquina> stack = new Stack<>();
        for (Maquina maquina : maquinas) {
            if (maquina.getSubred() > umbral) {
                stack.push(maquina);
            }
        }
        return stack;
    }

    public static TreeSet<Maquina> ordenarPorSubred(Stack<Maquina> pila) {
        TreeSet<Maquina> ordenado = new TreeSet<>((m1, m2) -> {
            int comparacionSubred = Integer.compare(m2.getSubred(), m1.getSubred());
            if (comparacionSubred != 0) {
                return comparacionSubred;
            }
            return m1.getNombre().compareTo(m2.getNombre());
        });
        ordenado.addAll(pila);
        return ordenado;
    }

    public static TreeMap<Integer, Queue<Maquina>> agruparPorRiesgo(List<Maquina> maquinas) {
        TreeMap<Integer, Queue<Maquina>> agrupadas = new TreeMap<>();
        for (Maquina maquina : maquinas) {
            int riesgo = maquina.getRiesgo();
            agrupadas.putIfAbsent(riesgo, new LinkedList<>());
            agrupadas.get(riesgo).offer(maquina);
        }
        return agrupadas;
    }

    public static Stack<Maquina> explotarGrupo(Map<Integer, Queue<Maquina>> mapa) {
        int maxCantidad = 0;
        int riesgoMaximo = 0;
        Queue<Maquina> grupoSeleccionado = null;

        for (Map.Entry<Integer, Queue<Maquina>> entry : mapa.entrySet()) {
            int cantidad = entry.getValue().size();
            int riesgo = entry.getKey();
            if (cantidad > maxCantidad || (cantidad == maxCantidad && riesgo > riesgoMaximo)) {
                maxCantidad = cantidad;
                riesgoMaximo = riesgo;
                grupoSeleccionado = entry.getValue();
            }
        }

        Stack<Maquina> stack = new Stack<>();
        if (grupoSeleccionado != null) {
            while (!grupoSeleccionado.isEmpty()) {
                stack.push(grupoSeleccionado.poll());
            }
        }
        return stack;
    }
}
