package eu.safedeed.psi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * A simple demo computing PSI.
 * WARNING: No TLS connection
 * Client sends its list of PSIDatum to the server, which does the intersection and reports back the results.
 * @author Lukas Helminger
 */
public class Main {

  public static void main(String[] args) throws IOException {
    String csvFile = args[1];
    String line = "";
    String cvsSplitBy = ",";
    String ip;
    int port;

    ArrayList<PSIDatum> customerNames = new ArrayList<PSIDatum>();
    ArrayList<PSIDatum> intersection = new ArrayList<PSIDatum>();
    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

      while ((line = br.readLine()) != null) {
        // use comma as separator
        String[] customer = line.split(cvsSplitBy);
        PSIDatum datum = new PSIDatum(customer[0]);
        customerNames.add(datum);

      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    if(args[0].equals("Server")) {
      port = Integer.parseInt(args[2]);
      PSIServer server = new PSIServer(port);
      server.runProtocol(customerNames);
    }

    if(args[0].equals("Client")) {
      ip = args[2];
      port = Integer.parseInt(args[3]);
      PSIClient client = new PSIClient(ip, port);
      intersection = (ArrayList<PSIDatum>) client.runProtocol(customerNames);

      // Write result into a file.
      FileWriter fileWriterRaw = new FileWriter("intersection_raw.txt");
      PrintWriter printWriterRaw = new PrintWriter(fileWriterRaw);
      FileWriter fileWriterHtml = new FileWriter("intersection.html");
      PrintWriter printWriterHtml = new PrintWriter(fileWriterHtml);
      printWriterHtml.println("<table>");
      int i = 0;
      for(PSIDatum d : intersection) {
        if (i==0) {
          printWriterHtml.println("  <tr>");
          printWriterHtml.println("    <th>" + d.getName() + "</th>");
          printWriterHtml.println("  </tr>");
          printWriterRaw.println(d.getName());
        }
        else {
          printWriterHtml.println("  <tr>");
          printWriterHtml.println("    <td>" + d.getName() + "</td>");
          printWriterHtml.println("  </tr>");
          printWriterRaw.println(d.getName());
        }
        i++;
      }
      printWriterHtml.println("</table>");
      printWriterRaw.close();
      printWriterHtml.close();
    }

  }
}
