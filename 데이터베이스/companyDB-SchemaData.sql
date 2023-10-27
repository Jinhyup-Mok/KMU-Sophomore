-- company Sample Database
-- Version 1.0

-- SHOW VARIABLES;
-- SELECT @@GLOBAL.sql_mode;
-- SELECT @@SESSION.sql_mode;

-- SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;					/* Default : 1 (ON) */
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;		/* Default : 1 (ON) */	

DROP SCHEMA IF EXISTS company;

CREATE SCHEMA company DEFAULT CHARACTER SET utf8;
USE company;

DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS dependent;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS dept_locations;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS works_on;


-------------------------------------------
-- Schema
-------------------------------------------

CREATE TABLE department 
(
	Dnumber 		INT(11) 	NOT NULL,
	Dname 			VARCHAR(15) NOT NULL,
	Mgr_start_date	DATE,
	Mgr_ssn 		CHAR(9),	/* 부분참여 */

	PRIMARY KEY 	(Dnumber),
	CONSTRAINT 		fk_department_employee		FOREIGN KEY (Mgr_ssn)	REFERENCES employee (Ssn)
														ON DELETE CASCADE
														ON UPDATE CASCADE,
                                                        
	UNIQUE INDEX 	idx_Dname 	(Dname ASC),
	INDEX 			idx_Mgr_ssn (Mgr_ssn ASC)
);

CREATE TABLE dept_locations 
(
	Dnumber			INT(11) 	NOT NULL,
	Dlocation		VARCHAR(15) NOT NULL,
    
	PRIMARY KEY 	(Dnumber, Dlocation),
	CONSTRAINT 		fk_locations_departtment	FOREIGN KEY (Dnumber)	REFERENCES department (Dnumber)
														ON DELETE CASCADE
														ON UPDATE CASCADE
);

CREATE TABLE employee 
(
	Ssn 		CHAR(9) 		NOT NULL,
	Fname 		VARCHAR(15) 	NOT NULL,
	Minit 		CHAR(1),
	Lname 		VARCHAR(15) 	NOT NULL,
	Bdate 		DATE,
	Address 	VARCHAR(30),
	Sex 		CHAR(1),
	Salary 		DECIMAL(10,2),
	Super_ssn 	CHAR(9),		/* 부붑참여 */
	Dnumber		INT(11),		/* 부붑참여 */
    
	PRIMARY KEY (Ssn),
	CONSTRAINT 	fk_employee_employee	FOREIGN KEY (Super_ssn)	REFERENCES employee (Ssn)
												ON DELETE CASCADE
												ON UPDATE CASCADE,
	CONSTRAINT 	fk_employee_department	FOREIGN KEY (Dnumber)	REFERENCES department (Dnumber) 
												ON DELETE CASCADE
												ON UPDATE CASCADE,
												/* FOREIGN_KEY_CHECKS=1이면, 두번째 FK 제약조건에서 에러 발생함. */
	INDEX 		idx_Super_ssn		(Super_ssn ASC),
	INDEX 		idx_Dnumber 		(Dnumber ASC)
);

CREATE TABLE dependent 
(
	Essn			CHAR(9) 	NOT NULL,
	Dependent_name	VARCHAR(15)	NOT NULL,
	Sex 			CHAR(1),
	Bdate 			DATE,
	Relationship	VARCHAR(8),
    
	PRIMARY KEY 	(Essn, Dependent_name),
	CONSTRAINT		fk_dependent_employee	FOREIGN KEY (Essn)	REFERENCES employee (Ssn)
												ON DELETE CASCADE
												ON UPDATE CASCADE
);

CREATE TABLE project 
(
	Pnumber			INT(11) 	NOT NULL,
	Pname			VARCHAR(15) NOT NULL,
	Plocation 		VARCHAR(15),
	Dnumber 		INT(11) 	NOT NULL,
    
    PRIMARY KEY 	(Pnumber),
	CONSTRAINT 		fk_project_department	FOREIGN KEY (Dnumber)	REFERENCES department (Dnumber)
													ON DELETE CASCADE
													ON UPDATE CASCADE,
                                                    
	UNIQUE INDEX	idx_Pname 		(Pname ASC),
	INDEX 			idx_Dnumber 	(Dnumber ASC)
);

CREATE TABLE works_on 
(
	Essn 			CHAR(9) 	NOT NULL,
	Pnumber	 		INT(11) 	NOT NULL,
	Hours			DECIMAL(3,1),
    
	PRIMARY KEY 	(Essn, Pnumber),
	CONSTRAINT 		fk_works_on_employee 	FOREIGN KEY (Essn) 	REFERENCES employee (Ssn)
													ON DELETE CASCADE
													ON UPDATE CASCADE,  
    CONSTRAINT 		fk_works_on_project		FOREIGN KEY (Pnumber)	REFERENCES project (Pnumber)
													ON DELETE CASCADE
													ON UPDATE CASCADE,

	INDEX 			idx_Pnumber 	(Pnumber ASC)
);


-------------------------------------------
-- Data
-------------------------------------------

--  Data for employee (8 instances)

INSERT INTO employee VALUES
('123456789', 'John', 'B', 'Smith', '1965-01-09', '731 Fondren, Houston, TX','M',30000,'333445555',5),
('333445555', 'Franklin', 'T', 'Wong', '1955-12-08', '638 Voss, Houston, TX', 'M', 40000.0, '888665555', 5),
('453453453', 'Joyce', 'A', 'English', '1972-07-31', '5631 Rice, Houston, TX', 'F', 25000.0, '987654321', 5),
('666884444', 'Ramesh', 'K', 'Narayan', '1962-09-15', '975 Fire Oak, Humble, TX', 'M', 38000.0, '333445555', 5),
('888665555', 'James', 'E', 'Borg', '1937-11-10', '450 Stone, Houston, TX', 'M', 55000.0, NULL, 1),
('987654321', 'Jennifer', 'S', 'Wallace', '1941-06-20', '291 Berry, Bellaire, TX', 'F', 43000.0, '888665555', 4),
('987987987', 'Ahmad', 'V', 'Jabbar', '1969-03-29', '980 Dallas, Houston, TX', 'M', 25000.0, '987654321', 4),
('999887777', 'Alicia', 'J', 'Zelaya', '1968-01-19', '3321 Castle, Spring, TX', 'F', 25000.0, '987654321', 4);

--  Data for dependant (7 instances)

INSERT INTO dependent VALUES
('123456789', 'Alice', 'F', '1988-12-30', 'Daughter'),
('123456789', 'Elizabeth', 'F', '1967-05-05', 'Spouse'),
('123456789', 'Michael', 'M', '1988-01-04', 'Son'),
('333445555', 'Alice', 'F', '1986-04-05', 'Daughter'),
('333445555', 'Joy', 'F', '1958-05-03', 'Spouse'),
('333445555', 'Theodore', 'M', '1983-10-25', 'Son'),
('987654321', 'Abner', 'M', '1942-02-28', 'Spouse');

--  Data for department (3 instances)

INSERT INTO department VALUES
(1, 'Headquarters', '1981-06-19', '888665555'),
(4, 'Administration', '1995-01-01', '987654321'),
(5, 'Research', '1988-05-22', '333445555');

--  Data for dept_locations (5 instances)

INSERT INTO dept_locations VALUES
(1, 'Houston'),
(4, 'Stafford'),
(5, 'Bellaire'),
(5, 'Houston'),
(5, 'Sugarland');

--  Data for project (6 instances)

INSERT INTO project VALUES
(1, 'ProductX', 'Bellaire', 5),
(2, 'ProductY', 'Sugarland', 5),
(3, 'ProductZ', 'Houston', 5),
(10, 'Computerization', 'Stafford', 4),
(20, 'Reorganization', 'Houston', 1),
(30, 'Newbenefits', 'Stafford', 4);

--  Data for works_on (16 instances)

INSERT INTO works_on VALUES
('123456789', 1, 32.5),
('123456789', 2, 7.5),
('333445555', 2, 10.0),
('333445555', 3, 10.0),
('333445555', 10, 10.0),
('333445555', 20, 10.0),
('453453453', 1, 20.0),
('453453453', 2, 20.0),
('666884444', 3, 40.0),
('888665555', 20, NULL),
('987654321', 20, 15.0),
('987654321', 30, 20.0),
('987987987', 10, 35.0),
('987987987', 30, 5.0),
('999887777', 10, 10.0),
('999887777', 30, 30.0);


-- SET SQL_MODE=@OLD_SQL_MODE;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;