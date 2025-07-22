package models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Maquina {
    private String nombre;
    private String ip;
    private List<Integer> codigos;

    public Maquina(String nombre, String ip, List<Integer> codigos) {
        this.nombre = nombre;
        this.ip = ip;
        this.codigos = codigos;
    }

    
    public String getNombre() {
        return nombre;
    }

    public String getIp() {
        return ip;
    }

    public List<Integer> getCodigos() {
        return codigos;
    }

    public int getSubred() {
        String[] octetos = ip.split("\\.");
        return Integer.parseInt(octetos[2]);
    }

    public int getRiesgo() {
        int sumaDivisiblesPor5 = 0;
        for (Integer codigo : codigos) {
            if (codigo % 5 == 0) {
                sumaDivisiblesPor5 += codigo;
            }
        }
        Set<Character> caracteresUnicos = new HashSet<>();
        for (char c : nombre.replace(" ", "").toCharArray()) {
            caracteresUnicos.add(c);
        }
        return sumaDivisiblesPor5 * caracteresUnicos.size();
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setIp(String ip) {
        this.ip = ip;
    }


    public void setCodigos(List<Integer> codigos) {
        this.codigos = codigos;
    }
}
