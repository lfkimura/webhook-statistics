package manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Webhook;

public class FileReaderManager {

	static Pattern webhookPattern = Pattern
			.compile(".*level=info response_body=\"\" request_to=\"(http.*)\".*response_status=\"([0-9]{3})\"");
	
	private String logFileName;

	public FileReaderManager(String logFileName) {
		this.logFileName = logFileName;
	}

	public List<Webhook> extraiWebhooks() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(logFileName));
			List<Webhook> webhooks = new ArrayList<Webhook>();
			br.lines().parallel().forEach(sCurrentLine -> {
				if (sCurrentLine != null) {
					Matcher webhookMatcher = webhookPattern.matcher(sCurrentLine);
					if (webhookMatcher.matches()) {
						webhooks.add(new Webhook(webhookMatcher.group(1), Integer.valueOf(webhookMatcher.group(2))));
					}
				}

			});
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

}
