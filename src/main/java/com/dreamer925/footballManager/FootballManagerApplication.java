package com.dreamer925.footballManager;

import com.dreamer925.footballManager.forms.TeamForm;
import com.dreamer925.footballManager.models.PlayerModel;
import com.dreamer925.footballManager.services.TeamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootApplication
public class FootballManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballManagerApplication.class, args);
	}

}
