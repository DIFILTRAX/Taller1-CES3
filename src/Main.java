import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/api/metodos", Main::handleRequest);

        System.out.println("Servidor iniciado en: http://localhost:8080/api/metodos");
        server.start();
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response;

        switch (method) {
            case "GET":
                response = "Respuesta GET: Obtener información.";
                break;

            case "POST":
                response = "Respuesta POST: Crear o enviar datos.";
                break;

            case "PUT":
                response = "Respuesta PUT: Actualizar recurso completo.";
                break;

            case "PATCH":
                response = "Respuesta PATCH: Actualizar parcialmente un recurso.";
                break;

            case "DELETE":
                response = "Respuesta DELETE: Eliminar recurso.";
                break;

            case "OPTIONS":
                Headers headers = exchange.getResponseHeaders();
                headers.add("Allow", "GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT");
                response = "Métodos permitidos enviados en cabecera.";
                break;

            case "HEAD":
                exchange.sendResponseHeaders(200, -1); // HEAD no devuelve cuerpo
                return;

            case "TRACE":
                response = "TRACE: El cliente solicita ver la petición tal como llega al servidor.";
                break;

            case "CONNECT":
                response = "CONNECT: Usado para establecer túneles en HTTPS, aquí solo se muestra.";
                break;

            default:
                response = "Método no soportado: " + method;
        }

        sendResponse(exchange, response);
    }

    private static void sendResponse(HttpExchange exchange, String respuesta) throws IOException {
        exchange.sendResponseHeaders(200, respuesta.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(respuesta.getBytes());
        os.close();
    }
}
