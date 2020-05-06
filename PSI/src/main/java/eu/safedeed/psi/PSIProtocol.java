package eu.safedeed.psi;

import java.io.IOException;
import java.util.List;

/**
 * Interface of the PSI protocol library.
 *
 * @author Sebastian Ramacher, Lukas Helminger
 */
public interface PSIProtocol {

  /**
   * Execute the PSI protocol with the other party.
   *
   * @param inputData
   *          Input data of the party
   * @return intersection if the local party's set and the remote party's set
   */
  List<PSIDatum> runProtocol(List<PSIDatum> inputData) throws IOException;

}
