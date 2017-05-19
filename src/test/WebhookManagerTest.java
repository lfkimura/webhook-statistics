package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import manager.WebhookManager;
import model.UrlStatistic;
import model.Webhook;

public class WebhookManagerTest {

	@Test
	public void deveRetornarAsTopNUrls() {
		List<Webhook> whks = new ArrayList<>();
		whks.add(new Webhook("http://www.uol.com.br", 200));
		whks.add(new Webhook("http://www.vivo.com.br", 200));
		whks.add(new Webhook("http://www.sony.com.br", 200));

		WebhookManager manager = new WebhookManager(whks);
		Assert.assertEquals(3, manager.getTopNWebhooks(3).size());

	}

	@Test
	public void deveRetornarOrdenadaPelaQuantidadedeRequisicoes() {
		List<Webhook> whks = new ArrayList<>();
		whks.add(new Webhook("http://www.uol.com.br", 200));
		whks.add(new Webhook("http://www.uol.com.br", 200));
		whks.add(new Webhook("http://www.uol.com.br", 404));
		whks.add(new Webhook("http://www.vivo.com.br", 500));
		whks.add(new Webhook("http://www.vivo.com.br", 200));
		whks.add(new Webhook("http://www.sony.com.br", 200));

		WebhookManager manager = new WebhookManager(whks);
		Assert.assertEquals("http://www.uol.com.br", manager.getTopNWebhooks(3).get(0).getUrl());

		Assert.assertEquals("http://www.vivo.com.br", manager.getTopNWebhooks(3).get(1).getUrl());

		Assert.assertEquals("http://www.sony.com.br", manager.getTopNWebhooks(3).get(2).getUrl());

	}

	@Test
	public void deveRetornarAQuantidadedeRequisicoes() {
		List<Webhook> whks = new ArrayList<>();
		whks.add(new Webhook("http://www.uol.com.br", 200));
		whks.add(new Webhook("http://www.uol.com.br", 200));
		whks.add(new Webhook("http://www.uol.com.br", 404));
		whks.add(new Webhook("http://www.vivo.com.br", 500));
		whks.add(new Webhook("http://www.vivo.com.br", 200));
		whks.add(new Webhook("http://www.sony.com.br", 200));

		WebhookManager manager = new WebhookManager(whks);
		List<UrlStatistic> list = manager.getTopNWebhooks(3);
		Assert.assertEquals(3, list.get(0).getQuantidade());

		Assert.assertEquals(2, list.get(1).getQuantidade());

		Assert.assertEquals(1, list.get(2).getQuantidade());

	}

	@Test
	public void testaQuantidadePorStatus() {
		List<Webhook> whks = new ArrayList<>();
		whks.add(new Webhook("http://www.uol.com.br", 200));
		whks.add(new Webhook("http://www.uol.com.br", 200));
		whks.add(new Webhook("http://www.uol.com.br", 404));
		whks.add(new Webhook("http://www.vivo.com.br", 500));
		whks.add(new Webhook("http://www.vivo.com.br", 200));
		whks.add(new Webhook("http://www.sony.com.br", 200));
		WebhookManager manager = new WebhookManager(whks);

		Assert.assertEquals(3, manager.getStatusQuantidadeTable().size());

	}

}
