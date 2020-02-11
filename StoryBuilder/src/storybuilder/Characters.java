package storybuilder;

public class Characters extends Asset {
  
  private String age;
  private String backstory;
  private String relatives;
  private String abilities;
  private String allies;
  
  public String getAge() {
    return age;
  }
  public void setAge(String age) {
    this.age = age;
  }
  public String getBackstory() {
    return backstory;
  }
  public void setBackstory(String backstory) {
    this.backstory = backstory;
  }
  public String getRelatives() {
    return relatives;
  }
  public void setRelatives(String relatives) {
    this.relatives = relatives;
  }
  public String getAbilities() {
    return abilities;
  }
  public void setAbilities(String abilities) {
    this.abilities = abilities;
  }
  public String getAllies() {
    return allies;
  }
  public void setAllies(String allies) {
    this.allies = allies;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append( super.toString());
    sb.append("age"+":"+age);
    sb.append(System.getProperty("line.separator"));
    sb.append("backstory"+":"+backstory);
    sb.append(System.getProperty("line.separator"));
    sb.append("relatives"+":"+relatives);
    sb.append(System.getProperty("line.separator"));
    sb.append("abilities"+":"+abilities);
    sb.append(System.getProperty("line.separator"));
    sb.append("allies"+":"+allies);
    return sb.toString();
  }
}
