package eu.safedeed.psi;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;

/**
 * Implementation of the client side of the PSI protocol.
 * @author Sebastian Ramacher, Lukas Helminger
 *
 */
public class PSIClient implements PSIProtocol {

  static {
    try {
      System.loadLibrary("droidcrypto");
      System.out.println("loaded native libary");
    }
    catch (UnsatisfiedLinkError e) {
      System.err.println("Native code library not found.\n" + e);
      System.exit(-1);
    }
  }

  private String host_;
  private int port_;
  /**
   * Start a client-side instance of the PSI protocol.
   *
   * TODO: Extended with options for a TLS library (certificates, etc.)
   *
   * @param host
   *          host name or IP address of the server
   * @param port
   *          port of the server
   */
  public PSIClient(String host, int port) {
    host_ = host;
    port_ = port;
  }

  private static native int[] doPSI(List<String> list,String ip, int p);

  @Override
  public List<PSIDatum> runProtocol(List<PSIDatum> inputData) throws IOException {
    ArrayList<String> input = new ArrayList<String>();
    for(int i = 0; i < inputData.size(); i++) {
      input.add(inputData.get(i).getName());
    }
    int[] indices = doPSI(input, host_, port_);
    ArrayList<PSIDatum> intersection = new ArrayList<PSIDatum>();
    for(int i = 0; i < indices.length; i++) {
      intersection.add(inputData.get(indices[i]));
    }
    return intersection;
  }
}
