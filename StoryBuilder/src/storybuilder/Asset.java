package storybuilder;


public class Asset {

  private String name;
  private String location;
  private String description;




  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("name" + ":" + name);
    sb.append(System.getProperty("line.separator"));
    sb.append("location" + ":" + location);
    sb.append(System.getProperty("line.separator"));
    sb.append("description" + ":" + description);
    sb.append(System.getProperty("line.separator"));
    return sb.toString();
  }
}
