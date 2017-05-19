package main;

import java.util.Date;
import java.util.List;

import manager.FilePrintManager;
import manager.FileReaderManager;
import model.Webhook;

public class WebhookStatistics {

	private static String defaultLogName = "log.txt";

	public static void main(String[] args) {
		Date start = new Date();
		String logFileName = buscaNomeArquivoLog(args);
		List<Webhook> webhookList = new FileReaderManager(logFileName).extraiWebhooks();

		System.out.println(new FilePrintManager(webhookList).imprimeResultados());
		Date finish = new Date();
		System.out.println("time elapsed: " + (finish.getTime() - start.getTime()));

	}

	private static String buscaNomeArquivoLog(String[] args) {
		String logName = defaultLogName;
		if (args.length > 0 && args[0] != null && !args[0].isEmpty())
			logName = args[0];
		return logName;
	}

}
