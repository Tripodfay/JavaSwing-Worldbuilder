package storybuilder;

public class Locations extends Asset {

	private String conflict;
	private String alliance;
	private String features;
	
	public String getConflict() {
		return conflict;
	}
	public void setConflict(String conflict) {
		this.conflict = conflict;
	}
	public String getAlliance() {
		return alliance;
	}
	public void setAlliance(String alliance) {
		this.alliance = alliance;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString());
		sb.append("conflict"+":"+conflict);
		sb.append(System.getProperty("line.separator"));
		sb.append("alliance"+":"+alliance);
		sb.append(System.getProperty("line.separator"));
		sb.append("features"+":"+features);
		return sb.toString();
	}
	
}
