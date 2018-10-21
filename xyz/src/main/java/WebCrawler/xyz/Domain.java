package WebCrawler.xyz;

public class Domain {
	private String domainHash;
	private String domainUrl;
	
	public Domain(String domainHash, String domainUrl) {
		this.domainHash = domainHash;
		this.domainUrl = domainUrl;
	}

	public String getDomainHash() {
		return domainHash;
	}

	public void setDomainHash(String domainHash) {
		this.domainHash = domainHash;
	}

	public String getDomainUrl() {
		return domainUrl;
	}

	public void setDomainUrl(String domainUrl) {
		this.domainUrl = domainUrl;
	}
	
}
