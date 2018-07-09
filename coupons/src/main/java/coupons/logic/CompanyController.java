package coupons.logic;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import coupons.beans.Company;
import coupons.dao.CompanyRepository;
@Controller
public class CompanyController {

		@Autowired
		private CompanyRepository companyRepository;

		public Optional<Company> get(long id) {
			return companyRepository.findById(id);
		}

		public void put(Company entity) {
			companyRepository.save(entity);			
		}

		public void remove(long id) {
			companyRepository.deleteById(id);			
		}

		public Iterable<Company> getAll() {
			return companyRepository.findAll();
		}
		
}
