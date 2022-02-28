package org.zeep.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.zeep.library.enums.AccountType;
import org.zeep.library.enums.Gender;
import org.zeep.library.enums.Genre;
import org.zeep.library.enums.Status;
import org.zeep.library.model.Address;
import org.zeep.library.model.LibraryCardModel;
import org.zeep.library.model.MemberModel;
import org.zeep.library.repo.*;

import java.util.Date;
import java.util.UUID;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}
}
