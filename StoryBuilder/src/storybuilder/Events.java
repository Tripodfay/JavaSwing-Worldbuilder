package storybuilder;

public class Events extends Asset {

	private String trigger;
	private String rewards;
	private String outcome;
	
	public String getTrigger() {
		return trigger;
	}
	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}
	public String getRewards() {
		return rewards;
	}
	public void setRewards(String rewards) {
		this.rewards = rewards;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString());
		sb.append("trigger"+":"+trigger);
		sb.append(System.getProperty("line.separator"));
		sb.append("rewards"+":"+rewards);
		sb.append(System.getProperty("line.separator"));
		sb.append("outcome"+":"+outcome);
		return sb.toString();
	}
	
}
