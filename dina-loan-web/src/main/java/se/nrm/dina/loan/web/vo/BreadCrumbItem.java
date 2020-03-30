package se.nrm.dina.loan.web.vo;

import java.io.Serializable;

/**
 *
 * @author idali
 */
public class BreadCrumbItem implements Serializable {

  private String name;
  private String url;
  private boolean visited;

  public BreadCrumbItem(String name, String url, boolean visited) {
    this.name = name;
    this.url = url;
    this.visited = visited;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean isVisited() {
    return visited;
  }

  public void setVisited(boolean visited) {
    this.visited = visited;
  }

}
