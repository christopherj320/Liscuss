package com.zerochass;

import java.util.Scanner;

public class Main {

    private static boolean HOST_VALIDATION = false;
    private static boolean PORT_VALIDATION = false;

    private static String host;
    private static int port;

    public static void main(String[] args) {
        promptForServer();

    }

    protected static void promptForServer() {
        // Initializer section
        String[] server_info = new String[10];
        Scanner kb = new Scanner(System.in);


            // Prompt the user for the server information in order to connect
            System.out.println("Hello this application allows you to make, comment on, and converse with others on a discussion board\n");

        do {
            System.out.println("Please enter the server name and port number in the following format to establish a connection\n");
            System.out.println("Servername Port\n");
            System.out.println("Typically, these values should be 'localhost' and '5001'. They should be separated by a space\n");

            // Retrieve that entered information into our string array
            server_info[0] = kb.nextLine();

            // Pass that information to be validated in a separate method
            validateServerInfo(server_info);
        } while(HOST_VALIDATION == false || PORT_VALIDATION == false );

        // Once the above condition is no longer met, we have completed prompting for the server information
        return;
    }

    protected static void validateServerInfo(String[] server_info) {
        // Split the entered server information at the space (as a delimiter)
        // The prospective host name goes into the 0th index and the port goes into the 1st index
        server_info = server_info[0].split("\\s+");

        // Check if the entered host name is valid
        if (server_info[0].trim().equals("localhost")) {
            // If the entered host name is valid, then continue with assigning it to the host variable
            host = server_info[0];
            HOST_VALIDATION = true;
        }   else {
                System.out.println("Error - you have entered an invalid server name\n");
                HOST_VALIDATION = false;
        }

        // Try to validate the entered port number
        try {
            // Attempt to validate it as an integer
            Integer.parseInt(server_info[1].trim());
        }   catch (NumberFormatException e) {
            // Since the error has been caught, our port is invalid
            System.out.println("Error - you have entered an invalid port");
            PORT_VALIDATION = false;
            // Return to prompt for correct information
            return;
        }

        // If this point has been reached in validation of the port, it must be correct
        PORT_VALIDATION = true;
        port = Integer.parseInt(server_info[1].trim());

        // Return to the calling method to continue with the program
        return;
    }
}
