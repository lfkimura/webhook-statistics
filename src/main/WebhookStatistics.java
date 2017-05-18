package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import manager.WebhookManager;
import model.UrlStatistic;
import model.Webhook;

public class WebhookStatistics {

	private static String defaultLogName = "log.txt";

	static Pattern webhookPattern = Pattern
			.compile(".*level=info response_body=\"\" request_to=\"(http.*)\".*response_status=\"([0-9]{3})\"");

	public static void main(String[] args) {
		Date start = new Date();
		String logFileName = buscaNomeArquivoLog(args);

		extraiRelatorio(logFileName);
		Date finish = new Date();
		System.out.println("time elapsed: " + (finish.getTime() - start.getTime()));
		
	}

	private static void extraiRelatorio(String logFileName) {

		List<Webhook> webhookList = extraiWebhooks(logFileName);

		imprimeResultados(webhookList);
	}

	private static void imprimeResultados(List<Webhook> webhooks) {
		WebhookManager manager = new WebhookManager(webhooks);

		// generate results
		System.out.println("total webhooks: " + webhooks.size());
		System.out.println("\n##########URLS MAIS ACESSADAS################\n");
		for (UrlStatistic urlstats : manager.getTopNWebhooks(3)) {

			System.out.println(urlstats.getUrl() + "  " + urlstats.getQuantidade());

		}
		System.out.println("\n##########Quantidade por status ################\n");
		for (Integer status : manager.getStatusQuantidadeTable().keySet()) {

			System.out.println(status + "  " + manager.getStatusQuantidadeTable().get(status));

		}
	}

	private static List<Webhook> extraiWebhooks(String logFileName) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(logFileName));
			String sCurrentLine;

			List<Webhook> webhooks = new ArrayList<Webhook>();

			// reading data
			while ((sCurrentLine = br.readLine()) != null) {
				Matcher webhookMatcher = webhookPattern.matcher(sCurrentLine);
				if (webhookMatcher.matches()) {
					webhooks.add(new Webhook(webhookMatcher.group(1), Integer.valueOf(webhookMatcher.group(2))));
				}
			}
			return webhooks;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private static String buscaNomeArquivoLog(String[] args) {
		String logName = defaultLogName;
		if (args.length > 0 && args[0] != null && !args[0].isEmpty())
			logName = args[0];
		return logName;
	}

}
