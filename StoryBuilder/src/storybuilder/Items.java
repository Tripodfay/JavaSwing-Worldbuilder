package storybuilder;

public class Items extends Asset {

	private String damage;
	private String legacy;
	private String type;
	private String mastery;
	
	public String getDamage() {
		return damage;
	}
	public void setDamage(String damage) {
		this.damage = damage;
	}
	public String getLegacy() {
		return legacy;
	}
	public void setLegacy(String legacy) {
		this.legacy = legacy;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMastery() {
		return mastery;
	}
	public void setMastery(String mastery) {
		this.mastery = mastery;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString());
		sb.append("damage"+":"+damage);
		sb.append(System.getProperty("line.separator"));
		sb.append("legacy"+":"+legacy);
		sb.append(System.getProperty("line.separator"));
		sb.append("type"+":"+type);
		sb.append(System.getProperty("line.separator"));
		sb.append("mastery"+":"+mastery);
		return sb.toString();
	}
}
