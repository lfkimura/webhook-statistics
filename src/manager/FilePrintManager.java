package manager;

import java.util.List;

import model.UrlStatistic;
import model.Webhook;

public class FilePrintManager {
	private List<Webhook> webhooks;

	public FilePrintManager(List<Webhook> webhooks) {
		super();
		this.webhooks = webhooks;
	}

	public String imprimeResultados() {
		String result = "";
		WebhookManager manager = new WebhookManager(webhooks);

		// generate results
		result += "total webhooks: " + webhooks.size() +'\n';
		result += "\n##########URLS MAIS ACESSADAS################\n";
		for (UrlStatistic urlstats : manager.getTopNWebhooks(3)) {

			result += urlstats.getUrl() + "  " + urlstats.getQuantidade();
			result += '\n';

		}
		result += "\n##########Quantidade por status ################\n";
		for (Integer status : manager.getStatusQuantidadeTable().keySet()) {

			result += status + "  " + manager.getStatusQuantidadeTable().get(status);
			result += '\n';

		}
		return result;
	}
}
