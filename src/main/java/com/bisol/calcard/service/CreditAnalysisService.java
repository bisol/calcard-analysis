package com.bisol.calcard.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.bisol.calcard.messaging.CreditProposalConsumerChannel;
import com.bisol.calcard.messaging.CreditProposalResultProducerChannel;
import com.esotericsoftware.minlog.Log;

@Service
public class CreditAnalysisService {

	private static final BigDecimal MIN_INCOME = new BigDecimal(500);
	private static final long MIN_SCORE = 1000;
	private static final long DEPENDENT_SCORE = 700;
	private static final long MARITAL_STATUS_SCORE = 600;

	private final Logger log = LoggerFactory.getLogger(CreditAnalysisService.class);

	private final MessageChannel producerChannel;

	public CreditAnalysisService(
			@Qualifier(CreditProposalResultProducerChannel.CHANNEL) MessageChannel producerChannel) {
		this.producerChannel = producerChannel;
	}

	/**
	 * Receives credit proposals from the main app.
	 * 
	 * @param creditProposal
	 */
	@StreamListener(CreditProposalConsumerChannel.CHANNEL)
	public void consume(CreditProposal creditProposal) {
		log.info("Received proposal: {}.", creditProposal);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			Log.error("Error simulating server load", e);
		}

		creditProposal = process(creditProposal);
		producerChannel.send(MessageBuilder.withPayload(creditProposal).build());
	}

	/**
	 * Approves or rejects credit proposals, based on hand crafted criteria to fit
	 * provided test data.
	 * 
	 * @param creditProposal
	 * @return modified creditProposal
	 */
	public CreditProposal process(CreditProposal creditProposal) {
		if (creditProposal.getIncome().compareTo(MIN_INCOME) <= 0) {
			return reject(creditProposal, RejectionReason.INCOME);
		}

		long score = creditProposal.getIncome().longValue();

		score -= creditProposal.getDependents() * DEPENDENT_SCORE;

		switch (creditProposal.getMaritalStatus()) {
		case SINGLE:
			if (creditProposal.getDependents() > 0) {
				score -= 100;
			}
		case MARRIED:
			break;
		default:
			score -= MARITAL_STATUS_SCORE;
		}

		if (creditProposal.getClientGender() == Gender.MALE) {
			score -= 100;
		}

		if (score < MIN_SCORE) {
			return reject(creditProposal, RejectionReason.POLICY);
		}

		creditProposal.setStatus(CreditProposalStatus.APROVED);
		creditProposal.setProcessingDate(LocalDate.now());
		creditProposal.setRejectionReason(null);

		if (score < 1500) {
			creditProposal.setAprovedMin(new BigDecimal(100));
			creditProposal.setAprovedMax(new BigDecimal(500));
		} else if (score < 2500) {
			creditProposal.setAprovedMin(new BigDecimal(500));
			creditProposal.setAprovedMax(new BigDecimal(1000));
		} else if (score < 4500) {
			creditProposal.setAprovedMin(new BigDecimal(1000));
			creditProposal.setAprovedMax(new BigDecimal(1500));
		} else if (score < 6500) {
			creditProposal.setAprovedMin(new BigDecimal(1500));
			creditProposal.setAprovedMax(new BigDecimal(2000));
		} else {
			creditProposal.setAprovedMin(new BigDecimal(2000));
		}
		return creditProposal;
	}

	private CreditProposal reject(CreditProposal creditProposal, RejectionReason reason) {
		creditProposal.setRejectionReason(reason);
		creditProposal.setStatus(CreditProposalStatus.REJECTED);
		creditProposal.setProcessingDate(LocalDate.now());
		return creditProposal;
	}

}
