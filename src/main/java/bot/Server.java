package bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Server {
    public static void main(String[] args) {
        Map<String, String> response = new HashMap<>();
        response.put("hello server", "Hello Client");
        response.put("what is your name", "My name is Deenn");
        response.put("what time is it?", "The time is " + new Date());
        response.put("how was your day?", "I have no feelings, but i have been busy all day");
        try (ServerSocket serverSocket = new ServerSocket(1019)) {
            System.out.println("Waiting for connection................");
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String echoString = in.readLine();
                String serverResponse = "Server reply: " + response.get(echoString);
                if (echoString.equalsIgnoreCase("exit")) {
                    break;
                } else if (response.containsKey(echoString.toLowerCase())) {
                    out.println(serverResponse);
                } else {
                    out.println("Sorry, I'm not smart enough to answer that, my creator need to better");
                }
            }
        } catch (IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }

    }
}
