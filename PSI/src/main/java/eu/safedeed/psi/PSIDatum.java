package eu.safedeed.psi;

import java.io.Serializable;

/**
 * Class holding a data entry of the party's set: a row of the CRM database
 * stripped down to the interesting entries.
 *
 * @author Sebastian Ramacher, Lukas Helminger
 */
public class PSIDatum implements Serializable {

  private static final long serialVersionUID = 5458L;
  /**
   * The customer's identifier for intersection.
   */
  final private String name_;

  /**
   * Initializes a datum.
   *
   * @param name
   *          the customers identifier for intersection
   */
  public PSIDatum(final String name) {
    name_ = name;
  }

  /**
   * Returns the cutomer's identifier for intersection.
   *
   * @return identifier for intersection
   */
  public String getName() {
    return name_;
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof PSIDatum)) {
      return false;
    }

    final PSIDatum other = (PSIDatum) obj;
    return name_.equals(other.name_);
  }

  @Override
  public int hashCode() {
    return name_.hashCode();
  }

}
