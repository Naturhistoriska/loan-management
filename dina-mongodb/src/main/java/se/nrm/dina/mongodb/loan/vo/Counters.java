package se.nrm.dina.mongodb.loan.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author idali
 */
public class Counters {

  private String _id;
  private int seq;

  @JsonCreator
  public Counters(@JsonProperty("_id") String id, @JsonProperty("seq") int seq) {
    this._id = id;
    this.seq = seq;
  }

  public String getId() {
    return _id;
  }

  public void setId(String _id) {
    this._id = _id;
  }

  public int getSeq() {
    return seq;
  }

  public void setSeq(int seq) {
    this.seq = seq;
  }

}
