package it.gestionecurricula.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import it.gestionecurricula.model.Curriculum;
import it.gestionecurricula.model.Esperienza;
import it.gestionecurricula.service.MyServiceFactory;
import it.gestionecurricula.service.curriculum.CurriculumService;
import it.gestionecurricula.service.esperienza.EsperienzaService;

public class TestGestioneCurricula {
	public static void main(String[] args) {
		// parlo direttamente con il service
		EsperienzaService esperienzaService = MyServiceFactory.getEsperienzaServiceImpl();
		CurriculumService curriculumService = MyServiceFactory.getCurriculumServiceImpl();

		try {
			testInserimentoNuovoCurriculum(curriculumService);

			testInserimentoNuovaEsperienza(esperienzaService, curriculumService);

			testRimozioneCurriculum(curriculumService);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void testInserimentoNuovoCurriculum(CurriculumService curriculumService) throws Exception {
		System.out.println(".......testInserimentoNuovoCurriculum inizio.............");
		Curriculum newCurriculumInstance = new Curriculum("Flavio", "Amato",
				new SimpleDateFormat("dd-MM-yyyy").parse("02-12-1999"), "12345", "f@example.com");
		if (curriculumService.inserisciNuovo(newCurriculumInstance) != 1)
			throw new RuntimeException("testInserimentoNuovoCurriculum FAILED ");

		System.out.println(".......testInserimentoNuovoCurriculum PASSED.............");
	}

	private static void testInserimentoNuovaEsperienza(EsperienzaService esperienzaService,
			CurriculumService curriculumService) throws Exception {

		System.out.println(".......testInserimentoNuovoEsperienza inizio.............");
		if (curriculumService.listAll().size() < 1) {
			throw new RuntimeException("testInserimentoNuovoEsperienza: FAILED, non ci sono curriculum");
		}
		Curriculum primoCurriculumLista = curriculumService.listAll().get(0);
		Date dataInizioPrimaEsperienza = new SimpleDateFormat("dd-MM-yyyy").parse("10-02-2022");
		Date dataFinePrimaEsperienza = new SimpleDateFormat("dd-MM-yyyy").parse("19-02-2022");

		Esperienza newEsperienzaInstance = new Esperienza("esperienza bella", dataInizioPrimaEsperienza,
				dataFinePrimaEsperienza, "Java", primoCurriculumLista);
		if (esperienzaService.inserisciNuovo(newEsperienzaInstance) != 1)
			throw new RuntimeException("testInserimentoNuovoEsperienza primo FAILED ");

		Date dataInizioSecondaEsperienza = new SimpleDateFormat("dd-MM-yyyy").parse("15-02-2022");
		Date dataFineSecondaEsperienza = new SimpleDateFormat("dd-MM-yyyy").parse("23-02-2022");

		Esperienza newSecondaEsperienzaInstance = new Esperienza("esperienza bellissima", dataInizioSecondaEsperienza,
				dataFineSecondaEsperienza, "Python", primoCurriculumLista);

		if (esperienzaService.inserisciNuovo(newSecondaEsperienzaInstance) != 1)
			throw new RuntimeException("testInserimentoNuovoEsperienza secondo FAILED ");

		System.out.println(".......testInserimentoNuovoEsperienza PASSED.............");
	}

	private static void testRimozioneCurriculum(CurriculumService curriculumService) throws Exception {

		System.out.println(".......testRimozioneCurriculum inizio.............");
		if (curriculumService.listAll().size() < 1) {
			throw new RuntimeException("testRimozioneCurriculum: FAILED, non ci sono curriculum");
		}
		// uso il primo per vedere se viene violata la regola di business
		// sono sicuro che abbia dei curriculum attivi per il test precedente
		Curriculum primoCurriculumLista = curriculumService.listAll().get(0);
		Date dataNascitaCurriculumDaRimuovere = new SimpleDateFormat("dd-MM-yyyy").parse("17-05-1970");

		Curriculum secondoCurriculum = new Curriculum("Giovanna", "Rossi", dataNascitaCurriculumDaRimuovere, "443",
				"grossi@example.com");

		if (curriculumService.inserisciNuovo(secondoCurriculum) != 1)
			throw new RuntimeException("testRimozioneCurriculum secondo: FAILED, curriculum non inserito ");
		Curriculum giovannaRossiDaList = curriculumService.listAll().get(curriculumService.listAll().size() - 1);

		if (curriculumService.rimuovi(giovannaRossiDaList) != 1) {
			throw new RuntimeException("testRimozioneCurriculum: FAILED (primo)");
		}

		if (curriculumService.rimuovi(primoCurriculumLista) != 1) {
			throw new RuntimeException("testRimozioneCurriculum: FAILED (secondo)");
		}

		System.out.println(".......testRimozioneCurriculum PASSED.............");
	}
}
