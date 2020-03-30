package se.nrm.dina.email.model.loan;

/**
 *
 * @author idali
 */
public final class Person {

  private int agentId;
  private String agentFirstName;
  private String agentLastName;
  private String agentEmail;

  public Person() {

  }

  public Person(int agentId, String agentFirstName, String agentLastName, String agentEmail) {
    this.agentId = agentId;
    this.agentFirstName = agentFirstName;
    this.agentLastName = agentLastName;
    this.agentEmail = agentEmail;
  }

  public String getAgentEmail() {
    return agentEmail;
  }

  public String getAgentFirstName() {
    return agentFirstName;
  }

  public int getAgentId() {
    return agentId;
  }

  public String getAgentLastName() {
    return agentLastName;
  }
}
