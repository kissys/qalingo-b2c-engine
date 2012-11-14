/**
 * Most of the code in the Qalingo project is copyrighted Hoteia and licensed
 * under the Apache License Version 2.0 (release version 0.7.0)
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *                   Copyright (c) Hoteia, 2012-2013
 * http://www.hoteia.com - http://twitter.com/hoteia - contact@hoteia.com
 *
 */
package fr.hoteia.qalingo.core.common.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.hoteia.qalingo.core.common.dao.CustomerConnectionLogDao;
import fr.hoteia.qalingo.core.common.domain.CustomerConnectionLog;

@Transactional
@Repository("customerConnectionLogDao")
public class CustomerConnectionLogDaoImpl extends AbstractGenericDaoImpl implements CustomerConnectionLogDao {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	public CustomerConnectionLog getCustomerConnectionLogById(Long customerConnectionLogId) {
		return em.find(CustomerConnectionLog.class, customerConnectionLogId);
	}

	public List<CustomerConnectionLog> findByExample(CustomerConnectionLog customerConnectionLogExample) {
		return super.findByExample(customerConnectionLogExample);
	}

	public void saveOrUpdateCustomerConnectionLog(CustomerConnectionLog customerConnectionLog) {
		if(customerConnectionLog.getId() == null){
			em.persist(customerConnectionLog);
		} else {
			em.merge(customerConnectionLog);
		}
	}

	public void deleteCustomerConnectionLog(CustomerConnectionLog customerConnectionLog) {
		em.remove(customerConnectionLog);
	}

}