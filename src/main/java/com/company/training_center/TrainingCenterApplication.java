package com.company.training_center;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Training center project !",
				description = "This is a learning center project and you can perform CRUD operations on the Students, Teachers, Subjects, Payment system and similar tables through this website! Author Komiljon Bakhromov You can get the license through the link below.",
				version = "version:4.0.0",
				license = @License(
						name = "For licence",
						url = "https://t.me/KomiljonBakhromov"
				),
				contact = @Contact(
						name = "Project manager Komiljon Bakhromov",
						url = "https://t.me/KomiljonBakhromov",
						email = "komiljonbakhromov@gmail.com"
				)
		),
		tags = {
				@Tag(name = "Post",description = "These urls are used to host data."),
				@Tag(name = "Get",description = "These urls are used to retrieve data."),
				@Tag(name = "Update",description = "These urls are used to modify the data."),
				@Tag(name = "Delete",description = "These urls are used to delete data.")
		},
		servers = @Server(
				url = "http://localhost:8087",
		        description ="This is a temporary site URL!"
		)
)
public class TrainingCenterApplication {
	public static void main(String[] args) {
		SpringApplication.run(TrainingCenterApplication.class, args);
	}

}
