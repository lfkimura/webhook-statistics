package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import manager.FilePrintManager;
import model.Webhook;

public class FilePrintManagerTest {
	@Test
	public void verificaImpressaoDeQuantidadeStatus(){
		List<Webhook> whks = new ArrayList<>();
		whks.add(new Webhook("http://www.uol.com.br", 200));
		
		
		String result = new FilePrintManager(whks).imprimeResultados();
		Assert.assertTrue(result.contains("200"));
		Assert.assertTrue(result.contains("http://www.uol.com.br"));

		Assert.assertTrue(result.contains("1"));
	
	}

}
