package eu.safedeed.psi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the server-side of the PSI protocol.
 *
 * @author Sebastian Ramacher, Lukas Helminger
 *
 */
public class PSIServer implements PSIProtocol {

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

  private int port_;
  /**
   * Start a server-side instance of the PSI protocol.
   *
   * TODO: Extended with options for a TLS library (certificates, etc.)
   *
   * @param port
   *          port to listen on
   */
  public PSIServer(int port) {
    port_ = port;
  }

  private static native void doPSI(List<String> list, int p);

  @Override
  public List<PSIDatum> runProtocol(List<PSIDatum> inputData) throws IOException {
    ArrayList<String> input = new ArrayList<String>();
    for(int i = 0; i < inputData.size(); i++) {
      input.add(inputData.get(i).getName());
    }
     doPSI(input, port_);

    return null;
  }
}
