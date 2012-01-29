package org.jboss.tools.as.sourcelookup;

public class NexusRepository {
	private String name;
	private String url;
	private boolean enabled;

	
	public NexusRepository() {
		super();
	}

	public NexusRepository(String name, String url, boolean enabled) {
		super();
		this.name = name;
		this.url = url;
		this.enabled = enabled;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean checked) {
		this.enabled = checked;
	}

	@Override
	public String toString() {
		return "NexusRepository [name=" + name + ", url=" + url + ", enabled="
				+ enabled + "]";
	}
}
