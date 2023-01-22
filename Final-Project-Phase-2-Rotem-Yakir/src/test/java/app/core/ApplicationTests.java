package app.core;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;

import app.core.entities.Company;
import app.core.services.AdminService;
import app.core.services.CompanyService;
import app.core.services.CustomerService;
import app.core.services.login.LoginManager;
import app.core.services.login.LoginManager.ClientType;
import app.core.threads.CouponExpirationDailyJob;

@EnableScheduling
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationTests {

	@Autowired
	LoginManager loginManager;
	@Autowired
	CouponExpirationDailyJob couponExpirationDailyJob;
	@Autowired
	AdminService adminService;
	@Autowired
	CompanyService companyService;
	@Autowired
	CustomerService customerService;

	@BeforeAll
	public static void initTest() {
		System.out.println("==== START OF TEST ====");

		// TODO how do i clear the sql each time im running the test (and not via
		// application properties)?

	}

	@Test
	@Order(1)
	void loginAdmin() {
		adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.ADMIN);
		Assertions.assertNotNull(adminService);
//	Assertions.assertDoesNotThrow(null);
	}

	@Test
	@Order(2)
	void addCompanies() {
		adminService.addCompany(new Company("Super Farm", "SuperFarm@email.com", "Farm123Super"));
		adminService.addCompany(new Company("McDonald's", "McDonalds@email.com", "NotBurgerKing"));
		adminService.addCompany(new Company("Dan Hotel", "DanHotel@email.com", "9485721"));
		adminService.addCompany(new Company("DutyFree", "DutyFree@email.com", "GurionBen48"));
		adminService.addCompany(new Company("Delete Example", "delete@meLater", "42398"));

		// TODO: the method adminService.getAllCompanies() is not being tested (at this
		// point) - is that OK?
		Assertions.assertEquals(5, adminService.getAllCompanies().size());
		;
	}

	@Test
	@Order(3)
	void deleteCompany() {
		adminService.deleteCompany(5);
		Assertions.assertEquals(4, adminService.getAllCompanies().size());
		;
	}

	@Test
	@Order(4)
	void updateCompany() {
		Company currentCompany3 = adminService.getCompany(3);
		Company updatedCompany3 = adminService.updateCompany(
				new Company(3, "Dan Hotel", "DanHotel@email.com", "changed :)", currentCompany3.getCoupons()));
		assertFalse(currentCompany3.getPassword().equals(updatedCompany3.getPassword()));
	}
	
	
	@Test
	@Order(5)
	void getAllCompanies() {
		System.out.println("All companies: ");
		for (Company company : adminService.getAllCompanies()) {
			System.out.println(company);
			Assertions.assertEquals(Company.class, company.getClass());
		}
	}


}
