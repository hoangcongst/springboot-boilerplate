package com.hoangcongst.sbboilerplate;

import com.hoangcongst.sbboilerplate.request.AdminIndexRequest;
import com.hoangcongst.sbboilerplate.util.MysqlSHAUtil;
import com.conght.common.requestcriteria.util.CriteriaParser;
import org.junit.jupiter.api.Test;

//@SpringBootTest
class SBBoilerplateApplicationTests {

	@Test
	void contextLoads() throws Exception {
		System.out.println(MysqlSHAUtil.decrypt("DCC61E475C7AEB640ABBF18F901421C5E5E78A61AE610305B9D99E6C6050B782"));
	}

	@Test
	void requestParseIntoCriteria() {
		CriteriaParser criteriaParser = new CriteriaParser();
		AdminIndexRequest adminIndexRequest = new AdminIndexRequest("hoangcongst", null, null);
		criteriaParser.parse(adminIndexRequest);
	}
}
