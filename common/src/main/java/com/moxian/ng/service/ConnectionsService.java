package com.moxian.ng.service;

import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.moxian.ng.repository.ConnectionsRepository;

public class ConnectionsService {

	private static final Logger log = LoggerFactory
			.getLogger(ConnectionsService.class);

	@Inject
	private ConnectionsRepository connectionsRepository;
}
