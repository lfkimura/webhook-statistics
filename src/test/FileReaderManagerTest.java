package test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import manager.FileReaderManager;
import model.Webhook;

public class FileReaderManagerTest {

	private void createFile() {
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("teste.txt"), "utf-8"));
			writer.write(
					"level=info response_body=\"\" request_to=\"https://woodenoyster.com.br\" response_headers=map[X-Ua-Compatible:[IE=Edge,chrome=1] Cache-Control:[max-age=0, private, must-revalidate] Status:[200] Etag:[7215ee9c7d9dc229d2921a40e899ec5f] Vary:[Accept-Encoding] Server:[nginx] X-Request-Id:[1381e8cb388db085cdc3bac457dab9bf] Date:[Tue, 07 Jul 2015 18:29:52 GMT] Connection:[keep-alive] Set-Cookie:[X-Mapping-fjhppofk=A67D55AC8119CAD031E35EC35B4BCFFD; path=/] Content-Type:[text/html; charset=utf-8] X-Powered-By:[Phusion Passenger (mod_rails/mod_rack) 3.0.17] X-Rack-Cache:[invalidate, pass] X-Runtime:[0.034645] Keep-Alive:[timeout=20]] response_status=\"500\"\r\n)");
			writer.write(
					"level=info response_body=\"\" request_to=\"https://woodenoyster.com.br\" response_headers=map[X-Ua-Compatible:[IE=Edge,chrome=1] Cache-Control:[max-age=0, private, must-revalidate] Status:[200] Etag:[7215ee9c7d9dc229d2921a40e899ec5f] Vary:[Accept-Encoding] Server:[nginx] X-Request-Id:[1381e8cb388db085cdc3bac457dab9bf] Date:[Tue, 07 Jul 2015 18:29:52 GMT] Connection:[keep-alive] Set-Cookie:[X-Mapping-fjhppofk=A67D55AC8119CAD031E35EC35B4BCFFD; path=/] Content-Type:[text/html; charset=utf-8] X-Powered-By:[Phusion Passenger (mod_rails/mod_rack) 3.0.17] X-Rack-Cache:[invalidate, pass] X-Runtime:[0.034645] Keep-Alive:[timeout=20]] response_status=\"500\"\r\n)");

		} catch (IOException ex) {
			// report
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				/* ignore */}
		}

	}

	@Test
	public void testaLeituraDeArquivos() {
		createFile();
		List<Webhook> list = new FileReaderManager("teste.txt").extraiWebhooks();
		Assert.assertEquals(2, list.size());
		Assert.assertEquals(500, list.get(0).getStatus());

		Assert.assertEquals("https://woodenoyster.com.br", list.get(0).getUrl());

	}

	@Test
	public void testaLeituraDeArquivosInexistente() {
		createFile();
		List<Webhook> list = new FileReaderManager("teste2.txt").extraiWebhooks();
		Assert.assertEquals(list, null);
	}

}
