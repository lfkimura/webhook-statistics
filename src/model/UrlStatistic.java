package model;

public class UrlStatistic implements Comparable<UrlStatistic> {
	private String url;
	private int quantidade;

	public UrlStatistic(String url, Integer quantidade) {
		this.url = url;
		this.quantidade = quantidade;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int compareTo(UrlStatistic other) {
		if (this.quantidade > other.quantidade)
			return -1;
		if (this.quantidade < other.quantidade)
			return 1;
		return 0;
	}

}
