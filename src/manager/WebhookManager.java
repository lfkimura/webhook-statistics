package manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import model.UrlStatistic;
import model.Webhook;

public class WebhookManager {
	List<Webhook> webhooks;
	// tabela com quantidade de chamadas por url
	Hashtable<String, Integer> webQuantidadeTable = new Hashtable<String, Integer>();

	// tabela com quantidade de retornos por status
	Hashtable<Integer, Integer> statusQuantidadeTable = new Hashtable<Integer, Integer>();

	// lista ordenada pela quantidades por url
	List<UrlStatistic> topWebhooks = new ArrayList<UrlStatistic>();

	public WebhookManager(List<Webhook> webhooks) {
		this.webhooks = webhooks;
		updateTables();
	}

	public Hashtable<Integer, Integer> getStatusQuantidadeTable() {
		return statusQuantidadeTable;
	}

	private void updateTables() {
		webhooks.parallelStream().forEach(w -> {
			if (w != null) {
				insertQuantidadeWebHook(w);
				insertStatus(w);
			}
		});
		updateTopWebhooks();

	}

	private void updateTopWebhooks() {
		webQuantidadeTable.keySet().stream().forEach(url -> {
			if (url != null)
				topWebhooks.add(new UrlStatistic(url, webQuantidadeTable.get(url)));
		});
		Collections.sort(topWebhooks);
	}

	public List<UrlStatistic> getTopNWebhooks(int n) {
		if (topWebhooks.size() < n)
			return topWebhooks;
		else
			return topWebhooks.subList(0, n);
	}

	private synchronized void insertStatus(Webhook web) {
		if (statusQuantidadeTable.get(web.getStatus()) == null)
			statusQuantidadeTable.put(web.getStatus(), 1);
		else
			statusQuantidadeTable.put(web.getStatus(), statusQuantidadeTable.get(web.getStatus()) + 1);
	}

	private void insertQuantidadeWebHook(Webhook web) {
		if (webQuantidadeTable.get(web.getUrl()) == null)
			webQuantidadeTable.put(web.getUrl(), 1);
		else
			webQuantidadeTable.put(web.getUrl(), webQuantidadeTable.get(web.getUrl()) + 1);

	}

}
