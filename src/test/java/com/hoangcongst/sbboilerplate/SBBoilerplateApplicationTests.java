package com.hoangcongst.sbboilerplate;

import com.hoangcongst.sbboilerplate.auth.vo.AdminIndexRequest;
import com.conght.common.requestcriteria.util.CriteriaParser;
import org.junit.jupiter.api.Test;

//@SpringBootTest
class SBBoilerplateApplicationTests {

	@Test
	void contextLoads() throws Exception {
	}

	@Test
	void requestParseIntoCriteria() {
		CriteriaParser criteriaParser = new CriteriaParser();
		AdminIndexRequest adminIndexRequest = new AdminIndexRequest("hoangcongst", null, null);
		criteriaParser.parse(adminIndexRequest);
	}
}
