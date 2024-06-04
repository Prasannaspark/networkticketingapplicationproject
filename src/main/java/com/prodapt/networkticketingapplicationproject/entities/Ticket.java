package com.prodapt.networkticketingapplicationproject.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tickets_table")

public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketId;
	
	private String title;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private Priority priority;
	
	@Enumerated(EnumType.STRING)
	private Severity severity;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private LocalDate creationDate;
	
	private LocalDate lastUpdated;
	
	@Enumerated(EnumType.STRING)
	private CustomerTier customerTier;
	
	@Enumerated(EnumType.STRING)
	private IssueType issueType;
	
	@ManyToOne
	private UserEntity user;

}
