package com.piggymetrics.account.controller;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.piggymetrics.account.client.StatisticsServiceClient;
import com.piggymetrics.account.domain.Account;

@RestController
public class AccountController {
	
	@Autowired
	public StatisticsServiceClient client;

	@PreAuthorize("#oauth2.hasScope('server') or #name.equals('demo')")
	@RequestMapping(path = "/{name}", method = RequestMethod.GET)
	public Account getAccountByName(@PathVariable String name) {
		Account a = new Account();
		a.setLastSeen(new Date());
		a.setName("account");
		a.setNote("bbbbbvvvvvbb");
		return a;
	}

	@RequestMapping(path = "/current", method = RequestMethod.GET)
	public Account getCurrentAccount(Principal principal) {
		Account a = new Account();
		a.setLastSeen(new Date());
		a.setName("account");
		a.setNote("bbbbbbb");
		System.out.println(principal.getName());
		OAuth2Authentication auth = (OAuth2Authentication)principal;
		OAuth2AuthenticationDetails tokenDetail = (OAuth2AuthenticationDetails)auth.getDetails();
		System.out.println(tokenDetail.getTokenType() + " " + tokenDetail.getTokenValue());
		return a;
	}
	@RequestMapping(path = "/statistics", method = RequestMethod.GET)
	public void getAccountstatistics(Principal principal) {
		client.updateStatistics("a", new Account());
	}
}
