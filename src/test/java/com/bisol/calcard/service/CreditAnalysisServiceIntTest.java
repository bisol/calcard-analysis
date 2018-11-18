package com.bisol.calcard.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bisol.calcard.CreditAnalysisServiceApp;

/**
 * Test class for the CreditProposalResource REST controller.
 *
 * @see CreditProposalResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CreditAnalysisServiceApp.class)
public class CreditAnalysisServiceIntTest {

	@Autowired
	private CreditAnalysisService creditAnalysisService;

	@Test
	@Transactional
	public void test01() throws Exception {
		// Lucas 28 M solteiro SC 0 2500 Aprovado entre 500 - 1000
		CreditProposal creditProposal = new CreditProposal().clientName("Lucas").clientAge(28).clientGender(Gender.MALE)
				.maritalStatus(MaritalStatus.SINGLE).federationUnit(FederationUnit.SC).dependents(0)
				.income(new BigDecimal(2500)).taxpayerId("12345678900");

		creditProposal = creditAnalysisService.process(creditProposal);

		assertThat(creditProposal.getStatus()).isEqualTo(CreditProposalStatus.APROVED);
		assertThat(creditProposal.getAprovedMin().intValue()).isEqualTo(500);
		assertThat(creditProposal.getAprovedMax().intValue()).isEqualTo(1000);
	}

	@Test
	@Transactional
	public void test02() throws Exception {
		// Ana 17 F solteiro SP 0 1000 Aprovado entre 100 - 500
		CreditProposal creditProposal = new CreditProposal().clientName("Ana").clientAge(17).clientGender(Gender.FEMALE)
				.maritalStatus(MaritalStatus.SINGLE).federationUnit(FederationUnit.SP).dependents(0)
				.income(new BigDecimal(1000)).taxpayerId("12345678900");

		creditProposal = creditAnalysisService.process(creditProposal);

		assertThat(creditProposal.getStatus()).isEqualTo(CreditProposalStatus.APROVED);
		assertThat(creditProposal.getAprovedMin().intValue()).isEqualTo(100);
		assertThat(creditProposal.getAprovedMax().intValue()).isEqualTo(500);
	}

	@Test
	@Transactional
	public void test03() throws Exception {
		// Pedro 68 M casado SC 3 8000 Aprovado entre 1500 - 2000
		CreditProposal creditProposal = new CreditProposal().clientName("Pedro").clientAge(68).clientGender(Gender.MALE)
				.maritalStatus(MaritalStatus.MARRIED).federationUnit(FederationUnit.SC).dependents(3)
				.income(new BigDecimal(8000)).taxpayerId("12345678900");

		creditProposal = creditAnalysisService.process(creditProposal);

		assertThat(creditProposal.getStatus()).isEqualTo(CreditProposalStatus.APROVED);
		assertThat(creditProposal.getAprovedMin().intValue()).isEqualTo(1500);
		assertThat(creditProposal.getAprovedMax().intValue()).isEqualTo(2000);
	}

	@Test
	@Transactional
	public void test04() throws Exception {
		// Paula 61 F casado RJ 3 5000 Aprovado entre 1000 - 1500
		CreditProposal creditProposal = new CreditProposal().clientName("Paula").clientAge(61)
				.clientGender(Gender.FEMALE)
				.maritalStatus(MaritalStatus.MARRIED).federationUnit(FederationUnit.RJ).dependents(3)
				.income(new BigDecimal(5000)).taxpayerId("12345678900");

		creditProposal = creditAnalysisService.process(creditProposal);

		assertThat(creditProposal.getStatus()).isEqualTo(CreditProposalStatus.APROVED);
		assertThat(creditProposal.getAprovedMin().intValue()).isEqualTo(1000);
		assertThat(creditProposal.getAprovedMax().intValue()).isEqualTo(1500);
	}

	@Test
	@Transactional
	public void test05() throws Exception {
		// João 56 M divorciado RJ 2 2000 Negado reprovado pela política de crédito
		CreditProposal creditProposal = new CreditProposal().clientName("João").clientAge(56).clientGender(Gender.MALE)
				.maritalStatus(MaritalStatus.DIVORCED).federationUnit(FederationUnit.RJ).dependents(2)
				.income(new BigDecimal(2000)).taxpayerId("12345678900");

		creditProposal = creditAnalysisService.process(creditProposal);

		assertThat(creditProposal.getStatus()).isEqualTo(CreditProposalStatus.REJECTED);
		assertThat(creditProposal.getRejectionReason()).isEqualTo(RejectionReason.POLICY);
	}

	@Test
	@Transactional
	public void test06() throws Exception {
		// Maria 45 F divorciado SP 1 2000 Negado reprovado pela política de crédito
		CreditProposal creditProposal = new CreditProposal().clientName("Maria").clientAge(45)
				.clientGender(Gender.FEMALE)
				.maritalStatus(MaritalStatus.DIVORCED).federationUnit(FederationUnit.SP).dependents(1)
				.income(new BigDecimal(2000)).taxpayerId("12345678900");

		creditProposal = creditAnalysisService.process(creditProposal);

		assertThat(creditProposal.getStatus()).isEqualTo(CreditProposalStatus.REJECTED);
		assertThat(creditProposal.getRejectionReason()).isEqualTo(RejectionReason.POLICY);
	}

	@Test
	@Transactional
	public void test07() throws Exception {
		// José 30 M casado MA 2 8000 Aprovado superior 2000
		CreditProposal creditProposal = new CreditProposal().clientName("José").clientAge(30).clientGender(Gender.MALE)
				.maritalStatus(MaritalStatus.MARRIED).federationUnit(FederationUnit.MA).dependents(2)
				.income(new BigDecimal(8000)).taxpayerId("12345678900");

		creditProposal = creditAnalysisService.process(creditProposal);

		assertThat(creditProposal.getStatus()).isEqualTo(CreditProposalStatus.APROVED);
		assertThat(creditProposal.getAprovedMin().intValue()).isEqualTo(2000);
		assertThat(creditProposal.getAprovedMax()).isNull();
	}

	@Test
	@Transactional
	public void test08() throws Exception {
		// Dinae 33 F casado SP 1 10000 Aprovado superior 2000
		CreditProposal creditProposal = new CreditProposal().clientName("Dinae").clientAge(33)
				.clientGender(Gender.FEMALE)
				.maritalStatus(MaritalStatus.MARRIED).federationUnit(FederationUnit.SP).dependents(1)
				.income(new BigDecimal(10000)).taxpayerId("12345678900");

		creditProposal = creditAnalysisService.process(creditProposal);

		assertThat(creditProposal.getStatus()).isEqualTo(CreditProposalStatus.APROVED);
		assertThat(creditProposal.getAprovedMin().intValue()).isEqualTo(2000);
		assertThat(creditProposal.getAprovedMax()).isNull();
	}

	@Test
	@Transactional
	public void test09() throws Exception {
		// Marcos 19 M solteiro SC 1 400 Negado renda baixa
		CreditProposal creditProposal = new CreditProposal().clientName("Marcos").clientAge(19)
				.clientGender(Gender.MALE)
				.maritalStatus(MaritalStatus.SINGLE).federationUnit(FederationUnit.SC).dependents(1)
				.income(new BigDecimal(400)).taxpayerId("12345678900");

		creditProposal = creditAnalysisService.process(creditProposal);

		assertThat(creditProposal.getStatus()).isEqualTo(CreditProposalStatus.REJECTED);
		assertThat(creditProposal.getRejectionReason()).isEqualTo(RejectionReason.INCOME);
	}

	@Test
	@Transactional
	public void test10() throws Exception {
		// Suzan 63 F viuva MA 3 1500 Negado reprovado pela política de crédito
		CreditProposal creditProposal = new CreditProposal().clientName("Suzan").clientAge(63)
				.clientGender(Gender.FEMALE)
				.maritalStatus(MaritalStatus.WIDOW).federationUnit(FederationUnit.MA).dependents(3)
				.income(new BigDecimal(1500)).taxpayerId("12345678900");

		creditProposal = creditAnalysisService.process(creditProposal);

		assertThat(creditProposal.getStatus()).isEqualTo(CreditProposalStatus.REJECTED);
		assertThat(creditProposal.getRejectionReason()).isEqualTo(RejectionReason.POLICY);
	}

	@Test
	@Transactional
	public void test11() throws Exception {
		// Luci 28 F solteiro SC 2 2500 Aprovado entre 100 - 500
		CreditProposal creditProposal = new CreditProposal().clientName("Luci").clientAge(28)
				.clientGender(Gender.FEMALE)
				.maritalStatus(MaritalStatus.SINGLE).federationUnit(FederationUnit.SC).dependents(2)
				.income(new BigDecimal(2500)).taxpayerId("12345678900");

		creditProposal = creditAnalysisService.process(creditProposal);

		assertThat(creditProposal.getStatus()).isEqualTo(CreditProposalStatus.APROVED);
		assertThat(creditProposal.getAprovedMin().intValue()).isEqualTo(100);
		assertThat(creditProposal.getAprovedMax().intValue()).isEqualTo(500);
	}

	@Test
	@Transactional
	public void test12() throws Exception {
		// Roberto 16 M solteiro SP 0 500 Negado renda baixa
		CreditProposal creditProposal = new CreditProposal().clientName("Roberto").clientAge(16)
				.clientGender(Gender.MALE)
				.maritalStatus(MaritalStatus.SINGLE).federationUnit(FederationUnit.SP).dependents(0)
				.income(new BigDecimal(500)).taxpayerId("12345678900");

		creditProposal = creditAnalysisService.process(creditProposal);

		assertThat(creditProposal.getStatus()).isEqualTo(CreditProposalStatus.REJECTED);
		assertThat(creditProposal.getRejectionReason()).isEqualTo(RejectionReason.INCOME);
	}

	@Test
	@Transactional
	public void test13() throws Exception {
		// Bruno 30 M casado MA 5 8000 Aprovado entre 1000 - 1500
		CreditProposal creditProposal = new CreditProposal().clientName("Bruno").clientAge(30).clientGender(Gender.MALE)
				.maritalStatus(MaritalStatus.MARRIED).federationUnit(FederationUnit.MA).dependents(5)
				.income(new BigDecimal(8000)).taxpayerId("12345678900");

		creditProposal = creditAnalysisService.process(creditProposal);

		assertThat(creditProposal.getStatus()).isEqualTo(CreditProposalStatus.APROVED);
		assertThat(creditProposal.getAprovedMin().intValue()).isEqualTo(1000);
		assertThat(creditProposal.getAprovedMax().intValue()).isEqualTo(1500);
	}

	@Test
	@Transactional
	public void test14() throws Exception {
		// Ariel 33 F viuva SP 0 10000 Aprovado superior 2000
		CreditProposal creditProposal = new CreditProposal().clientName("Ariel").clientAge(33)
				.clientGender(Gender.FEMALE)
				.maritalStatus(MaritalStatus.WIDOW).federationUnit(FederationUnit.SP).dependents(0)
				.income(new BigDecimal(10000)).taxpayerId("12345678900");

		creditProposal = creditAnalysisService.process(creditProposal);

		assertThat(creditProposal.getStatus()).isEqualTo(CreditProposalStatus.APROVED);
		assertThat(creditProposal.getAprovedMin().intValue()).isEqualTo(2000);
		assertThat(creditProposal.getAprovedMax()).isNull();
	}
}
