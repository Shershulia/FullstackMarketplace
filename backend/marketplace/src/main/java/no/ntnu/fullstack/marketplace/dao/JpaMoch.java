//package no.ntnu.fullstack.marketplace.dao;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import org.springframework.context.annotation.Configuration;
//
//import static jakarta.persistence.GenerationType.IDENTITY;
//
//@Configuration
//public class JpaMoch {
//    @Entity
//    public class Country {
//
//        @Id
//        @GeneratedValue(strategy = IDENTITY)
//        private Integer id;
//
//        @Column(nullable = false)
//        private String name;
//
//        public Country() {
//        }
//    }
//
////    @Sql({"/employees_schema.sql", "/import_employees.sql"})
////    public class SpringBootInitialLoadIntegrationTest {
////
////        @Autowired
////        private EmployeeRepository employeeRepository;
////
////        @Test
////        public void testLoadDataForTestClass() {
////            assertEquals(3, employeeRepository.findAll().size());
////        }
////    }
//
//
//}
