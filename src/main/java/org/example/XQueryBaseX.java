package org.example;

import org.basex.api.client.ClientSession;

public class XQueryBaseX {
    public static void main(String[] args) {
        try {
            // Conectar a BaseX con usuario y contraseña
            ClientSession session = new ClientSession("localhost", 1984, "Goku", "Goku");

            // Abrir la base de datos antes de ejecutar la consulta
            session.execute("OPEN dragonball");

            // Consulta XQuery
            String query = "for $p in //personaje return <nombre>{$p/nombre/text()}</nombre>";
            String result = session.execute("XQUERY " + query);

            // Mostrar resultado
            System.out.println("Resultado de la consulta:");
            System.out.println(result);

            // Cerrar la sesión
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}